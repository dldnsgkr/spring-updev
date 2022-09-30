function admin_infoupdate_update() {
	    var form = document.ufrm;
	    
	    var pw_validate = /^[A-Z0-9]{4,12}$/;
	    var nik_name_validate = /^[가-힣]{2,10}$/;
	    var mail_validate = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
	    var tel_validate = /^[0-9]{11}$/;

		const m_pw = document.getElementById("m_pw");
		const m_nick = document.getElementById("m_nick");
		const m_name = document.getElementById("m_name");
		const m_mail = document.getElementById("m_mail");
		const m_tel = document.getElementById("m_tel");
		const m_field = document.getElementById("m_field");
		
		var pw =$("#m_pw").val();
		var nick =$("#m_nick").val();
		var name =$("#m_name").val();
		var mail =$("#m_mail").val();
		var tel =$("#m_tel").val();
		var field =$("#m_field").val();
		
		if (!form.m_pw.value) {
	        alert("비밀번호를 입력해 주십시오.");
	        form.m_pw.focus();
	        return;
	    }
	    if(!pw_validate.test(m_pw.value)){
			alert("비밀번호는 4~12자의 영대문자, 숫자로 입력해주세요!");
			return false;
		}
	    if (!form.m_pw.value) {
	        alert("비밀번호를 입력해 주십시오.");
	        form.m_pw.focus();
	        return;
	    }
	    if(!pw_validate.test(m_pw.value)){
			alert("비밀번호는 4~12자의 영대문자, 숫자로 입력해주세요!");
			return false;
		}
	    if (!form.m_nick.value) {
	        alert("닉네임을 입력해 주십시오.");
	        form.m_nick.focus();
	        return;
	    }
	    if(!nik_name_validate.test(m_nick.value)){
			alert("닉네임은 2~10자 한글로 입력해주세요!");
			return false;
		}
	    if (!form.m_name.value) {
	        alert("이름을 입력해 주십시오.");
	        form.m_name.focus();
	        return;
	    }
	    if(!nik_name_validate.test(m_name.value)){
			alert("이름은 2~10자 한글로 입력해주세요!");
			return false;
		}
	    if (!form.m_mail.value) {
	        alert("이메일을 입력해 주십시오.");
	        form.m_mail.focus();
	        return;
	    }
	    if(!mail_validate.test(m_mail.value)){
			alert("이메일은 updev@example.com으로 입력해주세요!");
			return false;
		}
	    if (!form.m_tel.value) {
	        alert("전화번호를 입력해 주십시오.");
	        form.m_tel.focus();
	        return;
	    }
 		if(!tel_validate.test(m_tel.value)){
			alert("전화번호는 11글자로 공백과 -없이 입력해주세요!");
			return false;
		}
	    var result = confirm("정말 수정하시겠습니까?");
		if(result){
			var sam ={"pw":pw,"nick":nick,"name":name,"mail":mail,"tel":tel,"field":field};
			var sam =JSON.stringify(sam);	
			$.ajax({
				type:"post",
				async:false,
				url:"admin_infoupdate_update",
				
				data:{jsoninfo:sam},
				success:function(data,textStatus){
					alert("정보수정이 완료되었습니다.");
					location.href="admin_infoupdate";
				},
				error:function(data,textStatus){
					alert("에러 : 홈페이지 관리자에게 문의");
				}
			});
		}else{
			return false;
		}    
}
