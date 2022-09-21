function loginChk() {
        
    var form = document.login1;

    if (!form.m_id.value) {
        alert("아이디를 입력해 주십시오.");
        form.m_id.focus();
        return;
    }
 
    if (!form.m_pw.value) {
        alert("비밀번호를 입력해 주십시오.");
        form.m_pw.focus();
        return;
    }
    
    form.action = "/updev/loginact";
    form.submit();
    }

