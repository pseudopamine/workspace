import pymysql

ip_list = ["192.168.30.97", "192.168.30.97", "192.168.30.97"]

for ip in ip_list:
    conn = pymysql.connect(
    host = ip
    , user = "team2_user"
    , password = "mariadb"
    , database = "team2"
    , charset = "utf8mb4"
)

    cursor = conn.cursor()

    cursor.execute("""
    CREATE TABLE ENV(
    EYE_NUM INT PRIMARY KEY AUTO_INCREMENT
    , TEMP FLOAT
    , HUMI FLOAT
    , CO2 FLOAT
    , ILLUMI FLOAT
    , TIMESTAMP DATETIME DEFAULT SYSDATE()
)
    )
""")

cursor.close()






#################################

conn = pymysql.connect(
    host = "192.168.30.97"     
    , user = "team2_user"
    , password = "mariadb"
    , database = "team2"
    , charset = "utf8mb4")
cursor = conn.cursor()

cursor.execute("""
    CREATE TABLE TEMPERATURE (
        id INT AUTO_INCREMENT PRIMARY KEY,
        TEMP INT
    )
""")

cursor.close()
