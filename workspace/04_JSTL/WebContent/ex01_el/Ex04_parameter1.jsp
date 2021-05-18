<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>

	<form action="Ex04_parameter2.jsp"> <%-- 같은 경로에 있으니까 파일명만 적어도 된다. --%>
		<input type="text" name="name" placeholder="이름"><br>
		<input type="text" name="age" placeholder="나이"><br>
		<input type="radio" name="gender" value="남">남자
		<input type="radio" name="gender" value="여">여자<br>
		<input type="checkbox" name="hobbies" value="게임">게임
		<input type="checkbox" name="hobbies" value="영화">영화
		<input type="checkbox" name="hobbies" value="혼술">혼술<br>
		<input type="text" name="tel" size="4">-
		<input type="text" name="tel" size="4">-
		<input type="text" name="tel" size="4"><br>		<%-- name이 똑같으면 배열이 아니어도 하나의 배열로 본다. --%>	
		<button>전송</button>
	</form>

</body>
</html>