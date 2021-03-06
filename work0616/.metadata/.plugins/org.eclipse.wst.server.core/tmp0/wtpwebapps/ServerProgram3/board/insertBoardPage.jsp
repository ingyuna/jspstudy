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
		
		const f = $('#f');
		const title = $('#title');
		const insert_btn = $('#insert_btn');
		insert_btn.click(function(){
			f.attr('action', '/ServerProgram3/insertBoard.do');
			f.submit();
		})
		
		const list_btn = $('#list_btn');
		list_btn.click(function(){
			location.href = '/ServerProgram3/selectBoardList.do';
		})
		
	})
</script>
</head>
<body>

	<div class="insert_form">
	<h3>게시글 작성하기</h3>
	<form id="f" method="post">
		<p class="title">작성자</p>
		<input type="text" id="author" name="author" value="${loginDTO.id}" autofocus><br>
		<p class="title">제목</p>
		<input type="text" id="title" name="title"><br>
		<p class="content">내용</p>
		<textarea id="content" name="content"></textarea><br>
			
		<div class="btns">
			<input type="button" value="저장하기" class="btn" id="insert_btn">
			<input type="reset" value="작성초기화" class="btn" id="reset_btn">
			<input type="button" value="목록보기" class="btn" id="list_btn">	
		</div>
	</form>
	
</div>
	
</body>
</html>