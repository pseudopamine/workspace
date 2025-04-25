#피코에서 측정한 생활환경 값(온도, 습도, 유해가스 량)을 서버로 전송하는 코드

# 0.필요한 라이브러리 호출
from machine import Pin, PWM, ADC, RTC, I2C, Timer
from pico_i2c_lcd import I2cLcd
import utime, sys, math
import network, socket, urequests

# 1.I2C 객체 생성
i2c = I2C(1, sda = Pin(14), scl = Pin(15))
adr = i2c.scan()[0]


# 2.RTC 객체 생성
rtc = RTC()
#rtc.datetime((2025, 01, 04, 00, 12, 00, 00, 00))

# 3.DHT22 객체 생성
dht = PicoDHT22(dataPin = Pin(16))

# 4-0.MQ135 객체 생성
mq = ADC(0)

# 4-1.상수 설정 (센서 데이터시트를 기반으로 설정)
RLOAD = 10.0        # 로드 저항(kΩ)
RZERO = 76.63       # 센서 교정 값(깨끗한 공기 기준)
PARA = 116.6020682  # NO2 공식의 상수 A
PARB = 2.769034857  # NO2 공식의 상수 B
CO2_FACTOR = 1.3    # CO2 보정 계수 (센서 보정 필요)

# 4-2.유해 가스량 측정 함수
def hazadous_gas():
    volt = (mq.read_u16() / 65535) * 3.3    # MQ135센서에서 출력되는 전압측정
    res = (3.3 - volt) * RLOAD / volt       # 전압을 저항으로 변환
    ratio = res / RZERO                     # Rs/Ro의 Ratio 산출
    NO2 = PARA * math.pow(ratio, -PARB)     # NO2 농도 산출
    CO2 = ratio * CO2_FACTOR * 400          # CO2 농도 산출
    print("NO2:{} / CO2:{}".format(NO2, CO2))
    airq = NO2 + CO2                        # 유해가스 총량
    return airq
                

# 5.Wifi 연결함수
# Wi-Fi 연결 정보
# 5-1.피코의 Wifi 접속 함수
SSID = 'Your SSID'
PASSWORD = 'Your PASSWORD'
def connect_wifi():
    wlan = network.WLAN(network.STA_IF)
    wlan.active(True)
    wlan.connect(SSID, PASSWORD)
    while not wlan.isconnected():
        utime.sleep_ms(1000)
    print('Connected to Wi-Fi')
    print('IP Address:', wlan.ifconfig()[0])
    
# 5-2.피코에서 PC서버로 데이터를 전송하는 함수
def send_data_to_pc(temperature, humidity, gas):
    url = 'http://125.128.80.115:5000/data'  # PC 서버의 IP와 포트로 수정
    data = {'temperature': temperature, 'humidity': humidity, "gas":gas}
    try:
        response = urequests.post(url, json=data)
        print('Data sent to PC:', response.text)
    except Exception as e:
        print('Failed to send data:', e)

# 6.전체 시스템 작동
now = []                                    # 현재 시각 저장을 위한 리스트
connect_wifi()
try:
    while True:
        # 날짜와 시각 읽어 오기
        dt = rtc.datetime()
        # 1자리 시각을 2자리 시각으로 변경
        for i in range(4, 7):
            value = str(dt[i])
            if len(value) < 2:
                value = "0"+value
                now.append(value)
            else:
                now.append(value)
        ctime = "{}:{}:{}".format(now[0], now[1], now[2])
        
        # DHT22센서로 온도와 습도 읽어 오기
        temp, humi = dht.read()
        
        # MQ135센서로 유해가스량 읽어 오기
        airq = hazadous_gas()
        
        # 센서로 읽어 정보를 LCD에 출력하기
        lcd.move_to(1, 0)
        lcd.putstr("Time > {}".format(ctime))
        lcd.move_to(1, 1)
        lcd.putstr("Temp > {:>5.1f}'C".format(temp))
        lcd.move_to(1, 2)
        lcd.putstr("Humi > {:>5.1f} %".format(humi))
        lcd.move_to(1, 3)
        lcd.putstr("Gasc > {:>5.1f} ppm".format(airq))
        # 피코의 데이터를 PC서버로 전송하기      
        send_data_to_pc(temp, humi, airq)
        # now 리스트 초기화 (빈 리스트로 만들기)
        now = []
        utime.sleep_ms(5000)
        
except KeyboardInterrupt:
    print("Error was raised by system or part")
    for i in range(10, -1):
        print("Wait for {} second!".format(i))
        utime.sleep_ms(1000)
    print("The system will be rebooted!")
    reset()



#########################################################################

#서버에서 피코의 발송 데이터를 받아서 저장하는 코드

from flask import Flask, request, jsonify
import pandas as pd

app = Flask(__name__)
temp, humi, gas = [], [], []
@app.route('/data', methods=['POST'])
def receive_data():
    try:
        data = request.get_json()
        t= data.get("temperature")
        temp.append(t)
        h = data.get("humidity")
        humi.append(h)
        g = data.get("gas")
        gas.append(g)
        df = pd.DataFrame({"temp":temp, "humi":humi, "gas":gas})
        df.to_csv(r"G:\RPi_Pico\real_time_monitoring/measured.csv")
        
        print("온도: {} °C, 습도: {} %, 유해가스: {}ppm".format(t, h, g))
        return jsonify({"message": "데이터 수신 완료"}), 200
    except Exception as e:
        print("오류:", e)
        return jsonify({"message": "데이터 처리 실패"}), 400

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)