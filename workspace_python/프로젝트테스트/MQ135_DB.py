import sys
import math
import time
import spidev
import pymysql
from datetime import datetime

ip_list = ["192.168.30.97", "192.168.30.150", "192.168.30.147", "192.168.30.111"]

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
PARA = 116.6020682  
PARB = 2.769034857  
CO2_FACTOR = 1.3   

def hazardous_gas():
    mq_value = readadc(mq_channel)
    if mq_value == -1:
        print("Error reading ADC")
        return None
    
    volt = (mq_value / 1023.0) * 3.3  # ADC 값(0~1023)을 전압(0~3.3V)으로 변환
    if volt <= 0:
        return None
    
    res = (3.3 - volt) * RLOAD / volt  # 센서 저항값 계산
    ratio = res / RZERO  # 센서 저항 비율
    NO2 = PARA * math.pow(ratio, -PARB)  # NO2 농도 계산
    CO2 = ratio * CO2_FACTOR * 400  # CO2 농도 추정
    
    return NO2, CO2


#현재 공기질 데이터 측정
def getNowAirq():

    gas_values = hazardous_gas()
    if gas_values:
        NO2, CO2 = gas_values
        air_quality = NO2 + CO2
        timestamp = datetime.now()
        
        print("----------------------------------")
        print("NO2: {:.2f} ppm, CO2: {:.2f} ppm".format(NO2, CO2))
        print("Air Quality Index: {:.2f}".format(air_quality))
        print(f'Timestamp: {timestamp}')
    


##################################################
def insertData():

  for ip in ip_list:
    conn = pymysql.connect(
      host = ip
      , user = "code17_user"
      , password = "mariadb"
      , database = "code17"
      , charset = "utf8mb4"
    )
    
    cursor = conn.cursor()

    global air_quality
    global timestamp

    sql = "INSERT INTO FARMSEYE_ENV (ILLUMI, TIMESTAMP) VALUES (%s, %s)"
    try:
        
      cursor.execute(sql, (air_quality, timestamp))
      conn.commit() 
      print("COMPLETE ADD DATA")

    except pymysql.MySQLError as e:
        print(f"DB ERROR: {e}")

    finally:
        cursor.close()
####################################################################

def main():
  while True:
    getNowAirq()
    insertData()
    time.sleep(10)
    print("add data 10sec")

main()





#####################################################################
#####################################################################

