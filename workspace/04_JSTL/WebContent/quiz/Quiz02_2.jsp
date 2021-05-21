<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h3>일반 forEach문</h3>
	<c:forEach var="size" begin="${param.min}" end="${param.max}" step="1">
		<font size="${size}">폰트 사이즈 ${size}<br></font>
	</c:forEach>
	
	<%--
	<font size="1">폰트 사이즈 1<br></font>
	<font size="2">폰트 사이즈 2<br></font>
	<font size="3">폰트 사이즈 3<br></font>
	<font size="4">폰트 사이즈 4<br></font>
	<font size="5">폰트 사이즈 5<br></font>
	<font size="6">폰트 사이즈 6<br></font>
	<font size="7">폰트 사이즈 7<br></font>
	 --%>
		
	<h3>향상 forEach문</h3>
	<ul>
		<c:forEach var="food" items="${paramValues.foods}" varStatus="vs">	<%-- varStatus는 어떤 이름이든 상관없다. --%>
			<li>${vs.count}번째 음식: ${food}</li>
		</c:forEach>
	</ul>
	
</body>
</html>