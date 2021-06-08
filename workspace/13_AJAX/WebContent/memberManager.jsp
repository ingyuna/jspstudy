<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		// 페이지 로드 이벤트
		$(document).ready(function(){
			selectMemberList();
			selectMemberByNo();
			updateMember();
			init();
			insertMember();
			deleteMember();
			fn_paging();
		})
		// 함수
		
		// 페이징 처리를 위한 현재 페이지
		var page = 1;
		
		// 1. 회원 목록 가져오기
		function selectMemberList() {
			$.ajax({
				url: '/13_AJAX/selectMemberList.do',
				type: 'get',					// 보낼 데이터는 없기 때문에 data는 생략.
				data: 'page=' + page,
				dataType: 'json',
				success: function(result) {
					/*
						result = {
							"list":
								[
									{
										"no":5,
										"address":"부산",
										"gender":"여",
										"name":"에이미",
										"id":"user5"
									},
									{"no":4,"address":"대구","gender":"남","name":"스미스","id":"user4"},
									{"no":3,"address":"대전","gender":"여","name":"제시카","id":"user3"},
									{"no":2,"address":"인천","gender":"여","name":"앨리스","id":"user2"},
									{"no":1,"address":"서울","gender":"남","name":"제임스","id":"user1"}
								],
							"isExist":true}
						}
					*/
					
					// 전체 회원 수 
					$('#totalRecord').text('전체 회원: ' + result.paging.totalRecord + '명');
					
					// 기존의 목록을 화면에서 제거
					$('#memberList').empty();
					
					
					// 회원 목록 출력
					if (result.isExist) {	// 목록이 있다면,
						generateMemberList(result.list);
					} else {
						$('<tr>')
						.append( $('<td colspan="6">').text('회원 목록이 없습니다.') )		// <tr>에 <td>를 추가
						.appendTo('#memberList');	// #memberList에 넣기
					}
					
					// 페이징 출력
					var paging = result.paging;		// 페이징만 써서 변수에 담아둔다 (많이 써야하니까)
					
					// 페이징 영역 초기화
					$('#paging').empty();
					
					// console.log(paging.beginPage + ", " + paging.pagePerBlock);
					
					// 이전
					if (paging.beginPage <= paging.pagePerBlock) {		// 이전이 없는 1블록
						// class
						// 1. disable : 링크가 없다. css 색상 lightgray
						$('<div class="disable">이전</div>').appendTo('#paging');	
					} else {	
						// class
						// 1. prev_block : click 이벤트에서 사용
						// 2. link : 링크가 있다. css 포인터
						$('<div class="prev_block link" data-page="' + (paging.beginPage - 1) + '">이전</div>').appendTo('#paging');
					}
					// 1 2 3 4 5 
					for (let p = paging.beginPage; p <= paging.endPage; p++) {
						if (paging.page == p) {		// 현재 페이지는 링크가 없다.
							// class
							// 1. now_page : 링크가 없다. css 색상 limegreen				
							$('<div class="now_page">' + p + '</div>').appendTo('#paging');
						} else {
							// class
							// 1. go_page : click 이벤트에서 사용
							// 2. link : 링크가 있다. css 포인터						
							$('<div class="go_page link" data-page="' + (p) + '">' + p + '</div>').appendTo('#paging');
						}
					}
					// 다음
					if (paging.endPage == paging.totalPage) {		// 링크가 없는 마지막 블록
						// class
						// 1. disable : 링크가 없다. css 색상 lightgray
						$('<div class="disable">다음</div>').appendTo('#paging');
					} else {
						// class
						// 1. next_block : click 이벤트에서 사용
						// 2. link : 링크가 있다. css 포인터
						$('<div class="next_block link" data-page="' + (paging.endPage + 1) + '">다음</div>').appendTo('#paging');
					}
				},
				error: function(xhr, status, error) {
					console.log(status + " : " + error);
					alert('회원 목록 로드를 실패했습니다.');
				}			
			})			
		}
		// 1-1. 회원 목록을 받아서 테이블을 생성하는 함수
		function generateMemberList(list) {
			$.each(list, function(i, member){		// 여기서 member는 [ { } ] <- 위에 있는 사람 한명을 얘기함. 
				$('<tr>')
				.append( $('<td>').text(member.no) )		// 꺼내서 쓰기 쉽게 하겠다.
				.append( $('<td>').text(member.id) )
				.append( $('<td>').text(member.name) )
				.append( $('<td>').text(member.gender) )
				.append( $('<td>').text(member.address) )
				.append( $('<input type="hidden" name="no">').val(member.no) )
				.append( $('<td>').html('<input type="button" value="조회" id="view_btn"><input type="button" value="삭제" id="delete_btn">') )			
										// 버튼이 들어갈 수 있게 일반 text가 아니라 html 태그로.
				.appendTo('#memberList');
			})
		}
		// 1-2. 페이징의 링크를 처리하는 함수 : 이동할 페이지 번호를 계산하고 selectMemberList() 함수 호출 
		function fn_paging() {
			$('body').on('click', '.prev_block', function(){
				page = $(this).data('page');				// ﻿$(this).attr('data-page')요렇게 해도된다.	
															// $(this) : 클릭한 .prev_block 클래스
				selectMemberList();
			})
			$('body').on('click', '.go_page', function(){
				page = $(this).data('page');
				selectMemberList();
			})
			$('body').on('click', '.next_block', function(){
				page = $(this).data('page');
				selectMemberList();
			})
		}
		// 2. 회원 정보 가져오기
		function selectMemberByNo() {
			/* ajax로 만든 동적 객체(#view_btn)에 이벤트 처리가 안 됨
			$('#view_btn').on('click', function(){		// ajax로 만든거(동적으로 만듦) / '#view_btn' : 원래 있던거.
				alert('가져오려고?');
			})
			*/
			$('body').on('click', '#view_btn', function(){		// 'body' : 정적으로 만든거.
				// 회원번호 출력하기
				// var no = $(this).parent().parent().find('input:hidden[name="no"]').val();	 -> 이렇게 해도 된다.
				var no = $(this).parents('tr').find('input:hidden[name="no"]').val();		// this == view_btn
				$.ajax({
					url: '/13_AJAX/selectMemberByNo.do',
					type: 'get',
					data: 'no=' + no,
					success: function(result) {
						/*
							result = {
									"no":5,
									"address":"부산",
									"gender":"여",
									"name":"에이미",
									"id":"user5"
									"isExist":true
								}
						*/
						if (result.isExist) {
							// $('.left input:text[name="id"]').val(result.id);		-> 이렇게 해도 된다.
							$('#id').val(result.id);
							$('#name').val(result.name);
							$('.left input:radio[name="gender"][value="' + result.gender + '"]').prop('checked', true);		// prop = property (attr와 같은 '속성'인데 얘는 안먹힘)
													// value 속성만 하면 가져오고 마는거라서, 선택해주는 '체크'를 하기위해 요렇게.
							$('#address').val(result.address);
							$('.left input:hidden[name="no"]').remove(); 	// 기존 hidden 제거
							$('.left').append( $('<input type="hidden" name="no">').val(result.no) );	// 새 hidden 추가 
						} else {
							alert('해당 회원 정보를 확인할 수 없습니다.')
						}
					},
					error: function(xhr, status, error) {
						console.log(status + " : " + error);
						alert('회원 정보 로드를 실패했습니다.');
					}		
				})
			})
		}
		
		// 3. 회원 정보 수정하기
		function updateMember() {
			$('#update_btn').click(function(){
				// 서버로 JSON 데이터 보내기
				var obj = {
					"no": $('input:hidden[name="no"]').val(),
					"id": $('#id').val(),
					"name": $('#name').val(),
					"gender": $('input:radio[name="gender"]:checked').val(),
					"address": $('#address').val()
				};
				//console.log(JSON.stringify(obj));
				$.ajax({
					url: '/13_AJAX/updateMember.do',
					type: 'post',
					data: 'member=' + JSON.stringify(obj),
					dataType: 'json', 
					success: function(result) {
						if (result.isSucess) {
							alert('회원 정보가 수정되었습니다.');
							selectMemberList();		// 회원 목록을 다시 만들기
						} else {
							alert('변경된 회원 정보가 없습니다.');
						}
					},
					error: function(xhr, status, error) {
						console.log(status + " : " + error);
						alert('회원 정보 수정이 실패했습니다.');
					}	
				})			
			})
		}
		// 4. 초기화
		function init() {
			$('#init_btn').click(function(){
				$('#id').val('');	// 괄호안에 ('') 이렇게 비어있는 문자열을 넣어줘야 한다. 그냥 비워두면 X
				$('#name').val('');
				$('input:radio[name="gender"]').prop('checked', false);
				$('#address').val('');
			})
		}
		// 5. 회원 추가하기
		function insertMember(){
			$('#insert_btn').click(function(){
				var obj = {
					id: $('#id').val(),
					name: $('#name').val(),
					gender: $('input:radio[name="gender"]:checked').val(),
					address: $('#address').val()	
				};	
				// console.log(JSON.stringify(obj));	
				$.ajax({
					url: '/13_AJAX/insertMember.do',
					type: 'post',
					data: 'member=' + JSON.stringify(obj),
					dataType: 'json',
					success: function(result) {
						if (result.isSuccess) {
							alert('신규 회원이 추가되었습니다.')
							selectMemberList();		// 변경된 회원 목록이 나타날 수 있게 추가해준다. 
						} else {
							alert('신규 회원으로 등록되지 않았습니다.')
						}
					},
					error: function(xhr, status, error) {
						console.log(status + " : " + error);
						alert('회원 등록이 실패했습니다.');
					}	
				})
			})	
		}	
		// 6. 회원 삭제하기
		function deleteMember() {
			$('body').on('click', '#delete_btn', function(){	// 동적으로 만들어진 버튼이라 이렇게 해줘야 버튼이 실행된다.
				/*
					<tr>
						<td>
							<input type="button" value="삭제" id="delete_btn">
						</td>
						<input type="hidden" name="no" value="5">
					</tr>
				*/
				var no = $(this).parent().parent().find('input:hidden[name="no"]').val();
				// alert(no);
				if (confirm(no + '번 회원을 삭제할까요?')) {
					$.ajax({
						url: '/13_AJAX/deleteMember.do',
						type: 'get',		// 원래는 type:'delete'라고 해야한다. 근데 지금은 안배웠기때문에 'get'으로. (나중에 REST풀 할 때 할 거)
						data: 'no=' + no,
						dataType: 'json',
						success: function(result) {
							if (result.isSuccess) {
								alert('회원 정보가 삭제되었습니다.');
								selectMemberList();		// 삭제가 되었으니까, 변경된 목록을 다시 보여준다.
							} else {
								alert('삭제된 회원이 없습니다.')
							}
						},
						error: function(xhr, status, error) {
							console.log(status + " : " + error);
							alert('회원 삭제가 실패했습니다.');
						}	
					})
				}
			})	
		}
	</script>
	<style>
		* {
			box-sizing: border-box;
		}
		.container {
			display: flex;
		}
		.left {
			width: 500px;
		}
		.right {
			width: 1000px;
		}
		table {
			width: 100%;
			border-collapse: collapse;
		}
		td {
			padding: 5px 10px;
			text-align: center;
			border-top: 1px solid gray;
			border-bottom: 1px solid gray;
		}
		/* 페이징 처리 */
		#paging {
			width: 50%;
			margin: 0 auto;
			display: flex;
			justify-content: space-between;
			text-align: center;
		}
		#paging div {
			width: 40px;
			height: 20px;
		}
		.disable {
			color: lightgray;
		}
		.link {
			cursor: pointer;
		}
		.now_page {
			color: limegreen;
		}
		
	
	</style>	
