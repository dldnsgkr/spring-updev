<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./resources/css/admin_mypage.css">

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
<section>
<div class="jq_tabonoff comm_tab1">
		<div class="logo"><a href="index"><img alt="" src="resources/images/updev_1.png"></a></div>
		<div class="menu">
			<c:choose>
				<c:when test="${member.m_id=='admin'}">
					<p>환영합니다! 관리자님</p>
					<a href="admin_mypage">마이페이지</a>
					<a href="logout">로그아웃</a>
				</c:when>
			</c:choose>
		</div>
        <ul class="jq_tab tab_menu">
            <li><a href="javascript:;" class="tit">마이페이지</a></li>
            <li><a href="javascript:;" class="tit">게시판 관리</a></li>
            <li><a href="javascript:;" class="tit">신고 관리</a></li>
            <li><a href="javascript:;" class="tit">회원 관리</a></li>
        </ul>
        <div class="jq_cont tab_cont">
            <div class="cont">
                <div class="jq_tabonoff comm_tab2">
                    <div class="jq_cont tab_cont">
                        <div class="cont">
                        	<ul>
                        		<li><a onclick="admin_infoupdate()">정보수정</a></li>
                        		<li><a onclick="admin_mylist()">마이 글</a></li>
                        		<li><a onclick="admin_myalarm()">마이 알림</a></li>
                        	</ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="cont">
                <div class="jq_tabonoff comm_tab2">
                    <div class="jq_cont tab_cont">
                        <div class="cont">
                        	<ul>
                        		<li><a onclick="notice_manage()">공지</a></li>
                        		<li><a onclick="infoshare_manage()">정보공유</a></li>
                        		<li><a onclick="intellectual_manage()">지식인</a></li>
                        		<li><a onclick="counseling_manage()">고민상담소</a></li>
                        		<li><a onclick="qna_manage()">Q&A</a></li>
                        	</ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="cont">
                <div class="jq_tabonoff comm_tab2">
                    <div class="jq_cont tab_cont">
                        <div class="cont"><ul><li><a onclick="report_manage()">신고관리</a></li></ul></div>
                    </div>
                </div>
            </div>
            <div class="cont">
                <div class="jq_tabonoff comm_tab2">
                    <div class="jq_cont tab_cont">
                        <div class="cont"><ul><li><a onclick="member_manage()">회원관리</a></li></ul></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="./resources/js/admin/admin_mypage.js"></script>
</body>
</html>