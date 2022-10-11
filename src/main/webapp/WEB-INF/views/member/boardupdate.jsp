<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="./resources/js/mypageupdateconfirm.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
<form action="writeupdate" method="post" enctype="multipart/form-data">
<input type="hidden" name="b_num" value="${list.b_num }">
<input type="hidden" id="m_nick" name="m_nick" value="${list.m_nick }">
<table>
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
   <td><input type="submit" value="수정" onclick="updateconfirm()"></td>
</tr>
</table>
</form>
</body>
</html>