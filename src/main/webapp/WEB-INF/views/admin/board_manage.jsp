<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./resources/css/admin/admin_infoupdate.css">
</head>
<body>

	<c:set var="URL3" value="${requestScope['javax.servlet.forward.servlet_path']}" />
	<div class="infoupdate">
		<div class="title">
			<span></span>
			<p>게시판</p>
		</div>
		<div class="content">
			<div id="out">
				<table border="1">
					<tr><td>번호</td><td>종류</td><td>분류</td><td>제목</td><td>작성일</td>
					<td>내용</td><td>추천수</td><td>조회수</td><td>첨부파일1</td>
					<td>첨부파일2</td><td>신고회수</td><td>삭제</td><td>수정</td>
					</tr>
					<c:forEach items="${bpage1}" var="list">
					
					<tr><td>${list.b_num}</td><td>${list.b_cate}</td><td>${list.b_kind}</td>
					<td><a href="detail?b_num=${list.b_num}">${list.b_title}</a></td>
					<td>${list.b_wdate}</td><td>${list.b_content}</td><td>${list.b_likecnt}</td><td>${list.b_readcnt}</td>
					<td>${list.b_file1}</td>
					<td>${list.b_file2}</td><td>${list.b_report}</td>
					<td><a onclick="board_manage_delete(${list.b_num},'${URL3}');">삭제</a></td>
					<td><a href="writeupdatecheck?b_num=${list.b_num}">수정</a></td>
					<!-- 
					<td><a onclick="ttt(${list.b_num});">삭제</a></td>
					 -->
					</tr>
				
					</c:forEach>
				</table>
			<a href="write">글쓰기</a>
			</div>
		</div>
		
				<c:if test="${page1.nowPage > 10}">
					<a href="/updev/${URL3}?nowPage=${page1.startPage -1}">&#60;</a> 				
				</c:if>
					
				<c:forEach begin="${page1.startPage}" end="${page1.endPage}" var="p">
					<c:choose>
						<c:when test="${p==page1.nowPage}">
							<b>${p}</b>
						</c:when>
						<c:when test="${p!=page1.nowPage}">
							<a href="/updev/${URL3}?nowPage=${p}&cntPerPage=${page1.cntPerPage}">${p}</a>
						</c:when>
					</c:choose>
				</c:forEach>
				
				<c:if test="${page1.next && page1.endPage>0}">
					<a href="/updev/${URL3}?nowPage=${page1.endPage +1}">&#62;</a>
				</c:if>
		
		
		<!-- 
		<c:set var="URL1" value="${pageContext.request.scheme}" />

JSP에서 현재 URL 가져오기 : ${URL1}<br>
		<c:set var="URL2" value="${pageContext.request.serverPort}" />

JSP에서 현재 URL 가져오기 : ${URL2}<br>
		 -->
	</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="./resources/js/admin/board_manage.js"></script>
</body>
</html>