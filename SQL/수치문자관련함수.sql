
#수치 관련 함수
# CEIL : 올림
# FLOOR : 내림
# ROUND : 반올림
SELECT CEIL(70.1), FLOOR(70.1), ROUND(70.1);
SELECT ROUND(123.456, 1), ROUND(123.456, 2);

#TRUNCATE : 버림
SELECT TRUNCATE(123.456, 1), TRUNCATE(123.456, 2);

#MOD : 나눗셈의 나머지 연산
SELECT MOD(10, 4);

# EMP테이블에 사번이 짝수인 사원들의 모든 컬럼값 조회
SELECT * FROM emp 
WHERE MOD(EMPNO, 2) = 0;

# 문자 관련 함수
SELECT SUBSTR('ABCDEF', 3) #글자에서 3번째 글자부터 가져오겠습니다.
	, SUBSTRING('ABCDEF', 3)
	, SUBSTR('ABCDEF', 3, 2) #글자에서 3번째 글자부터 2개 가져오겠습니다.
	, SUBSTRING('ABCDEF', 3, 2);

#대소문자 변경
SELECT 'MariaDB', UPPER('MariaDB'), LOWER('MariaDB');

#데이터의 글자수 맞추는 함수
#LPAD(원본문자, 완성할 글자수, 채워줄 문자) 
#RPAD(원본문자, 완성할 글자수, 채워줄 문자)
SELECT LPAD('DB', 5, '@'), RPAD('DB', 5, '@');

#TRIM : 공백제거함수
SELECT LTRIM('   DB   '), RTRIM('   DB   '), TRIM('   DB   ');

#영어, 숫자 한 글자 : 1byte
#국어, 한자 한 글자 : 3byte
SELECT CHAR_LENGTH('디비'), LENGTHB('디비');

#CONCAT() : 문자나열
SELECT CONCAT('A', 'B', 'C');
SELECT CONCAT(JOB, ENAME) AS '나열값' FROM emp;

#REPLACE : 일부 문자 대체
SELECT REPLACE('나는 HOME에 있고싶다', 'HOME', '집');

#논리연산
SELECT IF(10 > 2, '참', '거짓'); #삼항연산자와 유사

#시험문제 : NULL이 많은 데이터에서
#IFNULL(컬럼, 대체값)
#첫번째 인자로 주어진 데이터가 NULL이 아니면 원래 데이터가 조회
#첫번째 인자로 주어진 데이터가 NULL이면 두번째 인자로 전달될 데이터 조회
SELECT COMM, IFNULL(COMM, 0) FROM emp;





