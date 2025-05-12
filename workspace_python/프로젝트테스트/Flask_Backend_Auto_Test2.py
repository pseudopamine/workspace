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
import time
import board
import adafruit_dht
import spidev
import pandas as pd
import numpy as np
from datetime import datetime

# 전역 user_id 변수를 스크립트 시작 부분에 정의 (수정됨)
user_id = "user"

app = Flask(__name__)
CORS(app)  # 크로스 오리진 요청 허용

# 데이터베이스 설정
DB_CONFIG = {
    "host": "192.168.30.97",  # 필요에 따라 IP 변경
    "user": "code17_user",
    "password": "mariadb",
    "database": "code17",
    "charset": "utf8mb4"
}

# GPIO 설정
SERVO_PIN = 18  # 서보모터 핀
LED_PIN = 14    # LED 핀

# GPIO 초기화
GPIO.setmode(GPIO.BCM)
GPIO.setup(SERVO_PIN, GPIO.OUT)
GPIO.setup(LED_PIN, GPIO.OUT)

# 서보모터 PWM 설정
servo_pwm = GPIO.PWM(SERVO_PIN, 50)  # 50Hz PWM
servo_pwm.start(0)  # 듀티 사이클 0으로 시작

# LED 깜빡임 제어를 위한 전역 변수
led_blinking = False
led_thread = None

def angle_to_duty_cycle(angle):
    """각도를 듀티 사이클로 변환 (0도~180도 -> 2.5%~12.5%)"""
    return 2.5 + (angle / 180) * 10

def control_servo(angle):
    """서보모터를 지정된 각도로 이동"""
    duty_cycle = angle_to_duty_cycle(angle)
    servo_pwm.ChangeDutyCycle(duty_cycle)
    time.sleep(0.5)  # 모터가 이동할 시간 여유
    servo_pwm.ChangeDutyCycle(0)  # 모터 떨림 방지


def start_led():
	GPIO.output(LED_PIN, GPIO.HIGH)


def stop_led():
    GPIO.output(LED_PIN, GPIO.LOW)


def get_db_connection():
    """데이터베이스 연결 반환"""
    return pymysql.connect(**DB_CONFIG)

def get_latest_sensor_data():
    """가장 최근 센서 데이터 조회"""
    try:
        conn = get_db_connection()
        cursor = conn.cursor(pymysql.cursors.DictCursor)
        
        sql = """
            SELECT TEMP, HUMI, NO2, CO2, NH3, H2S, TOLUENE, ILLUMI, TIMESTAMP
            FROM FARMSEYE_ENV
            ORDER BY TIMESTAMP DESC
            LIMIT 1
        """
        
        cursor.execute(sql)
        data = cursor.fetchone()
        return data
    except Exception as e:
        print(f"데이터베이스 오류: {e}")
        return None
    finally:
        cursor.close()
        conn.close()

@app.route('/api/sensor-data', methods=['GET'])
def get_sensor_data():
    """최신 센서 데이터를 JSON으로 반환"""
    data = get_latest_sensor_data()
    if data:
        # datetime 객체를 문자열로 변환
        data['TIMESTAMP'] = data['TIMESTAMP'].strftime('%Y-%m-%d %H:%M:%S')
        return jsonify(data)
    return jsonify({"error": "데이터를 가져올 수 없습니다."}), 500

@app.route('/api/control/servo', methods=['POST'])
def set_servo_angle():
    """서보모터 각도 설정"""
    data = request.get_json()
    angle = data.get('angle', 0)
    
    try:
        angle = float(angle)
        if angle < 0 or angle > 180:
            return jsonify({"error": "각도는 0에서 180 사이여야 합니다."}), 400
            
        control_servo(angle)
        return jsonify({"success": True, "angle": angle})
    except Exception as e:
        return jsonify({"error": str(e)}), 500

@app.route('/api/control/led', methods=['POST'])
def control_led():
    """LED 제어"""
    data = request.get_json()
    state = data.get('state', 'off')
    
    try:
        if data['state'] == 'on':
            GPIO.output(LED_PIN, GPIO.HIGH)
        elif data['state'] == 'off':
            GPIO.output(LED_PIN, GPIO.LOW)

        else:
            return jsonify({"error": "유효하지 않은 상태입니다."}), 400
            
        return jsonify({"success": True, "state": state})
    except Exception as e:
        return jsonify({"error": str(e)}), 500

