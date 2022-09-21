function  signupChk(){

	var form = document.signup1;
	var idpw = /^[A-Za-z0-9]{4,12}$/;
	var mailchk = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
	var namechk = /^[가-힣]{2,10}$/;
	var telchk = /^[0-9]{10,11}$/;

	const vid = document.getElementById("id");
	const vpw = document.getElementById("pw");
	const vpwchk = document.getElementById("pwchk");
	const vnick = document.getElementById("nick");
	const vname = document.getElementById("name");
	const vmail = document.getElementById("mail");
	const vtel = document.getElementById("tel");
	const vfield = document.getElementById("field");
	const agreechk = document.getElementById("agree1");
		
		//아이디 유효성
		if(vid.value==""){			   
			alert("아이디를 입력해주세요.");			      
			return false;			      
		}			       
		if(!idpw.test(vid.value)){
			alert("아이디는 4~12 글자 사이로 영문자, 숫자로 입력해주세요");
			return false;
		}
		//비밀번호 유효성
		if(vpw.value==""){
			alert("비밀번호를 입력해주세요.");
			return false;
		}
		if(!idpw.test(vpw.value)){
			alert("비밀번호는 4~20 글자 사이로 영문자, 숫자로 입력해주세요.");
			return false;
		}
		if(vpw.value==vid.value){
			alert("비밀번호와 아이디는 동일 할수 없습니다.")
			return false;
		}  
		if(vpwchk.value==""){
			alert("비밀번호 확인칸을 입력해주세요");
			return false;
		}
		if(vpwchk.value!=vpw.value){
			alert("비밀번호가 일치하지 않습니다. 다시 한번 확인해주세요.");
			return false;
		}
		//닉네임 유효성
		if(vnick.value==""){
			alert("닉네임을 입력해주세요");
			return false;
		}  
		if(!namechk.test(vnick.value)){
			alert("닉네임은 한글로 2~10글자 사이어야 합니다.");
			return false;
		} 
		//이름 유효성
		if(vname.value==""){
			alert("이름을 입력해주세요");
			return false;
		}  
		if(!namechk.test(vname.value)){
			alert("이름은 한글로 2~10글자 사이어야 합니다.");
			return false;
		} 
		//이메일 주소 유효성
		if(vmail.value==""){
			alert("이메일을 입력해주세요.");
			return false;
		} 
		if(!mailchk.test(vmail.value)){
			alert("올바흔 형식이 아닙니다. 다시 한번 확인해 주세요.");
			return false;
		} 
		//이메일 형식 다시 확인 
		//전화번호 유효성
		if(vtel.value==""){
			alert("전화번호를 입력해주세요.");
			return false;
		} 
		if(!telchk.test(vtel.value)){
			alert("올바른 전화번호 형식이 아닙니다.");
			return false;
		}  
	    //분야 유효성
		if(vfield.value==""){
			alert("분야를 선택하셔야 합니다.");
			return false;
		} 
	    //개인정보이용동의 유효성
		if(agreechk.value==""){
			alert("개인정보이용동의 확인해주세요.");
			return false;
		} 
		 
		form.action = "/updev/insert";
		form.submit();
}


