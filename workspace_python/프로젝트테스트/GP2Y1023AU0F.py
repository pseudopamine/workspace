import spidev
import RPi.GPIO as GPIO
import time

# 설정
LED_PIN = 25  # LED 제어용 GPIO 핀
SPI_CHANNEL = 4  # MCP3008의 CH0 사용

# SPI 초기화
spi = spidev.SpiDev()
spi.open(0, 0)  # (bus 0, device 0)
spi.max_speed_hz = 1350000  # SPI 통신 속도

# GPIO 설정
GPIO.setmode(GPIO.BCM)
GPIO.setup(LED_PIN, GPIO.OUT)

def read_adc(channel):
    """ MCP3008에서 ADC 값을 읽는 함수 """
    if channel > 7 or channel < 0:
        return -1
    adc = spi.xfer2([1, (8 + channel) << 4, 0])
    data = ((adc[1] & 3) << 8) + adc[2]
    return data

def get_dust_concentration():
    """ 먼지 농도를 측정하는 함수 (LED 펄스 방식) """
    GPIO.output(LED_PIN, GPIO.HIGH)  # LED 켜기
    time.sleep(0.00028)  # 280µs 대기
    adc_value = read_adc(SPI_CHANNEL)  # ADC 값 읽기
    time.sleep(0.00004)  # 40µs 대기
    GPIO.output(LED_PIN, GPIO.LOW)  # LED 끄기

    # 전압 변환 (MCP3008은 10비트 ADC, 라즈베리파이 3.3V 기준)
    voltage = (adc_value / 1023.0) * 3.3

    # 먼지 농도 변환 (데이터시트 기반)
    dust_density = (voltage - 0.6) * 0.5
    return dust_density if dust_density > 0 else 0

try:
    while True:
        dust = get_dust_concentration()
        print(f"미세먼지 농도: {dust:.2f} mg/m³")
        time.sleep(1)  # 1초마다 측정
except KeyboardInterrupt:
    print("프로그램 종료")
    spi.close()
    GPIO.cleanup()



################################################################


import spidev
import RPi.GPIO as GPIO
import time

LED_PIN = 25  
SPI_CHANNEL = 4  

spi = spidev.SpiDev()
spi.open(0, 0)  
spi.max_speed_hz = 1350000  

GPIO.setmode(GPIO.BCM)
GPIO.setup(LED_PIN, GPIO.OUT)

def read_adc(channel):
    if channel > 7 or channel < 0:
        return -1
    adc = spi.xfer2([1, (8 + channel) << 4, 0])
    data = ((adc[1] & 3) << 8) + adc[2]
    return data

def get_dust_concentration():
    GPIO.output(LED_PIN, GPIO.HIGH)  
    time.sleep(0.00028)  
    adc_value = read_adc(SPI_CHANNEL)  
    time.sleep(0.00004)  
    GPIO.output(LED_PIN, GPIO.LOW)  

    voltage = (adc_value / 1023.0) * 3.3

    dust_density = (voltage - 0.6) * 0.5
    return dust_density if dust_density > 0 else 0

try:
    while True:
        dust = get_dust_concentration()
        print(f"Dust Density : {dust:.2f} mg/m^3")
        time.sleep(1)  

except KeyboardInterrupt:
    print("Program exit")
    spi.close()
    GPIO.cleanup()

###########################################################

#LED 제어 없이
import spidev
import time

SPI_CHANNEL = 6  

spi = spidev.SpiDev()
spi.open(0, 0)
spi.max_speed_hz = 100000  
def read_adc(channel):
    if channel > 7 or channel < 0:
        return -1
    adc = spi.xfer2([1, (8 + channel) << 4, 0])
    data = ((adc[1] & 3) << 8) + adc[2]
    return data

def get_dust_concentration():
    adc_value = read_adc(SPI_CHANNEL)
    voltage = (adc_value / 1023.0) * 3.3  
    dust_density = (voltage - 0.6) * 0.5  
    return max(dust_density, 0)

try:
    while True:
        dust = get_dust_concentration()
        print(f"Dust Density : {dust:.2f} mg/m^3")
        time.sleep(1)

except KeyboardInterrupt:
    print("exit")
    spi.close()
