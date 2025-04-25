import board
import adafruit_dht
import time

sensor = adafruit_dht.DHT22(board.D23)  # GPIO23 = 핀번호 16

while True:

    try:

        temperature = sensor.temperature
        humidity = sensor.humidity
        print(f"Temp: {temperature:.1f}°C | Humidity: {humidity:.1f}%")

    except Exception as e:

        print("센서 오류:", e)

    time.sleep(2)