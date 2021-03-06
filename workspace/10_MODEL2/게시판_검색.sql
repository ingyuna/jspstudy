SELECT IDX, TITLE, CONTENT, AUTHOR
  FROM BOARD
 WHERE TITLE LIKE '%글%';
 
SELECT B.IDX, B.AUTHOR, B.TITLE, B.CONTENT, B.HIT, B.IP, B.FILENAME, B.STATE, B.POSTDATE, B.LASTMODIFIED
  FROM (SELECT ROWNUM AS RN, A.IDX, A.AUTHOR, A.TITLE, A.CONTENT, A.HIT, A.IP, A.FILENAME, A.STATE, A.POSTDATE, A.LASTMODIFIED
          FROM (SELECT IDX, AUTHOR, TITLE, CONTENT, HIT, IP, FILENAME, STATE, POSTDATE, LASTMODIFIED
                  FROM BOARD
                 WHERE TITLE LIKE '%글%'
                 ORDER BY POSTDATE DESC) A) B
  WHERE B.RN BETWEEN 1 AND 5;
  

SELECT * FROM BOARD;