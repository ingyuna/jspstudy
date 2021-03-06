<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("utf-8"); %>
<jsp:include page="../layout/header.jsp">
	<jsp:param value="로그인" name="title" />
</jsp:include>

<link rel="stylesheet" href="../assets/css/layout.css">
<style>
	.login_form {
		width: 300px;
		margin: 50px auto;
	}
	
	#f input {
		padding: 5px;
		width: 100%;
		height: 50px;
	}
	#f button {
		width: 100%;
		height: 50px;
		line-height: 50px;
		background-color: orange;
		border: none;
		font-size: 18px;
	}
	#f button:hover {
		cursor: pointer;
	}
	.message {
		font-size: 12px;
		color: crimson;
	}
	

</style>


<script>
	$(document).ready(function(){
		const f = $('#f');
		const id = $('#id');
		const pw = $('#pw');
		const id_message = $('#id_message');
		const pw_message = $('#pw_message');
		f.submit(function(event){	// 이벤트 실행해야하니까 event를 펑션에 넣어준다.  // form의 submit이니까 버튼을 눌렀을 때 이벤트 발생.
			if (id.val() == '') {
				id_message.text('아이디를 입력하세요');		// 메세지를 알럿창으로 띄우지않고 아래 나오게.
				id.focus();
				event.preventDefault();
				return false;
			} 
			else if (pw.val() == '') {
				id_message.text('');	// 아이디를 입력했을 때, 아이디 메세지를 지워주는 작업		
				pw_message.text('비밀번호를 입력하세요');
				pw.focus();
				event.preventDefault();
				return false;
			}
		})
		
	})
	
</script>

<div class="login_form">
	<form action="/10_MODEL2/login.m" id="f" method="post">
		<input type="text" name="id" id="id" placeholder="ID"><br>		
		<input type="password" name="name" id="name" placeholder="Password"><br>
		<button>로그인</button>
	</form>
</div>

<%@ include file="../layout/footer.jsp" %>