#-*- coding : utf-8 -*-#

#필요한 라이브러리를 불러옵니다
import Adafruit_BMP.BMP085 as BMP085

#BMP180센서의 인스턴스 sensor 생성
sensor = BMP085.BMP085(busnum=1)

#온도 , 압력, 고도 값을 읽어서 변수에 저장
temp = sensor.read_temperature()
pressure = sensor.read_pressure()
ALTitude = sensor.read.ALTitude()

#측정값을 출력
print('Temp = {0:02f} *C'.format(temp))
print('pressure = {0:02f} Pa'.format(pressure))
print('ALTitude = {0:02f} m'.format(ALTitude))

