SELECT b.rn, b.employee_id, b.first_name
  FROM (SELECT rownum as rn, a.employee_id, a.first_name
          FROM (SELECT employee_id, first_name
                  FROM employees
                 ORDER BY hire_date) a) b
 WHERE b.rn between 11 and 20;

-- a : 정렬한 테이블
-- b : a 테이블에 rn을 추가한 테이블 (a에서 정렬하고 -> b에서 행번호 붙임)



 
SELECT employee_id, first_name
  FROM employees;