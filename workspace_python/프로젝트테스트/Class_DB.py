import math
import time
import board
import adafruit_dht
import spidev
import pymysql
from datetime import datetime

class SensorReader:
    def __init__(self):
        self.spi = spidev.SpiDev()
        self.spi.open(0, 0)
        self.spi.max_speed_hz = 100000
        self.sensor = adafruit_dht.DHT22(board.D2)
        self.ldr_channel = 0
        self.mq_channel = 2
        
        # Gas sensor calibration values
        self.RLOAD = 10.0
        self.RZERO = 76.63
        self.PARA = 116.6020682
        self.PARB = 2.769034857
        self.CO2_FACTOR = 1.3
    
    def read_adc(self, channel):
        if channel < 0 or channel > 7:
            return -1
        r = self.spi.xfer2([1, (8 + channel) << 4, 0])
        data = ((r[1] & 3) << 8) + r[2]
        return data
    
    def read_gas_sensor(self):
        mq_value = self.read_adc(self.mq_channel)
        volt = (mq_value / 1023.0) * 3.3
        res = (3.3 - volt) * self.RLOAD / volt
        ratio = res / self.RZERO
        NO2 = self.PARA * math.pow(ratio, -self.PARB)
        CO2 = ratio * self.CO2_FACTOR * 400
        air_quality = NO2 + CO2
        return air_quality, NO2, CO2
    
    def get_sensor_data(self):
        airq, NO2, CO2 = self.read_gas_sensor()
        temperature = self.sensor.temperature
        humidity = self.sensor.humidity
        ldr_value = self.read_adc(self.ldr_channel)
        timestamp = datetime.now()

        print("----------------------------------")
        print(f'Temp = {temperature:.2f} °C')
        print(f'humidity = {humidity:.2f} %')
        print(f'LDR Value: {ldr_value}')
        print(f'NO2: {NO2:.2f} ppm, CO2: {CO2:.2f} ppm')
        print(f'Air Quality Index: {airq:.2f}')
        print(f'Timestamp: {timestamp}')
        
        return temperature, humidity, NO2, CO2, airq, ldr_value, timestamp


class DatabaseManager:
    def __init__(self, ip_list, user, password, database):
        self.ip_list = ip_list
        self.user = user
        self.password = password
        self.database = database
    
    def insert_data(self, data):
        temperature, humidity, NO2, CO2, airq, ldr_value, timestamp = data
        sql = """
        INSERT INTO FARMSEYE_ENV (TEMP, HUMI, NO2, CO2, AIR_QUALITY, ILLUMI, TIMESTAMP)
        VALUES (%s, %s, %s, %s, %s, %s, %s)
        """
        for ip in self.ip_list:
            try:
                conn = pymysql.connect(
                    host=ip,
                    user=self.user,
                    password=self.password,
                    database=self.database,
                    charset="utf8mb4"
                )
                cursor = conn.cursor()
                cursor.execute(sql, (temperature, humidity, NO2, CO2, airq, ldr_value, timestamp))
                conn.commit()
                print(f"COMPLETE ADD DATA into {ip}")
            except pymysql.MySQLError as e:
                print(f"DB ERROR on {ip}: {e}")
            finally:
                cursor.close()
                conn.close()


class FarmEyeMonitor:
    def __init__(self, sensor_reader, db_manager, interval=10):
        self.sensor_reader = sensor_reader
        self.db_manager = db_manager
        self.interval = interval

    def run(self):
        while True:
            try:
                data = self.sensor_reader.get_sensor_data()
                self.db_manager.insert_data(data)
                print("Added data, waiting 10 sec")
            except Exception as e:
                print("Error: ", e)
            time.sleep(self.interval)


if __name__ == "__main__":
    ip_list = ["192.168.30.97", "192.168.30.150", "192.168.30.147", "192.168.30.111"]
    db_manager = DatabaseManager(ip_list, "code17_user", "mariadb", "code17")
    sensor_reader = SensorReader()
    monitor = FarmEyeMonitor(sensor_reader, db_manager)
    monitor.run()
