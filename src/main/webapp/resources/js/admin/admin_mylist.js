

/*function admin_mylist_select(url){
	$.ajax({
		type:"post",
		dataType:"json",
		url,
		success:function(data){
            var htm = "";
         	htm += "<table border='1'>"
			htm += "<tr><td>번호</td><td>종류</td><td>분류</td><td>제목</td>"+
			"<td>작성일</td><td>내용</td><td>추천수</td><td>조회수</td><td>태그</td>"+
			"<td>첨부파일1</td><td>첨부파일2</td><td>신고회수</td>";
			if(url=="admin_mylike_select"){
				htm +="<td>좋아요 취소</td>";
			}else if(url=="admin_myscrap_select"){
				htm +="<td>스크랩 취소</td>";
			}else{
				htm +="<td>삭제</td><td>수정</td>";
			}
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
				
				if(url=="admin_mylike_select"){
					htm += "<td><a onclick='admin_mylike_cancel("+data.members[i].b_num+");'>좋아요 취소</a></td>";
				}else if(url=="admin_myscrap_select"){
					htm += "<td><a onclick='admin_myscrap_cancel("+data.members[i].b_num+");'>스크랩 취소</a></td>";
				}else{
					htm += "<td><a onclick='admin_mylist_delete("+data.members[i].b_num+");'>삭제</a></td>";
					htm += "<td><a onclick='admin_mylist_update("+data.members[i].b_num+");'>수정</a></td>";
				}
				htm += "</tr>"
				
				
            }
            htm += "</table>"



            $("#out").html(htm);
     	},     
		error:function(data){
			alert("실패");
		}
   });
}


function admin_mylist_update(b_num){
		var sam ={"b_num":b_num};
		var sam =JSON.stringify(sam);
		$.ajax({
			type:"post",
			async:false,
			url:"writeupdatecheck",
			data:{jsoninfo:sam},
			success:function(data,textStatus){
				location.href="admin_mylist";
			},
			error:function(data,textStatus){
				alert("전송실패!!");
			}
		});
	
	var modal = document.getElementById("myModal");
	var span = document.getElementsByClassName("close")[0];
	modal.style.display = "block";
	span.onclick = function() {
		modal.style.display = "none";
	}
	window.onclick = function(event) {
		if (event.target == modal) {
			modal.style.display = "block";
		}
	}
}
*/