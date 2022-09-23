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
		       			<div class="main_in1">
		       				<div class="text_box">
			       				개발자로 취업하자<br>
								교육비 전액 무료<br>
								<a href="">자세히 보기</a>
							</div>
		       			</div>
	    			</div>
	    			<div class="cont">
	       				<img src="./resources/images/banner2.jpg">
	       				<div class="main_in2">
		       				<div class="text_box">
			       				UPDEV 개발자 채용<br>
								<a href="">자세히 보기</a>
		       				</div>
						</div>
	    			</div>
	    			<div class="cont">
	         			<img src="./resources/images/banner3.jpg">
	         			<div class="main_in3">
	         				<div class="text_box">
			         			경력보다 실력<br>
								UPDEV 개발자를 만나다.<br>
								<a href="">자세히 보기</a>
	         				</div>
						</div>
	    			</div>
				</div>
				<div class="login">
					<form name="login1" method="POST">
						<input class="input" type="text" name="m_id" value="" placeholder="id">
						<button class="main_login" type="button" onclick="loginChk()">로그인</button>
						<input class="input" type="password" name="m_pw" value="" placeholder="pw">
						<div class="button1">
							<button class="button" type="button" name="sign" onclick="location.href = 'signup' ">회원가입</button>
							<button class="button" type="button" name="find_id" onclick="location.href = 'signup' ">아이디찾기</button>
							<button class="button" type="button" name="find_pw" onclick="location.href = 'signup' ">비밀번호찾기</button>
						</div>
					</form>
				</div>
			</div>
		<div class="main_line"></div>
		<div class="main_board">
			<div class="board">
				<h3>인기 게시글</h3>
				<a href="">더보기</a>
			</div>
			
			<div class="board">
				<h3>정보공유</h3>
				<a href="share">더보기</a>
			</div>

			
			<div class="board">
				<h3>질문창고</h3>
				<a href="question">더보기</a>
			</div>
			
			<div class="board">
				<h3>고민상담소</h3>
				<a href="worry">더보기</a>
			</div>

			<div class="board">
				<h3>공지</h3>
				<a href="notice">더보기</a>
			</div>
	
			<div class="board">
				<h3>QnA</h3>
				<a href="qna">더보기</a>
			</div>
			
			</div>				
		</div>
	</body>
</html>