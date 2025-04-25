import time
import sys
from hx711 import HX711

# GPIO 핀 번호 설정 (BCM 모드 기준)
DT = 27    # 데이터 핀
SCK = 17   # 클럭 핀


# 보정값 설정 (1g 당 얼마의 출력인지 보정 필요)
# 실제로는 추 기준으로 보정해야 함
referenceunit = 1

# HX711 인스턴스 생성
hx = HX711(dout_pin=DT, pd_sck_pin=SCK)
hx.set_reading_format("MSB", "MSB")
hx.set_reference_unit(referenceunit)

# 초기화
hx.reset()
hx.tare()  # 제로 설정 (영점 조정)
print("영점 설정 완료. 측정 시작...")

try:
    while True:
        weight = hx.get_weight_mean(5)  # 평균값 (5번 측정 후 평균)
        if weight is not None:
            print(f"측정된 무게: {weight:.2f} g")
        else:
            print("무게 측정 실패")
        time.sleep(1)

except (KeyboardInterrupt, SystemExit):
    print("프로그램 종료")
    sys.exit()
