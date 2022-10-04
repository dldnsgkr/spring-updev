<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./resources/css/admin/report_manage.css">
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
					<td>처리완료</td></tr>
					<c:forEach items="${board}" var="list">
					<tr><td>${list.r_num}</td><td>${list.r_status}</td><td>${list.r_reason}</td><td>${list.r_file1}</td>
					<td><a href="detail?b_num=${list.b_num}">${list.b_num}</a></td>
					<td><a onclick="report_manage_update(${list.r_num});">처리완료</a></td>
					</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="./resources/js/admin/report_manage.js"></script>
</body>
</html>