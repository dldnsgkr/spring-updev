<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	
	<link rel="stylesheet" type="text/css" href="./resources/css/admin/admin_mylist.css">
	<link rel="stylesheet" href="./resources/css/admin/admin_board.css" type="text/css">
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="./resources/js/admin/admin_mylist.js"></script>
	</head>
<body>
<div class="wrap">
<c:set var="URL" value="${requestScope['javax.servlet.forward.servlet_path']}" />
	<div class="mylist">
		<div class="title">
			<span></span>
			<c:choose>
				<c:when test="${URL=='/admin_mylike_select'}">
					<h1>내가 좋아요 한 글</h1>
				</c:when>
				<c:when test="${URL=='/admin_myscrap_select'}">
					<h1>내가 스크랩 한 글</h1>
				</c:when>
				<c:otherwise>
					<h1>내가 쓴 글</h1>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="board_write">
			<a href="write"><img src="./resources/images/iconmonstr-pencil-13-240.png" title="게시글 작성" class="board_img"></a>
		</div>
		<div class="content">
			<table border="1">
				<tr>
					<th>번호</th><th>종류</th><th>분류</th><th>제목</th><th>작성일</th><th>내용</th>
					<th>추천수</th><th>조회수</th><th>첨부파일1</th><th>첨부파일2</th><th>신고횟수</th>
					<c:choose>
						<c:when test="${URL=='/admin_mylike_select'}">
							<th>좋아요취소</th>
						</c:when>
						<c:when test="${URL=='/admin_myscrap_select'}">
							<th>스크랩취소</th>
						</c:when>
						<c:otherwise>
							<th>삭제</th><th>수정</th>
						</c:otherwise>
					</c:choose>
				</tr>
				<c:forEach items="${bpage1}" var="list">
					<tr>
						<td>${list.b_num}</td><td>${list.b_cate}</td><td>${list.b_kind}</td>
						<td><a href="detail?b_num=${list.b_num}">${list.b_title}</a></td>
						<td><fmt:parseDate value='${list.b_wdate}' var='date' pattern='yyyy-MM-dd HH:mm:ss'/> 
						<fmt:formatDate value="${date}" pattern="yyyy.MM.dd."/></td>
						<td>${list.b_content}</td><td>${list.b_likecnt}</td><td>${list.b_readcnt}</td>
						<td>${list.b_file1}</td><td>${list.b_file2}</td><td>${list.b_report}</td>
						<c:choose>
							<c:when test="${URL=='/admin_mylike_select'}">
								<td><a onclick="admin_mylike_cancel(${list.b_num});">좋아요취소</a></td>
							</c:when>
							<c:when test="${URL=='/admin_myscrap_select'}">
								<td><a onclick="admin_myscrap_cancel(${list.b_num});">스크랩취소</a></td>
							</c:when>
							<c:otherwise>
								<td><a onclick="admin_mywrite_delete(${list.b_num});">삭제</a></td>
								<td><a href="writeupdatecheck?b_num=${list.b_num}">수정</a></td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</table>
				<div id="tfoot">
				<c:if test="${page1.nowPage > 10}">
					<a href="admin_mylist?nowPage=${page1.startPage -1}">&#60;</a> 				
				</c:if>
				<c:forEach begin="${page1.startPage}" end="${page1.endPage}" var="p">
					<c:choose>
						<c:when test="${p==page1.nowPage}">
							<b>${p}</b>
						</c:when>
						<c:when test="${p!=page1.nowPage}">
							<a href="admin_mylist?nowPage=${p}&cntPerPage=${page1.cntPerPage}">${p}</a>
						</c:when>
					</c:choose>
				</c:forEach>
				<c:if test="${page1.next && page1.endPage>0}">
					<a href="admin_mylist?nowPage=${page1.endPage +1}">&#62;</a>
				</c:if>
				</div>
			</div>
		</div>
</div>
</body>
</html>