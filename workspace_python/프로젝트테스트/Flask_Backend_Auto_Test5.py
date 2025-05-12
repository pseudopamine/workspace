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
import pandas as pd
import numpy as np
from datetime import datetime


app = Flask(__name__)

# 1. 자동화 간격 수정
# 2. 톨루엔 제어 삭제
# 3. 이산화질소 제어 삭제
# 4. 이산화탄소 제어 변경
# 5. AutoController 클래스에서도 삭제

#################################자동화 제어 클래스######################################

# 로그 설정
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')
logger = logging.getLogger('auto_control')

# 자동화 제어 클래스
class AutoController:
    def __init__(self, db_config, gpio_setup):
        self.db_config = db_config
        self.gpio = gpio_setup
        self.running = False
        self.thread = None
        self.control_interval = 10  # 5분(300초) 간격으로 제어
        
        # 센서 이름과 DB 컬럼명 매핑
        self.sensor_mapping = {
            'TEMP': {'min': 'MIN_TEM', 'max': 'MAX_TEM'},
            'HUMI': {'min': 'MIN_HUMI', 'max': 'MAX_HUMI'},
            'ILLUMI': {'min': 'MIN_ILLUMI', 'max': 'MAX_ILLUMI'},
            'CO2': {'bou': 'BOU_CO2', 'dan': 'DAN_CO2'},
            'NH3': {'bou': 'BOU_NH3', 'dan': 'DAN_NH3'},
            'H2S': {'bou': 'BOU_H2S', 'dan': 'DAN_H2S'},
        }
        
        # 각 센서에 대한 제어 액션 설정
        self.control_actions = {
            'TEMP': self.control_temperature,
            'HUMI': self.control_humidity,
            'ILLUMI': self.control_illumination,
            'CO2': self.control_co2,
            'NH3': self.control_nh3,
            'H2S': self.control_h2s,
        }
        
        # 액츄에이터 상태 추적
        self.actuator_states = {
            'ac': False,        # 에어컨 (예: LED로 표시)
            'heater': False,    # 히터 (예: LED로 표시)
            'humidifier': False,# 가습기 (예: 서보모터로 표시)
            'dehumidifier': False, # 제습기 (예: 서보모터로 표시)
            'light': False,     # 조명 (예: LED로 표시)
            'ventilation': False # 환기 (예: 서보모터로 표시)
        }
    
    def get_db_connection(self):
        """데이터베이스 연결 반환"""
        return pymysql.connect(**self.db_config)
    
    def get_latest_sensor_data(self):
        """가장 최근 센서 데이터 조회"""
        try:
            conn = self.get_db_connection()
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
            logger.error(f"최신 센서 데이터 조회 오류: {e}")
            return None
        finally:
            cursor.close()
            conn.close()
    
    def get_minmax_settings(self, user_id="user"):
        """사용자별 최소/최대 설정값 조회"""
        try:
            conn = self.get_db_connection()
            cursor = conn.cursor(pymysql.cursors.DictCursor)
            
            sql = """
                SELECT * FROM FARMSEYE_ENV_MINMAX
                WHERE USER_ID = %s
                LIMIT 1
            """
            
            cursor.execute(sql, (user_id,))
            data = cursor.fetchone()
            
            if not data:
                logger.warning(f"사용자 {user_id}에 대한 설정을 찾을 수 없음. 기본값 사용.")
                # 기본값 설정
                sql = """
                    INSERT INTO FARMSEYE_ENV_MINMAX 
                    (MIN_TEM, MAX_TEM, MIN_HUMI, MAX_HUMI, MIN_ILLUMI, MAX_ILLUMI,
                    BOU_NO2, DAN_NO2, BOU_CO2, DAN_CO2, BOU_NH3, DAN_NH3,
                    BOU_H2S, DAN_H2S, BOU_TOLUENE, DAN_TOLUENE, USER_ID)
                    VALUES (20, 28, 60, 70, 5, 20, 2, 5, 3000, 5000, 10, 20, 0.5, 2, 0.2, 0.5, %s)
                """
                cursor.execute(sql, (user_id,))
                conn.commit()
                
                # 다시 조회
                cursor.execute("SELECT * FROM FARMSEYE_ENV_MINMAX WHERE USER_ID = %s LIMIT 1", (user_id,))
                data = cursor.fetchone()
            
            return data
        except Exception as e:
            logger.error(f"MIN/MAX 설정값 조회 오류: {e}")
            return None
        finally:
            cursor.close()
            conn.close()
    
    def start(self, user_id="user"):
        """자동화 제어 시작"""
        if self.running:
            logger.info("자동화 제어가 이미 실행 중입니다.")
            return
        
        self.running = True
        self.thread = threading.Thread(target=self.run_control_loop, args=(user_id,), daemon=True)
        self.thread.start()
        logger.info("자동화 제어 시작됨")
    
    def stop(self):
        """자동화 제어 중지"""
        self.running = False
        if self.thread:
            self.thread.join(timeout=1.0)
        logger.info("자동화 제어 중지됨")
    
    def run_control_loop(self, user_id):
        """자동화 제어 메인 루프"""
        while self.running:
            try:
                # 최신 센서 데이터 조회
                sensor_data = self.get_latest_sensor_data()
                if not sensor_data:
                    logger.warning("센서 데이터를 가져올 수 없습니다. 다음 주기까지 대기...")
                    time.sleep(self.control_interval)
                    continue
                
                # MIN/MAX 설정값 조회
                minmax_settings = self.get_minmax_settings(user_id)
                if not minmax_settings:
                    logger.warning("MIN/MAX 설정값을 가져올 수 없습니다. 다음 주기까지 대기...")
                    time.sleep(self.control_interval)
                    continue
                
                # 각 센서별 제어 수행
                control_results = []
                for sensor, action_func in self.control_actions.items():
                    if sensor in sensor_data:
                        result = action_func(sensor_data[sensor], minmax_settings)
                        if result:
                            control_results.append(result)
                
                # 제어 결과 로그
                if control_results:
                    logger.info(f"제어 조치 수행됨: {', '.join(control_results)}")
                else:
                    logger.info("모든 센서값이 정상 범위 내에 있습니다. 액추에이터 변경 없음.")
                
                # 다음 주기까지 대기
                time.sleep(self.control_interval)
                
            except Exception as e:
                logger.error(f"제어 루프 실행 중 오류 발생: {e}")
                time.sleep(60)  # 오류 발생 시 1분 후 다시 시도
    
    # 각 센서별 제어 함수
    def control_temperature(self, temp, settings):
        """온도 제어 로직"""
        if temp < settings['MIN_TEM']:
            # 온도가 너무 낮음 - 히터 작동, 에어컨 중지
            if not self.actuator_states['heater']:
                self.gpio.output(self.gpio.LED_PIN, self.gpio.HIGH)  # LED로 히터 작동 표시
                self.actuator_states['heater'] = True
                self.actuator_states['ac'] = False
                return f"온도 {temp}°C가 최소값({settings['MIN_TEM']}°C) 미만: 히터 작동"
        elif temp > settings['MAX_TEM']:
            # 온도가 너무 높음 - 에어컨 작동, 히터 중지
            if not self.actuator_states['ac']:
                self.gpio.output(self.gpio.LED_PIN, self.gpio.LOW)  # LED 끄기 (에어컨 작동 상태)
                self.actuator_states['ac'] = True
                self.actuator_states['heater'] = False
                return f"온도 {temp}°C가 최대값({settings['MAX_TEM']}°C) 초과: 에어컨 작동"
        else:
            # 온도가 적정 범위 내 - 히터와 에어컨 모두 중지
            if self.actuator_states['heater'] or self.actuator_states['ac']:
                self.gpio.output(self.gpio.LED_PIN, self.gpio.LOW)  # LED 끄기
                self.actuator_states['heater'] = False
                self.actuator_states['ac'] = False
                return f"온도 {temp}°C가 적정 범위 내: 온도 조절 장치 중지"
        return None
    
    def control_humidity(self, humi, settings):
        """습도 제어 로직 - 서보모터로 표현"""
        if humi < settings['MIN_HUMI']:
            # 습도가 너무 낮음 - 가습기 작동
            if not self.actuator_states['humidifier']:
                self.gpio.control_servo(45)  # 서보모터 45도로 설정하여 가습기 작동 표시
                self.actuator_states['humidifier'] = True
                self.actuator_states['dehumidifier'] = False
                return f"습도 {humi}%가 최소값({settings['MIN_HUMI']}%) 미만: 가습기 작동"
        elif humi > settings['MAX_HUMI']:
            # 습도가 너무 높음 - 제습기 작동
            if not self.actuator_states['dehumidifier']:
                self.gpio.control_servo(135)  # 서보모터 135도로 설정하여 제습기 작동 표시
                self.actuator_states['dehumidifier'] = True
                self.actuator_states['humidifier'] = False
                return f"습도 {humi}%가 최대값({settings['MAX_HUMI']}%) 초과: 제습기 작동"
        else:
            # 습도가 적정 범위 내 - 가습기와 제습기 모두 중지
            if self.actuator_states['humidifier'] or self.actuator_states['dehumidifier']:
                self.gpio.control_servo(90)  # 서보모터 90도로 설정하여 중립 상태 표시
                self.actuator_states['humidifier'] = False
                self.actuator_states['dehumidifier'] = False
                return f"습도 {humi}%가 적정 범위 내: 습도 조절 장치 중지"
        return None
    
    def control_illumination(self, illumi, settings):
        """조도 제어 로직"""
        # 이 예제에서는 LED로 표현하지 않고 로그만 출력
        if illumi < settings['MIN_ILLUMI']:
            return f"조도 {illumi}가 최소값({settings['MIN_ILLUMI']}) 미만: 조명 켜기 필요"
        elif illumi > settings['MAX_ILLUMI']:
            return f"조도 {illumi}가 최대값({settings['MAX_ILLUMI']}) 초과: 차광 필요"
        return None
    
    def control_co2(self, co2, settings):
        """이산화탄소 제어 로직"""
        if co2 >= settings['DAN_CO2']:
            # 위험 수준 - 강제 환기 작동 (서보모터 180도)
            self.gpio.control_servo(180)
            return f"CO2 농도 {co2}가 위험값({settings['DAN_CO2']}) 이상: 강제 환기 작동"
        elif co2 >= settings['BOU_CO2']:
            # 경계 수준 - 일반 환기 작동 (서보모터 120도)
            self.gpio.control_servo(120)
            return f"CO2 농도 {co2}가 경계값({settings['BOU_CO2']}) 이상: 일반 환기 작동"
        return None
    
    def control_nh3(self, nh3, settings):
        """암모니아 제어 로직"""
        if nh3 >= settings['DAN_NH3']:
            # 위험 수준 - 모든 액추에이터 작동
            self.gpio.output(self.gpio.LED_PIN, self.gpio.HIGH)
            self.gpio.control_servo(180)
            return f"NH3 농도 {nh3}가 위험값({settings['DAN_NH3']}) 이상: 전체 시스템 작동"
        elif nh3 >= settings['BOU_NH3']:
            # 경계 수준 - 일부 액추에이터 작동
            self.gpio.control_servo(150)
            return f"NH3 농도 {nh3}가 경계값({settings['BOU_NH3']}) 이상: 환기 시스템 작동"
        return None
    
    def control_h2s(self, h2s, settings):
        """황화수소 제어 로직"""
        if h2s >= settings['DAN_H2S']:
            # 위험 수준
            return f"H2S 농도 {h2s}가 위험값({settings['DAN_H2S']}) 이상: 알림 필요"
        elif h2s >= settings['BOU_H2S']:
            # 경계 수준
            return f"H2S 농도 {h2s}가 경계값({settings['BOU_H2S']}) 이상: 주의 필요"
        return None
    


