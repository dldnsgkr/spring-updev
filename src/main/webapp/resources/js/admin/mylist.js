function mywrite(){
	$.ajax({
				type:"post",
				dataType:"json",
				url:"mywrite",
				success:function(data){
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
		            $("#out").html(htm);
		      }//성공시 종료      
		   });//버튼을 클릭
}
function mylist_delete(b_num){
	console.log(b_num+"b_num");
	
	var result = confirm("정말 삭제하시겠습니까?");
	
	console.log(result+"result");
	if(result){
		var sam ={"b_num":b_num};
		var sam =JSON.stringify(sam);
		console.log(sam+"sam");
		
		$.ajax({
				type:"post",
				async:false,
				url:"mylist_delete",
				data:{jsoninfo:sam},
				success:function(data,textStatus){
					location.href="mylist";
				},
				error:function(data,textStatus){
					alert("전송실패!!");
				}
			});
			
	}else{
		return false;
	}
	/*
	$.ajax({
				type:"post",
				dataType:"json",
				url:"mylist_delete",
				success:function(data){
					console.log(data);
					console.log(data.members);
					console.log(data.members[0].id);
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
		            $("#out").html(htm);
		      }//성공시 종료      
		   });//버튼을 클릭
*/
}
//			int b_num, String b_cate, String b_kind, String b_title, String m_nick, String b_wdate,
//	         String b_content, int b_likecnt, int b_readcnt, int b_group, int b_step, int b_indent, String b_tag,
//	         String b_file1, String b_file2, int b_report) {
	
	function mylike(){
	console.log(1);
	$.ajax({
				type:"post",
				dataType:"json",
				url:"mylike",
				success:function(data){
					console.log(data);
					console.log(data.members);
					console.log(data.members[0].id);
					//var sam =JSON.stringify(data);
				//	var jsoninfo =JSON.parse(data);

		            var htm = " ";
		         	htm += "<table border='1'>"
					htm += "<tr><td>번호</td><td>종류</td><td>분류</td><td>제목</td>"+
					"<td>작성일</td><td>내용</td><td>추천수</td><td>조회수</td><td>태그</td>"+
					"<td>첨부파일1</td><td>첨부파일2</td><td>신고회수</td>"+
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
					htm += "</tr>"
		            }
		            htm += "</table>"
		            //console.log(htm + " : sam");
		            $("#out").html(htm);
		      }//성공시 종료      
		   });//버튼을 클릭
}
	function myscrap(){
	console.log(1);
	$.ajax({
				type:"post",
				dataType:"json",
				url:"myscrap",
				success:function(data){
					console.log(data);
					console.log(data.members);
					console.log(data.members[0].id);
					//var sam =JSON.stringify(data);
				//	var jsoninfo =JSON.parse(data);

		            var htm = " ";
		         	htm += "<table border='1'>"
					htm += "<tr><td>번호</td><td>종류</td><td>분류</td><td>제목</td>"+
					"<td>작성일</td><td>내용</td><td>추천수</td><td>조회수</td><td>태그</td>"+
					"<td>첨부파일1</td><td>첨부파일2</td><td>신고회수</td>"+
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
					htm += "</tr>"
		            }
		            htm += "</table>"
		            //console.log(htm + " : sam");
		            $("#out").html(htm);
		      }//성공시 종료      
		   });//버튼을 클릭
}