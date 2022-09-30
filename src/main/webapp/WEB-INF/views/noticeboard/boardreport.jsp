<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script>
$(function(){
      //입력박스 숨어있다가
$("#otherreason").hide();
$("#r_reason").change(function() {
                //기타를 선택하면 등장
        if($("#r_reason").val() == "etc") {
            $("#otherreason").show();
        }  else {
            $("#otherreason").hide();
        }
    }) 
});
</script>
<title>Insert title here</title>
</head>
<body>
<!-- 신고 게시판 -->
<form action="breport" method="post" enctype="multipart/form-data">
<input type="hidden" name="b_num" value="${b_num }">
<table>
	<tr>
		<th>게시글 제목</th>
		<td>${b_title }</td>
	</tr>
	<tr>
		<th>신고사유</th>
		<td><select name="r_reason" id="r_reason">
		<option value="원치않는 상업성 콘텐츠 또는 스팸,도배">원치않는 상업성 콘텐츠 또는 스팸,도배</option>
		<option value="포르노 또는 음란물">포르노 또는 음란물</option>
		<option value="증오심 표현 또는 혐오적인 발언 및 욕설">증오심 표현 또는 혐오적인 발언 및 욕설</option>
		<option value="희롱 또는 괴롭힘">희롱 또는 괴롭힘</option>
		<option value="잘못된 정보">잘못된 정보</option>
		<option value="개인정보 노출">개인정보 노출</option>
		<option value="etc">기타</option>
		</select>
		</td>
		<td><input type="text" id="otherreason" name="otherreason"/></td>
	</tr>
	<tr>
		<th>첨부파일</th>
		<td><input type="file" name="r_file1">
	</tr>
</table>
<input type="submit" value="신고하기">
</form>
</body>
</html>