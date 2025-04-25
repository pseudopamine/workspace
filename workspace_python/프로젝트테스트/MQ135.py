import sys
import math
import time
import spidev
from datetime import datetime

mq_channel = 2

spi = spidev.SpiDev()
spi.open(0, 0)
spi.max_speed_hz = 100000

# ADC에서 값을 읽는 함수
def readadc(adcnum):
    if adcnum > 7 or adcnum < 0:
        return -1
    r = spi.xfer2([1, (8 + adcnum) << 4, 0])
    data = ((r[1] & 3) << 8) + r[2]
    return data

RLOAD = 10.0       
RZERO = 76.63      
NO2_PARA = 0.4220682  
NO2_PARB = 2.769034857  
NH3_PARA = 1.8028257   # NH3 센서 계수 (예제 값)
NH3_PARB = -1.3523192  # NH3 센서 계수 (예제 값)
H2S_PARA = 0.0216549   # H2S 센서 계수 (예제 값)
H2S_PARB = -1.5547654  # H2S 센서 계수 (예제 값)
TOLUENE_PARA = 2.82  # 톨루엔 센서 계수 
TOLUENE_PARB = -1.56  # 톨루엔 센서 계수 

CO2_FACTOR = 1.3   

def hazardous_gas():
    mq_value = readadc(mq_channel)
    volt = (mq_value / 1023.0) * 3.3  # ADC 값(0~1023)을 전압(0~3.3V)으로 변환
    
    res = (3.3 - volt) * RLOAD / volt  # 센서 저항값 계산
    ratio = res / RZERO  # 센서 저항 비율
    NO2 = NO2_PARA * math.pow(ratio, -NO2_PARB)  # NO2 농도 계산
    CO2 = ratio * CO2_FACTOR * 400  # CO2 농도 추정
    NH3 = NH3_PARA * math.pow(ratio, NH3_PARB)  # NH3 농도 (ppm)
    H2S = H2S_PARA * math.pow(ratio, H2S_PARB)  # H2S 농도 (ppm)
    Toluene = TOLUENE_PARA * math.pow(ratio, TOLUENE_PARB)  # 톨루엔 농도 (ppm)
    return NO2, CO2, NH3, H2S, Toluene

try:
    while True:
        gas_values = hazardous_gas()
        if gas_values:
            NO2, CO2, NH3, H2S, Toluene = gas_values
            air_quality = NO2 + CO2
            timestamp = datetime.now()
            
            print("----------------------------------")
            print("NO2: {:.2f} ppm, CO2: {:.2f} ppm, NH3: {:.3f} ppm, H2S: {:.3f} ppm, Toluene: {:.2f} ppm".format(NO2, CO2, NH3, H2S, Toluene))
            print("Air Quality Index: {:.2f}".format(air_quality))
            print(f'Timestamp: {timestamp}')
        
        time.sleep(10)
        
except KeyboardInterrupt:
    print("The system will be terminated!")
    sys.exit()