</head>
<body>

	<div class="container">
		<%-- 회원삽입/수정/보기 --%>
		<div class="left">
			<h3>삽입/수정/보기</h3>
			아이디<br>
			<input type="text" name="id" id="id"><br><br>
			이름<br>
			<input type="text" name="name" id="name"><br><br>
			성별<br>
			<label><input type="radio" name="gender" value="남">남</label>
			<label><input type="radio" name="gender" value="여">여</label><br><br>
			주소<br>
			<select name="address" id="address">		
				<option value="">지역 선택</option>
				<option value="서울">서울</option>
				<option value="인천">인천</option>
				<option value="대전">대전</option>
				<option value="대구">대구</option>
				<option value="광주">광주</option>
				<option value="울산">울산</option>
				<option value="부산">부산</option>
			</select><br><br>
			<input type="button" value="회원등록" id="insert_btn">
			<input type="button" value="정보수정" id="update_btn">
			<input type="button" value="초기화" id="init_btn">
		</div>
		<%-- 회원목록/삭제 --%>
		<div class="right">
			<h3>회원목록/삭제</h3>
			<div id="totalRecord"></div>
			<table>
				<thead>
					<tr>
						<td>회원번호</td>
						<td>아이디</td>
						<td>이름</td>
						<td>성별</td>
						<td>주소</td>
						<td>버튼</td>
					</tr>
				</thead>
				<tbody id="memberList"></tbody>
				<tfoot>
					<tr>
						<td colspan="6">
							<div id="paging"></div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>	
	</div>
</body>
</html>