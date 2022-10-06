function board_manage_delete(b_num,url){
	var result = confirm("정말 삭제하시겠습니까?");
	if(result){
		var sam ={"b_num":b_num,"url":url};
		var sam =JSON.stringify(sam);
		$.ajax({
			type:"post",
			async:false,
			url:"board_manage_delete",
			data:{jsoninfo:sam},
			success:function(data,textStatus){
				console.log(data);
				console.log( window.location.href);
				location.href=window.location.href;
			},
			error:function(data,textStatus){
				alert("전송실패!!");
			}
		});
	}else{
		return false;
	}
}