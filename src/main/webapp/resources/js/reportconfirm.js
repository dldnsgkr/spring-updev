function report_confirm() {
        
    var form = document.reportform;
	const r_reason = document.getElementById("r_reason");

    if (!form.otherreason.value) {
        alert("신고 사항에 대헤 간단히 설명해주세요.");
        form.otherreason.focus();
        return;
    }
	if(r_reason.value==""){
			alert("신고사유를 선택해주세요.");
			return false;
		} 
	if (!confirm("이대로 신고접수를 하시겠습니까? 확인(예) 또는 취소(아니오)를 선택해주세요.")) {
            alert("취소(아니오)를 누르셨습니다.");
			location.href="breport?b_num="+b_num+"&b_title="+b_title;
        } else {
            alert("확인(예)을 누르셨습니다.");
        }
    
	form.action = "/updev/breport";
	form.submit();
    }
	