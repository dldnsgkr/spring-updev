function idtest(){
	console.log(1);
	var idchk = /^[a-z0-9]{4,12}$/;
	var id =$("#m_id").val();
			$("#m_id").mouseout(function() {
				if (idchk.test($(this).val())) {
						console.log(idchk.test($(this).val()));
						$("#id_check").text('');
				} else {
					alert("이름은 영소문자와 숫자로 4~12글자까지 가능합니다")
					$('#id_check').text("이름은 영소문자와 숫자로 4~12글자까지 가능합니다");
					$("#idcheck").attr("value","N");
					$('#id_check').css('color', 'red');
				}
			});
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
			if (data != 0) {
				$("#id_check").text("사용 중인 아이디입니다. 다른 아이디를 입력 해주세요.");
				$("#idcheck").attr("value","N");
				$("#id_check").css("color", "red");
				} else if(id == ""){
				$('#id_check').text("아이디를 입력해주세요.");
				$('#id_check').css('color', 'red');
				} else if  (data == 0){
				$("#id_check").text("사용 가능한 아이디입니다.");
				$("#id_check").css("color", "green");
				$("#idcheck").attr("value","Y");
				
				}
				console.log(idcheck);
					
			
		},
		error:function(data,textStatus){
			alert("전송실패!!");
		}
	}); 
}

function idselect(){
	var dup =$("#idcheck").val();
	if (dup == "N"){
	alert ("아이디를 입력하시고 꼭 중복 확인 버튼을 눌러주세요");
	} 
	console.log(dup);
}