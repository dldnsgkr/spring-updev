<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="resources/css/signup.css" type="text/css" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<script type="text/javascript" src="resources/js/signup.js"></script>
	</head>
	<body>
	<img src="./resources/images/updev.jpg" width="150" height="70">
	<div class="gra">
	</div>
	<div class="nogra">
		<div class="wrapper">
			<form action="insert" method="post" name="signup1">
				<div class="title"><h1>Sign Up</h1></div>
					<div class="myprofile">
						<input type="hidden" name="m_profile" value="nmimg.png">
					</div>
					<div class="myid">
						<label><input id="id" type="text" name="m_id" placeholder="아이디" autofocus></label>
						<button class="button button1" type="button" onclick="location.href='링크주소';">중복확인</button>
						<div class="check_font" id="id_check"></div>
					</div>
					<div class="mypw">
						<label><input id="pw" type="password" name="m_pw" placeholder="비밀번호" autofocus></label>
					</div>
					<div class="mypwchk">
						<label><input id="pwchk" type="password" name="m_pw" placeholder="비밀번호확인" autofocus></label>
					</div>
					<div class="mynick">
						<label><input id="nick" type="text" name="m_nick" placeholder="닉네임"></label>
						<button class="button button1" type="button" onclick="location.href='링크주소';" autofocus>중복확인</button>
					</div>
					<div class="myname">
						<label><input id="name" type="text" name="m_name" placeholder="이름" autofocus></label>
						<div class="check_font" id="name_check"></div>
					</div>
					<div class="mymail">
						<label><input id="mail" type="text" name="m_mail" placeholder="이메일 ex)abc123@gmail.com" autofocus></label>
					</div>
					<div class="mytel">
						<label><input id="tel" type="text" name="m_tel" placeholder="전화번호 ex)01012341234" autofocus></label>
					</div>	
					<div class="myfield">
						<label>
							<select id="field" name="m_field">
								<option selected disabled>분야</option>
								<option value="frontend">프론트엔드</option>
								<option value="backend">백엔드</option>
								<option value="dev_ops">데브옵스</option>
								<option value="etc">기타</option>
							</select>
						</label>
					</div>	
					<div class="mygrade">
						<input type="hidden" name="m_grade" value="회원">
					</div>
					<div class="terms">	
					<div class="myterms" style="border: 1px solid #242038; height:70px; overflow-y: scroll;">
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
					<div id="myagree">
						<label>개인 정보 수집 및 이용에 동의 합니다<input type="checkbox" id="agree1"  name="agree" autofocus></label>
					</div>
					<div class="signup_btn">
					<button class="button button2" type="button" onclick="signupChk()">회원가입</button>
					<button class="button button2" type="button" onclick="location.href = 'main' ">취소</button>
					</div>
			</form>
		</div>
	</div>
	</body>
</html>