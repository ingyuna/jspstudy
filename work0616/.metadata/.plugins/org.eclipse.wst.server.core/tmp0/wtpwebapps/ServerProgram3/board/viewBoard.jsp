<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
	$(document).ready(function(){
		const delete_btn = $('#delete_btn');
		delete_btn.click(function(){
			if (confirm('삭제할까요?')) {
				location.href='/ServerProgram3/deleteBoard.do?no=${dto.no}';
			}
		})
	})
	</script>
	<style>
		.container {
			margin: 0 auto;
			width: 500px;
			text-align: center;
		}
	
	</style>
</head>
<body>
	<div class="container">
	<div class="board_view">
		<div class="board_content">
		
			<h3>${dto.no}번 게시물</h3>
		
			작성자: ${dto.author}<br><br>
			작성일: ${dto.postdate}<br><br>
			작성IP: ${dto.ip}<br><br>
			조회수: ${dto.hit}<br><br>
			제목 : ${dto.title}<br><br>
			내용
			<pre>${dto.content}</pre>
		<form method="post">
			<input type="button" value="삭제하기" id="delete_btn">		
			<input type="button" value="목록보기" onclick="location.href='${referer}'">
		</form>		
		</div>
	</div>

	<hr>
	
	<%-- 댓글 입력창 --%>
	<div class="reply_form">
		<form action="/Serverprogram3/insertReply.do" method="post"> 
			<textarea name="content" placeholder="댓글을 입력하세요."></textarea><br>
			<input type="text" name="writer" placeholder="작성자">
			<button>작성</button>	
		</form>
	</div>
	
	<%-- 댓글 목록창 --%>
	<div class="reply_list">
		<table>
			<tbody>
				<c:forEach var="replyDTO" items="${replyList}">
					<tr>
						<td>${replyDTO.content}</td>
						<td>${replyDTO.author}</td>
						<td>${replyDTO.ip}</td>
						<td>${replyDTO.postdate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
</body>
</html>