# 기존 코드에 추가할 부분 (main 함수 아래에 추가)
def init_auto_controller(db_config, gpio_setup):
    """자동 제어 컨트롤러 초기화 및 시작"""
    controller = AutoController(db_config, gpio_setup)
    controller.start()
    return controller

# GPIO 및 DB 설정을 위한 간단한 래퍼 클래스
class GPIOWrapper:
    def __init__(self, gpio, servo_pin, led_pin):
        self.gpio = gpio
        self.SERVO_PIN = servo_pin
        self.LED_PIN = led_pin
        self.HIGH = gpio.HIGH
        self.LOW = gpio.LOW
    
    def output(self, pin, value):
        self.gpio.output(pin, value)
    
    def control_servo(self, angle):
        duty_cycle = 2.5 + (angle / 180) * 10
        servo_pwm = self.gpio.PWM(self.SERVO_PIN, 50)
        servo_pwm.start(duty_cycle)
        time.sleep(0.5)
        servo_pwm.ChangeDutyCycle(0)


####################################자동화기능########################################

# 자동 제어 설정 조회 API
@app.route('/api/auto-settings', methods=['GET'])
def get_auto_settings():
    """자동화 제어 설정값 조회"""
    user_id = request.args.get('user_id', 'user')
    
    try:
        conn = get_db_connection()
        cursor = conn.cursor(pymysql.cursors.DictCursor)
        
        sql = """
            SELECT * FROM FARMSEYE_ENV_MINMAX
            WHERE USER_ID = %s
        """
        
        cursor.execute(sql, (user_id,))
        settings = cursor.fetchone()
        
        if not settings:
            return jsonify({"error": "설정을 찾을 수 없습니다."}), 404
            
        # datetime 객체 처리
        for key, value in settings.items():
            if isinstance(value, datetime):
                settings[key] = value.strftime('%Y-%m-%d %H:%M:%S')
        
        # 액추에이터 상태 확인
        actuator_states = controller.actuator_states
        
        # 상태 기반 제어 의사결정 추가
        status = {
            "sensor_data": sensor_data,
            "settings": settings,
            "actuator_states": actuator_states,
            "timestamp": datetime.now().strftime('%Y-%m-%d %H:%M:%S')
        }
        
        return jsonify(status)
    except Exception as e:
        return jsonify({"error": str(e)}), 500

