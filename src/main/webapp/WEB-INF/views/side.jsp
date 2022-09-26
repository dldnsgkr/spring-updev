<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./resources/css/nav.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="./resources/js/nav.js"></script>
</head>
<body>
<nav>
	<ul class="first">
		<li><span><input type="button" class="on" onclick="test()" value="마이페이지"></span></li>
		<li><span><input type="button" onclick="test1()" value="게시판 관리"></span></li>
		<li><span><a>신고 관리</a></span></li>
		<li><span><a>회원 관리</a></span></li>
	</ul>
	
	<ul class="second">
		<li class="menu">
			<ul class="on">
				<li class="on"><a>정보수정</a></li>
				<li><a>마이 글</a></li>
				<li><a>마이 알림</a></li>
				<li><a>마이 쪽지</a></li>
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
				<!-- 
				<jsp:include page="./admin/infoupdate.jsp"></jsp:include>
				 -->
			</ul>
		</li>
		<li class="menu"><ul></ul></li>
		<li class="menu"><ul></ul></li>
		<li class="menu"><ul></ul></li>
	</ul>
</nav>
<script type="text/javascript" src="./resources/js/admin/test.js"></script>
</body>
</html>