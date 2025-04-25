import math
import time
import board
import adafruit_dht
import spidev
import pymysql
import pandas as pd
import numpy as np
from datetime import datetime

# DB 정보
ip_list = ["192.168.30.97", "192.168.30.150", "192.168.30.147", "192.168.30.111"]

# SPI 및 센서 설정
mq_channel = 2
ldr_channel = 0
sensor = adafruit_dht.DHT22(board.D2)

spi = spidev.SpiDev()
spi.open(0, 0)
spi.max_speed_hz = 100000

# 센서 데이터 읽기
def readadc(adcnum):
    if adcnum > 7 or adcnum < 0:
        return -1
    r = spi.xfer2([1, (8 + adcnum) << 4, 0])
    data = ((r[1] & 3) << 8) + r[2]
    return data

# 유해가스 데이터 측정
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

# 현재 데이터 수집
def getNowData():
    NO2, CO2, NH3, H2S, Toluene = hazardous_gas()
    temperature = sensor.temperature
    humidity = sensor.humidity
    ldr_value = readadc(ldr_channel)
    timestamp = datetime.now()

    return {
        "temperature": temperature,
        "humidity": humidity,
        "ldr_value": ldr_value,
        "NO2": NO2,
        "CO2": CO2,
        "NH3" : NH3,
        "H2S" : H2S,
        "Toluene" : Toluene,
        "timestamp": timestamp
    }

# 데이터 저장을 위한 리스트
data_list = []

# 이상값 검출 및 제거 (IQR 방식)
def remove_outliers(df):
    Q1 = df.quantile(0.25)
    Q3 = df.quantile(0.75)
    IQR = Q3 - Q1
    lower_bound = Q1 - 1.5 * IQR
    upper_bound = Q3 + 1.5 * IQR
    return df[~((df < lower_bound) | (df > upper_bound)).any(axis=1)]

# 데이터 DB 저장
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
                cursor.execute(sql, (row["temperature"], row["humidity"], row["NO2"], row["CO2"],
                                    row["NH3"], row["H2S"], row["Toluene"], row["ldr_value"], row["timestamp"]))

            conn.commit()
            print(f"COMPLETE ADD DATA into {ip}")

        except pymysql.MySQLError as e:
            print(f"DB ERROR on {ip}: {e}")

        finally:
            cursor.close()
            conn.close()

# 메인 루프
def main():
    global data_list

    while True:
        try:
            # 데이터 수집
            new_data = getNowData()
            data_list.append(new_data)
            print("Added new data point:", new_data)

            # 10개 이상 데이터가 모이면 가공 후 DB 저장
            if len(data_list) >= 10:
                df = pd.DataFrame(data_list)
                print("Raw Data:\n", df)

                # 이상값 제거
                filtered_df = remove_outliers(df)
                print("Filtered Data:\n", filtered_df)

                # DB 저장
                if not filtered_df.empty:
                    insertData(filtered_df)

                # 데이터 초기화
                data_list = []

            time.sleep(10)  # 10초 간격으로 데이터 수집

        except Exception as e:
            print("Error:", e)
            time.sleep(10)

if __name__ == "__main__":
    main()





########################################################################

import math
import time
import board
import adafruit_dht
import spidev
import pymysql
import pandas as pd
import numpy as np
from datetime import datetime

# DB 연결 정보
ip_list = ["192.168.30.97", "192.168.30.150", "192.168.30.147", "192.168.30.111"]

# 센서 설정
mq_channel = 2
ldr_channel = 0
sensor = adafruit_dht.DHT22(board.D2)

# SPI 설정
spi = spidev.SpiDev()
spi.open(0, 0)
spi.max_speed_hz = 100000

# 데이터 버퍼 (10개 모으면 DB 저장)
data_buffer = []

# ADC 값 읽기
def readadc(adcnum):
    if adcnum > 7 or adcnum < 0:
        return -1
    r = spi.xfer2([1, (8 + adcnum) << 4, 0])
    data = ((r[1] & 3) << 8) + r[2]
    return data

# 가스 센서 계수 설정
RLOAD = 10.0       
RZERO = 76.63      
NO2_PARA = 0.4220682  
NO2_PARB = 2.769034857  
NH3_PARA = 1.8028257   
NH3_PARB = -1.3523192  
H2S_PARA = 0.0216549   
H2S_PARB = -1.5547654  
TOLUENE_PARA = 2.82  
TOLUENE_PARB = -1.56  
CO2_FACTOR = 1.3   

# 유해가스 측정 함수
def hazardous_gas():
    mq_value = readadc(mq_channel)
    volt = (mq_value / 1023.0) * 3.3       
    res = (3.3 - volt) * RLOAD / volt      
    ratio = res / RZERO                    

    NO2 = NO2_PARA * math.pow(ratio, -NO2_PARB)     
    CO2 = ratio * CO2_FACTOR * 400          
    NH3 = NH3_PARA * math.pow(ratio, NH3_PARB)  
    H2S = H2S_PARA * math.pow(ratio, H2S_PARB)  
    Toluene = TOLUENE_PARA * math.pow(ratio, TOLUENE_PARB)  

    return NO2, CO2, NH3, H2S, Toluene

# 센서 데이터 수집
def getNowData():
    NO2, CO2, NH3, H2S, Toluene = hazardous_gas()

    temperature = sensor.temperature
    humidity = sensor.humidity
    ldr_value = readadc(ldr_channel)
    timestamp = datetime.now()

    sensor_data = {
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

    return sensor_data

# 데이터 가공 (이상값 제거 및 결측값 처리)
def process_data(data_list):
    df = pd.DataFrame(data_list)

    # 이상값(Outlier) 제거 (IQR 방법 사용)
    Q1 = df.quantile(0.25)
    Q3 = df.quantile(0.75)
    IQR = Q3 - Q1
    df = df[~((df < (Q1 - 1.5 * IQR)) | (df > (Q3 + 1.5 * IQR))).any(axis=1)]

    # 결측값(NaN) 처리 → 평균값으로 대체
    df.fillna(df.mean(), inplace=True)

    return df

# 데이터베이스 저장 함수
def insertDataToDB(df):
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

            for _, row in df.iterrows():
                cursor.execute(sql, (row["temperature"], row["humidity"], row["NO2"], row["CO2"],
                                    row["NH3"], row["H2S"], row["Toluene"], row["ldr_value"], row["timestamp"]))

            conn.commit()
            print(f" 데이터 저장 완료: {ip}")

        except pymysql.MySQLError as e:
            print(f"DB ERROR on {ip}: {e}")

        finally:
            cursor.close()
            conn.close()

# 메인 실행 함수
def main():
    global data_buffer

    while True:
        try:
            # 센서 데이터 수집
            sensor_data = getNowData()
            data_buffer.append(sensor_data)
            print(f"수집된 데이터: {sensor_data}")

            # 10개 데이터가 모이면 가공 후 DB 저장
            if len(data_buffer) >= 10:
                processed_df = process_data(data_buffer)
                insertDataToDB(processed_df)
                data_buffer.clear()  # 데이터 버퍼 초기화

            time.sleep(10)

        except Exception as e:
            print(f" ERROR: {e}")

if __name__ == "__main__":
    main()
