function idtest(){
	console.log(1);
	var id =$("#m_id").val();
	var idchk = /^[a-z0-9]{4,12}$/;
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
						if (data != 0) {
						$("#id_check").text("사용중인 아이디입니다.");
						$("#id_check").css("color", "red");
						$("#idcheck").attr("value","N");
						} else {
						
						if(idchk.test(id)){
							// 0 : 아이디 길이 / 문자열 검사
							$("#id_check").text("사용가능한 아이디 입니다.");
							$("#id_check").css("color", "green");
							$("#idcheck").attr("value","Y");
						} else if(id == ""){
							$('#id_check').text("아이디를 입력해주세요");
							$('#id_check').css('color', 'red');
						} else {
							$('#id_check').text("아이디는 소문자와 숫자 4~12자리만 가능합니다");
							$('#id_check').css('color', 'red');
							$("#idcheck").attr("value","N");
						}
						console.log(idcheck);
					}
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