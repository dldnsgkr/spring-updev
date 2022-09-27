$(document).ready(function(){
            $('ul.tabs li').click(function(){
                var tab_id = $(this).attr('data-tab');

                $('ul.tabs li').removeClass('current');
                $('.tab-content').removeClass('current');

                $(this).addClass('current');
                $("#"+tab_id).addClass('current');
            });

        });
        
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
     var form = document.pw_find;

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
				success:function(data){
					
					console.log(data+"나는 데이터")
					alert("전송성공!!")
					$(".id_find").css("display", "none");
					$("#pw_find").css("display", "block");
					$("#pw_find #pw_ck").html(data);				
				},
				error:function(data){
					alert("전송실패!!");
				}
			});
    }

        