<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="resources/css/board.css" type="text/css">
	</head>
	<body>
	<!-- 지식인 게시판 -->
		<div class="wrap">
				<div class="boardpage_top">
			<div class="board_notic">
				여기는 개발자들이 서로 질문하는 곳입니다.
			</div>
			</div>
			<div class="board_write">
				<button class="button" type="button" name="board_write" onclick="location.href = 'write?b_kind=지식인' ">
					<img src="./resources/images/iconmonstr-pencil-13-240.png" title="게시글 작성" class="board_img">
				</button>
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
				<c:forEach items="${bpage1 }" var="a">
				<tr>
					<td>${a.b_num }</td>
					<td><a href="detail?b_num=${a.b_num }">${a.b_title }</a>
					<c:if test="${a.b_replycnt ne 0}">
						[&nbsp;<c:out value="${a.b_replycnt}"/>&nbsp;]
					</c:if>
					</td>
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
				<c:if test="${page1.nowPage > 10}">
					<a href="questionpage?nowPage=${page1.startPage -1}">&#60;</a> 				
				</c:if>
					
				<c:forEach begin="${page1.startPage}" end="${page1.endPage}" var="p">
					<c:choose>
						<c:when test="${p==page1.nowPage}">
							<b>${p}</b>
						</c:when>
						<c:when test="${p!=page1.nowPage}">
							<a href="questionpage?nowPage=${p}&cntPerPage=${page1.cntPerPage}">${p}</a>
						</c:when>
					</c:choose>
				</c:forEach>
				<!-- 
				<c:if test="${page1.nowPage ne page1.realEnd && page1.endPage>0}"></c:if>
				 -->			
				<c:if test="${page1.next && page1.endPage>0}">
					<a href="questionpage?nowPage=${page1.endPage +1}">&#62;</a>
				</c:if> 
			</div>
      
		<div id="tfoot">
			<c:if test="${page1.nowPage ne 1}">
				<a href="noticepage?nowPage=${page1.startPage -1}">&#60;</a>
			</c:if>


			<c:forEach begin="${page1.startPage}" end="${page1.endPage}" var="p">
				<c:choose>
					<c:when test="${p==page1.nowPage}">
						<b>${p}</b>
					</c:when>
					<c:when test="${p!=page1.nowPage}">
						<a href="noticepage?nowPage=${p}&cntPerPage=${page1.cntPerPage}">${p}</a>
					</c:when>
				</c:choose>
			</c:forEach>

			<c:if test="${page1.nowPage ne page1.realEnd && page1.endPage>0}">
				<a href="noticepage?nowPage=${page1.endPage +1}">&#62;</a>
			</c:if>
		</div>
		</div>



	</body>
</html>