# 자동 제어 기능 활성화/비활성화 API
@app.route('/api/auto-control/toggle', methods=['POST'])
def toggle_auto_control():
    """자동 제어 기능 활성화/비활성화"""
    data = request.get_json()
    enabled = data.get('enabled', True)
    user_id = data.get('user_id', 'user')
    
    try:
        # 글로벌 자동 제어 컨트롤러 접근
        global auto_controller
        
        if enabled:
            if not auto_controller.running:
                auto_controller.start(user_id)
                return jsonify({"success": True, "message": "자동 제어 활성화됨", "status": "enabled"})
            else:
                return jsonify({"success": True, "message": "자동 제어가 이미 활성화되어 있습니다.", "status": "enabled"})
        else:
            if auto_controller.running:
                auto_controller.stop()
                return jsonify({"success": True, "message": "자동 제어 비활성화됨", "status": "disabled"})
            else:
                return jsonify({"success": True, "message": "자동 제어가 이미 비활성화되어 있습니다.", "status": "disabled"})
    except Exception as e:
        return jsonify({"error": str(e)}), 500

# 수동으로 즉시 제어 수행 API
@app.route('/api/auto-control/run-now', methods=['POST'])
def run_control_now():
    """수동으로 자동 제어 즉시 실행"""
    user_id = request.json.get('user_id', 'user')
    
    try:
        # 글로벌 자동 제어 컨트롤러 접근
        controller = auto_controller
        
        # 최신 센서 데이터 조회
        sensor_data = controller.get_latest_sensor_data()
        if not sensor_data:
            return jsonify({"error": "센서 데이터를 가져올 수 없습니다."}), 500
        
        # 설정값 조회
        settings = controller.get_minmax_settings(user_id)
        if not settings:
            return jsonify({"error": "설정값을 가져올 수 없습니다."}), 500
        
        # 각 센서별 제어 수행
        control_results = []
        for sensor, action_func in controller.control_actions.items():
            if sensor in sensor_data:
                result = action_func(sensor_data[sensor], settings)
                if result:
                    control_results.append(result)
        
        return jsonify({
            "success": True, 
            "sensor_data": sensor_data,
            "actions_taken": control_results,
            "timestamp": datetime.now().strftime('%Y-%m-%d %H:%M:%S')
        })
    except Exception as e:
        return jsonify({"error": str(e)}), 500        
        return jsonify(settings)
    except Exception as e:
        return jsonify({"error": str(e)}), 500
    finally:
        cursor.close()
        conn.close()

