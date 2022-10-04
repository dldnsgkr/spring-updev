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
	<!-- 정보공유 게시판 -->
		<div class="wrap">
			<div class="board_notic">
				여기는 개발자들의 지식을 공유하는 곳입니다.
			</div>
			<div class="board_write">
				<button class="button" type="button" name="board_write" onclick="location.href = 'write' ">글쓰기</button>
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
						<!-- 
						<td>${a.b_wdate }</td>
						 -->
						<td>${a.b_readcnt }</td>
						<td>${a.b_likecnt }</td>
						<td>스크랩 버튼</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<div id="tfoot">				
						<c:if test="${page1.nowPage > 10}">
							<a href="sharepage?nowPage=${page1.startPage -1}">&#60;</a> 				
						</c:if>
					
				
<<<<<<< HEAD
	
	<c:if test="${page1.next && page1.endPage>0}">
		<a href="sharepage?nowPage=${page1.endPage +1}">&#62;</a>
	</c:if>   
	</td>
</tr>
	
</table>
</body>
=======
						<c:forEach begin="${page1.startPage}" end="${page1.endPage}" var="p">
							<c:choose>
								<c:when test="${p==page1.nowPage}">
									<b>${p}</b>
								</c:when>
								<c:when test="${p!=page1.nowPage}">
									<a href="sharepage?nowPage=${p}&cntPerPage=${page1.cntPerPage}">${p}</a>
								</c:when>
							</c:choose>
						</c:forEach>
									
						
						<c:if test="${page1.next && page1.endPage>0 }">
							<a href="sharepage?nowPage=${page1.endPage +1}">&#62;</a>
						</c:if>   
				</div>			
		</div>
	</body>
>>>>>>> upstream/main
</html>