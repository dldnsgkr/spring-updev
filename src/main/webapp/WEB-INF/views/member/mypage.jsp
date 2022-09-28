<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/mypage.css" type="text/css" />
</head>
<body>
<<<<<<< HEAD
<a href="write">�۾���</a><!-- �۾��� ��������ġ ���Ͻô°��� ����Ʈ �κ��̼� �����ؼ� �������ּ��� �̴�� ���� ��������ϴ�-->
<a href="myp">���� ��</a>
<a href="#">���̾˸�</a>
<a href="#">��������</a>
<a href="proupdatecheck">�����ʼ���</a>
<table>
<tr>
	<th>��ȣ</th>
	<th>�з�</th>
	<th>����</th>
	<th>�ۼ���</th>
	<th>���ƿ��</th>
	<th>��ȸ��</th>
	<th>���� / ����</th>
=======
<div class="mypage_tabs">
   <div class="mypage_tab">
       <input type="radio" id="tab-1" name="tab-group-1">
       <label for="tab-1"><a href="proupdatecheck?m_nick=${member.m_nick }">마이 프로필</a></label>
       <div class="mypage_content">
           <span>프로필 수정을 누르면 나오는 거</span>
       </div> 
   </div>
    
   <div class="mypage_tab">
       <input type="radio" id="tab-2" name="tab-group-1">
       <label for="tab-2"><a href="myp?m_nick=${member.m_nick }">마이 글</a></label>
       <!-- 마이 글 안에 들어 가야 할 것 : 내가 쓴 글, 내가 좋아요한 글, 내가 스크랩한 글  -->
       <div class="mypage_content">
           <span>나의 글을 누르면 나오는 거</<span>
       </div> 
   </div>
    
   <div class="mypage_tab">
       <input type="radio" id="tab-3" name="tab-group-1">
       <label for="tab-3"><a href="#">마이알림</a></label>
       <div class="mypage_content">
           <span>마이알림을 누르면 나오는 거 </span>
       </div> 
   </div>
   <div class="mypage_tab">
       <input type="radio" id="tab-4" name="tab-group-1">
       <label for="tab-4"><a href="#">마이쪽지</a></label>
     
       <div class="mypage_content">
           <span>마이쪽지를 누르면 나오는 거 </span>
       </div> 
   </div>
   
    
</div>
<!--<a href="write">글쓰기</a> 글쓰기 가능한위치 원하시는곳에 프론트 두분이서 상의해서 변경해주세요 이대로 가도 상관없습니다-->

<a href="#">마이알림</a>
<a href="#">마이쪽지</a>
<a href="proupdatecheck?m_nick=${member.m_nick }">프로필수정</a>

<table>
<tr>
	<th>번호</th>
	<th>분류</th>
	<th>제목</th>
	<th>작성일</th>
	<th>좋아요수</th>
	<th>조회수</th>
	<th>수정 / 삭제</th>
>>>>>>> ae08c0a2cc3ed967af28cd21396e92ab74c061b9
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