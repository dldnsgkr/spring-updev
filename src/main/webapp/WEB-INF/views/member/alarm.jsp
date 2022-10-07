<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="wrapper">
		<form id="mywriteform">
		<div class="title"><h3>내가 스크랩한 게시물</h3></div>
			<table>
				<tr>
 					<th>번호</th>
					<th>내용</th>
				</tr>
				<c:forEach items="${list}" var="list">
				<tr>
					<td>${list.a_num}</td>
					<td><a href="detail?b_num=${list.b_num}">${list.a_content}</a></td>
					
					
				</tr>
				</c:forEach>
			</table>
		</form>
	</div>
</body>
</html>