<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="./resources/js/memberinfoupdate.js"></script>
<script type="text/javascript" src="./resources/js/nickchk2.js" charset="UTF-8"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="resources/css/memberinfoupdate.css" type="text/css" />
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<div class="wrapper">
			<form name="memupinfochk" method="post" id="changeform">
			<div class="title"><h1>EDIT PROFILE</h1></div>
			
			
			<input id="m_nick" type="hidden" name="m_nick" value="${list.m_nick}">
				<div class="input">
						<div class="sub_title">아이디</div>
						<input class="m_id" id="m_id" type="text" name="m_id" value="${list.m_id }" readonly>
						<div class="warning">*아이디는 변경이 불가능합니다</div>
				</div>
					<div class="input">
						<div class="sub_title">비밀번호</div>
						<input id="m_pw" type="password" name="m_pw" value="${list.m_pw}">
					</div>
					<div class="input">
						<div class="sub_title">비밀번호 확인</div>
						<input id="m_pwchk" type="password" name="m_pw" value="${list.m_pw}">
					</div>
					<div class="input">
					<div class="sub_title">닉네임</div>
						<input class="up_nick" id="up_nick" type="text" value="${list.m_nick}" >
						<button class="button button1" type="button" onclick="nicktest2();">중복확인</button>
						<div id="nick_check2" class="nick_check2" >${nickmsg}</div>
					</div>
					<div class="input">
						<div class="sub_title">이름</div>
						<input id="m_name" type="text" name="m_name" value="${list.m_name }" onclick="nickselect2()" >
						<input type="hidden" name="nickcheck2" id="nickcheck2" value="N">
					</div>
					<div class="input">
						<div class="sub_title">이메일</div>
						<input id="m_mail" type="text" name="m_mail" value="${list.m_mail }" >
					</div>
					<div class="input">
						<div class="sub_title">전화번호</div>
						<input id="m_tel" type="text" name="m_tel" value="${list.m_tel }" >
					</div>	
					<div class="input">
						<div class="sub_title">분야</div>
							<select id="m_field" name="m_field">
								<option value="">분야</option>
								<option value="frontend">프론트엔드</option>
								<option value="backend">백엔드</option>
								<option value="dev_ops">데브옵스</option>
								<option value="etc">기타</option>
							</select>
					</div>	
					<div class="update_btn">
					<button id="update_btn" class="button button2" type="button" onclick="meminfoup()">프로필 정보 수정</button>
					<button id="update_btn" class="button button2" type="button" onclick="location.href='myp?3m_nick=${list.m_nick}'">취소</button>
					</div>
			</form>
		</div>	
</body>
</html>