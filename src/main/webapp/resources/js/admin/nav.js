$(function(){
	$("nav .first li").click(function(){
		$("nav .third .menu ul").removeClass('on');
		$("nav .second .menu ul").removeClass('on');
		$("nav .first li").removeClass('on');
		$(this).addClass( 'on' );
	
		if($(this).text() == "마이페이지"){
			$("nav .second .menu ul").eq(0).addClass('on');
			$("nav .third .menu ul").eq(0).addClass('on');
		}else if($(this).text() == "게시판 관리"){
			$("nav .second .menu ul").eq(1).addClass('on');
		}else if($(this).text() == "신고 관리"){
			$("nav .second .menu ul").eq(2).addClass('on');
		}else if($(this).text() == "회원 관리"){
			$("nav .second .menu ul").eq(3).addClass('on');
		}
 	});
	$("nav .second .menu ul li").click(function(){
		console.log(1);
		$("nav .third .menu ul").removeClass('on');
		$("nav .second .menu ul li").removeClass('on');
		
		$(this).addClass( 'on' );

		if($(this).text() == "정보수정"){
			console.log(2);
			$("nav .third .menu ul").eq(0).addClass('on');
		}else if($(this).text() == "마이 글"){
			$("nav .third .menu ul").eq(1).addClass('on');
		}else if($(this).text() == "마이 알림"){
			$("nav .third .menu ul").eq(2).addClass('on');
		}else if($(this).text() == "마이 쪽지"){
			$("nav .third .menu ul").eq(3).addClass('on');
		}
 	});
	
});












function infoupdate() {
	    
	    var form = document.ufrm;
	    
	    var pw_validate = /^[A-Z0-9]{4,12}$/;
	    var nik_name_validate = /^[가-힣]{2,10}$/;
	    var mail_validate = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
	    var tel_validate = /^[0-9]{11}$/;

		const m_pw = document.getElementById("m_pw");
		const m_nick = document.getElementById("m_nick");
		const m_name = document.getElementById("m_name");
		const m_mail = document.getElementById("m_mail");
		const m_tel = document.getElementById("m_tel");
		const m_field = document.getElementById("m_field");
		
		var pw =$("#m_pw").val();
		var nick =$("#m_nick").val();
		var name =$("#m_name").val();
		var mail =$("#m_mail").val();
		var tel =$("#m_tel").val();
		var field =$("#m_field").val();
		
		console.log(field);
		if (!form.m_pw.value) {
	        alert("비밀번호를 입력해 주십시오.");
	        form.m_pw.focus();
	        return;
	    }
	    if(!pw_validate.test(m_pw.value)){
			alert("비밀번호는 4~12자의 영대문자, 숫자로 입력해주세요!");
			return false;
		}
	    if (!form.m_pw.value) {
	        alert("비밀번호를 입력해 주십시오.");
	        form.m_pw.focus();
	        return;
	    }
	    if(!pw_validate.test(m_pw.value)){
			alert("비밀번호는 4~12자의 영대문자, 숫자로 입력해주세요!");
			return false;
		}
	    if (!form.m_nick.value) {
	        alert("닉네임을 입력해 주십시오.");
	        form.m_nick.focus();
	        return;
	    }
	    if(!nik_name_validate.test(m_nick.value)){
			alert("닉네임은 2~10자 한글로 입력해주세요!");
			return false;
		}
	    if (!form.m_name.value) {
	        alert("이름을 입력해 주십시오.");
	        form.m_name.focus();
	        return;
	    }
	    if(!nik_name_validate.test(m_name.value)){
			alert("이름은 2~10자 한글로 입력해주세요!");
			return false;
		}
	    if (!form.m_mail.value) {
	        alert("이메일을 입력해 주십시오.");
	        form.m_mail.focus();
	        return;
	    }
	    if(!mail_validate.test(m_mail.value)){
			alert("이메일은 updev@example.com으로 입력해주세요!");
			return false;
		}
	    if (!form.m_tel.value) {
	        alert("전화번호를 입력해 주십시오.");
	        form.m_tel.focus();
	        return;
	    }
 		if(!tel_validate.test(m_tel.value)){
			alert("전화번호는 11글자로 공백과 -없이 입력해주세요!");
			return false;
		}
	    console.log(pw,nick,name,mail,tel,field);
	    var result = confirm("정말 수정하시겠습니까?");
		console.log(result);
		if(result){
			
			var sam ={"pw":pw,"nick":nick,"name":name,"mail":mail,"tel":tel,"field":field};
			console.log(sam);
			var sam =JSON.stringify(sam);	
			$.ajax({
				type:"post",
				async:false,
				url:"info_update",
				data:{jsoninfo:sam},
				success:function(data,textStatus){
					location.href="infoupdate";
				},
				error:function(data,textStatus){
					alert("전송실패!!");
				}
			});
		}else{
			
			return false;
		}    
}




































