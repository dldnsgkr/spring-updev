<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
		<th>조회수</th>
		<th>추천수</th>
	</tr>
	<c:forEach items="${bpage1 }" var="a">
	<tr>
		<td>${a.b_num }</td>
		<td><a href="detail?b_num=${a.b_num }">${a.b_title }</a>
		<c:if test="${a.b_replycnt ne 0}">
			[&nbsp;<c:out value="${a.b_replycnt}"/>&nbsp;]
		</c:if>
		</td>
		<td>${a.m_nick }</td>
		<td>${a.b_wdate }</td>
		<td>${a.b_readcnt }</td>
		<td>${a.b_likecnt }</td>
	</tr>
	</c:forEach>
	
<tr>
	<td colspan="7">
	<c:if test="${page1.nowPage > 10}">
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
				
	
	<c:if test="${page1.next && page1.endPage>0}">
		<a href="noticepage?nowPage=${page1.endPage +1}">&#62;</a>
	</c:if>   
	</td>
</tr>


</table>
</body>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="resources/css/board.css" type="text/css">
	</head>
	<body>
	<!-- 공지 게시판 -->
		<div class="wrap">
			<div class="board_notic">
				여기는 UPDEV의 공지를 올리는 곳입니다.
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
						<th width="50px">스크랩</th>
					</tr>
				</thead>
				<tbody id="tbody">
					<c:forEach items="${bpage1 }" var="a">
					<tr>
						<td>${a.b_num }</td>
						<td><a href="detail?b_num=${a.b_num }">${a.b_title }</a></td>
						<td>${a.m_nick }</td>
						<td><fmt:parseDate value='${a.b_wdate}' var='date' pattern='yyyy-MM-dd HH:mm:ss'/>
						<fmt:formatDate value="${date}" pattern="yyyy.MM.dd."/></td>	
						<td>${a.b_readcnt }</td>
						<td>${a.b_likecnt }</td>
						<td>이곳에 스크랩버튼 뻅니다</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
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