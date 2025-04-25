import time
import spidev
import Adafruit_BMP.BMP085 as BMP085
import pymysql
from datetime import datetime


ip_list = ["192.168.30.97", "192.168.30.150", "192.168.30.147", "192.168.30.111"]


sensor = BMP085.BMP085(busnum=1)

temp = 0
pressure = 0
altitude = 0
timestamp = 0

ldr_channel = 0

spi = spidev.SpiDev()
spi.open(0, 0)
spi.max_speed_hz = 100000

def readadc(adcnum) : 
  if adcnum > 7 or adcnum < 0 :
    return -1
  r = spi.xfer2([1, 8 + adcnum << 4, 0])
  data = ((r[1] & 3 ) << 8) + r[2]
  return data



#현재 센서 데이터 측정
def getNowData():
  global temp
  global pressure
  global altitude
  global ldr_value 
  global timestamp 

  temp = sensor.read_temperature()  
  pressure = sensor.read_pressure()  
  altitude = sensor.read_altitude()
  ldr_value = readadc(ldr_channel)
  timestamp = datetime.now()

  print("----------------------------------")
  print(f'Temp = {temp:.2f} °C')
  print(f'Pressure = {pressure:.2f} Pa')
  print(f'Altitude = {altitude:.2f} m')
  print("LDR Value : %d" % ldr_value)
  print(f'timestamp = {timestamp}')


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

    sql = "INSERT INTO FARMSEYE_ENV (TEMP, HUMI, CO2, ILLUMI, TIMESTAMP) VALUES (%s, %s, %s, %s, %s)"
    try:
        
      cursor.execute(sql, (temp, pressure, altitude, ldr_value, timestamp))
      conn.commit() 
      print("COMPLETE ADD DATA")

    except pymysql.MySQLError as e:
        print(f"DB ERROR: {e}")

    finally:
        cursor.close()
####################################################################

def main():
  while True:
    getNowData()
    insertData()
    time.sleep(10)
    print("add data 10sec")

main()
