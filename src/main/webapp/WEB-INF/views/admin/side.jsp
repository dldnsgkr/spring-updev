<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./resources/css/side.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<nav>
	<ul class="first">
		<li><span class="on">마이페이지</span></li>
		<li><span>게시판 관리</span></li>
		<li><span>신고 관리</span></li>
		<li><span>회원 관리</span></li>
	</ul>
	
	<ul class="second">
		<li class="menu">
			<ul class="on">
				<li class="on"><a onclick="go_infoupdate();">정보수정</a></li>
				<li><a onclick="go_mylist();">마이 글</a></li>
				<li><a onclick="go_mylist();">마이 알림</a></li>
			</ul>
		</li>
		<li class="menu">
			<ul>
				<li>공지게시판</li>
				<li>고민게시판</li>
				<li>정보공유게시판</li>
				<li>지식인게시판</li>
				<li>홈페이지문의게시판</li>
			</ul>
		</li>
		<li class="menu"><ul>신고 관리</ul></li>
		<li class="menu"><ul>회원 관리</ul></li>
	</ul>
	
	
	<ul class="third">
		<li class="menu">
			<ul class="on">
		
			</ul>
		</li>
		<li class="menu">
			<ul>
	
			</ul>
		</li>
		<li class="menu"><ul>
		</ul></li>
		<li class="menu"><ul>
		</ul></li>
	</ul>
</nav>
<script type="text/javascript" src="./resources/js/admin/side.js"></script>
</body>
</html>