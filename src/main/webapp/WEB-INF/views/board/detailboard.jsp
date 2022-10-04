<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<form name="frm" method="post">
	<input type="hidden" name="b_num" id="b_num" value="${list.b_num }">
	<input type="hidden" name="m_nick" id="m_nick" value="${member_nick}">
	<input type="hidden" name="scrap_chk" id="scrap_chk" value="${slist.scrap_chk }">
	<button type="button" id="sss">스크랩</button>
	</form>&emsp;
</body>
</html>