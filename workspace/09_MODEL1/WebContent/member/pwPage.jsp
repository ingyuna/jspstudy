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
			const pw0 = $('#pw0');
			const pw = $('#pw');
			const pw1 = $('#pw1');
			const f = $('#f');
			f.on('submit', function(event){
				if (pw0.val() != '${loginDTO.pw}') {
					alert('현재 비밀번호를 확인하세요.');
					event.preventDefault();
					return false;
				} else if (pw.val() == '${loginDTO.pw}') {
					alert('같은 비밀번호입니다.');
					event.preventDefault();
					return false;
				} else if (pw.val() != '' || pw1.val() == '') {
					alert('새 비밀번호를 확인하세요.');
					event.preventDefault();
					return false;
				} else if (pw.val() != pw1.val()) {
					alert('새 비밀번호를 확인하세요.');
					event.preventDefault();
					return false;
				}
			})
			
		})
	</script>
	<style>
		.container {
			width: 500px;
			margin: 100px auto;
			text-align: center;
		}
		label {
			display: inline-block;
			width: 150px;
		}
	</style>
</head>
<body>
	<div class="container">
		<form id="f" action="pwChange.jsp" method="post">
			<label for="pw0">현재 비밀번호</label><input type="password" id="pw0"><br>	<!-- 보낼려는 애들이 아니기 때문에, name이 필요없다. 단지 script 처리하기 위함일뿐 -->
			<label for="pw">새 비밀번호</label><input type="password" name="pw" id="pw"><br>	<!-- 실제로 DB로 보낼 애는 얘 하나. -->
			<label for="pw1">비밀번호 확인</label><input type="password" id="pw1"><br><br>	<!-- 얘도 같은 이유로 name이 필요없다. -->
			<button>비밀번호 변경하기</button>
			<input type="button" value="되돌아가기" id="return_btn">
		</form>
	</div>
</body>
</html>