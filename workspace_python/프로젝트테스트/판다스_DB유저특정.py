
import math
import time
import board
import adafruit_dht
import spidev
import pymysql
import pandas as pd
import numpy as np
from datetime import datetime

ip_list = ["192.168.30.97", "192.168.30.147", "192.168.30.111"]

mq_channel = 2
ldr_channel = 0
sensor = adafruit_dht.DHT22(board.D2)

spi = spidev.SpiDev()
spi.open(0, 0)
spi.max_speed_hz = 100000

prev_gas_data = None  

def readadc(adcnum):
    if adcnum > 7 or adcnum < 0:
        return -1
    r = spi.xfer2([1, (8 + adcnum) << 4, 0])
    data = ((r[1] & 3) << 8) + r[2]
    return data

def hazardous_gas():
    mq_value = readadc(mq_channel)
    volt = (mq_value / 1023.0) * 3.3
    res = (3.3 - volt) * 10.0 / volt
    ratio = res / 76.63
    NO2 = round(0.4220682 * math.pow(ratio, -2.769034857), 3)
    CO2 = round(ratio * 1.3 * 400, 3)
    NH3 = round(1.8028257 * math.pow(ratio, -1.3523192), 3)
    H2S = round(0.0216549 * math.pow(ratio, -1.5547654), 3)
    Toluene = round(2.82 * math.pow(ratio, -1.56), 3)
    return NO2, CO2, NH3, H2S, Toluene

def getNowData():
    global prev_gas_data
    
    NO2, CO2, NH3, H2S, Toluene = hazardous_gas()
    current_gas_data = (NO2, CO2, NH3, H2S, Toluene)
    
    if prev_gas_data == current_gas_data:
        print("Skipping duplicate gas data.")
        return None  
    
    prev_gas_data = current_gas_data  
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

user_id = "user"

def insertData(df, user_id):
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
                    (TEMP, HUMI, NO2, CO2, NH3, H2S, TOLUENE, ILLUMI, TIMESTAMP, USER_ID) 
                    VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s)"""
            
            for _, row in df.iterrows():
                cursor.execute(sql, (
                    round(row["temperature"], 3),
                    round(row["humidity"], 3),
                    round(row["NO2"], 3),
                    round(row["CO2"], 3),
                    round(row["NH3"], 3),
                    round(row["H2S"], 3),
                    round(row["Toluene"], 3),
                    row["ldr_value"],
                    row["timestamp"],
                    user_id
                ))
            
            conn.commit()
            print(f"COMPLETE ADD DATA into {ip}")
        except pymysql.MySQLError as e:
            print(f"DB ERROR on {ip}: {e}")
        finally:
            cursor.close()
            conn.close()

data_list = []

def main():
    global data_list
    while True:
        try:
            new_data = getNowData()
            if new_data:
                data_list.append(new_data)
                print("Added new data point:", new_data)
            
            if len(data_list) >= 5:
                df = pd.DataFrame(data_list)
                print("Raw Data:", df)
                insertData(df, user_id)
                data_list = []
            
            time.sleep(60)
        except Exception as e:
            print("Error:", e)
            time.sleep(60)

if __name__ == "__main__":
    main()