######################################################
# 자동 제어 설정 업데이트 API
@app.route('/api/auto-settings', methods=['POST'])
def update_auto_settings():
    """자동화 제어 설정값 업데이트"""
    data = request.get_json()
    user_id = data.get('USER_ID', 'user')
    
    # 필수 필드 확인
    required_fields = [
        'MIN_TEM', 'MAX_TEM', 'MIN_HUMI', 'MAX_HUMI', 'MIN_ILLUMI', 'MAX_ILLUMI',
        'BOU_NO2', 'DAN_NO2', 'BOU_CO2', 'DAN_CO2', 'BOU_NH3', 'DAN_NH3',
        'BOU_H2S', 'DAN_H2S', 'BOU_TOLUENE', 'DAN_TOLUENE'
    ]
    
    for field in required_fields:
        if field not in data:
            return jsonify({"error": f"필수 필드 {field}가 누락되었습니다."}), 400
    
    try:
        conn = get_db_connection()
        cursor = conn.cursor()
        
        # 해당 사용자의 설정이 있는지 확인
        cursor.execute("SELECT COUNT(*) FROM FARMSEYE_ENV_MINMAX WHERE USER_ID = %s", (user_id,))
        count = cursor.fetchone()[0]
        
        if count > 0:
            # 기존 설정 업데이트
            sql = """
                UPDATE FARMSEYE_ENV_MINMAX SET
                MIN_TEM = %s, MAX_TEM = %s, MIN_HUMI = %s, MAX_HUMI = %s,
                MIN_ILLUMI = %s, MAX_ILLUMI = %s, BOU_NO2 = %s, DAN_NO2 = %s,
                BOU_CO2 = %s, DAN_CO2 = %s, BOU_NH3 = %s, DAN_NH3 = %s,
                BOU_H2S = %s, DAN_H2S = %s, BOU_TOLUENE = %s, DAN_TOLUENE = %s
                WHERE USER_ID = %s
            """
            cursor.execute(sql, (
                data['MIN_TEM'], data['MAX_TEM'], data['MIN_HUMI'], data['MAX_HUMI'],
                data['MIN_ILLUMI'], data['MAX_ILLUMI'], data['BOU_NO2'], data['DAN_NO2'],
                data['BOU_CO2'], data['DAN_CO2'], data['BOU_NH3'], data['DAN_NH3'],
                data['BOU_H2S'], data['DAN_H2S'], data['BOU_TOLUENE'], data['DAN_TOLUENE'],
                user_id
            ))
        else:
            # 새 설정 생성
            sql = """
                INSERT INTO FARMSEYE_ENV_MINMAX (
                MIN_TEM, MAX_TEM, MIN_HUMI, MAX_HUMI, MIN_ILLUMI, MAX_ILLUMI,
                BOU_NO2, DAN_NO2, BOU_CO2, DAN_CO2, BOU_NH3, DAN_NH3,
                BOU_H2S, DAN_H2S, BOU_TOLUENE, DAN_TOLUENE, USER_ID
                ) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
            """
            cursor.execute(sql, (
                data['MIN_TEM'], data['MAX_TEM'], data['MIN_HUMI'], data['MAX_HUMI'],
                data['MIN_ILLUMI'], data['MAX_ILLUMI'], data['BOU_NO2'], data['DAN_NO2'],
                data['BOU_CO2'], data['DAN_CO2'], data['BOU_NH3'], data['DAN_NH3'],
                data['BOU_H2S'], data['DAN_H2S'], data['BOU_TOLUENE'], data['DAN_TOLUENE'],
                user_id
            ))
            
        conn.commit()
        return jsonify({"success": True, "message": "설정이 업데이트되었습니다."})
    except Exception as e:
        return jsonify({"error": str(e)}), 500
    finally:
        cursor.close()
        conn.close()
