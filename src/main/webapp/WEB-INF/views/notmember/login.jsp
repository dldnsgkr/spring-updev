<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	<link rel="stylesheet" href="resources/css/login.css" type="text/css">
	<script src="resources/js/login.js"></script>
	</head>
	<body>
		<div class="box">
			<div class="boxright">
				<form name="login1" id="login_form" action="loginact" method="post">
					<h1>LOGIN</h1>
					<input class="input" type="text" name="m_id" value="" placeholder="아이디">
					<input class="input" type="password" name="m_pw" value="" placeholder="비밀번호">
					 &ensp;					
					<input type="checkbox" name="auto_login" value="autocheck"> 자동 로그인
					<a href="findidpw">아이디/비밀번호 분실</a>
						<div class="button1">
							<button class="button" type="button" name="login" onclick="loginChk()">로그인</button>
							<button class="button" type="button" name="sign" onclick="location.href = 'signup' ">아직 회원이 아니신가요? 회원가입</button>
						</div>
				</form>
			</div>
		</div>
		<script type="text/javascript" src="resources/js/login.js"></script>
	</body>
</html>