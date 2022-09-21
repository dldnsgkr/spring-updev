<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
			<form action="proupdate" method="post" enctype="multipart/form-data">
			<input type="hidden" name="m_nick" value="${list.m_nick }">
				<table style="width: 400px">
					<tr>
						<td>프로필</td>
						<td><input type="file" name="m_profile" value="${list.m_profile }"></td>
					</tr>
					<tr>
						<td>아이디</td>
						<td><input type="text" name="m_id" value="${list.m_id }" ></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="password" name="m_pw" value="${list.m_pw }"></td>
					</tr>
					<tr>
						<td>비밀번호확인</td>
						<td><input type="password" name="m_pw" value="${list.m_pw }"></td>
					</tr>
					<tr>
						<td>닉네임</td>
						<td><input type="text" name="up_nick" value="${list.m_nick }"></td>
					</tr>
					<tr>
						<td>이름</td>
						<td><input type="text" name="m_name" value="${list.m_name }"></td>
					</tr>
					<tr>
						<td>이메일</td>
						<td><input type="text" name="m_mail" value="${list.m_mail }"></td>
					</tr>
					<tr>
						<td>전화번호</td>
						<td><input type="text" name="m_tel" value="${list.m_tel }"></td>
					</tr>
					<tr>
						<td>분야</td>
						<td><input type="text" name="m_field" value="${list.m_field }"></td>
					</tr>
				</table>
				<br> <input type="submit" value="프로필 정보 수정"> <input
					type="button" value="취소" onclick="location.href'myp?m_nick=${list.m_nick}'">
			</form>
		</table>
</body>
</html>