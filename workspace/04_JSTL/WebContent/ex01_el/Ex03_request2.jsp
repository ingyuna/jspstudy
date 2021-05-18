<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>

	<%	
		// 표현식을 사용하면, 꺼내서 캐스팅하기
		String name = (String)request.getAttribute("name");
		int age = (int)request.getAttribute("age");
	%>
	<h3>이름 : <%=name%></h3>
	<h3>나이 : <%=age%></h3>
	
	<%-- 표현언어(EL)를 사용하면, 그냥 사용 --%>
	<h3>이름: ${name}</h3>
	<h3>나이: ${age}</h3>

</body>
</html>