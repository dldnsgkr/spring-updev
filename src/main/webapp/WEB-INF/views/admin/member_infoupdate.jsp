<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./resources/css/admin/admin_infoupdate.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="./resources/js/admin/member_info_update.js"></script>
</head>
<body>
	<div class="infoupdate">
		<div class="title">
			<span></span>
			<p>정보수정</p>
		</div>
		<div class="content">
			<form name="ufrm" method="post">
				<div class="info">
					<label>아이디</label>
					<input type="text" name="m_id" id="m_id" value="${member.m_id}" readonly="readonly">
					<label>비밀번호</label>
					<input type="text" name="m_pw" id="m_pw" value="${member.m_pw}">
					<label>닉네임</label>
					<input type="text" name="m_nick" id="m_nick" value="${member.m_nick}" readonly="readonly">
					<label>이름</label>
					<input type="text" name="m_name" id="m_name" value="${member.m_name}">
					<label>이메일</label>
					<input type="text" name="m_mail" id="m_mail" value="${member.m_mail}">
					<label>전화번호</label>
					<input type="text" name="m_tel" id="m_tel" value="${member.m_tel}">
					<label>분야</label>
					<select name="m_field" id="m_field">
						<c:choose>
							<c:when test="${member.m_field=='프론트엔드'}">
								<option value="프론트엔드" selected="selected">프론트엔드</option>
							</c:when>
							<c:otherwise>
								<option value="프론트엔드">프론트엔드</option>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${member.m_field=='백엔드'}">
								<option value="백엔드" selected="selected">백엔드</option>
							</c:when>
							<c:otherwise>
								<option value="백엔드">백엔드</option>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${member.m_field=='데브옵스'}">
								<option value="데브옵스" selected="selected">데브옵스</option>
							</c:when>
							<c:otherwise>
								<option value="데브옵스">데브옵스</option>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${member.m_field=='기타'}">
								<option value="기타" selected="selected">기타</option>
							</c:when>
							<c:otherwise>
								<option value="기타">기타</option>
							</c:otherwise>
						</c:choose>
					</select>
					<label>등급</label>
					<input type="text" name="m_grade" id="m_grade" value="${member.m_grade}" readonly="readonly">
					<input type="button" name="button" value="정보수정" onclick="member_info_update();">
				</div>
			</form>
		</div>
	</div>
</body>
</html>