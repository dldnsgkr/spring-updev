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
			<div class="search-report-two">
				<form action="reportsearch" method="post" id="report_form" name="report_form">
					<input class="report_search" type="text" name="keyword_report" placeholder="검색">
					<input type="text" style="display:none;"/>
					<button class="report-btn" type="button" onclick="Report()">a</button>
				</form>
			</div>
		</div>
		<div class="content">
			<div id="out">
				<table border="1">
					<tr>
					<td>번호</td>
					<td>상태</td>
					<td>사유</td>
					<td>첨부파일</td>
					<td>게시글 번호</td>
					<td>처리완료</td></tr>
					<c:forEach items="${paging}" var="list">
					<tr><td>${list.r_num}</td><td>${list.r_status}</td>
					<td>${list.r_reason}</td><td>${list.r_file1}</td>
					<td><a href="detail?b_num=${list.b_num}">${list.b_num}</a></td>
					<td><a onclick="report_manage_update(${list.r_num});">처리완료</a></td>
					</tr>
					</c:forEach>
				</table>
				<div id="tfoot">
				<c:if test="${page.nowPage > 10}">
		<a href="search?nowPage=${page.startPage -1}&keyword=${keyword}">&#60;</a> 				
	</c:if>
	

	<c:forEach begin="${page.startPage}" end="${page.endPage}" var="p">
		<c:choose>
			<c:when test="${p==page.nowPage}">
				<b>${p}</b>
			</c:when>
			<c:when test="${p!=page.nowPage}">
				<a href="search?nowPage=${p}&cntPerPage=${page.cntPerPage}&keyword=${keyword}">${p}</a>
			</c:when>
		</c:choose>
	</c:forEach>
				
	
	<c:if test="${page.next && page.endPage>0}">
		<a href="search?nowPage=${page.endPage +1}&keyword=${keyword}">&#62;</a>
	</c:if>   
			</div>
		</div>
		</div>
	</div>
	</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="./resources/js/admin/report_manage.js"></script>
<script type="text/javascript" src="./resources/js/admin/report_search.js"></script>
</body>
</html>