<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="writeupdate" method="post" enctype="multipart/form-data">
<input type="hidden" name="b_num" value="${list.b_num }">
<input type="hidden" name="b_cate" value="${list.b_cate }">
<input type="hidden" name="m_nick" value="${list.m_nick }">
<table>
<tr>
   <th>분류</th>
   <td><input type="text" name="b_kind" value="${list.b_kind }"></td><!-- select 혹은 텍스트형식 두개 만들어놨습니당 -->
   <!-- <select name="b_kind">
   <option value="?">?</option>
   <option value="?">?</option>
   <option value="?">?</option>
   <option value="?">?</option>
   <option value="?">?</option>
   <option value="?">?</option>
   </select> -->
</tr>
<tr>
   <th>제목</th>
   <td><input type="text" name="b_title" value="${list.b_title }"></td>
</tr>
<tr>
   <th>내용</th>
   <td><input type="text" name="b_content" value="${list.b_content }"></td><!-- summernote -->
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
   <td><input type="text" name="b_tag" value="${list.b_tag }"></td>
</tr>
<tr>
   <td><input type="submit" value="수정"></td>
</tr>
</table>
</form>
</body>
</html>