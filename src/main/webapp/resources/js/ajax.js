function test(){
	
	var id =$("#m_id").val();
	var idJ = /^[a-z0-9]{4,12}$/;
	
	console.log(id);
	var sam ={"id":id};
	
	console.log(sam);
	var sam =JSON.stringify(sam);	
	
	$.ajax({
		type:"get",
		async:false,
		url:"test",
		data:{jsoninfo:sam},
		success:function(data,textStatus){
			$("#id_check").text(data);
			if (data == 1) {
						$("#id_check").text("사용 중인 아이디입니다.");
						$("#id_check").css("color", "red");
					} else {
						if(idJ.test(id)){
						} else if(id == ""){
							
							$('#id_check').text('아이디를 입력해주세요.');
							$('#id_check').css('color', 'red');
						}
						else {
							$("#id_check").text("사용 가능한 아이디입니다.");
							$("#id_check").css("color", "green");
						}
					}
			
		},
		error:function(data,textStatus){
			alert("전송실패!!");
		}
	}); 
}