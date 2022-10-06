<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="resources/css/boardwrite.css" type="text/css">
		<script src="resources/js/boardwrite.js"></script>

		<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet"> 
		<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
		<script src=" https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/lang/summernote-ko-KR.min.js"></script>

		<script src="resources/summernote/summernote-lite.js"></script>
  		<script src="resources/summernote/summernote-ko-KR.js"></script>
  		<link rel="stylesheet" href="resources/summernote/summernote-lite.css">

	</head>
	<body>
		<div class="wrap">
<div class="container">
  <textarea class="summernote" name="editordata"></textarea>    
</div>
<script>
$('.summernote').summernote({
	  height: 150,
	  lang: "ko-KR"
	});
</script>
			<form action="writesave" method="post" enctype="multipart/form-data" name="write">
				<table border="1">
				<tr>
				   <th>분류</th>
				   <td>
				   <select name="b_kind">
				   <option value="정보공유">정보공유</option>
				   <option value="지식인">지식인</option>
				   <option value="고민상담소">고민상담소</option>
				   <option value="QNA">Q&A</option>
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
			
			<button class="button" type="button" name="boardwrite" onclick="Board_write();">등록</button>
			</form>
		</div>
	</body>
</html>