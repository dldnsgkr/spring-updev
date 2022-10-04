<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./resources/css/info_update.css">
<style type="text/css">
.infoupdate{
float: left;
}
label{
	display: block;
}
.profile{
	width: 250px;
    float: left;
    height: 300px;
    margin-right: 50px;
}
.info{
    float: left;
}
input[type="button"]{
	display: block;
}
.content ul li{
	float: left;
	display: block;
	border: 2px solid #242038;
	border-right: 0;
}
.content ul li:last-child{
	border-right: 2px solid #242038;
}
.content ul{
	height: 50px;
}
</style>
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
					<td>${list.m_jdate}</td><td>${list.m_grade}</td><td>삭제</td><td>수정</td>
					</tr>
					</c:forEach>
				
				</table>
			
			</div>
		</div>
	</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- 
<script type="text/javascript" src="./resources/js/admin/admin_mylist.js"></script>
 -->
</body>
</html>