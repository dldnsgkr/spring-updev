<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
   <link rel="stylesheet" type="text/css" href="./resources/css/mypage.css">
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
   <script type="text/javascript" src="./resources/js/mypage.js">
   </script>
</head>
<body>
<section>
<div class="jq_tabonoff comm_tab1">
        <ul class="jq_tab tab_menu">
            <li><a href="javascript:;" class="tit">활동내역</a></li>
            <li><a href="javascript:;" class="tit">알림</a></li>
            <li><a href="javascript:;" class="tit">채팅</a></li>
            <li><a href="javascript:;" class="tit">프로필관리</a></li>
        </ul>
        <div class="jq_cont tab_cont">
            <!-- // 탭1 -->
            <div class="cont">
                <div class="jq_tabonoff comm_tab2">
                    <ul class="jq_tab tab_menu"></ul>
                    <div class="jq_cont tab_cont">
                        <div class="cont">
                            <a href="memwrite">내가 쓴 글</a>
                        </div>
                        <div class="cont">
                            <a href="memlike">좋아요</a>
                        </div>
                        <div class="cont">
                            <a href="memscrap">스크랩</a>
                        </div>
                    </div>
                </div>
            </div>
            <!-- // 탭2 -->
            <div class="cont">
                <div class="jq_tabonoff comm_tab2">
                
                    <div class="jq_cont tab_cont">
                        <div class="cont">
                            알림 내용
                        </div>
                        
                    </div>
                </div>
            </div>
            <!-- // 탭3 -->
            <div class="cont">
                <div class="jq_tabonoff comm_tab2">
                    
                    <div class="jq_cont tab_cont">
                        <div class="cont">
                            채팅
                        </div>
                        
                    </div>
                </div>
            </div>
            <!-- // 탭3 -->
            <div class="cont">
                <div class="jq_tabonoff comm_tab2">
                    
                    <div class="jq_cont tab_cont">
                        <div class="cont">
                            <a href="proupdatecheck?m_nick=${member.m_nick }">프로필 수정</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>