<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./resources/css/admin/admin_mylist.css">
</head>
<body>
	<div>
		<div class="title">
			<span></span>
			<p>마이 알림</p>
		</div>
		<div class="content">
			<table border="1">
				<tr><td>내용</td><td>날짜</td><td>읽음</td><td>읽지않음</td></tr>
				<c:forEach items="${list}" var="list">
				<tr><td>${list.a_content}</td><td>${list.a_adate}</td><td>읽음</td><td>읽지않음</td></tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>