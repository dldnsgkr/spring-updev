<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
		<link rel="stylesheet" href="resources/css/main.css" type="text/css">
		<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
		<script type="text/javascript" src="./resources/js/main.js"></script>
		<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
		<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
	</head>
	<body>
		<div class="wrap" id="main_body">
			<div class="main_top">
				<div class="slider-wrap">
	    			<div class="cont">
	       				<img src="./resources/images/banner1.jpg">
	    			</div>
	    			<div class="cont">
	       				<img src="./resources/images/banner2.jpg">
	    			</div>
	    			<div class="cont">
	         			<img src="./resources/images/banner3.jpg">
	    			</div>
				</div>
				<div class="login">
					<form name="login1" method="POST">
						<input class="input" type="text" name="m_id" value="" placeholder="id">
						<input class="input" type="password" name="m_pw" value="" placeholder="pw">
						<div class="button1">
							<button class="button" type="button" name="sign" onclick="location.href = 'signup' ">회원가입</button>
							<button class="button" type="button" name="login" onclick="loginChk()">로그인</button>
						</div>
					</form>
				</div>
			</div>

		<div class="main_board">
			<div class="board">
				<h3>인기 게시글</h3>
				<a href="">더보기</a>
			</div>
			
			<div class="board">
				<h3>정보공유</h3>
				<a href="">더보기</a>
			</div>

			
			<div class="board">
				<h3>지식인</h3>
				<a href="">더보기</a>
			</div>
			
			<div class="board">
				<h3>고민</h3>
				<a href="">더보기</a>
			</div>

			<div class="board">
				<h3>공지</h3>
				<a href="">더보기</a>
			</div>
	
			<div class="board">
				<h3>QnA</h3>
				<a href="">더보기</a>
			</div>
			
			</div>				
		</div>
	</body>
</html>