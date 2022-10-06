<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/boardreport.css"
	type="text/css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(function() {
		//입력박스 숨어있다가
		$("#otherreason").hide();
		$("#r_reason").change(function() {
			//기타를 선택하면 등장
			if ($("#r_reason").val() == "etc") {
				$("#otherreason").show();
				alert("기타사유를 작성해주세요.");
			} else {
				$("#otherreason").hide();
			}
		})
	});
</script>
<title>Insert title here</title>
</head>
<body>
	<!-- 신고 게시판 -->
	<div class="report">
		<form action="breport" method="post" enctype="multipart/form-data">
			<input type="hidden" name="b_num" value="${b_num }">
			<table border="1">
			<thead id="thead">
				<tr>
					<th>게시글 제목</th>
					<th>신고사유</th>
					<th>첨부파일</th>
				</tr>
			</thead>
			<tbody id="tbody">
				<tr>
					<td>${b_title }</td>
					<td><select name="r_reason" id="r_reason">
							<option value="원치않는 상업성 콘텐츠 또는 스팸,도배">원치않는 상업성 콘텐츠 또는 스팸,도배</option>
							<option value="포르노 또는 음란물">포르노 또는 음란물</option>
							<option value="증오심 표현 또는 혐오적인 발언 및 욕설">증오심 표현 또는 혐오적인 발언 및 욕설</option>
							<option value="희롱 또는 괴롭힘">희롱 또는 괴롭힘</option>
							<option value="잘못된 정보">잘못된 정보</option>
							<option value="개인정보 노출">개인정보 노출</option>
							<option value="etc">기타</option>
					</select>
					<input type="text" id="otherreason" name="otherreason" /></td>
					<td><input type="file" name="r_file1"></td>	
				</tr>
			</tbody>
			</table>
			<input type="submit" id="submit" value="신고하기">
		</form>
	</div>
</body>
</html>