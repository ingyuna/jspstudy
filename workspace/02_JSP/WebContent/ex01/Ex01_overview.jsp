<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- HTML 주석 : 소스보기에서 보인다. -->
<%-- JSP 주석 : 소스보기에서 안 보인다. --%>

<%--
	JSP 
	
	1. Java Server Page
	2. HTML문서 내부에서 Java 코드를 사용할 수 있다.
	3. 서블릿 기반의 서버측 언어이다. (front로 되어있지만, 프론트 언어 X)
	4. 모든 JSP 페이지는 서블릿으로 변한된 뒤 실행된다.
		abc.jsp -> abc_jsp.java -> abc_jsp.class -> run
		(JSP)		(Servlet)	
 --%>
<%--
	JSP 스크립트 요소
	
	1. <%@ 지시어 %> : 지시어 (directive)
		page 지시어, include 지시어, taglib(=tablibrary) 지시어
		
	2. <%! 선언부 %> : 선언부 (declaration)
		전역변수 선언, 메소드 정의
		
	3. <%= 표현식 %> : 표현식 (expression)
		결과값 출력 (변수, 메소드 호출, 연산)
		
	4. <% 스크립트릿 %> : scriptlet    
		Java 코드
--%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<% // 여기는 자바 영역입니다. %>
	
	<% int a = 10; %>
	<% if (a > 0) { %>
		<h3><%=a%>은 양수입니다.</h3>
	<% } else { %>
		<h3><%=a%>은 음수입니다.</h3>
	<% } %>		
	
	<% for(int i = 0; i <9; i++) { %>
		<%=i%><br>
	<% } %>	
		
	<%!
		// 메소드 정의는 선언부에서 한다.
		public int getTotal(int n) {
			int total = 0;
			for (int i = 1; i <= n; i++) {
				total += i;
			}
			return total;
		}
	%>
	<h3>결과 : <%=getTotal(10)%></h3> <%-- 반환 타입이 있기 때문에, 표현식으로 묶는다. --%>
	<%!
		int square = 1;		// 전역 변수이다.
		public void power(int n) {
			for (int i = 1; i <= n; i++) {
				square *= i;
			}
		}
	%>
	<% power(5); %>		<%-- 호출하더라도 결과값이 없어서 표현식으로 묶지 않고, 일반 자바코드로 묶어준다. --%> 
	<h3>결과: <%=square%></h3>
	
	
</body>
</html>