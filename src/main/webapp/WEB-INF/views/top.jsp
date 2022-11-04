<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
		<script src="resources/js/search.js"></script>
		<link rel="stylesheet" href="resources/css/top.css" type="text/css">
	</head>
	<body>
		<c:set var="result" value="${param.result}"/>
		<c:choose>
			<c:when test="${loginresult=='loginfail'}">
				<script type="text/javascript">
					window.onload=function(){
						alert("로그인 확인 해주세요!!");
					}
				</script>
			</c:when>
		</c:choose>
		
		<c:set var="check" value="${param.check}"/>
		<c:choose>
			<c:when test="${check=='nodata'}">
				<script type="text/javascript">
					window.onload=function(){
						alert("아이디나 비밀번호가 올바르지 않습니다.");
					}
				</script>
			</c:when>
		</c:choose>
			
		<c:set var="finish" value="${param.finish}"/>
		<c:choose>
			<c:when test="${finish=='good'}">
				<script type="text/javascript">
					window.onload=function(){
						alert("회원가입이 완료되었습니다!!");
					}
				</script>
			</c:when>
		</c:choose>
		
		<c:set var="gradecheck" value="${param.gradecheck}"/>
		<c:choose>
			<c:when test="${gradecheck=='badgrade'}">
				<script type="text/javascript">
					window.onload=function(){
						alert("관리자에 의해 차단되었습니다.");
					}
				</script>
			</c:when>
		</c:choose>
			
			<div class="header">
				<div class="wrap">
					<div class="top">
						<div id="dpTime"></div>
						<div class="top_login">
						<!-- 관리자로 로그인한 경우,회원으로 로그인한 경우, 기본 -->
							<c:choose>
								<c:when test="${loginState==true}">
								<c:choose>
								<c:when test="${member.m_id=='admin'}">
									<a>환영합니다!<b>관리자님</b></a>
									<a class="alarm" onclick="alarm_quick_view('${member.m_id}');">
									<img src="./resources/images/alarm.svg">
									<span class="alarm_cnt">${alarm_count}</span>
									</a>
									<a class="underlinee" href="admin_mypage">마이페이지</a>
								</c:when>
									<c:otherwise>
									<a>환영합니다!<b>${member.m_nick}님</b></a>
									<a class="alarm" onclick="alarm_quick_view('${member.m_id}');">
									<img src="./resources/images/alarm.svg">
									<span class="alarm_cnt">${alarm_count}</span>
									</a>
									<a class="underlinee" href="myp?m_nick=${member.m_nick}">마이페이지</a>
									</c:otherwise>
									</c:choose>
									<a class="underline" href="logout">로그아웃</a>
								</c:when>
								<c:otherwise>
									<ul>
										<li><a class="underline" href="signup">회원가입</a></li>
										<li><a class="underline" href="login">로그인</a></li>
									</ul>
								</c:otherwise>
							</c:choose>
						</div>
						<div id="quick">
						</div>
					</div>
					<div class="topbar">
						<div class="logo">
							<a href="index"><img src="./resources/images/updev_2.png" id="top_logo"></a>
						</div>
						<div id="bottom2">
							<div class="menu">
								<a href="noticepage" id="menu">공지</a>
								<a href="sharepage" id="menu">정보공유</a>
								<a href="questionpage" id="menu">지식인</a>
								<a href="worrypage" id="menu">고민상담소</a>
								<a href="qnapage" id="menu">Q&A</a>
								<div class="search">
									<form action="search" method="post" id="top_search_form" name="top_search_form">
										<input class="main_search" type="text" name="keyword" placeholder="검색">
										<input type="text" style="display:none;"/>
										<button class="search-btn" type="button" onclick="SearchChk()"></button>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
				
							</div>
		<script type="text/javascript">
		//시계
		$("#top_search_form").keypress(function(e) {
			if (e.keyCode === 13) {
				SearchChk();
			}
		});	
		dpTime()
		setInterval("dpTime()", 1000);
		function dpTime() {
		   var now = new Date();
		   hours = now.getHours();
		   min = now.getMinutes();
		   sec = now.getSeconds();
		   
		   if(hours > 12){
		      hours -= 12;
		      ampm = "오후";
		   } else {
		      ampm = "오전"
		   }
		   if(hours < 10){
		      hours = "0"+hours;
		   }
		   if(min < 10){
		      min = "0"+min;
		   }
		   if(sec < 10){
		      sec = "0"+sec;
		   }
		   
		   document.getElementById("dpTime").innerHTML = ampm + " " + hours + ":" + min + ":" + sec
		}
		
		//빠른 알림 보기
		function alarm_quick_view(m_id) {
			var sam ={"m_id":m_id};
			var sam =JSON.stringify(sam);
			$.ajax({
				type:"post",
				dataType:"json",
				url:"alarm_quick_view",
				data: {jsoninfo:sam},
				success:function(data){
					
		            var htm = "";
		         	//htm += "<div>"
					//htm += "<tr><td>번호</td><td>종류</td><td>분류</td><td>제목</td>"+
					//"<td>작성일</td><td>내용</td><td>추천수</td><td>조회수</td><td>태그</td>"+
					//"<td>첨부파일1</td><td>첨부파일2</td><td>신고회수</td>";
					//"</tr>"
		         	
		         		<c:choose>
			         		<c:when test="${member.m_nick=='관리자'}">
			         			htm += "<a href='admin_mypage'>전체알람</a>"
			         		</c:when>
			         		<c:otherwise>
		         				htm += "<a href='alarm'>전체알람</a>"
		         			</c:otherwise>
					
		         		</c:choose>
	
		         		htm += "<input type='button' onclick='cclose();' value='X'>"
		            for(var i in data.members){                           
		         		htm += "<div class='con'>"+data.members[i].a_content+"</div>"
		            }
		            //htm += "</table>"
					$("#quick").addClass('on');
		            $("#quick").css("display", "block");
		            $("#quick").html(htm);
		     	},     
				error:function(data){
					alert("실패");
				}
		   });
		}
		//빠른 알림 닫기
		function cclose() {
			$("#quick").css("display", "none");
		}
		
		 </script>
	</body>
</html>