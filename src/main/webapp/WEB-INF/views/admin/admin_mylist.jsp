<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./resources/css/admin/admin_mylist.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="./resources/js/admin/activities.js"></script>

</head>
<body>
<c:set var="URL3" value="${requestScope['javax.servlet.forward.servlet_path']}" />
	<div class="infoupdate">
		<div class="title">
			<span></span>
			<p></p>
		</div>
		<div class="content">
			<div id="out">
				<table border="1">
					<tr><td>번호</td><td>종류</td><td>분류</td><td>제목</td><td>작성일</td>
					<td>내용</td><td>추천수</td><td>조회수</td><td>태그</td><td>첨부파일1</td>
					<td>첨부파일2</td><td>신고횟수</td>
					<c:choose>
						<c:when test="${URL3=='/admin_mylike_select'}">
							<td>좋아요취소</td>
						</c:when>
						<c:when test="${URL3=='/admin_myscrap_select'}">
							<td>스크랩취소</td>
						</c:when>
						<c:otherwise>
							<td>삭제</td><td>수정</td>
						</c:otherwise>
					</c:choose>
					
					
					</tr>
					<c:forEach items="${bpage1}" var="list">
					<tr><td>${list.b_num}</td><td>${list.b_cate}</td><td>${list.b_kind}</td>
					<td><a href="detail?b_num=${list.b_num}">${list.b_title}</a></td>
					<td>${list.b_wdate}</td><td>${list.b_content}</td><td>${list.b_likecnt}</td><td>${list.b_readcnt}</td>
					<td>${list.b_tag}</td><td>${list.b_file1}</td><td>${list.b_file2}</td><td>${list.b_report}</td>
					
					
					<c:choose>
						<c:when test="${URL3=='/admin_mylike_select'}">
							<td><a onclick="admin_mylike_cancel(${list.b_num});">좋아요취소</a></td>
						</c:when>
						<c:when test="${URL3=='/admin_myscrap_select'}">
							<td><a onclick="admin_myscrap_cancel(${list.b_num});">스크랩취소</a></td>
						</c:when>
						<c:otherwise>
							<td><a onclick="ttt(${list.b_num});">삭제</a></td>
							<td><a href="writeupdatecheck?b_num=${list.b_num}">수정</a></td>
						</c:otherwise>
					</c:choose>
					</tr>
					</c:forEach>
				</table>
				
				<a href="write">글쓰기</a>
				
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

</body>
</html>