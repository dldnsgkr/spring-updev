<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="./resources/js/.js"></script> <!-- js 주소 확인 -->
<link rel="stylesheet" type="text/css"
	href="./resources/css/memwrite.css">
</head>
<body>
	<div class="wrapper">
		<form id="mywriteform">
		<div class="title"><h3>내가 좋아요한 게시물</h3></div>
			<table>
				<tr>
 					<th>번호</th>
					<th>제목</th>
					<th>분류</th>
					<th>추천수</th>
					<th>조회수</th>
					<th>작성일</th>
				</tr>
				<c:forEach items="${list}" var="list">
				<tr>
					<td>${list.b_num}</td>
					<td><a href="detail?b_num=${list.b_num}">${list.b_title}</a></td>
					<td>${list.b_kind}</td>
					<td>${list.b_likecnt}</td>
					<td>${list.b_readcnt}</td>
					<td><fmt:parseDate value='${list.b_wdate}' var='date' pattern='yyyy-MM-dd HH:mm:ss'/>
						<fmt:formatDate value="${date}" pattern="yyyy.MM.dd."/></td>
				</tr>
				</c:forEach>
			</table>
		</form>
	</div>
</body>
</html>