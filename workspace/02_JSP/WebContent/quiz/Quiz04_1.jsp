<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		$(document).ready(function(){
			$('#result').on('blur', function(){		// 숫자 외에 다른것을 한 글자만 입력해도 경고창이 뜨게 하려면
													// 'keyup'이라고 하면 된다. 대신 이거는 - 입력해도 경고창이 뜬다.
				if ( isNaN($(this).val()) ) {	// $(this) == $('#result')
					alert('정수만 입력할 수 있습니다.');
					$(this).val('');
					$(this).focus();
				}
			})
		})
	</script>
</head>
<body>
	
	<%
		// 1 ~9 랜덤 생성
		int a = (int)(Math.random() * 9) +1;
		int b = (int)(Math.random() * 9) +1;
		
		// +, -, *, /, % 랜덤 생성
		String[] opList = {"+", "-", "*", "/", "%"};
		String op = opList[(int)(Math.random() * 5)];
		
		// 연산
		int answer = 0;
		switch (op) {
		case "+": answer = a + b; break;
		case "-": answer = a - b; break;
		case "*": answer = a * b; break;
		case "/": answer = a / b; break;
		case "%": answer = a % b; break;
		}
	%>
	
	<h3>랜덤 계산기</h3>
	<form action="/02_JSP/quiz/Quiz04_2.jsp" method="post">		<!-- 뒤에 주렁주렁 안달리게 하려면 method="post"로 해준다. -->
		<%=a%>&nbsp;<%=op%>&nbsp;<%=b%>&nbsp;=&nbsp;
		<input type="text" name="result" size="3" id="result">
		<input type="hidden" name="a" value="<%=a%>">
		<input type="hidden" name="b" value="<%=b%>">
		<input type="hidden" name="op" value="<%=op%>">
		<input type="hidden" name="answer" value="<%=answer%>">
		
		
		<button>제출</button>
	</form>
	
</body>
</html>