import time
import pymysql
import Adafruit_BMP.BMP085 as BMP085

# BMP180 센서 초기화
sensor = BMP085.BMP085()

# 데이터베이스 연결 설정
db_config = {
    "host": "192.168.30.97",        # 예: "localhost"
    "user": "team2_user",    # MariaDB 사용자명
    "password": "mariadb", # 비밀번호
    "database": "team2", # 사용할 데이터베이스
    "charset": "utf8mb4"
}

#온도 데이터를 MariaDB에 INSERT
def insert_temperature(temp):
    
    try:
        conn = pymysql.connect(**db_config)
        cursor = conn.cursor()

        sql = "INSERT INTO temperature_log (temperature, timestamp) VALUES (%s, NOW())"
        cursor.execute(sql, (temp,))
        conn.commit()

        print(f"데이터 삽입 완료: 온도={temp:.2f}°C")
    
    except pymysql.MySQLError as e:
        print(f"DB 오류 발생: {e}")

    finally:
        cursor.close()
        conn.close()

# 메인 실행 루프
try:
    while True:
        temperature = sensor.read_temperature()  # 온도 읽기 (°C)
        print(f"현재 온도: {temperature:.2f}°C")
        
        insert_temperature(temperature)  # DB 저장

        time.sleep(30)  # 30초마다 측정

except KeyboardInterrupt:
    print("프로그램 종료")