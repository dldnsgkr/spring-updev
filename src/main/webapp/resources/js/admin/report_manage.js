function report_manage_update(r_num){
	var result = confirm("상태를 처리완료로 변경하시겠습니까?");
	if(result){
		var sam ={"r_num":r_num};
		var sam =JSON.stringify(sam);
		$.ajax({
			type:"post",
			async:false,
			url:"report_manage_update",
			data:{jsoninfo:sam},
			success:function(data,textStatus){
				location.href="report_manage";
			},
			error:function(data,textStatus){
				alert("전송실패!!");
			}
		});
	}else{
		return false;
	}
}