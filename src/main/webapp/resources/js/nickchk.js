function nicktest(){
	var nick =$("#m_nick").val();
	var nickchk = /^[가-힣]{2,10}$/;
		console.log(nick);
		
	var sam ={"nick":nick};
		console.log(sam);
		
	var sam =JSON.stringify(sam);	
	
	$.ajax({
		type:"post",
		async:false,
		url:"nicktest",
		data:{jsoninfo:sam},
		success:function(data,textStatus){
			if (data != 0) {
				$("#nick_check").text("사용중인 닉네임입니다.");
						$("#nick_check").css("color", "red");
						$("#nickcheck").attr("value","N");
						} else {
						
						if(nickchk.test(nick)){
							// 0 : 닉네임 길이 / 문자열 검사
							$("#nick_check").text("사용가능한 닉네임 입니다.");
							$("#nick_check").css("color", "green");
							$("#nickcheck").attr("value","Y");
						} else if(nick == ""){
							
							$('#nick_check').text("닉네임를 입력해주세요");
							$('#nick_check').css('color', 'red');
						} else {
							$('#nick_check').text("닉네임는 소문자와 숫자 4~12자리만 가능합니다");
							$('#nick_check').css('color', 'red');
							$("#nickcheck").attr("value","N");
						}
						}
				},
		error:function(data,textStatus){
			alert("전송 실패!");
		}
	}); 
}
function nickselect(){
	var dup =$("#nickcheck").val();
	if (dup == "N"){
	alert ("닉네임 형식을 확인하시고 꼭 중복 확인 버튼을 눌러주세요");
	}
	console.log(dup);
}
