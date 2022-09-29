$(function(){
	$("#button").click(function(){
		var b_num =$("#b_num").val();
		var m_nick =$("#m_nick").val();
		var sam ={"b_num":b_num,"m_nick":m_nick};
		var sam =JSON.stringify(sam);
		$.ajax({
			type:"post",
			async:false,
			url:"/like",
			data:{jsoninfo:sam},
			success:function(data,textStatus){
				location.href="index";
			},
			error:function(data,textStatus){
				alert("전송실패!!");
			}
		});
	});
	
});