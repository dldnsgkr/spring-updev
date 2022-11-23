function Report() {
        
    var Sform = document.report_form;
console.log(Sform.keyword_report.value);
    if (!Sform.keyword_report.value) {
        alert("키워드를 입력해 주십시오.");
        Sform.keyword.focus();
        return;
    }


    Sform.action = "/updev/reportsearch";
    Sform.submit();
    }

	$("#login_form").keypress(function(e) {
		if (e.keyCode === 13) {
			loginChk();
		}

	});