function nickname_duplicate_test(){
	var nick =$("#m_nick").val();
	var nickchk = /^[가-힣]{2,10}$/;
		console.log(nick);
		
	var sam ={"nick":nick};
		console.log(sam);
		
	var sam =JSON.stringify(sam);	
	
	$.ajax({
		type:"post",
		async:false,
		url:"nickname_duplicate_test",
		data:{jsoninfo:sam},
		success:function(data,textStatus){
			if (data != 0) {
				$("#nick_check").text("사용중인 닉네임입니다.");
						$("#nickname_status_alert").css("color", "red");
						$("#nickname_status").attr("value","N");
						} else {
						
						if(nickchk.test(nick)){
							// 0 : 닉네임 길이 / 문자열 검사
							$("#nickname_status_alert").text("사용가능한 닉네임 입니다.");
							$("#nickname_status_alert").css("color", "green");
							$("#nickname_status").attr("value","Y");
						} else if(nick == ""){
							
							$('#nickname_status_alert').text("닉네임를 입력해주세요");
							$('#nickname_status_alert').css('color', 'red');
						} else {
							$('#nickname_status_alert').text("닉네임은 한글로 2~10글자까지만 가능합니다");
							$('#nickname_status_alert').css('color', 'red');
							$("#nickname_status").attr("value","N");
						}
						}
				},
		error:function(data,textStatus){
			alert("전송 실패!");
		}
	}); 
}
function nickname_duplicate_check_alert(){
	var dup =$("#nickname_status").val();
	if (dup == "N"){
	alert ("닉네임 형식을 확인하시고 꼭 중복 확인 버튼을 눌러주세요");
	}
	console.log(dup);
}
