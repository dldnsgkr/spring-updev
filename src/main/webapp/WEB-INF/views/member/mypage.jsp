<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="write">글쓰기</a><!-- 글쓰기 가능한위치 원하시는곳에 프론트 두분이서 상의해서 변경해주세요 이대로 가도 상관없습니다-->
<a href="myp">나의 글</a>
<a href="#">마이알림</a>
<a href="#">마이쪽지</a>
<a href="proupdatecheck">프로필수정</a>
<table>
<tr>
	<th>번호</th>
	<th>분류</th>
	<th>제목</th>
	<th>작성일</th>
	<th>좋아요수</th>
	<th>조회수</th>
	<th>수정 / 삭제</th>
</tr>
<c:forEach items="${list }" var="a">
<tr>
	<td>${a.b_num }</td>
	<td>${a.b_kind }</td>
	<td>${a.b_title }</td>
	<td>${a.b_wdate }</td>
	<td>${a.b_likecnt }</td>
	<td>${a.b_readcnt }</td>
	<td>
	<input type="button" value="수정" onclick="location.href='writeupdatecheck?b_num=${a.b_num}'">&emsp;
	<input type="button" value="삭제" onclick="location.href='writedelete?b_num=${a.b_num}'"><!-- 정말 삭제하시겠습니까 유효성 띄우고 확인하면 삭제 되는 기능부탁합니다 -->
	</td>
</tr>
</c:forEach>
</table>
</body>
</html>