@app.route('/api/auto-control', methods=['POST'])
def set_auto_control():
    """자동 제어 규칙 설정"""
    rules = request.get_json()
    
    # 여기서는 규칙을 저장하지 않고 즉시 적용합니다.
    try:
        data = get_latest_sensor_data()
        if not data:
            return jsonify({"error": "센서 데이터를 가져올 수 없습니다."}), 500
        
        # 규칙 적용
        for rule in rules:
            sensor = rule.get('sensor')
            condition = rule.get('condition')
            value = float(rule.get('value', 0))
            action = rule.get('action')
            action_value = rule.get('actionValue')
            
            sensor_value = data.get(sensor.upper())
            if sensor_value is None:
                continue
                
            condition_met = False
            if condition == '>=':
                condition_met = sensor_value >= value
            elif condition == '<=':
                condition_met = sensor_value <= value
            elif condition == '>':
                condition_met = sensor_value > value
            elif condition == '<':
                condition_met = sensor_value < value
            elif condition == '==':
                condition_met = sensor_value == value
                
            if condition_met:
                if action == 'servo':
                    control_servo(float(action_value))
                elif action == 'led' and action_value == 'on':
                    GPIO.output(LED_PIN, GPIO.HIGH)
                elif action == 'led' and action_value == 'off':
                    GPIO.output(LED_PIN, GPIO.LOW)
                    
        return jsonify({"success": True})
    except Exception as e:
        return jsonify({"error": str(e)}), 500
    
def get_automation_rules():
    """사용자별 자동화 규칙 설정값 조회"""
    try:
        conn = get_db_connection()
        cursor = conn.cursor(pymysql.cursors.DictCursor)
        
        # SQL 쿼리에서 매개변수 제거 (수정됨)
        sql = """
            SELECT * FROM FARMSEYE_ENV_MINMAX
            LIMIT 1
        """
        
        cursor.execute(sql)  # user_id 매개변수 제거 (수정됨)
        data = cursor.fetchone()
        return data
    except Exception as e:
        print(f"자동화 규칙 조회 오류: {e}")
        return None
    finally:
        cursor.close()
        conn.close()


