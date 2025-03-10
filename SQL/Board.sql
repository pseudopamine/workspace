
CREATE TABLE BOARD (
	BOARD_NUM INT PRIMARY KEY AUTO_INCREMENT
	, TITLE VARCHAR(30) NOT NULL
	, WRITER VARCHAR(30) NOT NULL
	, CONTENT VARCHAR(30)
	, READ_CNT INT DEFAULT 0
	, REG_DATE DATETIME DEFAULT SYSDATE()
);

SELECT * FROM BOARD;

INSERT INTO BOARD 
(
	TITLE
	, WRITER
	, CONTENT
	, READ_CNT
	, REG_DATE
) VALUES ('가입인사올립니다', '임꺽정', '반갑습니다 가입했어요', 4, NOW());

COMMIT;

CREATE TABLE BOARD_REPLY (
	REPLY_NUM INT PRIMARY KEY AUTO_INCREMENT
	, CONTENT VARCHAR(30) NOT NULL
	, WRITER VARCHAR(20) NOT NULL
	, REG_DATE DATETIME DEFAULT SYSDATE()
	, BOARD_NUM INT NOT NULL REFERENCES BOARD (BOARD_NUM)
);

SELECT * FROM board_reply;

INSERT INTO board_reply VALUES (1, "저도 동감하는 바입니다.", "말벌아저씨", NOW(), 10); 

SELECT * FROM board
WHERE TITLE LIKE '%java%';

SELECT * FROM board
WHERE WRITER LIKE '%kim%';

 

 # BOARD_REPLY 테이블의 컬럼명 변경
ALTER TABLE board_reply CHANGE COLUMN WRITER REPLY_WRITER VARCHAR(20);

ALTER TABLE board_reply CHANGE COLUMN REG_DATE REPLY_REG_DATE DATETIME DEFAULT SYSDATE();

ALTER TABLE board_reply CHANGE COLUMN CONTENT REPLY_CONTENT VARCHAR(50);

SELECT * FROM board_reply;

SELECT * FROM board;
DESC board;
DESC board_reply;
 # 모든 게시글의 게시글번호, 제목, 작성자 및 게시글에 달린 댓글 내용, 댓글 작성자를 조회하시오
SELECT BOARD.BOARD_NUM
		, TITLE
		, WRITER
		, REPLY_CONTENT
		, REPLY_WRITER
FROM board INNER JOIN board_reply
ON board.BOARD_NUM = board_reply.BOARD_NUM;




