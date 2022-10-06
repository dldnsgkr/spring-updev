<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="resources/css/boardwrite.css" type="text/css">
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!--		<script src="resources/js/boardwrite.js"></script>
 
		<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet"> 
		<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
		<script src=" https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/lang/summernote-ko-KR.min.js"></script>

		<script src="resources/summernote/summernote-lite.js"></script>
  		<script src="resources/summernote/summernote-ko-KR.js"></script>
  		<link rel="stylesheet" href="resources/summernote/summernote-lite.css">
 -->
<script type="text/javascript">
$(function(){
	var member_nick =$("#m_nick").val();
	$("#notice").hide();
	$("#b_cate").hide();
	if(member_nick == "관리자")
		{
			$("#notice").show();
			$("#b_cate").show();
		} else {
			$("#notice").hide();
			$("#b_cate").hide();
			
		}
});

</script>
	</head>
	<body>
		<div class="wrap">
		<!--  
<div class="container">
  <textarea class="summernote" name="editordata"></textarea>    
</div>
<script>
$('.summernote').summernote({
	  height: 150,
	  lang: "ko-KR"
	});
</script>-->
			<form action="writesave" method="post" enctype="multipart/form-data" name="write">
				<table border="1">
				<tr id="b_cate">
					<th>종류</th>
					<td>
					<select name="b_cate">
				   <option value="공지">공지</option>
				   <option value="일반" selected="selected">일반</option>
				   </select>
					</td>
				</tr>
				<tr>
				   <th>분류</th>
				   <td>
				   <select name="b_kind">
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
					</c:choose><c:choose>
						<c:when test="${b_kind=='지식인'}">
							<option value="지식인" selected="selected">지식인</option>
						</c:when>
						<c:otherwise>
							<option value="지식인">지식인</option>
						</c:otherwise>
					</c:choose><c:choose>
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
				   <td><input type="text" name="b_title" id="input"></td>
				</tr>
				<tr>
				   <th>내용</th>
				   <td>
					
				   <textarea cols="50" rows="10" name="b_content" id="input_c"></textarea></td>
				   <!-- summernote -->
				</tr>
				<tr>
				   <th>첨부파일1</th>
				   <td><input type="file" name="b_file1" id="file"></td><!-- summernote 사용 예정 -->
				</tr>
				<tr>
				   <th>첨부파일2</th>
				   <td><input type="file" name="b_file2" id="file"></td><!-- summernote 사용 예정 -->
				</tr>
				</table>
			<input type="hidden" name="m_nick" id="m_nick" value="${member_nick }">
			<input type="submit" value="등록">
			<!--  <button class="button" type="button" name="boardwrite" onclick="boardwrite()">등록</button>-->
			</form>
		</div>
	</body>
</html>