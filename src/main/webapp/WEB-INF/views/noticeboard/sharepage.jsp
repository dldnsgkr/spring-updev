<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>작성일</th>
		<th>조회수</th>
		<th>추천수</th>
		<th>스크랩</th>
	</tr>
	<c:forEach items="${listb }" var="a">
	<tr>
		<td>${a.b_num }</td>
		<td>${a.b_title }</td>
		<td>${a.m_nick }</td>
		<td>${a.b_wdate }</td>
		<td>${a.b_readcnt }</td>
		<td>${a.b_likecnt }</td>
		<td>스크랩 버튼</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>