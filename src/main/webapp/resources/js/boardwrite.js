function Board_write() {
        
console.log(1);        

    var form = document.write;

    if (!form.b_title.value) {
        alert("제목을 입력해 주십시오.");
        form.b_title.focus();
        return;
    }
 
    if (!form.b_content.value) {
        alert("내용을 입력해 주십시오.");
        form.b_content.focus();
        return;
    }
    
    form.action = "/updev/writesave";
    form.submit();
    }

/*
$(document).ready(function() {
	//여기 아래 부분
	$('.summernote').summernote({
      height: 200,                 // 에디터 높이
      minHeight: null,             // 최소 높이
      maxHeight: null,             // 최대 높이
      focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
      lang: "ko-KR",                    // 한글 설정
      placeholder: '내용을 입력하세요.'    //placeholder 설정
   });
});
*/