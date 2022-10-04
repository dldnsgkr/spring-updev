<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	       				<img src="./resources/images/banner1.jpg" width="1200px">
		       			<div class="main_in1">
		       				<div class="text_box">
			       				개발자로 취업하자<br>
								교육비 전액 무료<br>
								<a href="">자세히 보기</a>
							</div>
		       			</div>
	    			</div>
	    			<div class="cont">
	       				<img src="./resources/images/banner2.jpg" width="1200px">
	       				<div class="main_in2">
		       				<div class="text_box">
			       				UPDEV 개발자 채용<br>
								<a href="">자세히 보기</a>
		       				</div>
						</div>
	    			</div>
	    			<div class="cont">
	         			<img src="./resources/images/banner3.jpg" width="1200px">
	         			<div class="main_in3">
	         				<div class="text_box">
			         			경력보다 실력<br>
								UPDEV 개발자를 만나다.<br>
								<a href="">자세히 보기</a>
	         				</div>
						</div>
	    			</div>
				</div>
				<!-- 
				<div class="login">
					<form name="login1" method="POST">
						<input class="input" type="text" name="m_id" value="" placeholder="id">
						<button class="main_login" type="button" onclick="loginChk()">로그인</button>
						<input class="input" type="password" name="m_pw" value="" placeholder="pw">
						<div class="button1">
							<button class="button" type="button" name="sign" onclick="location.href = 'signup' ">회원가입</button>
							<button class="button" type="button" name="find_idpw" onclick="location.href = 'findidpw' ">아이디/비밀번호분실</button>
						</div>
					</form>
				</div>
				 -->
			</div>
		<div class="main_board">
			<div class="board">
				<h3>인기게시글</h3>
				<a href="poppage">더보기</a>
				<table>
				<c:forEach items="${popmpage}" var="a" begin="0" end="7">
				<tr>
					<td><input type="hidden" value="${a.b_num}"></td>
					<td>${a.b_kind}</td>
					<td><a href="detail?b_num=${a.b_num}">${a.b_title}</a></td>
				</tr>
				</c:forEach>
				</table>
			</div>
			
			<div class="board">
				<h3>정보공유</h3>
				<table>
				<a href="sharepage">더보기</a>
				<c:forEach items="${sharempage}" var="a" begin="0" end="7">
				<tr>
					<td><input type="hidden" value="${a.b_num}"></td>
					<td>정보</td>
					<td><a href="detail?b_num=${a.b_num}">${a.b_title}</a></td>
				</tr>
				</c:forEach>
				</table>
			</div>

			
			<div class="board">
				<h3>지식인</h3>
				<a href="questionpage">더보기</a>
				<table>
				<c:forEach items="${questionmpage}" var="a" begin="0" end="7">
				<tr>
					<td><input type="hidden" value="${a.b_num}"></td>
					<td>${a.b_kind}</td>
					<td><a href="detail?b_num=${a.b_num}">${a.b_title}</a></td>
				</tr>
				</c:forEach>
				</table>
			</div>
			
			<div class="board">
				<h3>고민상담소</h3>
				<a href="worrypage">더보기</a>
				<table>
				<c:forEach items="${worrympage}" var="a" begin="0" end="7">
				<tr>
					<td><input type="hidden" value="${a.b_num}"></td>
					<td>${a.b_kind}</td>
					<td><a href="detail?b_num=${a.b_num}">${a.b_title}</a></td>
				</tr>
				</c:forEach>
				</table>
			</div>

			<div class="board">
				<h3>공지</h3>
				<a href="noticepage">더보기</a>
				<table>
				<c:forEach items="${noticempage}" var="a" begin="0" end="7">
				<tr>
					<td><input type="hidden" value="${a.b_num}"></td>
					<td>${a.b_kind}</td>
					<td><a href="detail?b_num=${a.b_num}">${a.b_title}</a></td>
				</tr>
				</c:forEach>
				</table>
			</div>
	
			<div class="board">
				<h3>Q&A</h3>
				<a href="qnapage">더보기</a>
				<table>
				<c:forEach items="${qnampage}" var="a" begin="0" end="7">
				<tr>
					<td><input type="hidden" value="${a.b_num}"></td>
					<td>${a.b_kind}</td>
					<td><a href="detail?b_num=${a.b_num}">${a.b_title}</a></td>
				</tr>
				</c:forEach>
				</table>			
			</div>
			
			</div>				
		</div>
	</body>
</html>