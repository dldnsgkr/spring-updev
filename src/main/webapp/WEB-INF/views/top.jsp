<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
		<script src="resources/js/search.js"></script>
		<link rel="stylesheet" href="resources/css/top.css" type="text/css">
		<script>
	var time = 300; //기준시간 작성
	var min = ""; //분
	var sec = ""; //초

	//setInterval(함수, 시간) : 주기적인 실행
	var x = setInterval(function() {
		//parseInt() : 정수를 반환
		min = parseInt(time/60); //몫을 계산
		sec = time%60; //나머지를 계산

		document.getElementById("demo").innerHTML = min + "분" + sec + "초";
		time--;

		//타임아웃 시
		if (time < 0) {
			clearInterval(x); //setInterval() 실행을 끝냄
			document.getElementById("demo").innerHTML = "시간초과";
		}
	}, 1000);
</script>

	</head>
	<body>
		<c:set var="result" value="${param.result}"/>
		<c:choose>
			<c:when test="${result=='loginfail'}">
				<script type="text/javascript">
					window.onload=function(){
						alert("로그인 확인 해주세요!!");
					}
				</script>
			</c:when>
		</c:choose>
		
		<c:set var="check" value="${param.check}"/>
		<c:choose>
			<c:when test="${check=='nodata'}">
				<script type="text/javascript">
					window.onload=function(){
						alert("아이디나 비밀번호가 올바르지 않습니다.");
					}
				</script>
			</c:when>
			</c:choose>
		
		<c:set var="finish" value="${param.finish}"/>
		<c:choose>
			<c:when test="${finish=='good'}">
				<script type="text/javascript">
					window.onload=function(){
						alert("회원가입이 완료되었습니다!!");
					}
				</script>
			</c:when>
			</c:choose>
			
			<c:set var="gradecheck" value="${param.gradecheck}"/>
		<c:choose>
			<c:when test="${gradecheck=='badgrade'}">
				<script type="text/javascript">
					window.onload=function(){
						alert("관리자에 의해 차단되었습니다");
					}
				</script>
			</c:when>
			</c:choose>
			<div class="header">
				<div class="wrap">
					<div class="top">
						<div class="top_login">
							<c:choose>
								<c:when test="${loginState==true}">
								<c:choose>
								<c:when test="${member.m_id=='admin'}">
									<span>환영합니다! 관리자님</span>
									<a href="admin_mypage">마이페이지</a>
								</c:when>
									<c:otherwise>
									 <span>환영합니다!!${member.m_nick}님</span>&emsp;<a href="myp?m_nick=${member.m_nick }">마이페이지</a>
									</c:otherwise>
									</c:choose>
									<a href="logout">&emsp;로그아웃</a>
								</c:when>
								<c:otherwise>
									<ul>
										<li><a href="signup">회원가입</a></li>
										<li><a href="login">&emsp;로그인</a></li>
									</ul>
								</c:otherwise>
							</c:choose>
							
 남은 시간:<div id="demo"></div>

						</div>
					</div>
					<div class="topbar">
						<div class="logo">
							<a href="index"><img src="./resources/images/updev_2.png" id="top_logo"></a>
						</div>
						<div id="bottom2">
							<div class="menu">
								<a href="noticepage" id="menu">공지</a>
								<a href="sharepage" id="menu">정보공유</a>
								<a href="questionpage" id="menu">지식인</a>
								<a href="worrypage" id="menu">고민상담소</a>
								<a href="qnapage" id="menu">Q&A</a>
								<div class="search">
									<form action="search" method="post" id="top_search_form" name="top_search_form">
										<input class="main_search" type="text" name="keyword" placeholder="검색">
										<button class="search-btn" type="button" onclick="SearchChk()"></button>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
							</div>
		<script type="text/javascript">
		$("#top_search_form").keypress(function(e) {
			if (e.keyCode === 13) {
				SearchChk();
			}
		});		
		</script>
	</body>
</html>