@app.route('/api/rules/check', methods=['GET'])
def check_rules():
    """현재 센서 데이터를 기반으로 규칙 확인"""
    try:
        data = get_latest_sensor_data()
        if not data:
            return jsonify({"error": "센서 데이터를 가져올 수 없습니다."}), 500
            
        # 데이터베이스에서 자동화 규칙 설정값 조회
        rules_config = get_automation_rules()
        if not rules_config:
            return jsonify({"error": "자동화 규칙 설정을 가져올 수 없습니다."}), 500
            
        # 규칙 정의 (데이터베이스에서 가져온 설정 기반)
        rules = [
            # 온도 관련 규칙
            {
                "sensor": "TEMP",
                "condition": ">=",
                "value": rules_config["MAX_TEM"],
                "action": "led",
                "actionValue": 'on'  # 에어컨 켜기
            },
            {
                "sensor": "TEMP",
                "condition": "<=",
                "value": rules_config["MIN_TEM"],
                "action": "led",
                "actionValue": 'off'  # 에어컨 끄기
            },
            # 습도 관련 규칙
            {
                "sensor": "HUMI",
                "condition": ">=",
                "value": rules_config["MAX_HUMI"],
                "action": "servo",
                "actionValue": 180  # 루프 최대로 열기
            },
            {
                "sensor": "HUMI",
                "condition": "<=",
                "value": rules_config["MIN_HUMI"],
                "action": "servo",
                "actionValue": 0  # 루프 닫기
            },
            # 유해가스 - 이산화질소 관련 규칙
            {
                "sensor": "NO2",
                "condition": ">=",
                "value": rules_config["DAN_NO2"],
                "action": "servo",
                "actionValue": 180  # 창문 최대로 열기
            },
            # 이산화탄소 관련 규칙
            {
                "sensor": "CO2",
                "condition": ">=",
                "value": rules_config["DAN_CO2"],
                "action": "servo",
                "actionValue": 180  # 창문 최대로 열기
            },
            # 암모니아 관련 규칙
            {
                "sensor": "NH3",
                "condition": ">=",
                "value": rules_config["BOU_NH3"],
                "action": "servo",
                "actionValue": 180  # 창문 최대로 열기
            },
            # 황화수소 관련 규칙
            {
                "sensor": "H2S",
                "condition": ">=",
                "value": rules_config["DAN_H2S"],
                "action": "servo",
                "actionValue": 180  # 창문 최대로 열기
            },
            # 톨루엔 관련 규칙
            {
                "sensor": "TOLUENE",
                "condition": ">=",
                "value": rules_config["BOU_TOLUENE"],
                "action": "servo",
                "actionValue": 180  # 창문 최대로 열기
            },
            # 조도 관련 규칙
            {
                "sensor": "ILLUMI",
                "condition": "<=",
                "value": rules_config["MIN_ILLUMI"],
                "action": "led",
                "actionValue": "on"  # 조명 켜기
            },
            {
                "sensor": "ILLUMI",
                "condition": ">=",
                "value": rules_config["MAX_ILLUMI"],
                "action": "led",
                "actionValue": "off"  # 조명 끄기
            }
        ]
        
        actions_taken = []
        
        # 규칙 적용
        for rule in rules:
            sensor = rule.get('sensor')
            condition = rule.get('condition')
            value = float(rule.get('value', 0))
            action = rule.get('action')
            action_value = rule.get('actionValue')
            
            sensor_value = data.get(sensor)
            if sensor_value is None:
                continue
                
            condition_met = False
            if condition == '>=':
                condition_met = sensor_value >= value
            elif condition == '<=':
                condition_met = sensor_value <= value
            elif condition == '>':
                condition_met = sensor_value > value
            elif condition == '<':
                condition_met = sensor_value < value
            elif condition == '==':
                condition_met = sensor_value == value
                
            if condition_met:
                actions_taken.append({
                    "rule": rule,
                    "currentValue": sensor_value
                })
                
                if action == 'servo':
                    control_servo(float(action_value))
                elif action == 'led' and action_value == 'on':
                    GPIO.output(LED_PIN, GPIO.HIGH)
                elif action == 'led' and action_value == 'off':
                    GPIO.output(LED_PIN, GPIO.LOW)

        return jsonify({
            "success": True,
            "data": data,
            "actionsTaken": actions_taken
        })
    except Exception as e:
        return jsonify({"error": str(e)}), 500
    
@app.route('/api/automation/config', methods=['GET'])
def get_automation_config():
    """자동화 설정 조회 API"""
    try:
        # 전역 변수로 정의된 user_id 사용
        config = get_automation_rules()
        if not config:
            return jsonify({"error": "자동화 설정을 찾을 수 없습니다."}), 404
            
        # 필요 없는 필드 제거
        if 'NUM' in config:
            del config['NUM']
            
        return jsonify(config)
    except Exception as e:
        return jsonify({"error": str(e)}), 500

@app.route('/api/automation/config', methods=['POST'])
def update_automation_config():
    """자동화 설정 업데이트 API"""
    try:
        data = request.get_json()
        
        # 필수 필드 확인
        required_fields = [
            'MIN_TEM', 'MAX_TEM', 'MIN_HUMI', 'MAX_HUMI', 
            'MIN_ILLUMI', 'MAX_ILLUMI', 'BOU_NO2', 'DAN_NO2',
            'BOU_CO2', 'DAN_CO2', 'BOU_NH3', 'DAN_NH3',
            'BOU_H2S', 'DAN_H2S', 'BOU_TOLUENE', 'DAN_TOLUENE'
        ]
        
        for field in required_fields:
            if field not in data:
                return jsonify({"error": f"필수 필드 {field}가 누락되었습니다."}), 400
                
        # 모든 숫자 필드 형변환 및 유효성 검사
        for field in required_fields:
            try:
                data[field] = float(data[field])
            except (ValueError, TypeError):
                return jsonify({"error": f"필드 {field}는 숫자여야 합니다."}), 400
                
        # 추가 유효성 검사 (최소값 < 최대값)
        if data['MIN_TEM'] >= data['MAX_TEM']:
            return jsonify({"error": "최저 온도는 최고 온도보다 작아야 합니다."}), 400
            
        if data['MIN_HUMI'] >= data['MAX_HUMI']:
            return jsonify({"error": "최저 습도는 최고 습도보다 작아야 합니다."}), 400
            
        if data['MIN_ILLUMI'] >= data['MAX_ILLUMI']:
            return jsonify({"error": "최저 조도는 최고 조도보다 작아야 합니다."}), 400
            
        if data['BOU_NO2'] >= data['DAN_NO2']:
            return jsonify({"error": "경계 이산화질소는 위험 이산화질소보다 작아야 합니다."}), 400
            
        if data['BOU_CO2'] >= data['DAN_CO2']:
            return jsonify({"error": "경계 이산화탄소는 위험 이산화탄소보다 작아야 합니다."}), 400
            
        if data['BOU_NH3'] >= data['DAN_NH3']:
            return jsonify({"error": "경계 암모니아는 위험 암모니아보다 작아야 합니다."}), 400
            
        if data['BOU_H2S'] >= data['DAN_H2S']:
            return jsonify({"error": "경계 황화수소는 위험 황화수소보다 작아야 합니다."}), 400
            
        if data['BOU_TOLUENE'] >= data['DAN_TOLUENE']:
            return jsonify({"error": "경계 톨루엔은 위험 톨루엔보다 작아야 합니다."}), 400
            
        # update_automation_rules 함수 호출 시 user_id 매개변수 제거 (수정됨)
        update_automation_rules(data)
        return jsonify({"success": True, "message": "자동화 설정이 업데이트되었습니다."})
    except Exception as e:
        return jsonify({"error": str(e)}), 500

