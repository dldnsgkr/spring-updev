<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	<link rel="stylesheet" href="resources/css/find_idpw.css" type="text/css">
	<script type="text/javascript" src="resources/js/find_idpw.js"></script>
	</head>
	<body>
	<div class="find">
	 <div class="container">
        <div class="tab-content current">
			<div class="id_find">
				<form name="id_find" method="post">
					<input class="input" type="text" id="m_name" name="m_name" value="" placeholder="이름">
					<input class="input" type="text" id="m_mail" name="m_mail" value="" placeholder="이메일">
					<button class="firstbutton" type="button" name="id_find" onclick="IdChk()">아이디 찾기</button> <!-- ajax로 단계를 이동함 -->
					<button class="button" type="button" name="pw_find" onclick="PwChk()">비밀번호 찾기</button> <!-- ajax로 단계를 이동함 -->
						<div class="button1">
							<button class="button" type="button" name="sign" onclick="location.href = 'signup' ">아직 회원이 아니신가요? 회원가입</button>
						</div>
				</form>
			</div>
			<div id="id_find">
				<h2>고객님의 정보와 일치하는 id입니다.</h2>
				<div class="find_header">
					<span id="ex_id">ID</span>
					<span id="sign_day">가입일</span>
				</div>
				<span id="id_ck"></span>
				<button class="firstbutton" onclick="location.href = 'login' ">로그인 하기</button>
			</div>
			<div id="pw_find">
				<form name="update_pw" method="POST">
					<h2>새로운 비밀번호를 설정해 주세요.</h2>
					<input class="input" type="text" id="m_id" name="m_id" value="" placeholder="아이디">
					<input class="input" type="text" id="m_pw" name="m_pw" value="" placeholder="기존 비밀번호">
					<input class="input" type="text" id="m_npw" name="m_npw" value="" placeholder="새 비밀번호">
					<input class="input" type="text" id="m_npwck" name="m_npwck" value="" placeholder="새 비밀번호 확인">
					<button class="firstbutton" type="button" name="passwordupdate" onclick="PwUpD()">비밀번호 변경</button>
				</form>
			</div>
		</div>
   	 </div>
   	 </div>
	</body>
</html>