
import math
import time
import board
import adafruit_dht
import spidev
import pymysql
from datetime import datetime

ip_list = ["192.168.30.97", "192.168.30.147", "192.168.30.111"]

mq_channel = 2

ldr_channel = 0

sensor = adafruit_dht.DHT22(board.D2)

temperature = 0
humidity = 0
NO2 = 0
CO2 = 0
airq = 0
timestamp = 0

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
    volt = (mq_value / 1023.0) * 3.3        # ADC 값(0~1023)을 전압(0~3.3V)으로 변환
    res = (3.3 - volt) * RLOAD / volt       # 전압을 저항으로 변환
    ratio = res / RZERO                     # Rs/Ro의 Ratio 산출
    NO2 = NO2_PARA * math.pow(ratio, -NO2_PARB)     # NO2 농도 산출
    CO2 = ratio * CO2_FACTOR * 400          # CO2 농도 산출
    NH3 = NH3_PARA * math.pow(ratio, NH3_PARB)  # NH3 농도 (ppm)
    H2S = H2S_PARA * math.pow(ratio, H2S_PARB)  # H2S 농도 (ppm)
    Toluene = TOLUENE_PARA * math.pow(ratio, TOLUENE_PARB)  # 톨루엔 농도 (ppm)
    return NO2, CO2, NH3, H2S, Toluene

# 현재 센서 데이터 측정
def getNowData():
  global temperature
  global humidity
  global ldr_value 
  global NO2
  global CO2
  global NH3
  global H2S
  global Toluene
  global timestamp

  NO2, CO2, NH3, H2S, Toluene = hazardous_gas()

  temperature = sensor.temperature
  humidity = sensor.humidity 
  ldr_value = readadc(ldr_channel)
  timestamp = datetime.now()

  print("----------------------------------")
  print(f'Temp = {temperature:.2f} °C')
  print(f'humidity = {humidity:.2f} %')
  print("LDR Value : %d" % ldr_value)
  print("NO2: {:.2f} ppm, CO2: {:.2f} ppm, ".format(NO2, CO2))
  print("NH3: {:.3f} ppm, H2S: {:.3f} ppm, Toluene: {:.2f} ppm".format(NH3, H2S, Toluene))
  print(f'Timestamp: {timestamp}')


##################################################
def insertData():

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

            sql = "INSERT INTO FARMSEYE_ENV (TEMP, HUMI, NO2, CO2, NH3, H2S, TOLUENE, ILLUMI, TIMESTAMP) VALUES (%s, %s, %s, %s, %s, %s, %s)"
            cursor.execute(sql, (temperature, humidity, NO2, CO2, NH3, H2S, Toluene, ldr_value, timestamp))
            conn.commit()
            print(f"COMPLETE ADD DATA into {ip}")

        except pymysql.MySQLError as e:
            print(f"DB ERROR on {ip}: {e}")

        finally:
            cursor.close()
##################################################

def main():
    while True:
        try:
          getNowData()
          insertData()
          print("Added data, waiting 10 sec")
        except Exception as e :
            print("error : ", e)
        time.sleep(10)

main()