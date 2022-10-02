$(document).ready (function () {
    // 탭(ul) onoff
    $('.jq_tabonoff>.jq_cont').children().css('display', 'none');
    $('.jq_tabonoff>.jq_cont div:first-child').css('display', 'block');
    $('.jq_tabonoff>.jq_tab li:first-child').addClass('on');
    $('.jq_tabonoff').delegate('.jq_tab>li', 'click', function() {
        var index = $(this).parent().children().index(this);
        $(this).siblings().removeClass();
        $(this).addClass('on');
        $(this).parent().next('.jq_cont').children().hide().eq(index).show();
    });
});
// 마이페이지 - 정보수정 페이지 이동
function admin_infoupdate(){
	$.ajax({
		type:"post",
		async:false,
		url:"admin_infoupdate",
		success:function(data,textStatus){
			location.href="admin_infoupdate";
		},
		error:function(data,textStatus){
			alert("전송실패!!");
		}
	});
}
// 마이페이지 - 마이 글 페이지 이동
function admin_mylist(){
	$.ajax({
		type:"post",
		async:false,
		url:"admin_mylist",
		success:function(data,textStatus){
			location.href="admin_mylist";
		},
		error:function(data,textStatus){
			alert("전송실패!!");
		}
	});
}
// 마이페이지 - 마이 알림 페이지 이동
function admin_myalarm(){
	$.ajax({
		type:"post",
		async:false,
		url:"admin_myalarm",
		success:function(data,textStatus){
			location.href="admin_myalarm";
		},
		error:function(data,textStatus){
			alert("전송실패!!");
		}
	});
}
// 게시판 관리 - 공지 게시판 관리 페이지 이동
function notice_manage(){
	$.ajax({
		type:"post",
		async:false,
		url:"notice_manage",
		success:function(data,textStatus){
			location.href="notice_manage";
		},
		error:function(data,textStatus){
			alert("전송실패!!");
		}
	});
}
// 게시판 관리 - 정보공유 게시판 관리 페이지 이동
function infoshare_manage(){
	$.ajax({
		type:"post",
		async:false,
		url:"infoshare_manage",
		success:function(data,textStatus){
			location.href="infoshare_manage";
		},
		error:function(data,textStatus){
			alert("전송실패!!");
		}
	});
}
// 게시판 관리 - 지식인 게시판 관리 페이지 이동
function intellectual_manage(){
	$.ajax({
		type:"post",
		async:false,
		url:"intellectual_manage",
		success:function(data,textStatus){
			location.href="intellectual_manage";
		},
		error:function(data,textStatus){
			alert("전송실패!!");
		}
	});
}
// 게시판 관리 - 고민상담소 게시판 관리 페이지 이동
function counseling_manage(){
	$.ajax({
		type:"post",
		async:false,
		url:"counseling_manage",
		success:function(data,textStatus){
			location.href="counseling_manage";
		},
		error:function(data,textStatus){
			alert("전송실패!!");
		}
	});
}
// 게시판 관리 - Q&A 게시판 관리 페이지 이동
function qna_manage(){
	$.ajax({
		type:"post",
		async:false,
		url:"qna_manage",
		success:function(data,textStatus){
			location.href="qna_manage";
		},
		error:function(data,textStatus){
			alert("전송실패!!");
		}
	});
}
// 신고 관리 - 신고 관리 페이지 이동
function report_manage(){
	$.ajax({
		type:"post",
		async:false,
		url:"report_manage",
		success:function(data,textStatus){
			location.href="report_manage";
		},
		error:function(data,textStatus){
			alert("전송실패!!");
		}
	});
}
// 회원 관리 - 회원 관리 페이지 이동
function member_manage(){
	$.ajax({
		type:"post",
		async:false,
		url:"member_manage",
		success:function(data,textStatus){
			location.href="member_manage";
		},
		error:function(data,textStatus){
			alert("전송실패!!");
		}
	});
}