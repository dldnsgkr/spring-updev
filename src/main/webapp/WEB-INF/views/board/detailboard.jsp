<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="resources/js/boardwrite.js"></script>
<link rel="stylesheet" href="resources/css/detailboard.css" type="text/css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" charset="UTF-8">
	$(document).ready(function(){
		changeIMG();
	});

	$(function changeIMG(){
	var like_chk =$("#like_chk").val();
	var a_existence =$("#a_existence").val();
	if(like_chk == 1) {
			$("#detail_ttt").attr("src", "./resources/images/iconmonstr-favorite-3-240.png");
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
			var b_title =$("#b_title").val();
			var m_id =$("#m_id").val();
			var b_kind =$("#b_kind").val();
			var sam ={"b_num":b_num,"m_nick":m_nick,"b_kind":b_kind,"m_id":m_id,"b_title":b_title};
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

$(document).ready(function(){
	changeIMG();
});

$(function changeIMG(){
	var scrap_chk =$("#scrap_chk").val();
	if(scrap_chk == 1) {
			$("#detail_sss").attr("src", "./resources/images/iconmonstr-bookmark-3-240.png");
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
			var b_title =$("#b_title").val();
			var m_id =$("#m_id").val();
			var b_kind =$("#b_kind").val();
			var sam ={"b_num":b_num,"m_nick":m_nick,"b_kind":b_kind,"m_id":m_id,"b_title":b_title};
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

<script type="text/javascript">
$(function() {
	var loginstate=$("#loginstate").val();
	var member_nick=$("#m_nick").val();
	var m_id=$("#m_id").val();
	$("#de").hide();
	$("#up").hide();
		if (loginstate == "true" && member_nick == m_id) {
			$("#de").show();
			$("#up").show();
		} else {
			$("#de").hide();
			$("#up").hide();
		}
});
</script>


<script type="text/javascript">
function del()
{
	var b_num = $("#b_num").val();
	var b_kind = $("#b_kind").val();
  var x = confirm("해당 게시글을 삭제하시겠습니까?");
  if (x)
      location.href="writedelete?b_num="+b_num+"&b_kind="+b_kind;
  else
    return false;
}
</script>
<script type="text/javascript">
function reply_save()
{
	var loginstate = $("#loginstate").val();
	var re_content = $("#f").val();
	var b_num = $("#b_num").val();
	var b_kind = $("#b_kind").val();
	var m_nick = $("#e").val();
	var b_title = $("#b_title").val();
	var m_id = $("#m_id").val();
	if(loginstate == "false"){
		alert("로그인이 필요합니다.");
		location.href="login";
	} else if(re_content==""){
		alert("비밀번호 확인칸을 입력해주세요.");
		return false;
	} else {
		location.href="replysave?b_num="+b_num+"&b_kind="+b_kind+"&b_title="+b_title+"&m_nick="+m_nick+"&re_content="+re_content+"&m_id="+m_id;
	}
}
</script>
</head>
<body>
	<div class="wrap">
		<table>
			<thead id="thead">
				<tr>
					<th id="a">${list.b_kind }</th>
				</tr>
				<tr>
					<th id="b">${list.b_title }</th>
				</tr>
				<tr>
					<th id="c">${list.m_nick }ㅣ<fmt:parseDate
							value='${list.b_wdate}' var='date' pattern='yyyy-MM-dd HH:mm:ss' />
						<fmt:formatDate value="${date}" pattern="yyyy.MM.dd. HH:mm" />
					</th>
				</tr>
			</thead>
		</table>
		<div id="d">${list.b_content }</div>

	<div class="detail_write">

	<input type="hidden" id="loginstate" value="${loginState }">
	<input class="button" type="button" value="수정" id="up" onclick="location.href='writeupdatecheck?b_num=${list.b_num}&b_kind=${list.b_kind }'">
	
	<form action="writedelete" method="post">
	<input type="hidden" id="loginstate" value="${loginState }">
	<input type="hidden" id="b_num" value="${list.b_num }">
	<input type="hidden" id="b_kind" value="${list.b_kind }">
	<input class="button" type="button" value="삭제" id="de" onclick="del()">
	</form>
	</div>

	<div class="detail_button">
	
	<form name="frm" method="post">
	<input type="hidden" name="b_num" id="b_num" value="${list.b_num }">
	<input type="hidden" name="m_nick" id="member_nick" value="${member_nick}">
	<input type="hidden" name="like_chk" id="like_chk" value="${llist.like_chk }">
	<input type="hidden" name="b_kind" id="b_kind" value="${list.b_kind }">
	<input type="hidden" name="b_title" id="b_title" value="${list.b_title }">
	<input type="hidden" name="a_existence" id="a_existence" value="1">
	<input type="hidden" name="m_id" id="m_id" value="${list.m_nick }">
	<button type="button" class="detail_button" id="ttt"><img src="./resources/images/iconmonstr-favorite-4-240.png" id="detail_ttt" title="좋아요"></button>
	</form>

	
	<form name="frm" method="post">
	<input type="hidden" name="b_num" id="b_num" value="${list.b_num }">
	<input type="hidden" name="m_nick" id="m_nick" value="${member_nick}">
	<input type="hidden" name="scrap_chk" id="scrap_chk" value="${slist.scrap_chk }">
	<input type="hidden" name="b_kind" id="b_kind" value="${list.b_kind }">
	<input type="hidden" name="b_title" id="b_title" value="${list.b_title }">
	<input type="hidden" name="a_existence" id="a_existence" value="2">
	<input type="hidden" name="m_id" id="m_id" value="${list.m_nick }">
	<button type="button" class="detail_button" id="sss"><img src="./resources/images/iconmonstr-bookmark-4-240.png" id="detail_sss" title="스크랩"></button>
	</form>	

	<button	type="button" class="detail_button" value="신고" onclick="location.href='boardreportpage?b_num=${list.b_num}&b_title=${list.b_title }'">
	<img src="./resources/images/iconmonstr-warning-filled-240.png" id="detail_img" title="신고하기"></button> 
	
	</div>

	
<!-- 댓글 -->
		<div class="reply">
			<table>
				<form action="replysave" method="post" name="reply">
					<tr>
						<td><input type="hidden" name="b_num" value="${list.b_num}"></td>
					</tr>
					<tr>
						<td><input type="hidden" name="m_id" value="${list.m_nick}"></td>
					</tr>
					<tr>
						<td><input type="hidden" name="b_title" value="${list.b_title}"></td>
					</tr>
					<tr>
						<td><input type="hidden" name="b_kind" value="${list.b_kind}"></td>
					</tr>
					<tr>
						<td><input type="text" id="e" name="m_nick"
							value="${member_nick}" readonly></td>
					</tr>
					<tr>
					<td colspan="2"><textarea id="f" name="re_content" rows="6" cols="50" placeholder="댓글은 회원만 등록할 수 있습니다."></textarea></td>
<<<<<<< HEAD
					<td><input type="hidden" id="m_id" name="m_id" value="${id }"></td>
					<td><input type="hidden" id="loginstate" value="${loginState }"></td>
					<td><input type="hidden" id="b_kind" name="b_kind" value="${list.b_kind }"></td>
					<td><input type="hidden" id="b_title" name="b_title" value="${list.b_title }"></td>
					<td><input class="button" id="reply_su" type="button" onclick="reply_save()" value="등록"></td>
=======
					<td><button class="button" type="button" id="reply_su" name="boardwrite" onclick="reply_save();">등록</button></td>
>>>>>>> 8002ca8fe042e126efff45db4181ab4ecb61073f
					</tr>
				</form>

				<c:forEach items="${repage}" var="a">
					<tr>
						<td width="200px">${a.m_nick}</td>
						<td>${a.re_content}</td>
						<td><fmt:parseDate value="${a.re_wdate}" var="reply_date" pattern="yyyy-MM-dd HH:mm:ss" /> 
							<fmt:formatDate value="${reply_date}" pattern="yyyy.MM.dd. HH:mm" /></td>
					</tr>
				</c:forEach>
			</table>

	<div class="tfoot">	
			
					<c:if test="${page1.nowPage > 10}">
							<a href="detail?b_num=${b_num}&nowPage=${page1.startPage -1}">&#60;</a>
						</c:if> <c:forEach begin="${page1.startPage}" end="${page1.endPage}" var="p">
							<c:choose>
								<c:when test="${p==page1.nowPage}">
									<b>${p}</b>
								</c:when>
								<c:when test="${p!=page1.nowPage}">
									<a href="detail?b_num=${b_num}&nowPage=${p}&cntPerPage=${page1.cntPerPage}">${p}</a>
								</c:when>
							</c:choose>
						</c:forEach> <c:if test="${page1.next && page1.endPage>0}">
							<a href="detail?b_num=${b_num}&nowPage=${page1.endPage +1}">&#62;</a>
						</c:if>
	</div>
		</div>
</div>

</body>
</html>