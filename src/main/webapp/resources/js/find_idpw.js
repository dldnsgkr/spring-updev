function IdChk() {
    var form = document.id_find;

		const m_name = document.getElementById("m_name");
		const m_mail = document.getElementById("m_mail");
		
	var name =$("#m_name").val();
	var mail =$("#m_mail").val();

    if (!form.m_name.value) {
        alert("이름을 입력해 주십시오.");
        form.m_name.focus();
        return;
    }
 
    if (!form.m_mail.value) {
        alert("이메일을 입력해 주십시오.");
        form.m_mail.focus();
        return;
    }
			var sam ={"name":name,"mail":mail};
			var sam =JSON.stringify(sam);	
			$.ajax({
				type:"post",
				async:false,
				url:"find_id",
				data:{jsoninfo:sam},
				success:function(data){
					
					$(".id_find").css("display", "none");
					$("#id_find").css("display", "block");
					$("#id_find #id_ck").html(data);				
				},
				error:function(data){
					alert("전송실패!!");
				}
			});
    }

function PwChk() {
    var form = document.id_find;
		const m_name = document.getElementById("m_name");
		const m_mail = document.getElementById("m_mail");
		
	var name =$("#m_name").val();
	var mail =$("#m_mail").val();

    if (!form.m_name.value) {
        alert("이름을 입력해 주십시오.");
        form.m_name.focus();
        return;
    }
 
    if (!form.m_mail.value) {
        alert("이메일을 입력해 주십시오.");
        form.m_mail.focus();
        return;
    }

			var sam ={"name":name,"mail":mail};
			var sam =JSON.stringify(sam);	
			$.ajax({
				type:"post",
				async:false,
				url:"find_pw",
				data:{jsoninfo:sam},
				success:function(data, textStatus){
					console.log(data+"나는 성공");
					if(data =="없음"){
						alert("존재하지 않는 정보입니다.");
						return;
					}
					else{
					$(".id_find").css("display", "none");
					$("#pw_find").css("display", "block");
					$("#pw_find #pw_ck").html(data);				
					}
				},
				error:function(data, textStatus){
					console.log(data+"나는 실패");
					var sam =JSON.stringify(data);
					console.log(sam+"나는 sam");
					alert("전송실패!!");
				}
			});
    }

function PwUpD() {
    var form = document.update_pw;

	var pwchk = /^[A-Za-z0-9]{4,12}$/;
		const m_id = document.getElementById("m_id");
		const m_pw = document.getElementById("m_pw");
		const m_npw = document.getElementById("m_npw");
		const m_npwck = document.getElementById("m_npwck");
		
		var id =$("#m_id").val();
		var pw =$("#m_pw").val();
		var npw =$("#m_npw").val();
	
	/*
	var m_pw =$("#m_pw").val();
	var m_npw =$("#m_npw").val();
	var m_npwck =$("#m_npwck").val();
*/

    if (!form.m_id.value) {
        alert("아이디를 입력해 주십시오.");
        form.m_id.focus();
        return;
    }

    if (!form.m_pw.value) {
        alert("기존 비밀번호를 입력해 주십시오.");
        form.m_pw.focus();
        return;
    }
			var sam ={"id":id,"pw":pw};
			console.log("비밀번호"+pw+"아이디"+id);
			var sam =JSON.stringify(sam);	
			console.log("sam"+sam);
			$.ajax({
				type:"post",
				async:false,
				url:"find_mpw",
				data:{jsoninfo:sam},
				success:function(data, textStatus){
					console.log(data+"나는 성공");
					if(data =="없음"){
						alert("존재하지 않는 계정입니다. 아이디와 비밀번호를 다시 확인해 주십시오.");
						return false;
					}
					if (!form.m_npw.value) {
			        alert("새 비밀번호를 입력해 주십시오.");
			        form.m_npw.focus();
			        return;
			    }
			
				if(!pwchk.test(m_npw.value)){
					alert("비밀번호는 4~20 글자 사이로 영문자, 숫자로 입력해주십시오.");
					return;
				}
			
			    if (!form.m_npwck.value) {
			        alert("새 비밀번호를 다시 확인해 주십시오.");
			        form.m_npwck.focus();
			        return;
			    }
			
				if(m_npwck.value!=m_npw.value){
					alert("새 비밀번호가 일치하지 않습니다. 다시 확인해 주십시오.");
					return;
				}
			 
					console.log(pw, npw);
				    var result = confirm("비밀번호를 변경하시겠습니까?");
					console.log(result);
					if(result){
						var sam ={"pw":pw, "npw":npw};
						var sam =JSON.stringify(sam);	
						$.ajax({
							type:"post",
							async:false,
							url:"update_pw",
							data:{jsoninfo:sam},
							success:function(data, textStatus){
								alert("비밀번호가 변경되었습니다.");				
							},
							error:function(data, textStatus){
								alert("전송실패!!");
							}
						});
				}else{
						return false;
					}  
							},
							error:function(data, textStatus){
								console.log(data+"나는 실패");
								var sam =JSON.stringify(data);
								console.log(sam+"나는 sam");
								alert("전송실패!!");
							}
						});
    }
        