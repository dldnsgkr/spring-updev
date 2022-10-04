function nicktest2(){
	var up_nick =$("#up_nick").val();
		console.log(up_nick);
	var sam ={"up_nick":up_nick};
		console.log(sam);
	var sam =JSON.stringify(sam);	
	
	$.ajax({
		type:"post",
		async:false,
		url:"nicktest2",
		data:{jsoninfo:sam},
		success:function(data,textStatus){
			$("#nick_check2").text(data);
			$("#nickcheck2").text(data);
			if (data >= 1) {
						$("#nick_check2").text("사용 중인 닉네임입니다. 다른 닉네임을 입력해주세요");
						$("#nick_check2").css("color", "red");
						} else if(up_nick == ""){
							$('#nick_check2').text('닉네임을 입력해주세요.');
							$('#nick_check2').css('color', 'red');
						} else if  (data == 0){
						$("#nick_check2").text("사용 가능한 닉네임입니다.");
						$("#nick_check2").css("color", "green");
						$("#nickcheck2").attr("value","Y");
			}
		},
		error:function(data,textStatus){
			alert("전송 실패!");
		}
	}); 
}
function nickselect2(){
	var mnickdup =document.getElementById("nickcheck2");
	mnickdup = "N";
	alert ("닉네임을 입력하시고 꼭 중복 확인 버튼을 눌러주세요");
}
