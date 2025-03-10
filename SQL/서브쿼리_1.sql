
# 서브쿼리 : 하나의 쿼리문 안에 또 다른 쿼리문이 들어간 sql 문법

# 김사랑 사원과 같은 부서에 속한 사원들의 사번, 사원명, 부서번호를 조회
# 1 -> 김사랑 사원이 속한 부서를 확인

SELECT DEPTNO
FROM emp
WHERE ENAME = '김사랑';

# 2 -> 20번 부서에 속한 사원들의 사번, 사원명, 부서번호를 조회
SELECT EMPNO, ENAME, DEPTNO
FROM emp
WHERE DEPTNO = 20;

# 서브쿼리 적용 (괄호 안에 있는 서브쿼리부터 해석)
SELECT EMPNO, ENAME, DEPTNO
FROM emp
WHERE DEPTNO = (SELECT DEPTNO
						FROM emp
						WHERE ENAME = '김사랑');

# 사번이 1001번인 사원과 같은 직급을 갖는 사원들의 모든 정보를 조회
SELECT *
FROM emp
WHERE JOB = (SELECT JOB FROM emp WHERE EMPNO = 1001);

# 사원의 사번, 사원명, 부서번호, 부서명, 부서가 위치한 지역을 조회 (JOIN 사용 금지)
SELECT 
	EMPNO
	, ENAME
	, DEPTNO
	, (SELECT DNAME 
		FROM dept 
		WHERE DEPTNO = emp.DEPTNO) AS DNAME
	, (SELECT LOC 
		FROM dept 
		WHERE DEPTNO = emp.DEPTNO) AS LOC
FROM emp;

# 모든 사원의 평균 급여보다 급여가 더 많은 사원의 사번, 사원명, 급여를 조회
SELECT EMPNO, ENAME, SAL
FROM emp
WHERE SAL > (SELECT AVG(SAL) FROM emp);

SELECT * FROM board;
SELECT * FROM board_reply;

# 댓글 번호가 7번인 댓글의 댓글내용, 댓글 작성자, 해당 댓글이 달린 게시글의 제목, 내용을 조회
# 위 문제를 JOIN, 서브쿼리로 각각 풀이

#서브쿼리
SELECT REPLY_CONTENT
		, REPLY_WRITER
		, (SELECT TITLE FROM board WHERE BOARD_NUM = board_reply.BOARD_NUM) AS 게시글제목
		, (SELECT CONTENT FROM board WHERE BOARD_NUM = board_reply.BOARD_NUM) AS 게시글내용
FROM board_reply
WHERE REPLY_NUM = 7;

#JOIN
SELECT REPLY_CONTENT
		, REPLY_WRITER
		, TITLE
		, CONTENT
FROM BOARD_REPLY INNER JOIN board
ON board.BOARD_NUM = board_reply.BOARD_NUM 
WHERE REPLY_NUM = 7;


# 모든 게시글의 글번호, 제목, 작성자, 해당 게시글에 달린 댓글 수를 조회

# 서브쿼리
SELECT BOARD_NUM
		, TITLE
		, WRITER
		, (SELECT COUNT(REPLY_NUM) 
			FROM board_reply 
			WHERE BOARD_NUM = board.BOARD_NUM) AS 댓글수
FROM board;


