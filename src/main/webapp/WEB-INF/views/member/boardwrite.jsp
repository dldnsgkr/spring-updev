<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="./resources/js/boardwrite.js"></script>
<link rel="stylesheet" href="resources/css/boardwrite.css" type="text/css">
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet"> 
  <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
  <script src=" https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/lang/summernote-ko-KR.min.js"></script>
</head>
<body>

<div class="container">
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
 				<textarea class="summernote" name="b_content"></textarea>
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

<script>
$('.summernote').summernote({
	width: 995,
     height: 230,                 // 에디터 높이
      minHeight: null,             // 최소 높이
      maxHeight: null,             // 최대 높이
      focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
      lang: "ko-KR",                    // 한글 설정
      placeholder: '내용을 입력하세요.'    //placeholder 설정
   });
$('.note-statusbar').hide()
</script>

</body>
</html>