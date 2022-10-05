function ajaxmywrite(url){
	$.ajax({
		type:"post",
		dataType:"json",
		url,
		success:function(data){
            var memtest = "";
         	memtest += "<table border='1'>"
			memtest += "<tr><td>번호</td><td>분류</td><td>제목</td>"+
			"<td>작성일</td><td>추천수</td><td>조회수</td></tr>"
            for(var i in data.members){                           
         		memtest += "<tr>"
				memtest += "<td>"+data.members[i].b_num+"</td>";
				memtest += "<td>"+data.members[i].b_kind+"</td>";
				memtest += "<td>"+data.members[i].b_title+"</td>";
				memtest += "<td>"+data.members[i].b_wdate+"</td>";
				memtest += "<td>"+data.members[i].b_likecnt+"</td>";
				memtest += "<td>"+data.members[i].b_readcnt+"</td>";
			
				memtest += "</tr>"
            }
            memtest += "</table>"
            $("#out").html(memtest);
     	},     
		error:function(data){
			alert("실패");
		}
   });
}

