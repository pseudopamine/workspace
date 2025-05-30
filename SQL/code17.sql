CREATE TABLE FARMSEYE_ENV(
    EYE_NUM INT PRIMARY KEY AUTO_INCREMENT
    , TEMP FLOAT # 온도 측정 데이터
    , HUMI FLOAT # 습도 측정 데이터
    , NO2 FLOAT # 질소산화물 측정 데이터 
    , CO2 FLOAT # 이산화탄소 측정 데이터
    , NH3 FLOAT # 암모니아 측정 데이터 
    , H2S FLOAT # 황화수소 측정 데이터
    , TOLUENE FLOAT # 톨루엔 측정 데이터
    , ILLUMI FLOAT # 조도 측정 데이터
    , TIMESTAMP DATETIME DEFAULT SYSDATE()
);

SELECT * FROM FARMSEYE_ENV;

CREATE TABLE FARMSEYE_CHI(
   STOCK_NUM INT PRIMARY KEY AUTO_INCREMENT
   , FEED_WEIGHT FLOAT
   , STOCK_WEIGHT FLOAT
);


CREATE TABLE FAMSEYE_USER(
	USER_ID VARCHAR(20) PRIMARY KEY
	, USER_PW VARCHAR(20) NOT NULL
	, USER_NAME VARCHAR(30) NOT NULL
	, USER_AGE INT
	, USER_TEL VARCHAR(20) NOT NULL UNIQUE
	, USER_ADDR VARCHAR(100) NOT NULL
	, REG_DATE DATETIME DEFAULT SYSDATE()
	, IS_USING VARCHAR(10) DEFAULT 'Y' # 상태 : Y - 사용중, N - 탈퇴 
	, USER_ROLL VARCHAR(10) DEFAULT 'USER' # 관리자 : ADMIN
);





DROP TABLE farmseye_env;

