SELECT * FROM study_student;

-- 1. 국어점수가 60점 이상이고 영어점수 70점 이상인 학생들의 
-- 학번, 이름, 국어점수, 영어점수를 조회하되, 
-- 학번은 '학생번호', 이름은 '학생명'이라는 별칭을 사용하여 조회하시오.
SELECT STU_NUM 학생번호, STU_NAME AS 학생이름, KOR_SCORE, ENG_SCORE 
FROM study_student
WHERE KOR_SCORE >= 60 AND ENG_SCORE >= 70;

-- 2. 디자인반 혹은 영상반에 소속된 학생 중, 취미가 null이 아닌 학생들의 모든 컬럼 정보를 조회하시오.
SELECT * FROM study_student 
WHERE CLASS_NAME IN ('디자인반', '영상반') AND HOBBY IS NOT NULL;

-- 3. 김씨 성을 지닌 학생 중, 국어점수가 70점에서 85점 사이인 학생들의 
-- 학번, 이름, 국어점수를 조회하되,
-- 학번 기준 내림차순으로 정렬하여 조회하시오.
SELECT STU_NUM, STU_NAME, KOR_SCORE
FROM study_student 
WHERE KOR_SCORE BETWEEN 70 AND 85 AND STU_NAME LIKE '김%' 
ORDER BY STU_NUM DESC;

-- 4. 취미가 null이 아닌 학생들의 학번, 이름, 학급, 영어점수를 조회하되,
-- 학급 기준 오름차순으로 정렬 후, 같은 학급이라면 영어점수가 높은 순부터 조회하시오.
SELECT STU_NUM, STU_NAME, CLASS_NAME, ENG_SCORE
FROM study_student
WHERE HOBBY IS NOT NULL
ORDER BY CLASS_NAME, ENG_SCORE DESC;

-- 5. 국어점수, 영어 점수를 더한 총점이 170점 이상인 학생들의 모든 컬럼 정보를 조회하시오.
SELECT * FROM study_student
WHERE KOR_SCORE + ENG_SCORE >= 170;

-- 6. 영상반이 아닌 학생들의 학번, 이름, 국어점수, 영어점수, 국어와 영어 점수의 합을 조회하되,
-- 점수의 합은 별칭을 사용하여 '총점'으로 조회하시오.
SELECT STU_NUM, STU_NAME, KOR_SCORE, ENG_SCORE, KOR_SCORE+ENG_SCORE AS 총점
FROM study_student 
WHERE CLASS_NAME != '영상반';
-- WHERE CLASS_NAME NOT IN ('영상반');

-- 7. 학생들이 가진 취미의 종류를 조회할 수 있는 쿼리문을 작성하시오.
SELECT DISTINCT HOBBY FROM study_student WHERE HOBBY IS NOT NULL;


