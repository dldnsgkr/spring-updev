<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="resources/css/top.css" type="text/css">
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
						alert("회원가입이 필요합니다!!");
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
			<div class="header">
				<div class="wrap">
					<div class="top">
						<c:choose>
							<c:when test="${loginState==true}">
								<c:choose>
								<c:when test="${member.m_id=='admin'}">
									<span>환영합니다!! 관리자님 :)</span>
									<a href="admin">마이페이지</a>
								</c:when>
								<c:otherwise>
									<a href=""><span>환영합니다!!${member.m_nick}님</span></a>&emsp;<a href="myp?m_nick=${member.m_nick }">마이페이지</a>
								</c:otherwise>
								</c:choose>
								<a href="logout">로그아웃</a>
							</c:when>
							<c:otherwise>
								<ul>
									<li><a href="signup">회원가입</a></li>
									<li><a href="login">&emsp;로그인</a></li>
								</ul>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="bottom">
						<div class="logo">
							<a href="index"><img src="./resources/images/updev_2.png"></a>
						</div>
						<div class="search">
								<form action="" method="post" name="top_search_form">
								<input class="main_search" type="text" name="search" placeholder="검색">
								<button class="search-btn" type="button" onclick="SearchChk()"></button>
							</form>
						</div>
					<!-- <button class="top_logo" type="button" onclick="location.href = '/updev' "></button>  -->
					</div>
				</div>
						<div class="menu" id="bottom2">
							<div class="wrap">
								<a href="notice" id="menu">공지</a>
								<a href="share" id="menu">정보공유</a>
								<a href="question" id="menu">질문창고</a>
								<a href="worry" id="menu">고민상담소</a>
								<a href="qna" id="menu">Q&A</a>
								<!--<a href="totboard" id="menu">글 목록</a>-->
							</div>
						</div>
			</div>
	</body>
</html>