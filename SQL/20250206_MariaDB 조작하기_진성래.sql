shop_item#1. 도서정보 테이블 BOOK_INFO를 생성해보자. 
#도서정보 테이블은 도서 번호, 도서명, 저자, 도서 가격, 출판사, 발행일이 있다. 
#기본키는 도서번호로 지정하고, 도서명, 저자는 NULL값이 들어올 수 없다. 
#출판사 컬럼에 들어갈 데이터가 지정되지 않으면 ‘미지정’이라는 문자 데이터가 기본값으로 들어가야 한다.

CREATE TABLE BOOK_INFO (
	BOOK_NUM INT PRIMARY KEY
	, BOOK_NAME VARCHAR(20) NOT NULL
	, WRITER VARCHAR(20) NOT NULL
	, BOOK_PRICE INT
	, BOOK_COMP VARCHAR(20) DEFAULT '미지정'
	, PUBLISH_DATE DATETIME
);

#2. 위에서 생성한 BOOK_INFO 테이블에 다음의 데이터를 삽입하는 쿼리문을 작성하시오.

INSERT INTO BOOK_INFO (
	BOOK_NUM
	, BOOK_NAME
	, WRITER
	, BOOK_PRICE
	, BOOK_COMP
	, PUBLISH_DATE
) VALUES (
101, '자바 기초', '홍길동', 30000, 'A출판', '2025-02-15'
);

INSERT INTO BOOK_INFO (
	BOOK_NUM
	, BOOK_NAME
	, WRITER
	, BOOK_PRICE
	, BOOK_COMP
	, PUBLISH_DATE
) VALUES (
102, '리액트 기초', '이순신', 20000, 'B출판', '2025-01-22'
);

SELECT * FROM BOOK_INFO;
COMMIT;

#3. BOOK_INFO에서 도서번호가 101번인 도서의 
#도서명은 ‘자바_고급’, 가격은 25000, 출판사는 ‘IT출판’으로 변경하는 쿼리문을 작성하시오

UPDATE BOOK_INFO
SET BOOK_NAME = '자바 고급'
	, BOOK_PRICE = 25000
	, BOOK_COMP = 'IT 출판'
WHERE BOOK_NUM = 101;

# 4. 급여가 500에서 3000 사이이고 커미션이 NULL인 사원의 
#사원번호, 사원명, 급여, 커미션을 조회하는 쿼리문을 작성하세요. 
#단, 커미션 컬럼은 ‘인센티브’라는 별칭으로 조회하시오

SELECT EMPNO, ENAME, SAL, COMM AS '인센티브'
FROM emp 
WHERE SAL BETWEEN 500 AND 3000
AND COMM IS NOT NULL;

# 5. 사원들 중 이름에 ‘병’이 들어가거나, ‘김’이 들어가는 사원들의 
#사번, 이름, 직급, 입사일을 조회하되, 직급 기준 오름차순으로 정렬 후, 
#사번기준 내림차순 정렬하여 조회하는 쿼리문을 작성하세요.

SELECT EMPNO, ENAME, JOB, HIREDATE
FROM emp
WHERE ENAME LIKE '%병%' OR ENAME LIKE '%김%'
ORDER BY JOB, EMPNO DESC;

#6. 사원의 사번, 이름, 부서번호, 부서명을 조회해보자. 
#부서명은 부서번호가 10일 때는 ‘인사부’, 20일 때는 ‘영업부’, 30일 때는 ‘개발부’, 
#그 외의 값은 ‘생산부’로 조회되어야 한다. 
#조인 사용하는 문제 아님. CASE 사용

SELECT EMPNO, ENAME, DEPTNO
	, CASE
		WHEN DEPTNO = 10 THEN '인사부'
		WHEN DEPTNO = 20 THEN '영업부'
		WHEN DEPTNO = 30 THEN '개발부'
		ELSE '생산부'
		END AS '부서명'
FROM emp;

#7.  짝수 월에 입사한 모든 사원의 사번, 이름, 입사일, 커미션을 입사일 기준 오름차순으로 조회하는 
#쿼리문을 작성하세요. 단, 커미션이 NULL일 경우 커미션은 0으로 조회되어야 한다

SELECT EMPNO, ENAME, HIREDATE, IFNULL(COMM, 0)
FROM emp
WHERE MOD(MONTH(HIREDATE), 2) = 0
ORDER BY HIREDATE ASC;

#8. 조인을 사용하여 부서명이 ‘인사부’가 아니고 급여가 500이상인 
#사원의 사번, 이름, 입사일, 급여, 부서번호, 부서명을 조회하는 쿼리문을 작성해보자. 
#단, 정렬은 사번 기준 내림차순으로 정렬 후 사원 이름 기준 오름차순으로 정렬한다.

SELECT * FROM emp;
SELECT * FROM DEPT;

SELECT EMPNO, ENAME, HIREDATE, SAL, EMP.DEPTNO, DNAME
FROM emp INNER JOIN dept
ON emp.DEPTNO = dept.DEPTNO
WHERE DNAME != '인사부'
AND SAL >= 500
ORDER BY EMPNO DESC, ENAME;

#9. 입사년도가 2003년부터 2007년 전까지인 사원들의 
#사번, 부서번호, 부서명, 성을 제외한 사원명을 조회하여라.
#성을 제외한 사원명은 ‘이름’이라는 별칭을 사용하고, 성은 전체이름 중 처음 한 글자만 성이라고 간주한다.

SELECT EMPNO, EMP.DEPTNO, DNAME, SUBSTR(ENAME, 2) AS '이름'
FROM emp INNER JOIN dept
ON emp.DEPTNO = dept.DEPTNO
WHERE YEAR(HIREDATE) >= 2003 AND YEAR(HIREDATE) < 2007;

#10. 조인을 활용하여 
#사번, 사원명, 직급, 직속 상사의 사번, 직속 상사명, 직속 상사의 직급을 조회하는 쿼리문을 작성하시오.
#MGR 컬럼이 직속 상사의 사번이다

SELECT E1.EMPNO, E1.ENAME, E1.JOB, E2.EMPNO, E2.ENAME, E2.JOB
FROM emp E1 JOIN emp E2
ON E1.MGR = E2.EMPNO;





