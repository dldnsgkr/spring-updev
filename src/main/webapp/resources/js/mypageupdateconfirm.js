function updateconfirm(){
	
	var form = document.board_update_write;
	
	var b_num = $("#b_num").val();
	var b_cate = $("#b_cate").val();
	var b_kind = $("#b_kind").val();
	var b_title = $("#b_title").val();
	var m_nick = $("#m_nick").val();
	var b_content = $("#b_content").val();
	
	
	if (!form.b_title.value) {
        alert("제목을 입력해 주십시오.");
        form.b_title.focus();
        return; 	
    }
 
    if (!form.b_content.value) {
        alert("내용을 입력해 주십시오.");
       form.b_content.focus();
        return;
    }
	
	console.log(b_num+b_cate+b_kind+b_title+m_nick+b_content);
	
  	var ask_boardupdate = confirm("정말 수정하시겠습니까?");
		if(ask_boardupdate){
			var sam ={"b_num":b_num,"b_cate":b_cate,"b_kind":b_kind,"b_title":b_title,"m_nick":m_nick,"b_content":b_content};
			var sam =JSON.stringify(sam);	
			$.ajax({
				type:"get",
				async:false,
				url:"writeupdate",
				data:{jsoninfo:sam},
				success:function(data,textStatus){
					alert("수정이 완료되었습니다.");
				},
				error:function(data,textStatus){
					alert("에러 : 홈페이지 관리자에게 문의");
				}
			});
		form.action = "/updev/ajaxmywrite";
		form.submit();
		}    
	}
 

<<<<<<< HEAD

 
=======
	var result = confirm("이대로 수정하시겠습니까?");
		if(result){
			location.href="writeupdatecheckb_num="+b_num+"&b_kind="+b_kind;
		}else{
			return false;
		}
}
>>>>>>> 7e8b4176da32d2a1687bd0e336a19a5b251887cb
