<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./resources/css/admin/admin_mylist.css">
</head>
<body>
	<div class="infoupdate">
		<div class="title">
			<span></span>
			<p>마이 글</p>
		</div>
		<div class="content">
			<ul>
				<li onclick="admin_mylist_select('admin_mywrite_select');">내가 쓴 글</li>
				<li onclick="admin_mylist_select('admin_mylike_select');">내가 좋아요 한 글</li>
				<li onclick="admin_mylist_select('admin_myscrap_select');">내가 스크랩 한 글</li>
			</ul>
			<div id="out">
				<table border="1">
					<tr><td>번호</td><td>종류</td><td>분류</td><td>제목</td><td>작성일</td>
					<td>내용</td><td>추천수</td><td>조회수</td><td>태그</td><td>첨부파일1</td>
					<td>첨부파일2</td><td>신고횟수</td><td>삭제</td><td>수정</td>
					</tr>
					<c:forEach items="${list}" var="list">
					<tr><td>${list.b_num}</td><td>${list.b_cate}</td><td>${list.b_kind}</td>
					<td><a href="detail?b_num=${list.b_num}">${list.b_title}</a></td>
					<td>${list.b_wdate}</td><td>${list.b_content}</td><td>${list.b_likecnt}</td><td>${list.b_readcnt}</td>
					<td>${list.b_tag}</td><td>${list.b_file1}</td><td>${list.b_file2}</td><td>${list.b_report}</td>
					<td><a onclick="admin_mylist_delete(${list.b_num});">삭제</a></td>
					<td><a href="writeupdatecheck?b_num=${list.b_num}">수정</a></td>
					</tr>
					</c:forEach>
				</table>
				<a href="write">글쓰기</a>
			</div>
			<!-- 
			<div id="myModal" class="modal">
			  <div class="modal-content">
			    <div class="modal-header">
			      <h2>아래의 내용으로 수정합니다.</h2>
			    </div>
			    <div class="modal-body">
			    <table border="1">
					<tr><td>번호</td><td>종류</td><td>분류</td><td>제목</td><td>작성일</td>
					<td>내용</td><td>추천수</td><td>조회수</td><td>태그</td><td>첨부파일1</td>
					<td>첨부파일2</td><td>신고횟수</td><td>삭제</td><td>수정</td>
					</tr>
					<c:forEach items="${list}" var="list">
					<tr><td>${list.b_num}</td><td>${list.b_cate}</td><td>${list.b_kind}</td>
					<td><a href="detail?b_num=${list.b_num}">${list.b_title}</a></td>
					<td>${list.b_wdate}</td><td>${list.b_content}</td><td>${list.b_likecnt}</td><td>${list.b_readcnt}</td>
					<td>${list.b_tag}</td><td>${list.b_file1}</td><td>${list.b_file2}</td><td>${list.b_report}</td>
					<td><a onclick="admin_mylist_delete(${list.b_num});">삭제</a></td>
					<td><a onclick="admin_mylist_update(${list.b_num});">수정</a></td>
					</tr>
					</c:forEach>
				</table>
			    </div>
			 
			    <div class="modal-footer">
			      <h3>Modal Footer</h3>
			    </div>
			   
			  </div>
			 -->
			</div>
		</div>
	</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="./resources/js/admin/admin_mylist.js"></script>
</body>
</html>