<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// request에 속성을 저장해 두기
	request.setAttribute("name", "브레드");
	request.setAttribute("age", 50);
	
	// request를 그대로 유지하는(전달하는) forward
	request.getRequestDispatcher("/ex06/Ex02_request2.jsp").forward(request, response);
										// 앞에 /ex06 생략하고, 파일이름만 적을 수 있음 (단, 폴더가 달라지지 않으면)
%>