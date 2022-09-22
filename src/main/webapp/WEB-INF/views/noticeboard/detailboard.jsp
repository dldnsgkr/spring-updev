<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>${list.b_kind }</h1>
<h4>${list.b_title }</h4>
${list.m_nick }&emsp;${list.b_wdate }
<table>
	<tr>
		<td>${list.b_content }</td>
	</tr>
	<tr>
	<td><input type="button" value="좋아요"></td>&emsp;<td><input type="button" value="신고" onclick="location.href=''"></td>
	</tr>
</table>
</body>
</html>