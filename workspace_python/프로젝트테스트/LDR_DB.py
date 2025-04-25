import time
import spidev
import pymysql
from datetime import datetime


ip_list = ["192.168.30.97", "192.168.30.150", "192.168.30.147", "192.168.30.111"]

delay = 10
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


#현재 조도 데이터 측정
def getNowIllumi():

  global timestamp 
  global ldr_value 

  ldr_value = readadc(ldr_channel)
  timestamp = datetime.now()

  print("----------------------------------")
  print("LDR Value : %d" % ldr_value)
  print(f'timestamp = {timestamp}')


##################################################
def inserData():

  for ip in ip_list:
    conn = pymysql.connect(
      host = ip
      , user = "code17_user"
      , password = "mariadb"
      , database = "code17"
      , charset = "utf8mb4"
    )
    
    cursor = conn.cursor()

    sql = "INSERT INTO FARMSEYE_ENV (ILLUMI, TIMESTAMP) VALUES (%s, %s)"
    try:
        
      cursor.execute(sql, (ldr_value, timestamp))
      conn.commit() 
      print("COMPLETE ADD DATA")

    except pymysql.MySQLError as e:
        print(f"DB ERROR: {e}")

    finally:
        cursor.close()
####################################################################

def main():
  while True:
    getNowIllumi()
    inserData()
    time.sleep(delay)
    print("add data 10sec")

main()
