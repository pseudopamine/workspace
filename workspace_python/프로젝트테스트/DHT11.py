import RPi.GPIO as GPIO
import dht11
import time

# GPIO Setting
GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM) 
GPIO.cleanup()

sensor = dht11.DHT11(pin=10)

print("START...")

time.sleep(5)

cnt = 0

try:
    while True:
        result = sensor.read()

        if result.is_valid():
            print(f"temp: {result.temperature} | humidity: {result.humidity}%")
            print(cnt)
        
        cnt = cnt + 1

        time.sleep(3)

except KeyboardInterrupt:
    print("STOP")
    GPIO.cleanup()