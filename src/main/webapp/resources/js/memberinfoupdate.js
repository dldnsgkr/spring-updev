function member_info_update_check(){
	
	var form =document.memupinfochk;
	console.log(1);
	
	var m_id =$("#m_id").val();
	var m_pw =$("#m_pw").val();
	var m_nick =$("#m_nick").val();
	var up_nick =$("#up_nick").val();
	var m_name =$("#m_name").val();
	var m_mail =$("#m_mail").val();
	var m_tel =$("#m_tel").val();
	var m_field =$("#m_field").val();
	
	var mpwchk = /^[A-Za-z0-9]{4,12}$/;
	var mmailchk = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
	var mnamechk = /^[가-힣]{2,10}$/;
	var mtelchk = /^[0-9]{10,11}$/;

	var mnickdup = document.getElementById("nickname_status_mypage");
	const mid = document.getElementById("m_id");
	const mpw = document.getElementById("m_pw");
	const pwchk = document.getElementById("m_pwchk");
	const mnick = document.getElementById("m_nick");
	const mupnick = document.getElementById("up_nick");
	const mname = document.getElementById("m_name");
	const mmail = document.getElementById("m_mail");
	const mtel = document.getElementById("m_tel");
	const mfield = document.getElementById("m_field");
	
	//비밀번호 유효성
		if(mpw.value==""){
			alert("비밀번호를 입력해주세요.");
			return false;
		}
		if(!mpwchk.test(mpw.value)){
			alert("비밀번호는 4~20 글자 사이로 영문자, 숫자로 입력해주세요.");
			return false;
		}
		if(mpw.value==mid.value){
			alert("비밀번호와 아이디는 동일 할수 없습니다.")
			return false;
		}  
		if(pwchk.value==""){
			alert("비밀번호 확인칸을 입력해주세요");
			return false;
		}
		if(pwchk.value!=mpw.value){
			alert("비밀번호가 일치하지 않습니다. 다시 한번 확인해주세요.");
			return false;
		}
		//닉네임 유효성
		/*if(mupnick.value==""){
			alert("닉네임을 입력해주세요");
			return false;
		}  
		if(!mnamechk.test(mupnick.value)){
			alert("닉네임은 한글로 2~10글자 사이어야 합니다.");
			return false;
		} 
		if(mnickdup.value == "N"){			   
			alert("사용중인 닉네임입니다. 다른 아이디를 입력해주세요. from 유효성");			      
			return false;			      
		}*/
		//이름 유효성
		if(mname.value==""){
			alert("이름을 입력해주세요");
			return false;
		}  
		if(!mnamechk.test(mname.value)){
			alert("이름은 한글로 2~10글자 사이어야 합니다.");
			return false;
		} 
		//이메일 주소 유효성
		if(mmail.value==""){
			alert("이메일을 입력해주세요.");
			return false;
		} 
		if(!mmailchk.test(mmail.value)){
			alert("올바른 형식이 아닙니다. 다시 한번 확인해 주세요.");
			return false;
		} 
		
		//전화번호 유효성
		if(mtel.value==""){
			alert("전화번호를 입력해주세요.");
			return false;
		} 
		if(!mtelchk.test(mtel.value)){
			alert("올바른 전화번호 형식이 아닙니다.");
			return false;
		}  
	    //분야 유효성
		if(mfield.value==""){
			alert("분야를 선택해주세요.");
			return false;
		} 
		
		var sam ={"m_id":m_id,"m_pw":m_pw,"m_nick":m_nick,"up_nick":up_nick,"m_name":m_name,"m_mail":m_mail,"m_tel":m_tel,"m_field":m_field};
		console.log(sam);
		console.log(sam.tel);
		console.log(sam.field);
		var sam =JSON.stringify(sam);	
			$.ajax({
				type:"get",
				async:false,
				url:"myinfoupdate",
				data:{jsoninfo:sam},
				success:function(data,textStatus){
					alert("프로필이 수정 되었습니다 다시 로그인 해주세요");
				},
				error:function(data,textStatus){
					alert("전송실패!!");
				}
	}); 
		 
		form.action = "/updev/logout";
		form.submit();
	
}