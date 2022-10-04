<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./resources/css/admin/member_manage.css">
</head>
<body>
	<div class="infoupdate">
		<div class="title">
			<span></span>
			<p>회원관리</p>
		</div>
		<div class="content">
			<div id="out">
				<table border="1">
					<tr><td>번호</td><td>아이디</td><td>닉네임</td><td>이름</td>
					<td>메일</td><td>전화번호</td><td>분야</td><td>가입일자</td><td>등급</td>
					<td>삭제</td><td>수정</td>
					</tr>
					<c:forEach items="${board}" var="list">
					<tr><td>${list.m_num}</td><td>${list.m_id}</td><td>${list.m_nick}</td>
					<td>${list.m_name}</td><td>${list.m_mail}</td><td>${list.m_tel}</td><td>${list.m_field}</td>
					<td>${list.m_jdate}</td><td>${list.m_grade}</td>
					<td><a onclick="member_manage_delete(${list.m_num});">삭제</a></td>
					<td>수정</td>
					</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="./resources/js/admin/member_manage.js"></script>
</body>
</html>