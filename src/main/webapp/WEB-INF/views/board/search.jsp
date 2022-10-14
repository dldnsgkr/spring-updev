<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/board.css" type="text/css">
</head>
<body>
<div class="wrap">
		<div class="boardpage_top">
			<div class="board_notic">
				키워드로 검색한 게시글입니다.
			</div>
		</div>
			<table border="1">
				<thead id="thead">
					<tr>
						<th width="60px">번호</th>
						<th>제목</th>
						<th width="100px">작성자</th>
						<th width="80px">작성일</th>
						<th width="50px">조회수</th>
						<th width="50px">추천수</th>
					</tr>
				</thead>
				<tbody id="tbody">
					<c:forEach items="${paging}" var="a">
					<tr>
						<td>${a.b_num }</td>
						<td><a href="detail?b_num=${a.b_num }">${a.b_title }</a></td>
						<td>${a.m_nick }</td>
						<td><fmt:parseDate value='${a.b_wdate}' var='date' pattern='yyyy-MM-dd HH:mm:ss'/>
						<fmt:formatDate value="${date}" pattern="yyyy.MM.dd."/></td>	
						<td>${a.b_readcnt }</td>
						<td>${a.b_likecnt }</td>
					</tr>
					</c:forEach>
				</tbody>
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
</body>
</html>