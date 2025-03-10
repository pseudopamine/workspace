
-- 연습용 자동차 정보 테이블
CREATE TABLE TEST_CAR(
	MODEL_NUMBER INT PRIMARY KEY -- 중복 데이터 X, NULL X
	, MODEL_NAME VARCHAR(20) NOT NULL -- 필수입력값
	, PRICE INT
	, BRAND VARCHAR(20)
);

SELECT * FROM test_car;

-- 게시글 정보
CREATE TABLE BOARD(
	BOARD_NUM INT PRIMARY KEY
	, TITLE VARCHAR(30) NOT NULL
	, WRITER VARCHAR(20)
	, READ_CNT INT DEFAULT 0
);

SELECT * FROM board;

COMMIT;

-- 데이터 삽입
-- INSERT INTO 테이블명 (컬럼, 컬럼...) VALUES (데이터, 데이터...);
-- 컬럼 넣는 순서는 상관없지만, VALUES 내에는 컬럼 넣은 순서대로 넣어야 함
INSERT INTO board (BOARD_NUM, TITLE, WRITER, READ_CNT) 
VALUES (1, '제목1', '김자바', 0);

INSERT INTO board (BOARD_NUM, TITLE, WRITER, READ_CNT) 
VALUES (2, '제목1', '김자바', 0);

INSERT INTO board (BOARD_NUM, TITLE, READ_CNT) 
VALUES (3, '제목2', 3);

INSERT INTO board (BOARD_NUM, READ_CNT) 
VALUES (4, 3);

INSERT INTO board (BOARD_NUM, TITLE) 
VALUES (5, '제목3');

-- VALUES의 데이터가 컬럼의 순서대로 모든 데이터를 넣는다면 테이블명 뒤에 컬럼 필요 없음
INSERT INTO board VALUES (9, '제목5', '박자바', 10);

ROLLBACK;



