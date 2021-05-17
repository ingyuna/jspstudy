<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- 1~100 사이 모든 홀수를 출력하시오. --%>
	<% for (int n = 1; n <= 100; n++) { %>
		<% if (n % 2 == 1) { %>
			<%=n%>&nbsp;&nbsp;
		<% } %>
		<% if (n % 10 == 0) { %>
			<br>
		<% } %>
	<% } %>	
	
	<% 
		for (int n= 1; n <= 100; n++) {
			if (n % 2 == 1) {
				out.println(n + "&nbsp;&nbsp;");	// 복잡하지 않은 출력문이라면,
													// 이렇게 out 객체를 쓰면 가독성을 높일 수 있다. (=읽기 쉬움)
			}
			if (n % 10 == 0) {
				out.println("<br>");
			}
		}
	%>
	
</body>
</html>