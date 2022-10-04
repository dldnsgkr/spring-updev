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
			<p>신고관리</p>
		</div>
		<div class="content">
			<div id="out">
				<table border="1">
					<tr><td>번호</td><td>상태</td><td>사유</td><td>첨부파일</td><td>게시글 번호</td>
					<td>회원 닉네임</td><td>삭제</td><td>수정</td>
					</tr>
					
					<c:forEach items="${board}" var="list">
					<tr><td>${list.r_num}</td><td>${list.r_status}</td><td>${list.r_reason}</td><td>${list.r_file1}</td>
					<td>${list.b_num}</td><td>${list.m_nick}</td><td>삭제</td><td>수정</td>
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