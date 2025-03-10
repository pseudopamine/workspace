
# 테이블명 생략 가능
SELECT EMP.EMPNO, EMP.ENAME, EMP.SAL
FROM emp;
# 테이블명 별칭 가능
SELECT E.EMPNO, E.ENAME, E.SAL
FROM emp E;

SELECT * FROM dept; #4
SELECT * FROM emp; #14

# 사원번호, 사원명, 급여, 부서번호, 부서명, 부서의 지역을 조회
# CROSS JOIN : 모든 데이터 조회, 현재에는 잘 쓰지 않음
# INNER JOIN : 두 테이블이 공통으로 지닌 컬럼의 값이 같은 것만 조회한다는 조건 작성
SELECT EMPNO, ENAME, SAL, E.DEPTNO, D.DEPTNO, DNAME, LOC
FROM emp E INNER JOIN dept D
ON E.DEPTNO = D.DEPTNO;

# 직급이 '사원'이 아닌 직원의 사번, 사원명, 직급, 부서번호, 부서명 조회
SELECT EMPNO, ENAME, JOB, EMP.DEPTNO, DNAME
FROM emp INNER JOIN dept
ON EMP.DEPTNO = DEPT.DEPTNO
WHERE JOB != '사원';

# 부서번호가 10, 20번인 직원의 사번, 사원명, 부서명, 부서의 지역을 조회
SELECT EMPNO, ENAME, DNAME, LOC
FROM emp E INNER JOIN dept D
ON E.DEPTNO = D.DEPTNO
WHERE E.DEPTNO IN (10, 20);



# 10. 조인을 사용하여 부서명이 ‘인사부’가 아니고 급여가 500이상인 사원의 
# 사번, 이름, 입사일, 급여, 부서번호, 부서명을 조회하는 쿼리문을 작성해보자. 
# 단, 정렬은 사번 기준 내림차순으로 정렬 후 사원 이름 기준 오름차순으로 정렬한다
SELECT EMPNO, ENAME, HIREDATE, SAL, EMP.DEPTNO, DNAME
FROM emp INNER JOIN dept
ON emp.DEPTNO = dept.DEPTNO
WHERE DNAME != '인사부' AND SAL >= 500
ORDER BY EMPNO DESC, ENAME ASC;



# 6. 사원의 사번, 이름, 부서번호, 부서명을 조회해보자. 
# 부서명은 부서번호가 10일 때는 ‘인사부’, 20일 때는 ‘영업부’, 30일때는 ‘개발부’, 그 외의 값은 ‘생산부’로 조회되어야 한다. 
# 조인 사용하는 문제 아님. CASE 사용.
SELECT EMPNO, ENAME, DEPTNO
CASE DEPTNO
	WHEN DEPTNO = 10 THEN '인사부'
	WHEN DEPTNO = 20 THEN '영업부'
	WHEN DEPTNO = 30 THEN '개발부'
	ELSE '생산부'
END
FROM emp;