# user_id 매개변수 제거 (수정됨)
def update_automation_rules(data):
    """사용자별 자동화 규칙 설정값 업데이트"""
    try:
        conn = get_db_connection()
        cursor = conn.cursor()
        
        # 해당 사용자의 설정이 있는지 확인 - user_id 매개변수 제거 (수정됨)
        check_sql = "SELECT COUNT(*) FROM FARMSEYE_ENV_MINMAX"
        cursor.execute(check_sql)  # user_id 매개변수 제거 (수정됨)
        count = cursor.fetchone()[0]
        
        if count > 0:
            # 업데이트 SQL - user_id 참조 제거 (수정됨)
            sql = """
                UPDATE FARMSEYE_ENV_MINMAX
                SET 
                    MIN_TEM = %s,
                    MAX_TEM = %s,
                    MIN_HUMI = %s,
                    MAX_HUMI = %s,
                    MIN_ILLUMI = %s,
                    MAX_ILLUMI = %s,
                    BOU_NO2 = %s,
                    DAN_NO2 = %s,
                    BOU_CO2 = %s,
                    DAN_CO2 = %s,
                    BOU_NH3 = %s,
                    DAN_NH3 = %s,
                    BOU_H2S = %s,
                    DAN_H2S = %s,
                    BOU_TOLUENE = %s,
                    DAN_TOLUENE = %s
            """
            cursor.execute(sql, (
                data['MIN_TEM'], data['MAX_TEM'],
                data['MIN_HUMI'], data['MAX_HUMI'],
                data['MIN_ILLUMI'], data['MAX_ILLUMI'],
                data['BOU_NO2'], data['DAN_NO2'],
                data['BOU_CO2'], data['DAN_CO2'],
                data['BOU_NH3'], data['DAN_NH3'],
                data['BOU_H2S'], data['DAN_H2S'],
                data['BOU_TOLUENE'], data['DAN_TOLUENE']
            ))  # user_id 매개변수 제거 (수정됨)
        else:
            # 새 설정 추가 SQL - user_id 참조 제거 (수정됨)
            sql = """
                INSERT INTO FARMSEYE_ENV_MINMAX (
                    MIN_TEM, MAX_TEM, MIN_HUMI, MAX_HUMI,
                    MIN_ILLUMI, MAX_ILLUMI, BOU_NO2, DAN_NO2,
                    BOU_CO2, DAN_CO2, BOU_NH3, DAN_NH3,
                    BOU_H2S, DAN_H2S, BOU_TOLUENE, DAN_TOLUENE,
                    USER_ID
                ) VALUES (
                    %s, %s, %s, %s, %s, %s, %s, %s,
                    %s, %s, %s, %s, %s, %s, %s, %s, %s
                )
            """
            cursor.execute(sql, (
                data['MIN_TEM'], data['MAX_TEM'],
                data['MIN_HUMI'], data['MAX_HUMI'],
                data['MIN_ILLUMI'], data['MAX_ILLUMI'],
                data['BOU_NO2'], data['DAN_NO2'],
                data['BOU_CO2'], data['DAN_CO2'],
                data['BOU_NH3'], data['DAN_NH3'],
                data['BOU_H2S'], data['DAN_H2S'],
                data['BOU_TOLUENE'], data['DAN_TOLUENE'],
                user_id  # 전역 변수 user_id 사용 (수정됨)
            ))
        
        conn.commit()
        return True
    except Exception as e:
        print(f"자동화 규칙 업데이트 오류: {e}")
        conn.rollback()
        raise e
    finally:
        cursor.close()
        conn.close()

