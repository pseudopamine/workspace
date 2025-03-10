
# 단일행 함수 : 데이터의 갯수에 상관업이 무조건 1행의 데이터만 조회되는 함수

# 단일행 함수가 아닌 함수의 예시
# 이 함수들은 테이블에 저장된 데이터 수에 따라 조회 결과 갯수가 달라진다. 
SELECT COMM, IFNULL(COMM, 0), CONCAT(EMPNO, '_',  ENAME), SUBSTR(JOB, 1, 1)
FROM emp;

# 단일행 함수의 예시 : 모든 사원들 중 급여가 가장 높은 사원의 급여를 조회
# -> 이 예시의 경우에는 사원수가 달라져도 조회 결과는 무조건 1행만 조회된다.
SELECT MAX(SAL) FROM emp;
SELECT MIN(SAL) FROM emp;
SELECT AVG(SAL) FROM emp;
SELECT COUNT(SAL) FROM emp;
SELECT SUM(SAL) FROM emp;

# 단일행 함수 사용시 주의점 1. 
# COUNT와 AVG 함수 사용 시에는 NULL 데이터에 유의!!
# COUNT 함수에서 데이터의 갯수는 NULL 데이터는 제외한 갯수를 알려줌
# 결론 1 : 만약, 테이블에 저장된 데이터의 갯수를 알고 싶다면 NULL 데이터가 들어간
#				컬럼으로 COUNT() 함수를 사용하는 것은 금지!
# 				-> COUNT 함수는 PK 컬럼으로 진행
SELECT COUNT(SAL), COUNT(COMM) FROM emp;

# COUNT(*)를 사용해도 테이블의 데이터 갯수를 조회할 수 있지만
# * 보다 PK 컬럼을 사용할 것을 권장 (속도 느려짐 이슈 등)
SELECT COUNT(EMPNO), COUNT(*) FROM emp;
 
# AVG 함수로 평균을 계산할 때도 NULL 데이터의 갯수에서 제외되기 때문에 주의!!
SELECT AVG(SAL), AVG(COMM), SUM(COMM), AVG(IFNULL(COMM, 0)) FROM emp;


# 단일행 함수 사용시 주의점 2.
# 조회문의 각 컬럼은 조회 결과 데이터의 수가 일치하는 컬럼만 사용 가능!
# EX) 사원들 중 가장 높은 급여와 그 급여를 받는 사원명을 조회
# 결론 : 단일행 함수는 단일행 함수끼리만 사용 가능!! 다른 일반 컬럼과 단일행 함수를 같이 사용하면 쿼리 오류!!
# 단, GROUP BY절에 작성한 컬럼은 단일행함수와 함께 사용 가능
SELECT MAX(SAL), ENAME FROM emp; # 잘못된 쿼리


#####################################################################################

# 조회 데이터 그룹화 하기
# 모든 사원 정보에서 부서별로 가장 높은 급여를 조회하시오
SELECT DEPTNO, MAX(SAL), MIN(SAL), SUM(SAL), COUNT(SAL), AVG(SAL)
FROM emp
GROUP BY DEPTNO; 

# 직급별 인원수 조회, 직급기준 내림차순
# 그룹화한 컬럼은 단일행 함수와 같이 사용가능
SELECT JOB, COUNT(EMPNO) AS 사원수
FROM emp
GROUP BY JOB
ORDER BY JOB DESC;

# 입사일 기준 월별 입사자 수
SELECT MONTH(HIREDATE) AS 월별, COUNT(EMPNO) AS 입사자수
FROM emp
GROUP BY MONTH(HIREDATE); 

# 부서별 커미션의 평균을 조회 
# 단, 10번 부서는 제외하고, 동시에 평균은 전체 사원수를 기준으로 조회
SELECT DEPTNO, AVG(IFNULL(COMM, 0))
FROM emp
WHERE DEPTNO != 10
GROUP BY DEPTNO;

#부서별로 급여의 합을 조회. 단, 급여의 합이 2000 이상인 데이터만 조회
# 위의 조건은 그룹화 한 후 적용되는 조건!!
# 이런 조건은 HAVING절에 조건을 작성 
SELECT DEPTNO AS 부서, SUM(SAL)
FROM emp
GROUP BY DEPTNO
HAVING SUM(SAL) >= 2000;

# 직급별 최소 급여를 조회하되, 사원 직급은 제외, 직급별 최소 급여가 300 미만 데이터도 제외
# 조회 결과 데이터를 직급 기준 오름차순 정렬
SELECT JOB AS 직급, MIN(SAL)
FROM emp
WHERE JOB != '사원'
GROUP BY JOB
HAVING MIN(SAL) > 300
ORDER BY JOB;











