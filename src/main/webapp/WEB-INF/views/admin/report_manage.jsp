<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./resources/css/admin/report_manage.css">
<link rel="stylesheet" href="./resources/css/admin/admin_board.css" type="text/css">
</head>
<body>
<div class="wrap">
	<div class="infoupdate">
		<div class="title">
			<span></span>
			<h1>신고관리</h1>
		</div>
		<div class="content">
			<div id="out">
				<table border="1">
					<tr><td>번호</td><td>상태</td><td>사유</td><td>첨부파일</td><td>게시글 번호</td>
					<td>처리완료</td></tr>
					<c:forEach items="${bpage1}" var="list">
					<tr><td>${list.r_num}</td><td>${list.r_status}</td><td>${list.r_reason}</td><td>${list.r_file1}</td>
					<td><a href="detail?b_num=${list.b_num}">${list.b_num}</a></td>
					<td><a onclick="report_manage_update(${list.r_num});">처리완료</a></td>
					</tr>
					</c:forEach>
				</table>
				<div id="tfoot">
				<c:if test="${page1.nowPage > 10}">
					<a href="report_manage?nowPage=${page1.startPage -1}">&#60;</a> 				
				</c:if>
					
				<c:forEach begin="${page1.startPage}" end="${page1.endPage}" var="p">
					<c:choose>
						<c:when test="${p==page1.nowPage}">
							<b>${p}</b>
						</c:when>
						<c:when test="${p!=page1.nowPage}">
							<a href="report_manage?nowPage=${p}&cntPerPage=${page1.cntPerPage}">${p}</a>
						</c:when>
					</c:choose>
				</c:forEach>
				<!-- 
				<c:if test="${page1.nowPage ne page1.realEnd && page1.endPage>0}"></c:if>
				 -->			
				<c:if test="${page1.next && page1.endPage>0}">
					<a href="report_manage?nowPage=${page1.endPage +1}">&#62;</a>
				</c:if>
			</div>
		</div>
		</div>
	</div>
	</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="./resources/js/admin/report_manage.js"></script>
</body>
</html>