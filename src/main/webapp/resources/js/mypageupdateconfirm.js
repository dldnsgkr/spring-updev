var referrer = document.referrer;

window.onhashchange = function() {
location.href=referrer;
}

function updateconfirm(){
	
	var updateform = document.board_update_write;
	
	const b_title = document.getElementById("b_title");
	const b_content = document.getElementById("b_content");
	
	if (!updateform.b_title.value) {
        alert("제목을 입력해 주십시오.");
        updateform.b_title.focus();
        return; 	
    }
 
    if (!updateform.b_content.value) {
        alert("내용을 입력해 주십시오.");
        updateform.b_content.focus();
        return;
    }

	var result = confirm("이대로 수정하시겠습니까?");
		if(result){
			location.href="writeupdatecheckb_num="+b_num+"&b_kind="+b_kind;
			return true;
		}else{
			return false;
		}
}