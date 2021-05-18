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
		pageContext.setAttribute("model", "a-class");
		request.setAttribute("model", "b-class");
		session.setAttribute("model", "c-class");
		application.setAttribute("model", "e-class");
	%>
	<%-- 우선순위 확인 --%>
	${model}<br>	<%-- pageContext의 model --%>
	${requestScope.model}<br>
	${sessionScope.model}<br>	<%-- session은 안적어도 되는 상황에서도 session에 들어있다는걸 정확하게 명시하기 위해서 적어주기도 한다. --%>
	${applicationScope.model}<br>
	
	
</body>
</html>