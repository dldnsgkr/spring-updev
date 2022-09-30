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
#side{
display: block;
}
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
<script type="text/javascript" src="./resources/js/admin/boardmanage.js"></script>
</head>
<body>
	<div class="infoupdate">
		<div class="title">
			<span></span>
			<p>공지게시판</p>
		</div>
		<div class="content" id="out">
			
		</div>
	</div>
</body>
</html>