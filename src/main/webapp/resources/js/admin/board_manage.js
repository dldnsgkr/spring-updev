function board_manage_delete(b_num){
	var result = confirm("정말 삭제하시겠습니까?");
	if(result){
		var sam ={"b_num":b_num};
		var sam =JSON.stringify(sam);
		$.ajax({
			type:"post",
			async:false,
			url:"board_manage_delete",
			data:{jsoninfo:sam},
			success:function(data,textStatus){
				location.href="admin_mylist";
			},
			error:function(data,textStatus){
				alert("전송실패!!");
			}
		});
	}else{
		return false;
	}
}