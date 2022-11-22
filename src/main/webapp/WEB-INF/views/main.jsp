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
		<link rel="stylesheet" href="resources/css/main.css" type="text/css">
		<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
		<script type="text/javascript" src="./resources/js/main.js"></script>
		<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
		<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
	</head>
	<body>
		<div class="wrap" id="main_body">
			<div class="main_top">
				<div class="slider-wrap">
	    			<div class="cont">
	       				<img src="./resources/images/banner1.jpg" width="1200px">
		       			<div class="main_in1">개발자로 취업하자<br>교육비 전액 무료<br><a href="">자세히 보기</a></div>
	    			</div>
	    			<div class="cont">
	       				<img src="./resources/images/banner2.jpg" width="1200px">
	       				<div class="main_in2">UPDEV 개발자 채용<br><a href="">자세히 보기</a></div>
	    			</div>
	    			<div class="cont">
	         			<img src="./resources/images/banner3_1.jpg" width="1200px">
	         			<div class="main_in3">경력보다 실력<br>UPDEV 개발자를 만나다.<br><a href="">자세히 보기</a></div>
	    			</div>
				</div>
			</div>
		<div class="main_board">
			<div class="board">
				<span class="main_boardtitle">인기게시글</span>
				<table>
				<a href="poppage" id="alink">더보기</a>
				<c:forEach items="${popmpage}" var="a" begin="0" end="7">
				<tr>
					<td><input type="hidden" value="${a.b_num}"></td>
					<td class="main_boardkind">
					<c:set var="KindValue" value="${a.b_kind}"/>${fn:substring(KindValue,0,2) }</td>
					<td class="main_popboardtitle">
					<c:set var="TitleValue" value="${a.b_title}"/>
					<c:choose>
						<c:when test="${fn:length(TitleValue) gt 5}">
							<a href="detail?b_num=${a.b_num}&receiveread=a">${fn:substring(TitleValue,0,5).trim() }.....</a>
						</c:when>
						<c:otherwise>
							<a href="detail?b_num=${a.b_num}&receiveread=a">${fn:substring(TitleValue,0,5).trim() }</a>
						</c:otherwise>
					</c:choose>
					<c:if test="${a.b_replycnt ne 0}">
						[<c:out value="${a.b_replycnt}"/>]
					</c:if></td>
					<td class="main_popboarddate"><fmt:parseDate value='${a.b_wdate}' var='date' pattern='yyyy-MM-dd HH:mm:ss'/>
					<fmt:formatDate value="${date}" pattern="yy.MM.dd."/></td>	
				</tr>
				</c:forEach>
				</table>
			</div>
			<div class="board">
				<span class="main_boardtitle">공지</span>
				<a href="noticepage" id="alink">더보기</a>
				<table>
				<c:forEach items="${noticempage}" var="a" begin="0" end="7">
				<tr>
					<td><input type="hidden" value="${a.b_num}"></td>
					<td>
					<c:set var="TitleValue" value="${a.b_title}"/>
					<c:choose>
					<c:when test="${fn:length(TitleValue) gt 5}">
					<a href="detail?b_num=${a.b_num}&receiveread=a">${fn:substring(TitleValue,0,5) }</a>
					</c:when>
					<c:otherwise>
					<a href="detail?b_num=${a.b_num}&receiveread=a">${fn:substring(TitleValue,0,5) }</a>
					</c:otherwise>
					</c:choose>
					<c:if test="${a.b_replycnt ne 0}">
						[<c:out value="${a.b_replycnt}"/>]
					</c:if></td>
					<td class="main_boarddate"><fmt:parseDate value='${a.b_wdate}' var='date' pattern='yyyy-MM-dd HH:mm:ss'/>
					<fmt:formatDate value="${date}" pattern="yy.MM.dd."/></td>	
				</tr>
				</c:forEach>
				</table>
			</div>
			<div class="board">
				<span class="main_boardtitle">정보공유</span>
				<table>
					<a href="sharepage" id="alink">더보기</a>
					<c:forEach items="${sharempage}" var="a" begin="0" end="7">
					<tr>
					<td><input type="hidden" value="${a.b_num}"></td>
					<td>
					<c:set var="TitleValue" value="${a.b_title}"/>
					<c:choose>
					<c:when test="${fn:length(TitleValue) gt 5}">
					<a href="detail?b_num=${a.b_num}&receiveread=a">${fn:substring(TitleValue,0,5).trim() }.....</a>
					</c:when>
					<c:otherwise>
					<a href="detail?b_num=${a.b_num}&receiveread=a">${fn:substring(TitleValue,0,5).trim() }</a>
					</c:otherwise>
					</c:choose>
					<c:if test="${a.b_replycnt ne 0}">
					[<c:out value="${a.b_replycnt}"/>]
					</c:if></td>
					<td class="main_boarddate"><fmt:parseDate value='${a.b_wdate}' var='date' pattern='yyyy-MM-dd HH:mm:ss'/>
					<fmt:formatDate value="${date}" pattern="yy.MM.dd."/></td>	
					</tr>
					</c:forEach>
				</table>
			</div>
			<div class="board">
				<span class="main_boardtitle">지식인</span>
				<a href="questionpage" id="alink">더보기</a>
				<table>
				<c:forEach items="${questionmpage}" var="a" begin="0" end="7">
				<tr>
					<td><input type="hidden" value="${a.b_num}"></td>
					<td>
					<c:set var="TitleValue" value="${a.b_title}"/>
					<c:choose>
					<c:when test="${fn:length(TitleValue) gt 5}">
					<a href="detail?b_num=${a.b_num}&receiveread=a">${fn:substring(TitleValue,0,5).trim() }.....</a>
					</c:when>
					<c:otherwise>
					<a href="detail?b_num=${a.b_num}&receiveread=a">${fn:substring(TitleValue,0,5).trim() }</a>
					</c:otherwise>
					</c:choose>
					<c:if test="${a.b_replycnt ne 0}">
						[<c:out value="${a.b_replycnt}"/>]
					</c:if></td>
					<td class="main_boarddate"><fmt:parseDate value='${a.b_wdate}' var='date' pattern='yyyy-MM-dd HH:mm:ss'/>
					<fmt:formatDate value="${date}" pattern="yy.MM.dd."/></td>	
				</tr>
				</c:forEach>
				</table>
			</div>
			<div class="board">
				<span class="main_boardtitle">고민상담소</span>
				<a href="worrypage" id="alink">더보기</a>
				<table>
				<c:forEach items="${worrympage}" var="a" begin="0" end="7">
				<tr>
					<td><input type="hidden" value="${a.b_num}"></td>
					<td>
					<c:set var="TitleValue" value="${a.b_title}"/>
					<c:choose>
					<c:when test="${fn:length(TitleValue) gt 5}">
					<a href="detail?b_num=${a.b_num}&receiveread=a">${fn:substring(TitleValue,0,5).trim() }.....</a>
					</c:when>
					<c:otherwise>
					<a href="detail?b_num=${a.b_num}&receiveread=a">${fn:substring(TitleValue,0,5).trim() }</a>
					</c:otherwise>
					</c:choose>
					<c:if test="${a.b_replycnt ne 0}">
						[<c:out value="${a.b_replycnt}"/>]
					</c:if></td>
					<td class="main_boarddate"><fmt:parseDate value='${a.b_wdate}' var='date' pattern='yyyy-MM-dd HH:mm:ss'/>
					<fmt:formatDate value="${date}" pattern="yy.MM.dd."/></td>	
				</tr>
				</c:forEach>
				</table>
			</div>
			
			<div class="board">

				<span class="main_boardtitle">Q&A</span>
				<a href="qnapage" id="alink">더보기</a>
				<table>
				<c:forEach items="${qnampage}" var="a" begin="0" end="7">
				<tr>
					<td><input type="hidden" value="${a.b_num}"></td>
					<td>
					<c:set var="TitleValue" value="${a.b_title}"/>
					<c:choose>
					<c:when test="${fn:length(TitleValue) gt 5}">
					<a href="detail?b_num=${a.b_num}&receiveread=a">${fn:substring(TitleValue,0,5).trim() }.....</a>
					</c:when>
					<c:otherwise>
					<a href="detail?b_num=${a.b_num}&receiveread=a">${fn:substring(TitleValue,0,5).trim() }</a>
					</c:otherwise>
					</c:choose>
					<c:if test="${a.b_replycnt ne 0}">
						[<c:out value="${a.b_replycnt}"/>]
					</c:if></td>
					<td class="main_boarddate"><fmt:parseDate value='${a.b_wdate}' var='date' pattern='yyyy-MM-dd HH:mm:ss'/>
					<fmt:formatDate value="${date}" pattern="yy.MM.dd."/></td>	
				</tr>
				</c:forEach>
				</table>			
			</div>
			</div>				
		</div>
	</body>
</html>