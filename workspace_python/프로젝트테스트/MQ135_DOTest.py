#  MQ135와 피코 연결하기
#     1) MQ135에는 4개의 핀이 있습니다. AO, DO, GND, VCC입니다.
#     2) AO는 Analog Data OUT의 약자이고, DO는 Digital Data OUT입니다. 우리는 실시간으로 측정되는 
#         유해 가스량을 측정할 것이므로, AO핀을 사용합니다. AO핀을 GP 26번 (ADC 0번 채널)에 연결합니다.
#     3) GND는 피코의 GND, VCC는 피코의 5V에 각각 연결합니다.
#  생활환경(온도, 습도, 유해 가스량) 실시간 측정하는 코드
#  오늘은 처음 접하는 센서인 MQ135를 이용하여 유해가스량을 측정하는 코드를 살펴봅니다.
#     1) MQ135의 출력은 Analog Data입니다. 그러므로, ADC핀을 통해서 피코로 값을 읽어 옵니다.
#         > MQ135객체 생성 : mq = ADC(0) 
#     2) MQ135에서 피코로 입력되는 값은 16진수의 전압값입니다. 이것을 저항으로 환산합니다.
#        > volt = (mq.read_u16() / 65535) * 3.3
#        > res = (3.3 - volt) * RLOAD / volt
#     3) Rs / Ro의 비를 구합니다.
#       > ratio = res / RZERO (Rs : MQ135의 전압을 저항으로 전환한 값 / Ro = Rzero)
#     4) 유해가스 농도를 산출합니다.
#         ① 이산화질소 : NO2 = PARA * math.pow(ratio, -PARB)
#         ② 이산화탄소 : CO2 = ratio * CO2_FACTOR * 400
#     5) NO2농도와 CO2농도를 합산하여 총 유해가스량으로 나타냅니다.


# 0.필요한 라이브러리 호출
from machine import Pin, PWM, ADC, RTC, I2C
import sys, math
import time
import spidev
from datetime import datetime


# 4-0.MQ135 객체 생성
mq_channel = 0

spi = spidev.SpiDev()
spi.open(0, 0)
spi.max_speed_hz = 100000

def readadc(adcnum) : 
    if adcnum > 7 or adcnum < 0 :
        return -1
    r = spi.xfer2([1, 8 + adcnum << 4, 0])
    data = ((r[1] & 3 ) << 8) + r[2]
    return data

# 4-1.상수 설정 (센서 데이터시트를 기반으로 설정)
RLOAD = 10.0        # 로드 저항(kΩ)
RZERO = 76.63       # 센서 교정 값(깨끗한 공기 기준)
PARA = 116.6020682  # NO2 공식의 상수 A
PARB = 2.769034857  # NO2 공식의 상수 B
CO2_FACTOR = 1.3    # CO2 보정 계수 (센서 보정 필요)

# 4-2.유해 가스량 측정 함수
def hazadous_gas():
    volt = (mq_channel.read_u16() / 65535) * 3.3    # MQ135센서에서 출력되는 전압측정
    res = (3.3 - volt) * RLOAD / volt       # 전압을 저항으로 변환
    ratio = res / RZERO                     # Rs/Ro의 Ratio 산출
    NO2 = PARA * math.pow(ratio, -PARB)     # NO2 농도 산출
    CO2 = ratio * CO2_FACTOR * 400          # CO2 농도 산출
    print("NO2:{} / CO2:{}".format(NO2, CO2))
    airq = NO2 + CO2                        # 유해가스 총량
    return airq

# 5.전체 시스템 작동

try:
    while True:

        mq_value = readadc(mq_channel)
        # DHT22센서로 온도와 습도 읽어 오기
        # temp, humi = dht.read()
        
        # MQ135센서로 유해가스량 읽어 오기
        airq = hazadous_gas()
        
        # 센서로 읽어 정보를 LCD에 출력하기
        # lcd.move_to(1, 0)
        # lcd.putstr("Time > {}".format(ctime))
        # lcd.move_to(1, 1)
        # lcd.putstr("Temp > {:>5.1f}'C".format(temp))
        # lcd.move_to(1, 2)
        # lcd.putstr("Humi > {:>5.1f} %".format(humi))
        # lcd.move_to(1, 3)
        # lcd.putstr("Gasc > {:>5.1f} ppm".format(airq))
        
        timestamp = datetime.now()
        print("----------------------------------")
        print("Gasc > {:>5.1f} ppm".format(airq))
        print(f'timestamp = {timestamp}')
        time.sleep(10)
        
except KeyboardInterrupt:
    print("The system will be terminated!")
    sys.exit()
