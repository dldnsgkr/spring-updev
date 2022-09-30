function SearchChk() {
        
    var Sform = document.top_search_form;

    if (!Sform.search.value) {
        alert("내용을 입력해 주십시오.");
        Sform.search.focus();
        return;
    }

    Sform.action = "search";
    Sform.submit();
    }