def cleanup():
    """프로그램 종료 시 GPIO 정리"""
    stop_led()
    servo_pwm.stop()
    GPIO.cleanup()

################################모션 감지 센서########################################

def motion_detection():
    # import RPi.GPIO as GPIO
    # import time

    led_G = 20
    led_Y = 21
    sensor = 4

    GPIO.setwarnings(False)
    GPIO.setmode(GPIO.BCM)

    GPIO.setup(led_G, GPIO.OUT)
    GPIO.setup(led_Y, GPIO.OUT)
    GPIO.setup(sensor, GPIO.IN)

    print("PIR Ready . . . . ")
    time.sleep(5)

    try:
        while True:
            if GPIO.input(sensor) == 1:
                GPIO.output(led_Y, 1)
                GPIO.output(led_G, 0)
                time.sleep(0.5)

            elif GPIO.input(sensor) == 0:
                GPIO.output(led_G, 1)
                GPIO.output(led_Y, 0)
                time.sleep(0.5)
    except Exception as e:
        print(f"모션 감지 스레드 오류: {e}")
    finally:
        GPIO.output(led_G, 0)
        GPIO.output(led_Y, 0)
        GPIO.cleanup()


################################실시간 영상#########################################

PAGE = """\
<html>
<head><title>Camera Stream</title></head>
<body><h1>Picamera2 MJPEG Stream</h1>
<img src="stream.mjpg" width="640" height="480" />
</body>
</html>
"""

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

################################DB 데이터 적재#########################################

# DB 정보 (연결할 IP 리스트)
ip_list = ["192.168.30.97", "192.168.30.147", "192.168.30.111"]

# SPI 및 센서 설정
mq_channel = 2  # 유해가스 센서 채널
ldr_channel = 0   # 조도 센서 채널
sensor = adafruit_dht.DHT22(board.D2)  # 온습도 센서

# SPI 인터페이스 설정
spi = spidev.SpiDev()
spi.open(0, 0)
spi.max_speed_hz = 100000

# 이전 측정된 가스 데이터 (중복 확인용)
prev_gas_data = None  

# ADC 데이터 읽기 함수
def readadc(adcnum):
    if adcnum > 7 or adcnum < 0:
        return -1
    r = spi.xfer2([1, (8 + adcnum) << 4, 0])
    data = ((r[1] & 3) << 8) + r[2]
    return data

# 유해가스 데이터 측정 함수
def hazardous_gas():
    mq_value = readadc(mq_channel)
    volt = (mq_value / 1023.0) * 3.3
    res = (3.3 - volt) * 10.0 / volt
    ratio = res / 76.63
    # 각 가스별 농도 계산
    NO2 = round(0.4220682 * math.pow(ratio, -2.769034857), 3)
    CO2 = round(ratio * 1.3 * 400, 3)
    NH3 = round(1.8028257 * math.pow(ratio, -1.3523192), 3)
    H2S = round(0.0216549 * math.pow(ratio, -1.5547654), 3)
    Toluene = round(2.82 * math.pow(ratio, -1.56), 3)
    return NO2, CO2, NH3, H2S, Toluene

