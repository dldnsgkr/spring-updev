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
</style>
<script type="text/javascript" src="./resources/js/infoupdate.js"></script>
</head>
<body>
	<div class="infoupdate">
		<div class="title">
			<span></span>
			<p>마이 글</p>
		</div>
		<div class="content">
			내가 쓴 글<br>
			
			
			내가 좋아요 한 글<br>
			
			
			내가 스크랩 한 글<br>
		</div>
	</div>
</body>
</html>