##########################################################################################


# 자동 제어 상태 확인 API
@app.route('/api/auto-control/status', methods=['GET'])
def get_auto_control_status():
    """자동 제어 상태 확인"""
    try:
        # 글로벌 자동 제어 컨트롤러 접근
        controller = auto_controller
        
        # 최신 센서 데이터 조회
        sensor_data = controller.get_latest_sensor_data()
        if not sensor_data:
            return jsonify({"error": "센서 데이터를 가져올 수 없습니다."}), 500
        
        # 설정값 조회
        user_id = request.args.get('user_id', 'user')
        settings = controller.get_minmax_settings(user_id)
        if not settings:
            return jsonify({"error": "설정값을 가져올 수 없습니다."}), 500
        
        # datetime 객체 처리
        for key, value in sensor_data.items():
            if isinstance(value, datetime):
                sensor_data[key] = value.strftime('%Y-%m-%d %H:%M:%S')
        
        for key, value in settings.items():
            if isinstance(value, datetime):
                settings[key] = value.strftime('%Y-%m-%d %H:%M:%S')
                
                
        return jsonify(settings)
    except Exception as e:
        return jsonify({"error": str(e)}), 500
        

##################################################################################

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
                print("Motion Detected !")
                time.sleep(0.5)

            elif GPIO.input(sensor) == 0:
                GPIO.output(led_G, 1)
                GPIO.output(led_Y, 0)
                print("Motion Out !")
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
        
        # 센서 초기화 테스트
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
        
        # GPIO 래퍼 초기화 (자동 제어용)
        gpio_wrapper = GPIOWrapper(GPIO, SERVO_PIN, LED_PIN)
        
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
        
        # 자동 제어 스레드 시작
        print("자동 제어 스레드 시작...")
        auto_controller = init_auto_controller(DB_CONFIG, gpio_wrapper)

        # Flask 실행
        print("Flask 서버 시작...")
        app.run(host='0.0.0.0', port=5000, debug=False)
        
    finally:
        try:
            auto_controller.stop()  # 자동 제어 스레드 정상 종료
        except:
            pass
        cleanup()