function idtest(){
	console.log(1);
	var id =$("#m_id").val();
	
	console.log(id);
	var sam ={"id":id};
	
	console.log(sam);
	var sam =JSON.stringify(sam);	
	
	$.ajax({
		type:"get",
		async:false,
		url:"idtest",
		data:{jsoninfo:sam},
		success:function(data,textStatus){
			$("#id_check").text(data);
			$("#idcheck").text(data);
			if (data >= 1) {
						$("#id_check").text("사용 중인 아이디입니다. from js");
						$("#id_check").css("color", "red");
						alert("아이디 중복체크 해주세요.");
						} else if(id == ""){
							$('#id_check').text('아이디를 입력해주세요.');
							$('#id_check').css('color', 'red');
						} else if  (data == 0){
						$("#id_check").text("사용 가능한 아이디입니다.");
						$("#id_check").css("color", "green");
						$("#idcheck").attr("value","Y");
						}
						
					
			
		},
		error:function(data,textStatus){
			alert("전송실패!!");
		}
	}); 
}

function idselect(){
	var dup =document.getElementById("idcheck");
	if (dup = "N"){
	alert ("아이디를 입력하시고 꼭 중복 확인 버튼을 눌러주세요");
	}
	
}