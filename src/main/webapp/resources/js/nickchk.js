function nicktest(){
	var nick =$("#m_nick").val();
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
			$("#nick_check").text(data);
			if (data >= 1) {
						$("#nick_check").text("사용 중인 닉네임입니다. 다른 닉네임을 입력해주세요");
						$("#nick_check").css("color", "red");
						} else if(nick == ""){
							$('#nick_check').text('닉네임을 입력해주세요.');
							$('#nick_check').css('color', 'red');
						} else if  (data == 0){
						$("#nick_check").text("사용 가능한 닉네임입니다.");
						$("#nick_check").css("color", "green");
						$("#nickcheck").attr("value","Y");
			}
		},
		error:function(data,textStatus){
			alert("전송 실패!");
		}
	}); 
}
function nickselect(){
	var nickdup =document.getElementById("nickcheck");
	nickdup = "N";
	alert ("닉네임을 입력하시고 꼭 중복 확인 버튼을 눌러주세요");
}
