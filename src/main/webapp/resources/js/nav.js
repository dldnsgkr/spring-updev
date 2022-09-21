$(function(){
	$("nav .first li span").click(function(e){
		$("nav .third .menu ul").removeClass('on');
		$("nav .second .menu ul").removeClass('on');
		$("nav .first li span").removeClass('on');
		$(this).addClass( 'on' );
	
		if($(this).text() == "마이페이지"){
			$("nav .second .menu ul").eq(0).addClass('on');
		}else if($(this).text() == "게시판 관리"){
			$("nav .second .menu ul").eq(1).addClass('on');
		}else if($(this).text() == "신고 관리"){
			$("nav .second .menu ul").eq(2).addClass('on');
		}else if($(this).text() == "회원 관리"){
			$("nav .second .menu ul").eq(3).addClass('on');
		}
 	});
	
	$("nav .second .menu ul li").click(function(e){
		
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

	$("#output").click(()=>{
		$.ajax({
			type: "POST",
			async : false,
			url: "infoupdate",
			success: function(data, textStatus){
				alert("성공2");
				var jsoninfo = JSON.parse(data);
				var out="";
				for(var i in jsoninfo.infos){
					
					out+="이름" + jsoninfo.infos[i].name+"<br>";
					out+="나이" + jsoninfo.infos[i].age+"<br>";
					out+="전화번호" + jsoninfo.infos[i].phone+"<br>";
					out+="<br>";
				}
				$("#out").html(out);
			},
			error : function(data, textStatus){
				alert("에러2");
			}
		});
	});
});