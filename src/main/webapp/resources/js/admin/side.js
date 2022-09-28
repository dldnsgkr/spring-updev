
function go_infoupdate(){
	$.ajax({
				type:"post",
				async:false,
				url:"infoupdate",
				success:function(data,textStatus){
					location.replace("infoupdate");
				},
				error:function(data,textStatus){
					alert("전송실패!!");
				}
			});
}
function go_mylist(){
	$.ajax({
				type:"post",
				async:false,
				url:"mylist",
				success:function(data,textStatus){
					location.replace("mylist");
				},
				error:function(data,textStatus){
					alert("전송실패!!");
				}
			});
}

$(function(){
	$("nav .first li span").click(function(e){
		e.preventDefault()
		$("nav .third .menu ul").removeClass('on');
		$("nav .second .menu ul").removeClass('on');
		$("nav .first li span").removeClass('on');
		$(this).addClass( 'on' );
	
		if($(this).text() == "마이페이지"){
			$("nav .second .menu ul").eq(0).addClass('on');
			$("nav .third .menu ul").eq(0).addClass('on');
		}else if($(this).text() == "게시판 관리"){
			$("nav .second .menu ul").eq(1).addClass('on');
		}else if($(this).text() == "신고 관리"){
			$("nav .second .menu ul").eq(2).addClass('on');
		}else if($(this).text() == "회원 관리"){
			$("nav .second .menu ul").eq(3).addClass('on');
		}
 	});
	/*
	$("nav .second .menu ul li").click(function(e){
		 e.preventDefault()
		$("nav .third .menu ul").removeClass('on');
		$("nav .second .menu ul li").removeClass('on');
		
		$(this).addClass( 'on' );

		if($(this).text() == "정보수정"){
			$("nav .third .menu ul").eq(0).addClass('on');
		}else if($(this).text() == "마이 글"){
			$("nav .third .menu ul").eq(1).addClass('on');
		}else if($(this).text() == "마이 알림"){
			$("nav .third .menu ul").eq(2).addClass('on');
		}else if($(this).text() == "마이 쪽지"){
			$("nav .third .menu ul").eq(3).addClass('on');
		}
 	});
*/
	
});
