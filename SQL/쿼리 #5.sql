
CREATE TABLE ITEM_INFO (
	ITEM_NUM INT AUTO_INCREMENT PRIMARY KEY-- 상품번호
	, ITEM_NAME VARCHAR(30) NOT NULL UNIQUE-- 상품명	
	, ITEM_PRICE INT NOT NULL-- 상품가격
	, SELLER VARCHAR(20) NOT NULL-- 판매자
	, ITEM_INTRO VARCHAR(50)-- 상품 설명
	, REG_DATE DATETIME DEFAULT SYSDATE()
 );
 
 SELECT * FROM ITEM_INFO;
 
 
#주문정보테이블
CREATE TABLE ORDER_INFO (
	ORDER_NUM INT PRIMARY KEY AUTO_INCREMENT-- 주문번호
	, ITEM_NUM INT REFERENCES ITEM_INFO (ITEM_NUM)
	, BUYER VARCHAR(20) NOT NULL-- 구매자
	, BUY_CNT INT NOT NULL-- 구매 수량
	, BUY_PRICE INT NOT NULL
	, BUY_DATE DATETIME DEFAULT SYSDATE()
 );
 
 SELECT * FROM order_info;
 
 SELECT EMPNO, ENAME, SAL, EMP.DEPTNO, DNAME, LOC
 FROM emp INNER JOIN DEPT
 ON emp.DEPTNO = dept.DEPTNO;
 
 
  SELECT * FROM emp;
  DESC emp;
  DESC dept;