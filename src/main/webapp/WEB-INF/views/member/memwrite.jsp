<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="./resources/js/ajaxmywrite.js"></script>
<link rel="stylesheet" type="text/css" href="./resources/css/memwrite.css">
</head>
<body>
	<div class="wrapper">
		<form id="mywriteform">
		<div class="title"><h3>내가 쓴 게시물</h3></div>
			<table>
				<tr>
 					<th>번호</th>
					<th>제목</th>
					<th>분류</th>
					<th>추천수</th>
					<th>조회수</th>
					<th>작성일</th>
					<th></th>
				</tr>
				<c:forEach items="${bpage1}" var="list">
				<tr>
					<td>${list.b_num}</td>
					<td>
					<c:set var="TitleValue" value="${list.b_title}"/>
					<c:choose>
						<c:when test="${fn:length(TitleValue) gt 25}">
							<a href="detail?b_num=${list.b_num}&receiveread=a">${fn:substring(TitleValue,0,25).trim() }.....</a>
						</c:when>
						<c:otherwise>
							<a href="detail?b_num=${list.b_num}&receiveread=a">${fn:substring(TitleValue,0,25).trim() }</a>
						</c:otherwise>
					</c:choose>
					
					</td>
					<td>${list.b_kind}</td>
					<td>${list.b_likecnt}</td>
					<td>${list.b_readcnt}</td>
					<td><fmt:parseDate value='${list.b_wdate}' var='date' pattern='yyyy-MM-dd HH:mm:ss'/>
						<fmt:formatDate value="${date}" pattern="yyyy.MM.dd."/></td>

						
					<form action="writedelete" method="post">
					<input type="hidden" id="loginstate" value="${loginState }">
					<input type="hidden" id="b_num" value="${list.b_num }">
					<input type="hidden" id="b_kind" value="내가쓴글">	
					<td>	
					<a href="javascript:del();" id="de">삭제</a>

               		</form>
               		<a href='writeupdatecheck?b_num=${list.b_num}&b_kind=${list.b_kind }'">수정</a></td>


				</tr>
				</c:forEach>
			</table>
		</form>
	</div>
				<div id="tfoot">				
			<c:if test="${page1.nowPage > 10}">
				<a href="ajaxmywrite?m_nick=${member_nick}&nowPage=${page1.startPage -1}">&#60;</a>
			</c:if>


			<c:forEach begin="${page1.startPage}" end="${page1.endPage}" var="p">
				<c:choose>
					<c:when test="${p==page1.nowPage}">
						<b>${p}</b>
					</c:when>
					<c:when test="${p!=page1.nowPage}">
						<a href="ajaxmywrite?m_nick=${member_nick}&nowPage=${p}&cntPerPage=${page1.cntPerPage}">${p}</a>
					</c:when>
				</c:choose>
			</c:forEach>

			<c:if test="${page1.next && page1.endPage>0}">
				<a href="ajaxmywrite?m_nick=${member_nick}&nowPage=${page1.endPage +1}">&#62;</a>
			</c:if>
		</div> 
</body>
</html>