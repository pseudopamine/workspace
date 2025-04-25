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
def hazardous_gas():
    mq_value = readadc(mq_channel)
    volt = (mq_value / 1023.0) * 3.3
    res = (3.3 - volt) * 10.0 / volt
    ratio = res / 76.63
    NO2 = 0.4220682 * math.pow(ratio, -2.769034857)
    CO2 = ratio * 1.3 * 400
    NH3 = 1.8028257 * math.pow(ratio, -1.3523192)
    H2S = 0.0216549 * math.pow(ratio, -1.5547654)
    Toluene = 2.82 * math.pow(ratio, -1.56)
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
        "NH3": NH3,
        "H2S": H2S,
        "Toluene": Toluene,
        "timestamp": timestamp
    }

# 데이터 저장 리스트
data_list = []

# 이상값 처리 함수 (연속 데이터 기반)
def clean_anomalies(df, threshold={'temperature': 5, 'humidity': 10}):
    for col, th in threshold.items():
        df[col] = df[col].astype(float)
        df['prev_' + col] = df[col].shift(1)
        df['next_' + col] = df[col].shift(-1)
        
        # 이상값 감지 (이전 값, 다음 값과 비교)
        anomalies = (abs(df[col] - df['prev_' + col]) > th) & (abs(df[col] - df['next_' + col]) > th)
        df.loc[anomalies, col] = (df['prev_' + col] + df['next_' + col]) / 2  # 보간
    
    return df.drop(columns=['prev_temperature', 'next_temperature', 'prev_humidity', 'next_humidity'])

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
                cursor.execute(sql, (
                    round(row["temperature"], 3),
                    round(row["humidity"], 3),
                    round(row["NO2"], 3),
                    round(row["CO2"], 3),
                    round(row["NH3"], 3),
                    round(row["H2S"], 3),
                    round(row["Toluene"], 3),
                    row["ldr_value"],
                    row["timestamp"]
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
                print("Raw Data:", df)

                # 이상값 제거
                filtered_df = clean_anomalies(df)
                print("Filtered Data:", filtered_df)

                # DB 저장
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



##############################################################


#같은 센서에서 받아오는 NO2, CO2, NH3, H2S, Toluene 값이 
# 이전 데이터와 완전히 동일하면 DB에 저장하지 않도록 수정

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

# 이전 측정값 저장 변수
previous_values = {"NO2": None, "CO2": None, "NH3": None, "H2S": None, "Toluene": None}

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
NH3_PARA = 1.8028257   
NH3_PARB = -1.3523192  
H2S_PARA = 0.0216549   
H2S_PARB = -1.5547654  
TOLUENE_PARA = 2.82  
TOLUENE_PARB = -1.56  
CO2_FACTOR = 1.3   

def hazardous_gas():
    mq_value = readadc(mq_channel)
    volt = (mq_value / 1023.0) * 3.3  
    
    res = (3.3 - volt) * RLOAD / volt  
    ratio = res / RZERO  
    NO2 = round(NO2_PARA * math.pow(ratio, -NO2_PARB), 3)  
    CO2 = round(ratio * CO2_FACTOR * 400, 3)  
    NH3 = round(NH3_PARA * math.pow(ratio, NH3_PARB), 3)  
    H2S = round(H2S_PARA * math.pow(ratio, H2S_PARB), 3)  
    Toluene = round(TOLUENE_PARA * math.pow(ratio, TOLUENE_PARB), 3)  
    return NO2, CO2, NH3, H2S, Toluene

# 현재 데이터 수집
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
                global previous_values
                current_values = {
                    "NO2": row["NO2"],
                    "CO2": row["CO2"],
                    "NH3": row["NH3"],
                    "H2S": row["H2S"],
                    "Toluene": row["Toluene"]
                }
                
                if current_values != previous_values:
                    cursor.execute(sql, (row["temperature"], row["humidity"], row["NO2"], row["CO2"],
                                        row["NH3"], row["H2S"], row["Toluene"], row["ldr_value"], row["timestamp"]))
                    previous_values = current_values  # 업데이트

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
