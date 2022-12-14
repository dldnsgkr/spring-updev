<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="resources/css/signup.css" type="text/css" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<script type="text/javascript" src="./resources/js/signup.js" charset="UTF-8"></script>
		<script type="text/javascript" src="./resources/js/idchk.js" charset="UTF-8"></script>
		<script type="text/javascript" src="./resources/js/nickchk.js" charset="UTF-8"></script>
	</head>
	<body>
	<div class="signupwrap">
		<div class="wrapper">
			<form action="insert" method="post" name="signup1" id="signupform">
					<div class="title"><h1>SIGN UP</h1></div>
					<div class="signup_input">
						<label><input class="m_id" id="m_id" type="text" name="m_id" placeholder="아이디: 영소문자, 숫자로 4~12 글자까지 가능"></label>
						 <button id="id_duplicate_button" class="button button1" type="button" onclick="id_duplicate_test()">중복확인</button>
						<div id="id_status_alert" class="id_status_alert" >${id_availability}</div>
					</div>
					<div class="signup_input">
						<label><input id="pw" type="password" name="m_pw" placeholder="비밀번호" onclick="id_duplicate_check_alert()" ></label>
						<input type="hidden" name="id_status_value" id="id_status_value" value="N">
					</div>
					<div class="signup_input">
						<label><input id="pwchk" type="password" name="m_pw" placeholder="비밀번호확인" ></label>
					</div>
					<div class="signup_input">
						<label><input class="m_nick" id="m_nick" type="text" name="m_nick" placeholder="닉네임: 한글로 2~10글자까지 가능" ></label>
						<button class="button button1" type="button" onclick="nickname_duplicate_test()">중복확인</button>
						<div id="nickname_status_alert" class="nickname_status_alert" >${nickname_availability}</div>
					</div>
					<div class="signup_input">
						<label><input id="name" type="text" name="m_name" placeholder="이름" onclick="nickname_duplicate_check_alert()" ></label>
						<input type="hidden" name="nickname_status_value" id="nickname_status_value" value="N">
					</div>
					<div class="signup_input">
						<label><input id="mail" type="text" name="m_mail" placeholder="이메일 ex)abc123@gmail.com" ></label>
					</div>
					<div class="signup_input">
						<label><input id="tel" type="text" name="m_tel" placeholder="전화번호 ex)01012341234" ></label>
					</div>	
					<div class="signup_input">
						<label>
							<select id="field" name="m_field">
								<option value="">분야</option>
								<option value="frontend">프론트엔드</option>
								<option value="backend">백엔드</option>
								<option value="dev_ops">데브옵스</option>
								<option value="etc">기타</option>
							</select>
						</label>
					</div>	
					<div class="signup_status">
						<input type="hidden" name="m_grade" value="회원">
					</div>
					<div class="terms">	
					<div class="terms_description" style="border: 1px solid #242038; height:70px; overflow-y: scroll;">
						<p>
						UPDEV(이하 업데브)는 귀하의 개인정보를 중요시하며, 『정보통신망 이용촉진 및 정보보호 등에 관한 법률』, 『개인정보 보호법』, 『통신비밀보호법』, 『전기통신사업법』 등
						정보통신 서비스 제공자가 준수하여야 할 관련 법령상의 개인정보보호 규정을 준수하고 있습니다. 당사는 본 개인정보취급방침을 통하여 귀하가 회사에 제공하는 개인정보가
						어떠한 용도와 방식으로 이용되고 있으며, 회사가 개인정보보호를 위해 어떠한 조치를 취하고 있는지 알려드립니다.
						당사의 개인정보취급방침은 정부정책, 관련 법령 및 회사 내부 방침 변경 등 사회적 필요와 변화에 따라 수시로 변경될 수 있고,
						회사는 이에 따른 개인정보취급방침의 지속적인 개선을 위하여 필요한 절차를 정하고 있습니다.
						개인정보취급방침을 개정하는 경우 회사는 그 개정사항을 홈페이지에 게시하여 귀하가 개정된 사항을 쉽게 확인할 수 있도록 하고 있습니다.
						</p>
					</div>
					</div>
					<div id="agreement">
						<label>개인 정보 수집 및 이용에 동의 합니다 <input type="checkbox" id="agree1"  name="agree" ></label>
					</div>
					<div class="signup_btn">
					<button id="signup_btn" class="button button2" type="button" onclick="signupChk()">회원가입</button>
					<button id="signup_btn" class="button button2" type="button" onclick="location.href = '/updev/index' ">취소</button>
					</div>
			</form>
		</div>
	</div>
	</body>
</html>