function go_notice_manage(){
	
	console.log(111);
	$.ajax({
				type:"post",
				dataType:"json",
				url:"noticeboardmanage",
				success:function(data){
					alert("전송성공!!");
					//console.log(data);
					//console.log(data.members);
					//console.log(data.members[0].id);
					//var sam =JSON.stringify(data);
				//	var jsoninfo =JSON.parse(data);

		            var htm = " ";
		         	htm += "<table border='1'>"
					htm += "<tr><td>번호</td><td>종류</td><td>분류</td><td>제목</td>"+
					"<td>작성일</td><td>내용</td><td>추천수</td><td>조회수</td><td>태그</td>"+
					"<td>첨부파일1</td><td>첨부파일2</td><td>신고회수</td><td>삭제</td><td>수정</td>"+
					"</tr>"
		            for(var i in data.members){                           
		         	htm += "<tr>"
		               htm += "<td>"+data.members[i].b_num+"</td>";
		               htm += "<td>"+data.members[i].b_cate+"</td>";
		               htm += "<td>"+data.members[i].b_kind+"</td>";
		               htm += "<td>"+data.members[i].b_title+"</td>";
		               htm += "<td>"+data.members[i].b_wdate+"</td>";
		               htm += "<td>"+data.members[i].b_content+"</td>";
		               htm += "<td>"+data.members[i].b_likecnt+"</td>";
		               htm += "<td>"+data.members[i].b_readcnt+"</td>";
		               htm += "<td>"+data.members[i].b_tag+"</td>";
		               htm += "<td>"+data.members[i].b_file1+"</td>";
		               htm += "<td>"+data.members[i].b_file2+"</td>";
		               htm += "<td>"+data.members[i].b_report+"</td>";
		               htm += "<td><a onclick='mylist_delete("+data.members[i].b_num+");'>삭제</a></td>";
					htm += "</tr>"
		            }
		            htm += "</table>"
		            //console.log(htm + " : sam");
					location.replace("go_notice");
					console.log(1+"확인");
		           	$("#out").html(htm);
		      },//성공시 종료      

			error:function(data,textStatus){
					alert("전송실패!!");
				}

		   });//버튼을 클릭

		/*
	$.ajax({
				type:"post",
				async:false,
				url:"noticeboardmanage",
				success:function(data,textStatus){
					location.replace("noticeboardmanage");
				},
				error:function(data,textStatus){
					alert("전송실패!!");
				}
			});
			*/
			
}


function go_infoupdate(){
	$.ajax({
				type:"post",
				async:false,
				url:"infoupdate",
				success:function(data,textStatus){
					location.replace("infoupdate");
				},
				error:function(data,textStatus){
					alert("전송실패!!");
				}
			});
}
function go_mylist(){
	$.ajax({
				type:"post",
				async:false,
				url:"mylist",
				success:function(data,textStatus){
					location.replace("mylist");
				},
				error:function(data,textStatus){
					alert("전송실패!!");
				}
			});
}



