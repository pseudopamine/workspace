
#주문정보 테이블
CREATE TABLE ORDER_INFO(
	ORDER_NUM INT PRIMARY KEY,		#주문번호
	ORDER_ITEM VARCHAR(20),			#주문상품
	ORDER_CNT INT,						#주문수량
	ORDER_USER_ID VARCHAR(20),		#주문자ID
	PAY_NUM INT							#결제번호
);

#결제정보 테이블
CREATE TABLE PAY_INFO (
	PAY_NUM INT PRIMARY KEY,		#결제번호
	PAY_PRICE INT,						#결제금액
	PAY_TYPE VARCHAR(10),			#결제방법
	PAY_DATE DATETIME					#결제일
);

#주문정보 데이터 삽입
INSERT INTO ORDER_INFO VALUES (1, '맨투맨 후드티', 1, 'kim', 3);
INSERT INTO ORDER_INFO VALUES (2, '데님 청바지', 2, 'lee', 1);
INSERT INTO ORDER_INFO VALUES (3, '라운드 긴팔티', 3, 'lee', 4);
INSERT INTO ORDER_INFO VALUES (4, '스포츠 양말', 5, 'kim', 2);
INSERT INTO ORDER_INFO VALUES (5, '슬렉스 바지', 1, 'park', 5);

#결제정보 데이터 삽입
INSERT INTO PAY_INFO VALUES (1, 15000, '계좌이체', '2025-01-10');
INSERT INTO PAY_INFO VALUES (2, 20000, '신용카드', '2025-01-12');
INSERT INTO PAY_INFO VALUES (3, 35000, '신용카드', '2025-01-15');
INSERT INTO PAY_INFO VALUES (4, 10000, '계좌이체', '2025-01-20');
INSERT INTO PAY_INFO VALUES (5, 80000, '계좌이체', '2025-01-22');

COMMIT;


#사번, 사원명, 급여, 부서번호, 부서명을 조회
SELECT EMPNO, ENAME, SAL, EMP.DEPTNO, DNAME
FROM EMP INNER JOIN dept
ON emp.DEPTNO = dept.DEPTNO;

#1. 모든 데이터에 대한 주문번호, 주문상품명, 주문수량, 주문자ID, 결제번호, 결제금액, 결제일을 조회하시오.

SELECT ORDER_NUM, ORDER_ITEM, ORDER_CNT, ORDER_USER_ID, PAY_INFO.PAY_NUM, PAY_PRICE, PAY_DATE
FROM order_info INNER JOIN pay_info
ON order_info.PAY_NUM = pay_info.PAY_NUM;

#2. 결제방법이 계좌이체이며, 결제금액이 만원을 초과한 주문에 대한 주문상품명, 주문수량, 결제금액, 결제방법을 조회하되, 결제일이 최신인 데이터부터 조회하시오.

SELECT ORDER_ITEM, ORDER_CNT, PAY_PRICE, PAY_TYPE
FROM order_info INNER JOIN pay_info
ON order_info.PAY_NUM = pay_info.PAY_NUM
WHERE PAY_TYPE = '계좌이체'
AND PAY_PRICE > 10000
ORDER BY PAY_DATE DESC;

#3. 결제일이 2025-01-20일 이전인 주문 중, 주문 수량이 홀수인 주문들의 주문번호, 주문수량, 주문자ID, 결제일을 조회하시오.

SELECT ORDER_NUM, ORDER_CNT, ORDER_USER_ID, PAY_DATE
FROM order_info INNER JOIN pay_info
ON order_info.PAY_NUM = pay_info.PAY_NUM
WHERE MOD(ORDER_CNT, 2) = 1 
AND PAY_DATE < '2025-01-20';

#6. 사원의 사번, 이름, 부서번호, 부서명을 조회해보자. 
#부서명은 부서번호가 10일 때는 ‘인사부’, 20일 때는 ‘영업부’, 30일때는 ‘개발부’, 그 외의 값은 ‘생산부’로 조회되어야 한다. 
#조인 사용하는 문제 아님. CASE 사용.

SELECT EMPNO, ENAME, DEPTNO
	, CASE
		WHEN DEPTNO = 10 THEN '인사부'
		WHEN DEPTNO = 20 THEN '영업부'
		WHEN DEPTNO = 30 THEN '개발부'
		ELSE '생산부'
		END AS '부서명'
FROM emp;

SELECT NOW(), YEAR(NOW()), DAY(NOW()), HOUR(NOW());


SELECT * FROM emp;

SELECT SAL
	, CASE
		WHEN SAL > 500 THEN '500초과'
		WHEN SAL < 500 THEN '500미만'
		ELSE '500'
		END AS AAA
FROM emp;


SELECT EMPNO, ENAME, HIREDATE, IFNULL(COMM, 0)
FROM emp
WHERE MONTH(HIREDATE) = 1
ORDER BY HIREDATE ASC;


