<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" charset="UTF-8">
/*
function ttt(){
	
	
		var like_chk =$("#like_chk").val();
		var b_num =$("#b_num").val();
		var m_nick =$("#m_nick").val();
		var sam ={"b_num":b_num,"m_nick":m_nick};
		var sam =JSON.stringify(sam);
		$.ajax({
			type:"post",
			async:false,
			url:"goodup",
			data:{jsoninfo:sam},
			success:function(data,textStatus){
				console.log(data);
				location.href="index";
			},
			error:function(data,textStatus){
				alert("전송실패!!");
			}
		});
	
		
}
*/

$(function(){
	var like_chk =$("#like_chk").val();
	if(like_chk == 1) {
			$("#ttt").html("좋아요취소");
			$("#ttt").click(function(){
				var b_num =$("#b_num").val();
				var m_nick =$("#m_nick").val();
				var sam ={"b_num":b_num,"m_nick":m_nick};
				var sam =JSON.stringify(sam);
				$.ajax({
					type:"post",
					async:false,
					url:"gooddown",
					data:{jsoninfo:sam},
					success:function(data,textStatus){
						location.href="detailajax";
					},
					error:function(data,textStatus){
						alert("전송실패!!");
					}
		
		});		//end ajax
	}); //자료입력
	} else {
		$("#ttt").click(function(){
			var b_num =$("#b_num").val();
			var m_nick =$("#m_nick").val();
			var sam ={"b_num":b_num,"m_nick":m_nick};
			var sam =JSON.stringify(sam);
			$.ajax({
				type:"post",
				async:false,
				url:"goodup",
				data:{jsoninfo:sam},
				success:function(data,textStatus){
					console.log(data);
					if(m_nick == "unknown")
						{
							alert("로그인이 필요합니다");
							location.href="login";
						} else {
					location.href="detailajax";
						}
				},
				error:function(data,textStatus){
					alert("전송실패!!");
				}
			
			});		//end ajax
		}); //자료입력
	}
});
</script>
<script type="text/javascript">
$(function(){
	var scrap_chk =$("#scrap_chk").val();
	if(scrap_chk == 1) {
			$("#sss").html("스크랩취소");
			$("#sss").click(function(){
				var b_num =$("#b_num").val();
				var m_nick =$("#m_nick").val();
				var sam ={"b_num":b_num,"m_nick":m_nick};
				var sam =JSON.stringify(sam);
				$.ajax({
					type:"post",
					async:false,
					url:"scrapcancel",
					data:{jsoninfo:sam},
					success:function(data,textStatus){
						location.href="detailajax";
					},
					error:function(data,textStatus){
						alert("전송실패!!");
					}
		
		});		//end ajax
	}); //자료입력
	} else {
		$("#sss").click(function(){
			var b_num =$("#b_num").val();
			var m_nick =$("#m_nick").val();
			var sam ={"b_num":b_num,"m_nick":m_nick};
			var sam =JSON.stringify(sam);
			$.ajax({
				type:"post",
				async:false,
				url:"scrap",
				data:{jsoninfo:sam},
				success:function(data,textStatus){
					console.log(data);
					if(m_nick == "unknown")
						{
							alert("로그인이 필요합니다");
							location.href="login";
						} else {
					location.href="detailajax";
						}
				},
				error:function(data,textStatus){
					alert("전송실패!!");
				}
			
			});		//end ajax
		}); //자료입력
	}
});
</script>
</head>
<body>
<h1>${list.b_kind }</h1>
<h4>${list.b_title }</h4>
${list.m_nick }&emsp;${list.b_wdate }
<table>
	<tr>
		<td>${list.b_content }</td>
	</tr>
	
	<tr>
	<td><input type="button" value="신고" onclick="location.href='boardreportpage?b_num=${list.b_num}&b_title=${list.b_title }'"></td>
	</tr>
</table>
<form name="frm" method="post">
	<input type="hidden" name="b_num" id="b_num" value="${list.b_num }">
	<input type="hidden" name="m_nick" id="m_nick" value="${member_nick}">
	<input type="hidden" name="like_chk" id="like_chk" value="${llist.like_chk }">
	<button type="button" id="ttt">좋아요</button>
	</form>&emsp;
<<<<<<< HEAD
	
<!-- 댓글 -->
<table>
<form action="replysave" metod="post">
<tr>
	<td><input type="hidden" name="b_num" value="${list.b_num}"></td>
	<td><input type="text" name="m_nick" value="${member_nick}" readonly></td>
</tr>
<tr>
	<td colspan="2"><textarea name="re_content" rows="6" cols="60"></textarea></td>
	<td><input type="submit" value="등록"></td>
</tr>
</form>

<c:forEach items="${repage}" var="a">
<tr>
	<td>${a.m_nick}</td>
	<td>${a.re_content}</td>
	<td>
		<fmt:parseDate value="${a.re_wdate}" var="reply_date"  pattern="yyyy-MM-dd"/>
		<fmt:formatDate value="${reply_date}" pattern="yyyy-MM-dd"/>
	</td>
</tr>
</c:forEach>

<tr>
	<td colspan="3">
	<c:if test="${page1.nowPage > 10}">
		<a href="detail?b_num=${b_num}&nowPage=${page1.startPage -1}">&#60;</a> 				
	</c:if>
	

	<c:forEach begin="${page1.startPage}" end="${page1.endPage}" var="p">
		<c:choose>
			<c:when test="${p==page1.nowPage}">
				<b>${p}</b>
			</c:when>
			<c:when test="${p!=page1.nowPage}">
				<a href="detail?b_num=${b_num}&nowPage=${p}&cntPerPage=${page1.cntPerPage}">${p}</a>
			</c:when>
		</c:choose>
	</c:forEach>
				
	
	<c:if test="${page1.next && page1.endPage>0}">
		<a href="detail?b_num=${b_num}&nowPage=${page1.endPage +1}">&#62;</a>
	</c:if>   
	</td>
</tr>

</table>
=======
	<form name="frm" method="post">
	<input type="hidden" name="b_num" id="b_num" value="${list.b_num }">
	<input type="hidden" name="m_nick" id="m_nick" value="${member_nick}">
	<input type="hidden" name="scrap_chk" id="scrap_chk" value="${slist.scrap_chk }">
	<button type="button" id="sss">스크랩</button>
	</form>&emsp;
>>>>>>> upstream/main
</body>
</html>