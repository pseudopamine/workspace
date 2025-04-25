import time
import pymysql
import Adafruit_BMP.BMP085 as BMP085

# MariaDB 연결 설정
conn = pymysql.connect(
    host="192.168.30.97",
    user="team2_user",
    password="mariadb",
    database="team2",
    charset="utf8mb4"
)
cursor = conn.cursor()

# BMP180 센서 초기화
sensor = BMP085.BMP085(busnum=1)

# 센서 데이터 읽기
temp = sensor.read_temperature()  # 온도
pressure = sensor.read_pressure()  # 압력
altitude = sensor.read_altitude()  # 고도

# 측정값 출력
print(f'Temp = {temp:.2f} °C')
print(f'Pressure = {pressure:.2f} Pa')
print(f'Altitude = {altitude:.2f} m')


# 데이터베이스에 삽입
sql = "INSERT INTO ENV (TEMP) VALUES (%s)"
try:
    while True : 
        cursor.execute(sql, (temp, pressure, altitude))
        conn.commit()  # 변경사항 저장
        print("COMPLETE ADD DATA")

        time.sleep(10)

except pymysql.MySQLError as e:
    print(f"DB ERROR: {e}")

finally:
    cursor.close()