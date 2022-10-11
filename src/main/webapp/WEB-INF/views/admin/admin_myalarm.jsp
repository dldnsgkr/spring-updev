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
				<tr><td>번호</td><td>내용</td><td>날짜</td></tr>
				<c:forEach items="${bpage1}" var="list">
				<tr><td>${list.a_num}</td>
				<td><a href="detail?b_num=${list.b_num}">${list.a_content}</a></td>
				<td>${list.a_adate}</td></tr>
				</c:forEach>
			</table>
			<c:if test="${page1.nowPage > 10}">
					<a href="admin_mylist?nowPage=${page1.startPage -1}">&#60;</a> 				
				</c:if>
					
				<c:forEach begin="${page1.startPage}" end="${page1.endPage}" var="p">
					<c:choose>
						<c:when test="${p==page1.nowPage}">
							<b>${p}</b>
						</c:when>
						<c:when test="${p!=page1.nowPage}">
							<a href="admin_mylist?nowPage=${p}&cntPerPage=${page1.cntPerPage}">${p}</a>
						</c:when>
					</c:choose>
				</c:forEach>
							
				<c:if test="${page1.next && page1.endPage>0}">
					<a href="admin_mylist?nowPage=${page1.endPage +1}">&#62;</a>
				</c:if>
		</div>
	</div>
</body>
</html>