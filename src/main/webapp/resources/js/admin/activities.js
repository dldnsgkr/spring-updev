console.log(1);
function ttt(b_num){
	var result = confirm("정말 삭제하시겠습니까?");
	if(result){
		var sam ={"b_num":b_num};
		var sam =JSON.stringify(sam);
		$.ajax({
			type:"post",
			async:false,
			url:"admin_mylist_delete",
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
function admin_mylike_cancel(b_num){
	var result = confirm("정말 취소하시겠습니까?");
	if(result){
		var sam ={"b_num":b_num};
		var sam =JSON.stringify(sam);
		$.ajax({
			type:"post",
			async:false,
			url:"admin_mylike_cancel",
			data:{jsoninfo:sam},
			success:function(data,textStatus){
				location.href="admin_mylike_select";
			},
			error:function(data,textStatus){
				alert("admin_mylike_cancel error");
			}
		});
	}else{
		return false;
	}
}
function admin_myscrap_cancel(b_num){
	var result = confirm("정말 취소하시겠습니까?");
	if(result){
		var sam ={"b_num":b_num};
		var sam =JSON.stringify(sam);
		$.ajax({
			type:"post",
			async:false,
			url:"admin_myscrap_cancel",
			data:{jsoninfo:sam},
			success:function(data,textStatus){
				location.href="admin_myscrap_select";
			},
			error:function(data,textStatus){
				alert("전송실패!!");
			}
		});
	}else{
		return false;
	}
}