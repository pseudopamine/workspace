from flask import Flask, request, jsonify
from flask_cors import CORS
import pymysql
import RPi.GPIO as GPIO
import time
import threading

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

def blink_led(interval):
    """LED를 지정된 간격으로 깜빡임"""
    global led_blinking
    while led_blinking:
        GPIO.output(LED_PIN, GPIO.HIGH)
        time.sleep(interval)
        GPIO.output(LED_PIN, GPIO.LOW)
        time.sleep(interval)

def start_led_blinking(interval):
    """LED 깜빡임 시작"""
    global led_blinking, led_thread
    if led_blinking and led_thread and led_thread.is_alive():
        return

    led_blinking = True
    led_thread = threading.Thread(target=blink_led, args=(interval,))
    led_thread.daemon = True
    led_thread.start()

def stop_led_blinking():
    """LED 깜빡임 중지"""
    global led_blinking
    led_blinking = False
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
    interval = data.get('interval', 0.5)  # 기본 깜빡임 간격
    
    try:
        if state == 'on':
            GPIO.output(LED_PIN, GPIO.HIGH)
            stop_led_blinking()  # 깜빡임 중지
        elif state == 'off':
            GPIO.output(LED_PIN, GPIO.LOW)
            stop_led_blinking()  # 깜빡임 중지
        elif state == 'blink':
            start_led_blinking(interval)
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
                    stop_led_blinking()
                elif action == 'led' and action_value == 'off':
                    GPIO.output(LED_PIN, GPIO.LOW)
                    stop_led_blinking()
                elif action == 'led' and action_value == 'blink':
                    interval = rule.get('blinkInterval', 0.5)
                    start_led_blinking(float(interval))
                    
        return jsonify({"success": True})
    except Exception as e:
        return jsonify({"error": str(e)}), 500

@app.route('/api/rules/check', methods=['GET'])
def check_rules():
    """현재 센서 데이터를 기반으로 규칙 확인"""
    try:
        data = get_latest_sensor_data()
        if not data:
            return jsonify({"error": "센서 데이터를 가져올 수 없습니다."}), 500
            
        # 규칙 정의 (실제로는 데이터베이스나 설정 파일에서 불러올 수 있음)
        rules = [
            {
                "sensor": "TEMP",
                "condition": ">=",
                "value": 30,
                "action": "servo",
                "actionValue": 20
            },
            {
                "sensor": "NH3",
                "condition": ">=",
                "value": 2,
                "action": "led",
                "actionValue": "blink",
                "blinkInterval": 0.5
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
                    stop_led_blinking()
                elif action == 'led' and action_value == 'off':
                    GPIO.output(LED_PIN, GPIO.LOW)
                    stop_led_blinking()
                elif action == 'led' and action_value == 'blink':
                    interval = rule.get('blinkInterval', 0.5)
                    start_led_blinking(float(interval))
                    
        return jsonify({
            "success": True,
            "data": data,
            "actionsTaken": actions_taken
        })
    except Exception as e:
        return jsonify({"error": str(e)}), 500

def cleanup():
    """프로그램 종료 시 GPIO 정리"""
    stop_led_blinking()
    servo_pwm.stop()
    GPIO.cleanup()

if __name__ == '__main__':
    try:
        app.run(host='0.0.0.0', port=5000)
    finally:
        cleanup()