# 현재 데이터 수집 함수
def getNowData():
    global prev_gas_data
    
    try:
        NO2, CO2, NH3, H2S, Toluene = hazardous_gas()
        current_gas_data = (NO2, CO2, NH3, H2S, Toluene)

        # 이전 가스 데이터와 동일하면 저장하지 않음
        if prev_gas_data == current_gas_data:
            print("Skipping duplicate gas data.")
            return None  
        
        prev_gas_data = current_gas_data  # 현재 데이터를 이전 데이터로 저장
        
        # DHT22 센서 읽기에 예외 처리 추가
        try:
            temperature = round(sensor.temperature, 3)
            humidity = round(sensor.humidity, 3)
        except Exception as e:
            print(f"DHT22 센서 읽기 오류: {e}")
            temperature = None
            humidity = None
            
        ldr_value = readadc(ldr_channel)
        timestamp = datetime.now()
        
        # 필수 데이터가 없으면 None 반환
        if temperature is None or humidity is None:
            print("필수 데이터(온도/습도) 누락으로 데이터 저장 건너뜀")
            return None
            
        return {
            "temperature": temperature,
            "humidity": humidity,
            "ldr_value": ldr_value,
            "NO2": NO2,
            "CO2": CO2,
            "NH3": NH3,
            "H2S": H2S,
            "Toluene": Toluene,
            "timestamp": timestamp
        }
    except Exception as e:
        print(f"데이터 수집 중 오류 발생: {e}")
        return None

user_id = "user"

# 데이터 DB 저장 함수
def insertData(df, user_id):
    for ip in ip_list:
        try:
            conn = pymysql.connect(
                host=ip,
                user="code17_user",
                password="mariadb",
                database="code17",
                charset="utf8mb4"
            )
            cursor = conn.cursor()
            sql = """INSERT INTO FARMSEYE_ENV 
                    (TEMP, HUMI, NO2, CO2, NH3, H2S, TOLUENE, ILLUMI, TIMESTAMP, USER_ID) 
                    VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s)"""
            
            for _, row in df.iterrows():
                cursor.execute(sql, (
                    round(row["temperature"], 3),
                    round(row["humidity"], 3),
                    round(row["NO2"], 3),
                    round(row["CO2"], 3),
                    round(row["NH3"], 3),
                    round(row["H2S"], 3),
                    round(row["Toluene"], 3),
                    row["ldr_value"],
                    row["timestamp"],
                    user_id
                ))
            
            conn.commit()
            print(f"COMPLETE ADD DATA into {ip}")
        except pymysql.MySQLError as e:
            print(f"DB ERROR on {ip}: {e}")
        finally:
            cursor.close()
            conn.close()

# 데이터 저장 리스트
data_list = []

# 메인 루프
def main():
    global data_list
    print("DB 데이터 적재 스레드 시작...")

    while True:
        try:
            print("데이터 수집 시도 중...")
            new_data = getNowData()
            if new_data:
                data_list.append(new_data)
                print("Added new data point:", new_data)

            # 5개 이상 데이터가 모이면 가공 후 DB 저장
            if len(data_list) >= 5:
                df = pd.DataFrame(data_list)
                print("Raw Data:", df)
                insertData(df, user_id)
                data_list = []
            
            time.sleep(60)
        except Exception as e:
            print("Error:", e)
            import traceback
            traceback.print_exc()  # 상세 오류 정보 출력
            time.sleep(60)


#########################################################################


if __name__ == '__main__':
    try:
        print("프로그램 시작...")
        
        # 스레드 시작 전 센서 초기화 테스트
        print("센서 초기화 테스트...")
        try:
            test_temp = sensor.temperature
            test_humidity = sensor.humidity
            print(f"DHT22 테스트: 온도={test_temp}°C, 습도={test_humidity}%")
            
            test_mq = readadc(mq_channel)
            test_ldr = readadc(ldr_channel)
            print(f"ADC 테스트: MQ={test_mq}, LDR={test_ldr}")
        except Exception as e:
            print(f"센서 초기화 테스트 실패: {e}")
            # 여기서 중단할지 계속 진행할지 결정
        
        # 카메라 스트리밍 스레드
        print("카메라 스트리밍 스레드 시작...")
        cam_thread = threading.Thread(target=start_camera_stream, daemon=True)
        cam_thread.start()

        # 모션 감지 스레드
        print("모션 감지 스레드 시작...")
        motion_thread = threading.Thread(target=motion_detection, daemon=True)
        motion_thread.start()

        # 데이터 적재 스레드
        print("데이터 적재 스레드 시작...")
        data_thread = threading.Thread(target=main, daemon=True)
        data_thread.start()

        # Flask 실행
        print("Flask 서버 시작...")
        app.run(host='0.0.0.0', port=5000, debug=False)
        
        
    finally:
        cleanup()