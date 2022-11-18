function nicktest2(){
	
	var up_nick =$("#up_nick").val();
	var m_num =$("#m_num").val();
	var sam ={"up_nick":up_nick};
		console.log(sam);
	var sam =JSON.stringify(sam);	
	var nickchk = /^[가-힣]{2,10}$/;
	
	$.ajax({
		type:"get",
		async:false,
		url:"nicktest2",
		data:{jsoninfo:sam},
		success:function(data,textStatus){
				console.log(data);
				console.log(typeof(data)+": type");
				var test1 = parseInt(data);
				console.log(typeof(test1)+": type");
				console.log(test1);
				
				$("#nick_check2").text(test1);
				$("#nickcheck2").text(test1);
				
						if (test1 != 0) {
							$("#nick_check2").text("사용중인 닉네임입니다.");
							$("#nick_check2").css("color", "red");
							$("#nickcheck2").attr("value","N");
						console.log(nickcheck2);
						} else {
						if(nickchk.test(up_nick)){
							// 0 : 닉네임 길이 / 문자열 검사
							$("#nick_check2").text("사용가능한 닉네임 입니다.");
							$("#nickcheck2").attr("value","Y");
							$("#nick_check2").css("color", "green");
						} else if(up_nick == ""){
							$('#nick_check2').text("닉네임를 입력해주세요");
							$('#nick_check2').css('color', 'red');
						} else {
							$('#nick_check2').text("닉네임는 소문자와 숫자 4~12자리만 가능합니다");
							$('#nick_check2').css('color', 'red');
							$("#nickcheck2").attr("value","N");
						}
						}
				},
		error:function(test1){
			alert("전송 실패!");
		}
	}); 
}
function nickselect2(){
var mnickdup =$("#nickcheck2").val();
	if (mnickdup == "N"){
	alert ("닉네임을 입력하시고 꼭 중복 확인 버튼을 눌러주세요");
	}
	console.log(dup);
	}
