import time
import pymysql
import Adafruit_BMP.BMP085 as BMP085
from datetime import datetime


ip_list = ["192.168.30.97", "192.168.30.150", "192.168.30.147", "192.168.30.111"]

sensor = BMP085.BMP085(busnum=1)

temp = 0
pressure = 0
altitude = 0
timestamp = 0


#현재 온도 데이터 측정
def getNowTemp():
  global temp
  global pressure
  global altitude
  global timestamp 

  temp = sensor.read_temperature()  
  pressure = sensor.read_pressure()  
  altitude = sensor.read_altitude()
  timestamp = datetime.now() 

  print(f'Temp = {temp:.2f} °C')
  print(f'Pressure = {pressure:.2f} Pa')
  print(f'Altitude = {altitude:.2f} m')
  print(f'timestamp = {timestamp}')


##################################################
def bmp180Data():

  for ip in ip_list:
    conn = pymysql.connect(
      host = ip
      , user = "code17_user"
      , password = "mariadb"
      , database = "code17"
      , charset = "utf8mb4"
    )
    
    cursor = conn.cursor()

    sql = "INSERT INTO FARMSEYE_ENV (TEMP, HUMI, CO2, TIMESTAMP) VALUES (%s, %s, %s, %s)"
    try:
        
      cursor.execute(sql, (temp, pressure, altitude, timestamp))
      conn.commit() 
      print("COMPLETE ADD DATA")

    except pymysql.MySQLError as e:
        print(f"DB ERROR: {e}")

    finally:
        cursor.close()
####################################################################

def main():
  while True:
    getNowTemp()
    bmp180Data()
    time.sleep(10)
    print("add data 10sec")

main()
