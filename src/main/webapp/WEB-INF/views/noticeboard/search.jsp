<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <table align="center">
 
<form action="search">
    <tr>
        <input type="text" name="keyword">
        <input type="submit" value="검색">
        </th>
    </tr>
</form>

    </table>
<table border="2" align="center">
<tr>
	<th>번호</th>
	<th>제목</th>
	<th>작성자</th>
	<th>작성일</th>
	<th>조회수</th>
	<th>추천수</th>
</tr>
<c:forEach items="${paging }" var="a">
<tr>
	<td>${a.b_num }</td>
	<td><a href="detail?b_num=${a.b_num }">${a.b_title }</a></td>
	<td>${a.m_nick }</td>
	<td>${a.b_wdate }</td>
	<td>${a.b_readcnt }</td>
	<td>${a.b_likecnt }</td>
</tr>
</c:forEach>

<tr>
	<td colspan="7">
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
				
	
	<c:if test="${page.nowPage ne page.realEnd && page.endPage>0}">
		<a href="search?nowPage=${page.endPage +1}&keyword=${keyword}">&#62;</a>
	</c:if>   
	</td>
</tr>
</table>
</body>
</html>