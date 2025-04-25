import time
import pymysql
import Adafruit_BMP.BMP085 as BMP085

ip_list = ["192.168.30.97", "192.168.30.150", "192.168.30.147", "192.168.30.111"]

sensor = BMP085.BMP085(busnum=1)

temp = sensor.read_temperature()  
pressure = sensor.read_pressure()  
altitude = sensor.read_altitude()  

print(f'Temp = {temp:.2f} *C')
print(f'Pressure = {pressure:.2f} Pa')
print(f'Altitude = {altitude:.2f} m')

sql = "INSERT INTO FARMSEYE_ENV (TEMP, HUMI, CO2) VALUES (%s, %s, %s)"

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
        
        cursor.execute(sql, (temp, pressure, altitude))
        conn.commit()  
        
        print(f"Data inserted successfully for IP {ip}")
    
    except pymysql.MySQLError as e:
        print(f"DB ERROR for IP {ip}: {e}")
    
    finally:
        cursor.close()
        conn.close()

    time.sleep(10)
