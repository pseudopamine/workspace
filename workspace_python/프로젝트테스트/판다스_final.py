import math
import time
import board
import adafruit_dht
import spidev
import pymysql
import pandas as pd
import numpy as np
from datetime import datetime

# DB 정보 (연결할 IP 리스트)
ip_list = ["192.168.30.97", "192.168.30.147", "192.168.30.111"]

# SPI 및 센서 설정
mq_channel = 2  # 유해가스 센서 채널
ldr_channel = 0  # 조도 센서 채널
sensor = adafruit_dht.DHT22(board.D2)  # 온습도 센서

# SPI 인터페이스 설정
spi = spidev.SpiDev()
spi.open(0, 0)
spi.max_speed_hz = 100000

# 이전 측정된 가스 데이터 (중복 확인용)
prev_gas_data = None

# ADC 데이터 읽기 함수
def readadc(adcnum):
    if adcnum > 7 or adcnum < 0:
        return -1
    r = spi.xfer2([1, (8 + adcnum) << 4, 0])
    data = ((r[1] & 3) << 8) + r[2]
    return data

# 유해가스 데이터 측정 함수
def hazardous_gas():
    mq_value = readadc(mq_channel)  # ADC 값 읽기
    volt = (mq_value / 1023.0) * 3.3  # 전압 변환
    res = (3.3 - volt) * 10.0 / volt  # 센서 저항값 계산
    ratio = res / 76.63  # 센서 저항 비율

    # 각 가스별 농도 계산
    NO2 = 0.4220682 * math.pow(ratio, -2.769034857)
    CO2 = ratio * 1.3 * 400
    NH3 = 1.8028257 * math.pow(ratio, -1.3523192)
    H2S = 0.0216549 * math.pow(ratio, -1.5547654)
    Toluene = 2.82 * math.pow(ratio, -1.56)
    
    return round(NO2, 3), round(CO2, 3), round(NH3, 3), round(H2S, 3), round(Toluene, 3)

# 현재 데이터 수집 함수
def getNowData():
    NO2, CO2, NH3, H2S, Toluene = hazardous_gas()
    temperature = round(sensor.temperature, 3)
    humidity = round(sensor.humidity, 3)
    ldr_value = readadc(ldr_channel)
    timestamp = datetime.now()
    
    return {
        "temperature": temperature,
        "humidity": humidity,
        "ldr_value": ldr_value,
        "NO2": NO2,
        "CO2": CO2,
        "NH3": NH3,
        "H2S": H2S,
        "Toluene": Toluene,
        "timestamp": timestamp
    }

# 데이터 저장 리스트
data_list = []

# 이상값 처리 함수 (이전 및 이후 데이터 비교하여 이상값 제거)
def clean_anomalies(df, threshold={'temperature': 5, 'humidity': 10}):
    for col, th in threshold.items():
        df[col] = df[col].astype(float)
        df['prev_' + col] = df[col].shift(1)
        df['next_' + col] = df[col].shift(-1)
        
        # 이상값 감지 (이전 값, 다음 값과 비교)
        anomalies = (abs(df[col] - df['prev_' + col]) > th) & (abs(df[col] - df['next_' + col]) > th)
        df.loc[anomalies, col] = (df['prev_' + col] + df['next_' + col]) / 2  # 보간 처리
    
    return df.drop(columns=['prev_temperature', 'next_temperature', 'prev_humidity', 'next_humidity'])

# 데이터 DB 저장 함수
def insertData(filtered_df):
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

            sql = """INSERT INTO FARMSEYE_ENV 
                    (TEMP, HUMI, NO2, CO2, NH3, H2S, TOLUENE, ILLUMI, TIMESTAMP) 
                    VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s)"""

            for _, row in filtered_df.iterrows():
                cursor.execute(sql, (
                    row["temperature"], row["humidity"], row["NO2"],
                    row["CO2"], row["NH3"], row["H2S"], row["Toluene"],
                    row["ldr_value"], row["timestamp"]
                ))

            conn.commit()
            print(f"COMPLETE ADD DATA into {ip}")

        except pymysql.MySQLError as e:
            print(f"DB ERROR on {ip}: {e}")
        
        finally:
            cursor.close()
            conn.close()

# 메인 루프
def main():
    global data_list, prev_gas_data

    while True:
        try:
            new_data = getNowData()
            gas_data = (new_data["NO2"], new_data["CO2"], new_data["NH3"], new_data["H2S"], new_data["Toluene"])
            
            # 이전 가스 데이터와 동일하면 저장하지 않음
            if prev_gas_data == gas_data:
                print("Duplicate gas data detected, skipping entry...")
                time.sleep(10)
                continue
            
            prev_gas_data = gas_data  # 현재 데이터를 이전 데이터로 저장
            data_list.append(new_data)
            print("Added new data point:", new_data)

            # 10개 이상 데이터가 모이면 가공 후 DB 저장
            if len(data_list) >= 10:
                df = pd.DataFrame(data_list)
                print("Raw Data:", df)

                # 이상값 제거
                filtered_df = clean_anomalies(df)
                print("Filtered Data:", filtered_df)

                # DB 저장 (비어 있지 않은 경우만)
                if not filtered_df.empty:
                    insertData(filtered_df)

                # 데이터 초기화
                data_list = []

            time.sleep(10)
        
        except Exception as e:
            print("Error:", e)
            time.sleep(10)

if __name__ == "__main__":
    main()
