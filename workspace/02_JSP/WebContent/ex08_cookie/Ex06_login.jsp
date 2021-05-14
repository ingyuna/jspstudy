<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style>
		.container{
			margin: 300px auto;
			width: 300px;
		}
		.blind {
			display: none;	/* 낭독기가 'id'라고 읽어주게 하기 위해서 안보이게 할꺼여도 만듦. */
		}	
	</style>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	
</head>
<body>

	<%
		// 쿠키명이 id인 쿠키를 찾는다.
		String id = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length != 0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("id")) {
					id = cookie.getValue();
					break;
				}
			}
		}	
	%>
	
	<script>
	
		$(document).ready(function(){
			
			if ('<%=id%>' != 'null') {		// 변수처리때문에 ''로 묶는다고 한건가,, 안묶으면 admin 오류가 난다.
				$('#id').val('<%=id%>');
				$('#chk').attr('checked', true);
			}
		})
		
	
	</script>
	
	

	<div class="container">
		<form action="/02_JSP/ex08_cookie/Ex06_idCheck.jsp" method="post">
			<label for="id" class="blind">아이디</label>
			<input type="text" name="id" id="id" placeholder="아이디"><br>
			<label for="pw" class="blind">비밀번호</label>
			<input type="password" name="pw" id="pw" placeholder="●●●●"><br>
			<button>로그인</button><br><br>
			<input type="checkbox" name="chk" id="chk">
			<label for="chk">아이디 기억하기</label>
		</form>
	</div>

</body>
</html>