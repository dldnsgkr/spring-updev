<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style type="text/css">
#container {
    width: 100%;
    height: 700px;
    position: relative;
    overflow: hidden;
}

#right, #left {
    -webkit-transition: all 1s ease;
    -moz-transition: all 1s ease;
    width: 100%;
    height: 100%;
    position: absolute;
}

#left {
    left: 0
}

#right {  
    left: 100%;
}

.transitioned {
    -webkit-transform: translate(-100%, 0);
    -moz-transform: translate(-100%, 0);
}
</style>
</head>
<body>
<script type="text/javascript">
var _url_params = {
	    page:1
	};

	 // 콜백함수
	function onReady($_,Backbone){
		app.initialize({
	        'document':{
	            "example" :{
	                "list"  :{ "dataType":"html", "template":"example_list", "value":"document_list"},
	                "view":{ "dataType":"html", "template":"example_view","value":"document_view"}
	            }
	        }
	    });

	    // document list
	    require(['document/list','document/view'], function(document_list,document_view)
	    {
	        // 블로그 목록
	        var DocumentListActivity = {
	            viewObj : null,
	            // history_count : 0,
	            initialize : function(params){
	                viewObj = $('#document_view');

	                document_list.initialize(["example", params], 
	                function(data)
	                {
						$('#document_list').html(data);
	                    $('.click').on('click', function(){
	                        var this_data_id = $(this).data("id");
	                        DocumentListActivity.view(this_data_id);
	                    });
	                });
	            },
	            view : function(id){
	                document_view.initialize(["example", {'id':id}], 
	                function(data)
	                {
	                    viewObj.html(data);

	                    window.history.replaceState({id: id}, "title "+id, "?id="+id);
	                    $("#left, #right").toggleClass("transitioned");
	                });
	            }
	            
	        };
	 
	        DocumentListActivity.initialize(_url_params);

	        // 히스토리 컨트롤
	        window.onpopstate = function(event) {
	            if(event.state !=null){
	                history.go(-1);
	            }else{
	                if($('#right').hasClass("transon")){
	                    $("#left, #right").toggleClass('transitioned');
	                    $('#right').removeClass("transon");
	                }else{
	                    $('#right').addClass("transon");
	                }                
	                
	            }
	        };

	    });
	}
</script>
</body>
</html>