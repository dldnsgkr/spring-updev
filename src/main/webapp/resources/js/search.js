function SearchChk() {
        
    var Sform = document.top_search_form;

    if (!Sform.keyword.value) {
        alert("키워드를 입력해 주십시오.");
        Sform.keyword.focus();
        return;
    }


    Sform.action = "/updev/search";
    Sform.submit();
    }

	$("#login_form").keypress(function(e) {
		if (e.keyCode === 13) {
			loginChk();
		}

	});