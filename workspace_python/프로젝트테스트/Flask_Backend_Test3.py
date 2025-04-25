import threading
import time
import io
import logging
import socketserver
from http import server
from flask import Flask, request, jsonify
from flask_cors import CORS
import pymysql
import RPi.GPIO as GPIO
from picamera2 import Picamera2
from picamera2.encoders import JpegEncoder
from picamera2.outputs import FileOutput
import math
import board
import adafruit_dht
import spidev
from datetime import datetime

# Flask 앱 생성
app = Flask(__name__)
CORS(app)

# 데이터베이스 설정
DB_CONFIG = {
    "host": "192.168.30.97",
    "user": "code17_user",
    "password": "mariadb",
    "database": "code17",
    "charset": "utf8mb4"
}

# GPIO 핀 설정
SERVO_PIN = 18
LED_PIN = 14
led_G = 20
led_Y = 21
motion_sensor = 4

# GPIO 초기화
GPIO.setmode(GPIO.BCM)
GPIO.setup(SERVO_PIN, GPIO.OUT)
GPIO.setup(LED_PIN, GPIO.OUT)
GPIO.setup(led_G, GPIO.OUT)
GPIO.setup(led_Y, GPIO.OUT)
GPIO.setup(motion_sensor, GPIO.IN)
servo_pwm = GPIO.PWM(SERVO_PIN, 50)
servo_pwm.start(0)

# 센서 초기화
mq_channel = 2
ldr_channel = 0
sensor = adafruit_dht.DHT22(board.D2)
spi = spidev.SpiDev()
spi.open(0, 0)
spi.max_speed_hz = 100000

# HTML 페이지
PAGE = """
<html>
<head><title>Camera Stream</title></head>
<body><h1>Picamera2 MJPEG Stream</h1>
<img src="stream.mjpg" width="640" height="480" />
</body>
</html>
"""

# GPIO 관련 함수들
def angle_to_duty_cycle(angle):
    return 2.5 + (angle / 180) * 10

def control_servo(angle):
    duty_cycle = angle_to_duty_cycle(angle)
    servo_pwm.ChangeDutyCycle(duty_cycle)
    time.sleep(0.5)
    servo_pwm.ChangeDutyCycle(0)

def get_db_connection():
    return pymysql.connect(**DB_CONFIG)

def readadc(adcnum):
    if adcnum > 7 or adcnum < 0:
        return -1
    r = spi.xfer2([1, (8 + adcnum) << 4, 0])
    data = ((r[1] & 3) << 8) + r[2]
    return data

def hazardous_gas():
    mq_value = readadc(mq_channel)
    volt = (mq_value / 1023.0) * 3.3
    res = (3.3 - volt) * 10.0 / volt
    ratio = res / 76.63
    NO2 = round(0.4220682 * math.pow(ratio, -2.769034857), 3)
    CO2 = round(ratio * 1.3 * 400, 3)
    NH3 = round(1.8028257 * math.pow(ratio, -1.3523192), 3)
    H2S = round(0.0216549 * math.pow(ratio, -1.944112329), 3)
    TOLUENE = round(0.1241032 * math.pow(ratio, -1.848246046), 3)
    return NO2, CO2, NH3, H2S, TOLUENE

def read_sensor_and_store():
    while True:
        try:
            humidity = sensor.humidity
            temperature = sensor.temperature
            NO2, CO2, NH3, H2S, TOLUENE = hazardous_gas()
            illumination = readadc(ldr_channel)

            user_id = "user"
            conn = get_db_connection()
            cursor = conn.cursor()
            sql = """
                INSERT INTO FARMSEYE_ENV (TEMP, HUMI, NO2, CO2, NH3, H2S, TOLUENE, ILLUMI, TIMESTAMP, USER_ID)
                VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
            """
            now = datetime.now().strftime('%Y-%m-%d %H:%M:%S')
            cursor.execute(sql, (temperature, humidity, NO2, CO2, NH3, H2S, TOLUENE, illumination, now, user_id))
            conn.commit()
        except Exception as e:
            print(f"센서 데이터 저장 오류: {e}")
        finally:
            cursor.close()
            conn.close()
            time.sleep(10)

# 카메라 스트리밍 설정
class StreamingOutput(io.BufferedIOBase):
    def __init__(self):
        self.frame = None
        self.condition = threading.Condition()
    def write(self, buf):
        with self.condition:
            self.frame = buf
            self.condition.notify_all()

class StreamingHandler(server.BaseHTTPRequestHandler):
    def do_GET(self):
        if self.path == '/':
            self.send_response(301)
            self.send_header('Location', '/index.html')
            self.end_headers()
        elif self.path == '/index.html':
            content = PAGE.encode('utf-8')
            self.send_response(200)
            self.send_header('Content-Type', 'text/html')
            self.send_header('Content-Length', len(content))
            self.end_headers()
            self.wfile.write(content)
        elif self.path == '/stream.mjpg':
            self.send_response(200)
            self.send_header('Age', 0)
            self.send_header('Cache-Control', 'no-cache, private')
            self.send_header('Pragma', 'no-cache')
            self.send_header('Content-Type', 'multipart/x-mixed-replace; boundary=FRAME')
            self.end_headers()
            try:
                while True:
                    with output.condition:
                        output.condition.wait()
                        frame = output.frame
                    self.wfile.write(b'--FRAME\r\n')
                    self.send_header('Content-Type', 'image/jpeg')
                    self.send_header('Content-Length', len(frame))
                    self.end_headers()
                    self.wfile.write(frame)
                    self.wfile.write(b'\r\n')
            except Exception as e:
                logging.warning(f"Streaming client removed: {self.client_address} - {str(e)}")
        else:
            self.send_error(404)
            self.end_headers()

class StreamingServer(socketserver.ThreadingMixIn, server.HTTPServer):
    allow_reuse_address = True
    daemon_threads = True

def start_camera_stream():
    global output
    picam2 = Picamera2()
    picam2.configure(picam2.create_video_configuration(main={"size": (640, 480)}))
    output = StreamingOutput()
    picam2.start_recording(JpegEncoder(), FileOutput(output))
    try:
        address = ('', 8000)
        server = StreamingServer(address, StreamingHandler)
        server.serve_forever()
    finally:
        picam2.stop_recording()

# 모션 감지 루프
def motion_detection():
    print("PIR Ready . . .")
    time.sleep(5)
    try:
        while True:
            if GPIO.input(motion_sensor):
                GPIO.output(led_Y, 1)
                GPIO.output(led_G, 0)
                print("Motion Detected!")
            else:
                GPIO.output(led_G, 1)
                GPIO.output(led_Y, 0)
                print("Motion Out!")
            time.sleep(0.5)
    except Exception as e:
        print(f"모션 감지 오류: {e}")
    finally:
        GPIO.output(led_G, 0)
        GPIO.output(led_Y, 0)

# 정리 함수
def cleanup():
    GPIO.output(LED_PIN, GPIO.LOW)
    servo_pwm.stop()
    GPIO.cleanup()

# 스레드 시작 및 Flask 실행
if __name__ == '__main__':
    try:
        threading.Thread(target=start_camera_stream, daemon=True).start()
        threading.Thread(target=motion_detection, daemon=True).start()
        threading.Thread(target=read_sensor_and_store, daemon=True).start()
        app.run(host='0.0.0.0', port=5000)
    finally:
        cleanup()
