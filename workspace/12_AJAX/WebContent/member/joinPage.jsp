<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style>
		* {
			padding: 0;
			margin: 0;
			box-sizing: border-box;
		}
		label {
			display: block;
			font-weight: 700;
		}
		input {
			padding: 5px 10px;
			width: 250px;
			height: 50px;
			border: 1px solid gray;
			outline: none;
		}
	</style>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
	
		// 페이지 로드 
		$(document).ready(function(){
			
			// DOM선언
			const join_form = $('#join_form');		
			const id = $('#id');
			const id_msg = $('#id_msg');
			const join_btn = $('#join_btn');
			
			a = idCheck();
			
			// 이벤트
			id.blur(function(){			// id가 넘어갔을 때 function()을 하겠다.
				idCheck();
			})			
			
			join_btn.click(function(){
				join();
			})
			
			
			// 함수
			function idCheck() {
				// 반환
				let result = false;
				// 아이디 정규식 : 4 ~ 32자, 소문자, 숫자, 특수문자(_, -), 시작은 소문자
				const regID = /^[a-z][a-z0-9_-]{3,31}$/;	// 앞에서 한글자 이미 썼으니까 뒤에는 3글자~31자가 된다.
					
					if ( regID.test(id.val()) ) { 	// 입력된 ID의 정규식 검사
						$.ajax({		// ajax는 객체를 받고, ulr 프로퍼티, type프로퍼티, data 프로퍼티를 갖고 있다.
							url: '/12_AJAX/idCheck.do',
							type: 'post',
							data: 'id=' + id.val(),		// 파라미터 id를 서버로 보낸다.
							dataType: 'json',			// 서버가 보내준 응답 데이터의 타입
							success: function(obj) {	// 서버의 응답 데이터는 function()의 매개변수
								// obj : {"result" : true} 또는 {"result": false}
								// obj.result == obj["result"]	-> 둘 중 하나를 써서 true 또는 false가 나온다
								if (obj.result) {	// 사용 가능한 아이디
										id_msg.text('멋진 아이디네요~');
										result = true;
								} else {
										id_msg.text('이미 가입된 아이디입니다');
								}
							},
							error: function() {
								alert('오류가 발생했습니다.');		// 왜 발생했는지는 모른다.
							}
						})						
					} else {
						id_msg.text('4~32자, 소문자/숫자/특수문자(_, -), 소문자 시작');
					}		
				return result;		// id체크가 실패하면, 가입하기 버튼을 눌러도 submit 되지 않는다.
			}
			
			function join() {
				$.ajax({
					url: '/12_AJAX/join.do',
					type: 'post',
					data: join_form.serialize(),		// join_form의 모든 파라미터를 보낸다.
					datType: 'json',
					success: function(obj) {
						if (obj.result == 1) {
							alert('회원 가입되었습니다.');
							location.href = '/12_AJAX/loginPage.do';
						} else {
							alert('회원 가입이 실패했습니다.');
						}
					},
					error: function(){
						alert('오류가 발생했습니다.');
					}
				})
			}	
		})
	</script>
</head>
<body>

	
	<form id="join_form" method="post">
	
		<h1>회원가입</h1>
		
		<label for="id">아이디</label>
		<input type="text" id="id" name="id">
		<div id="id_msg"></div>
		
		<label for="pw">비밀번호</label>
		<input type="password" id="pw" name="pw">
		<div id="pw_msg"></div>
		
		<label for="name">이름</label>
		<input type="text" id="name" name="name">
		<div id="name_msg"></div>
		
		<label for="email">이메일</label>
		<input type="text" id="email" name="email">
		<div id="email_msg"></div>
		
		<label for="phone">전화번호</label>
		<input type="text" id="phone" name="phone">
		<div id="phone_msg"></div>
		
		<input type="button" value="가입하기" id="join_btn">
		
	</form>










</body>
</html>