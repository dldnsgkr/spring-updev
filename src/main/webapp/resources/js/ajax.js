function test(){
	
	var id =$("#m_id").val();
	
	console.log(id);
	var sam ={"id":id};
	
	console.log(sam);
	var sam =JSON.stringify(sam);	
	$.ajax({
		type:"get",
		async:false,
		url:"test",
		data:{jsoninfo:sam},
		success:function(data,textStatus){
			alert("전송성공!!");
			console.log(data);
			$("#id_check").text(data);
			
		},
		error:function(data,textStatus){
			alert("전송실패!!");
		}
	}); 
}