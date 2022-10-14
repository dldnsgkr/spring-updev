function member_manage_delete(m_num){
	var result = confirm("정말 삭제하시겠습니까?");
	if(result){
		var sam ={"m_num":m_num};
		var sam =JSON.stringify(sam);
		$.ajax({
			type:"post",
			async:false,
			url:"member_manage_delete",
			data:{jsoninfo:sam},
			success:function(data,textStatus){
				location.href="member_manage";
			},
			error:function(data,textStatus){
				alert("전송실패!!");
			}
		});
	}else{
		return false;
	}
}
