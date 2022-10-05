<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="./resources/js/ajaxmywrite.js"></script>
</head>
<body>
<table>
<tr><td>번호</td><td>분류</td><td>제목</td>
<td>작성일</td><td>추천수</td><td>조회수</td></tr>
<c:forEach items="${list}" var="list">
	<tr><td>${list.b_num}</td><td>${list.b_kind}</td>
	<td><a href="detail?b_num=${list.b_num}">${list.b_title}</a></td>
	<td>${list.b_wdate}</td><td>${list.b_likecnt}</td><td>${list.b_readcnt}</td>
	</tr>
</c:forEach>
</table>
</body>
</html>