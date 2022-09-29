<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./resources/css/side.css">

<link rel="stylesheet" type="text/css" href="./resources/css/tab.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript" src="./resources/js/tab.js">
	<script type="text/javascript" src="./resources/js/admin/infoupdate.js">
	</script>
	
<c:choose>
<c:when test="${member.m_id!='admin'}">
	<script type="text/javascript">
			alert("접근할 수 없는 페이지입니다.");
			window.location.href = 'index';
	</script>
</c:when>
</c:choose>
</head>
<body>
<header>
	<div class="admin_wrap">
		<div class="logo">
			<a href="index"><img alt="" src="resources/images/updev_1.png"></a>
		</div>
		<div class="menu">
			<c:choose>
				<c:when test="${member.m_id=='admin'}">
					<span>환영합니다! 관리자님</span>
					<a href="admin_mypage">마이페이지</a>
					<a href="logout">로그아웃</a>
				</c:when>
			</c:choose>
		</div>
	</div>
</header>
<section>
<div class="jq_tabonoff comm_tab1">
        <ul class="jq_tab tab_menu">
            <li><a href="javascript:;" class="tit">마이페이지</a></li>
            <li><a href="javascript:;" class="tit">게시판 관리</a></li>
            <li><a href="javascript:;" class="tit">신고 관리</a></li>
            <li><a href="javascript:;" class="tit">회원 관리</a></li>
        </ul>
        <div class="jq_cont tab_cont">
            <!-- // 탭1 -->
            <div class="cont">
                <div class="jq_tabonoff comm_tab2">
                    <ul class="jq_tab tab_menu">
                     <!--
                        <li><a href="javascript:;" class="tit">탭2-1</a></li>
                       
                        <li><a href="javascript:;" class="tit">탭2-2</a></li>
                        <li><a href="javascript:;" class="tit">탭2-3</a></li>
                        <li><a href="javascript:;" class="tit">탭2-4</a></li>
                     --></ul>
                    <div class="jq_cont tab_cont">
                        <div class="cont">
                           
                           <div>
                           	<a>정보수정</a>
                           
                           </div>
                           <div>
                           	마이글
                           
                           </div>
                           <div>
                           	마이알림
                           
                           </div>
                        </div>
                      
                        <!--
                        <div class="cont">
                            탭2-2의 내용
                        </div>
                        <div class="cont">
                            탭2-3의 내용
                        </div>
                        <div class="cont">
                            탭2-4의 내용
                        </div>-->
                    </div>
                </div>
            </div>
            <!-- // 탭2 -->
            <div class="cont">
                <div class="jq_tabonoff comm_tab2">
                <!--
                    <ul class="jq_tab tab_menu">
                        <li><a href="javascript:;" class="tit">탭2-1</a></li>
                        <li><a href="javascript:;" class="tit">탭2-2</a></li>
                        <li><a href="javascript:;" class="tit">탭2-3</a></li>
                    </ul>
                    -->
                    <div class="jq_cont tab_cont">
                        <div class="cont">
                             <div>
                           	<a>공지게시판</a>
                           
                           </div>
                           <div>
                           	마이글
                           
                           </div>
                           <div>
                           	마이알림
                           
                           </div>
                        </div>
                        <!-- 
                        <div class="cont">
                            탭2-2의 내용
                        </div>
                        <div class="cont">
                            탭2-3의 내용
                        </div>
                         -->
                    </div>
                </div>
            </div>
            <!-- // 탭3 -->
            <div class="cont">
                <div class="jq_tabonoff comm_tab2">
                    <!--
                    <ul class="jq_tab tab_menu">
                        <li><a href="javascript:;" class="tit">탭3-1</a></li>
                        <li><a href="javascript:;" class="tit">탭3-2</a></li>
                        <li><a href="javascript:;" class="tit">탭3-3</a></li>
                    </ul>
                     -->
                    <div class="jq_cont tab_cont">
                        <div class="cont">
                             <div>
                           	<a>정보수정</a>
                           
                           </div>
                           <div>
                           	마이글
                           
                           </div>
                           <div>
                           	마이알림
                           
                           </div>
                        </div>
                        <!--
                        <div class="cont">
                            탭3-2의 내용
                        </div>
                        <div class="cont">
                            탭3-3의 내용
                        </div>
                        -->
                    </div>
                </div>
            </div>
            <!-- // 탭3 -->
            <div class="cont">
                <div class="jq_tabonoff comm_tab2">
                    <!--
                    <ul class="jq_tab tab_menu">
                        <li><a href="javascript:;" class="tit">탭3-1</a></li>
                        <li><a href="javascript:;" class="tit">탭3-2</a></li>
                        <li><a href="javascript:;" class="tit">탭3-3</a></li>
                    </ul>
                     -->
                    <div class="jq_cont tab_cont">
                        <div class="cont">
                             <div>
                           	<a>정보수정</a>
                           
                           </div>
                           <div>
                           	마이글
                           
                           </div>
                           <div>
                           	마이알림
                           
                           </div>
                        </div>
                        <!--
                        <div class="cont">
                            탭3-2의 내용
                        </div>
                        <div class="cont">
                            탭3-3의 내용
                        </div>
                        -->
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>


</body>
</html>