import RPi.GPIO as GPIO
import time

led_G = 20
led_Y = 21
sensor = 4

def setup():
    GPIO.setwarnings(False)
    GPIO.setmode(GPIO.BCM)
    GPIO.setup(led_G, GPIO.OUT)
    GPIO.setup(led_Y, GPIO.OUT)
    GPIO.setup(sensor, GPIO.IN)
    print("PIR Ready . . . . ")
    time.sleep(5)

def loop():
    while True:
        if GPIO.input(sensor) == 1:
            GPIO.output(led_Y, 1)
            GPIO.output(led_G, 0)
            print("Motion Detected !")
        else:
            GPIO.output(led_Y, 0)
            GPIO.output(led_G, 1)
            print("Motion Out !")
        time.sleep(0.5)

def main():
    try:
        setup()
        loop()
    except KeyboardInterrupt:
        print("Stopped by User")
    finally:
        GPIO.cleanup()

if __name__ == "__main__":
    main()
