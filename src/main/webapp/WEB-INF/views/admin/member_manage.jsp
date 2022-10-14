<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./resources/css/admin/member_manage.css">
<link rel="stylesheet" href="./resources/css/admin/admin_board.css" type="text/css">
</head>
<body>
<div class="wrap">
	<div class="infoupdate">
		<div class="title">
			<span></span>
			<h1>회원관리</h1>
		</div>
		<div class="content">
			<div id="out">
				<table border="1">
					<tr><td>번호</td><td>아이디</td><td>닉네임</td><td>이름</td>
					<td>메일</td><td>전화번호</td><td>분야</td><td>가입일자</td><td>등급</td>
					<td>삭제</td><td>수정</td>
					</tr>
					<c:forEach items="${bpage1}" var="list">
					<tr><td>${list.m_num}</td><td>${list.m_id}</td><td>${list.m_nick}</td>
					<td>${list.m_name}</td><td>${list.m_mail}</td><td>${list.m_tel}</td><td>${list.m_field}</td>
					<td>${list.m_jdate}</td><td>${list.m_grade}</td>
					<td><a onclick="member_manage_delete(${list.m_num});">삭제</a></td>
					<td>수정</td>
					</tr>
					</c:forEach>
				</table>
				<div id="tfoot">
				<c:if test="${page1.nowPage > 10}">
					<a href="member_manage?nowPage=${page1.startPage -1}">&#60;</a> 				
				</c:if>
					
				<c:forEach begin="${page1.startPage}" end="${page1.endPage}" var="p">
					<c:choose>
						<c:when test="${p==page1.nowPage}">
							<b>${p}</b>
						</c:when>
						<c:when test="${p!=page1.nowPage}">
							<a href="member_manage?nowPage=${p}&cntPerPage=${page1.cntPerPage}">${p}</a>
						</c:when>
					</c:choose>
				</c:forEach>
				<!-- 
				<c:if test="${page1.nowPage ne page1.realEnd && page1.endPage>0}"></c:if>
				 -->			
				<c:if test="${page1.next && page1.endPage>0}">
					<a href="member_manage?nowPage=${page1.endPage +1}">&#62;</a>
				</c:if>
			</div>
			</div>
		</div>
	</div>
	</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="./resources/js/admin/member_manage.js"></script>
</body>
</html>