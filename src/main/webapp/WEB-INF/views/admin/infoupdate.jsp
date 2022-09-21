<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./resources/css/info_update.css">
<style type="text/css">
#side{
display: block;
}
.infoupdate{
float: left;
}
label{
	display: block;
}
.profile{
	width: 250px;
    float: left;
    height: 300px;
    margin-right: 50px;
}
.info{
    float: left;
}
input[type="button"]{
	display: block;
}
</style>
</head>
<body>

	<div class="infoupdate">
		<div class="title">
			<span></span>
			<p>정보수정</p>
		</div>
		<div class="content">
			<form action="update" method="post" name="ufrm">
				<div class="profile"><img alt="" src="./resources/images/${admin.m_profile}">
					<input type="file" name="m_profile" value="">
				</div>
				<div class="info">
						<label>아이디</label>
						<input type="text" name="m_id" id="m_id" value="${admin.m_id}" readonly="readonly">
						<label>비밀번호</label>
						<input type="text" name="m_pw" id="m_pw" value="${admin.m_pw}">
						<label>닉네임</label>
						<input type="text" name="m_nick" id="m_nick" value="${admin.m_nick}">
						<label>이름</label>
						<input type="text" name="m_name" id="m_name" value="${admin.m_name}">
						<label>이메일</label>
						<input type="text" name="m_mail" id="m_mail" value="${admin.m_mail}">
						<label>전화번호</label>
						<input type="text" name="m_tel" id="m_tel" value="${admin.m_tel}">
						<label>분야</label>
						<input type="text" name="m_field" id="m_field" value="${admin.m_field}">
						<label>등급</label>
						<input type="text" name="m_grade" id="m_grade" value="${admin.m_grade}">
						
						<input type="button" name="button" value="정보수정" onclick="infoupdate();">
				</div>
			</form>
		</div>
	</div>
</body>
</html>