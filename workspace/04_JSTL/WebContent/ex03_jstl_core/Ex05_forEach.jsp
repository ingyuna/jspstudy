<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
	
	<%
		for (int i = 0; i < 10; i++) {
			out.println(i + "<br>");
		}
	%>
	<c:forEach var="i" begin="0" end="9" step="1">
		${i}<br>
	</c:forEach>
	
	
	<%-- Java 배열을 forEach문으로 사용하기 --%>
	<%
		String[] hobbies = {"낚시", "운동", "등산"};
		pageContext.setAttribute("hobbies", hobbies);
	%>
	<c:forEach var="hobby" items="${hobbies}">
		${hobby}<br>
	</c:forEach>
	
	<%-- varStatus : index, count --%>
	<c:forEach var="hobby" items="${hobbies}" varStatus="k">	<%-- varStatus 이름은 변수 이름처럼 해도 된다. --%>
		${hobby}의 인덱스: ${k.index}, 순번: ${k.count}<br>		<%-- ${k.index + 1}로 해도 순번을 확인할 수 있다. --%>
	</c:forEach>
	
	<%-- ArryaList forEach문으로 사용하기 --%>
	<%
		List<String> list = new ArrayList<>();
		list.add("튀김");
		list.add("떡볶이");
		list.add("순대");
		pageContext.setAttribute("list", list);
	%>
	<c:forEach var="food" items="${list}" varStatus="p">
		${food}의 인덱스: ${p.index}<br>
	</c:forEach>
	
	
	
	
	
</body>
</html>