function nicktest2(){
	
	var up_nick =$("#up_nick").val();
	var m_num =$("#m_num").val();
	var sam ={"up_nick":up_nick};
		console.log(sam);
	var sam =JSON.stringify(sam);	
	var namechk = /^[가-힣]{2,10}$/;
	$("#up_nick").mouseout(function() {
		if (namechk.test($(this).val())) {
				console.log(namechk.test($(this).val()));
				$("#nick_check2").text('');
		} else {
			alert("닉네임은 한글로 2~10글자까지 가능합니다.")
			$('#nick_check2').text("닉네임은 한글로 2~10글자까지 가능합니다.");
			$("#nickcheck2").attr("value","N");
			$('#nick_check2').css('color', 'red');
			}
			});
	
	$.ajax({
		type:"post",
		async:false,
		url:"nicktest2",
		data:{jsoninfo:sam},
		success:function(data,textStatus){
			$("#nick_check2").text(data);
			$("#nickcheck2").text(data);
			if (data != 0) {
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
var mnickdup =$("#nickcheck2").val();
	if (mnickdup == "N"){
	alert ("닉네임을 입력하시고 꼭 중복 확인 버튼을 눌러주세요");
	}
	console.log(dup);
	}
