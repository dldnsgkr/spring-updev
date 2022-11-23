<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"
	src="./resources/js/mypageupdateconfirm.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="resources/css/boardwrite.css" type="text/css">
<script src="resources/js/boardwrite.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script src="resources/summernote/summernote-lite.js"></script>
<script src="resources/summernote/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="resources/summernote/summernote-lite.css">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet"> 
		  <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
		  <script src=" https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/lang/summernote-ko-KR.min.js"></script>

<script type="text/javascript">
	$(function() {
		var member_nick = $("#m_nick").val();
		$("#notice").hide();
		$("#cate").hide();
		if (member_nick == "관리자") {
			$("#notice").show();
			$("#cate").show();
		} else {
			$("#notice").hide();
			$("#cate").hide();

		}
	});
</script>
</head>
<body>
	<div class="wrap">
		<div class="container">
			<form method="get" enctype="multipart/form-data" name="board_update_write">
				<input type="hidden" id="b_num" name="b_num" value="${list.b_num }"> 
				<input type="hidden" id="m_nick" name="m_nick" value="${list.m_nick }">
				<table border="1">
					<tr id="cate">
						<th>종류</th>
						<td><select name="b_cate" id="b_cate">
								<option value="공지">공지</option>
								<option value="일반" selected="selected">일반</option>
						</select></td>
					</tr>
					<tr>
						<th>분류</th>
						<td><select name="b_kind" id="b_kind">
								<c:choose>
									<c:when test="${b_kind=='공지'}">
										<option value="공지" selected="selected">공지</option>
									</c:when>
									<c:otherwise>
										<option id="notice" value="공지">공지</option>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${b_kind=='정보공유'}">
										<option value="정보공유" selected="selected">정보공유</option>
									</c:when>
									<c:otherwise>
										<option value="정보공유">정보공유</option>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${b_kind=='지식인'}">
										<option value="지식인" selected="selected">지식인</option>
									</c:when>
									<c:otherwise>
										<option value="지식인">지식인</option>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${b_kind=='고민상담소'}">
										<option value="고민상담소" selected="selected">고민상담소</option>
									</c:when>
									<c:otherwise>
										<option value="고민상담소">고민상담소</option>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${b_kind=='QNA'}">
										<option value="QNA" selected="selected">Q&A</option>
									</c:when>
									<c:otherwise>
										<option value="QNA">Q&A</option>
									</c:otherwise>
								</c:choose>
						</select></td>
					</tr>
					<tr>
						<th>제목</th>
						<td><input type="text" name="b_title" id="input" value="${list.b_title }"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea class="summernote" name="b_content" id="b_content">${list.b_content }</textarea></td>
						<!-- summernote -->
					</tr>
					
				</table>
						<input type="submit" class="button" value="수정" onclick="updateconfirm()">
			</form>
			</div>
			</div>

			<script>
				$('.summernote').summernote({
					width : 995,
					height : 230, // 에디터 높이
					minHeight : null, // 최소 높이
					maxHeight : null, // 최대 높이
					focus : true, // 에디터 로딩후 포커스를 맞출지 여부
					lang : "ko-KR", // 한글 설정
					placeholder : '내용을 입력하세요.' //placeholder 설정
				});
				$('.note-statusbar').hide()
				
			</script>
</body>
</html>