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
			if(b_kind == '공지'){
				form.action = "/updev/noticepage";//공지 페이지
				form.submit();
			} else if(b_kind == '정보공유') {
				form.action = "/updev/sharepage";// 정보공유 페이지
				form.submit();
			} else if(b_kind == '고민상담소') {
				form.action = "/updev/worrypage";// 고민상담소 페이지
				form.submit();
			} else if(b_kind == '지식인') {
				form.action = "/updev/questionpage";// 지식인 페이지
				form.submit();
			} else if(b_kind == 'QNA') {
				form.action = "/updev/qnapage";//Q&A 페이지
				form.submit();
			} else {
		form.action = "/updev/ajaxmywrite";//마이글 페이지(적용 안됨)
		form.submit();
		}
		}    
	}