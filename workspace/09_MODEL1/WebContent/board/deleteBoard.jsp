<%@page import="dao.BoardDAO"%>
<%@page import="dto.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 1. 삭제할 게시글 번호 확인
	long idx = ((BoardDTO)session.getAttribute("boardDTO")).getIdx();

	// 2. DAO의 deleteBoard() 메소드 호출
	int result = BoardDAO.getInstance().deleteBoard(idx);
	pageContext.setAttribute("result", result);
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${result eq 1}">
	<h1>게시글이 삭제되었습니다.</h1>
	<a href="baordList.jsp">게시판으로 이동</a>
</c:if>
<c:if test="${result eq 0}">	<%-- 삭제가 안됐을 때 --%>
	<h1>게시글이 삭제되지 않았습니다.</h1>
	<a href="view.jsp?idx=<%=idx%>">게시글로 돌아가기</a>	<%-- view.jsp로 가려면 그냥 가면 안되고 index가 있어야한다. --%>
	<a href="boardList.jsp">게시판으로 이동</a>
</c:if>