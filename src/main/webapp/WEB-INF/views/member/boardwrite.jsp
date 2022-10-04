<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="writesave" method="post" enctype="multipart/form-data">
<input type="hidden" name="b_cate" value="일반">
<input type="hidden" name="m_nick" value="${member.m_nick }">
<table>
<tr>
   <th>분류</th>
   <td>
   <select name="b_kind">
   <option value="정보">정보공유</option>
   <option value="질문">지식인</option>
   <option value="고민">고민상담소</option>
   <option value="QNA">Q&A</option>
   </select></td>
</tr>
<tr>
   <th>제목</th>
   <td><input type="text" name="b_title"></td>
</tr>
<tr>
   <th>내용</th>
   <td><input type="text" name="b_content"></td><!-- summernote -->
</tr>
<tr>
   <th>첨부파일1</th>
   <td><input type="file" name="b_file1"></td><!-- summernote 사용 예정 -->
</tr>
<tr>
   <th>첨부파일2</th>
   <td><input type="file" name="b_file2"></td><!-- summernote 사용 예정 -->
</tr>
<tr>
   <th>태그</th>
   <td><input type="text" name="b_tag"></td>
</tr>
<tr>
   <td><input type="submit" value="등록"></td>
</tr>
</table>
</form>
</body>
</html>