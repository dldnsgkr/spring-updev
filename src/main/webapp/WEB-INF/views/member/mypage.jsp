<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="resources/js/mypage.js"></script>
<title>Insert title here</title>
   <link rel="stylesheet" type="text/css" href="./resources/css/mypage.css">
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
   <script type="text/javascript" src="./resources/js/mypage.js"></script>
   <script type="text/javascript" src="./resources/js/ajaxmywrite.js"></script>
</head>
<body>

<section>
<div class="jq_tabonoff comm_tab1">
        <ul class="jq_tab tab_menu">
            <li><a href="javascript:;" class="tit">활동내역</a></li>
            <li><a href="javascript:;" class="tit">알림</a></li>
            <li><a href="javascript:;" class="tit">글쓰기</a></li>
            <li><a href="javascript:;" class="tit">내 정보</a></li>
        </ul>
        <div class="jq_cont tab_cont">
            <!-- // 탭1 -->
            <div class="cont">
                <div class="jq_tabonoff comm_tab2">
                    <ul class="jq_tab tab_menu"></ul>
                    <div class="jq_cont tab_cont">
                        <div class="cont">
                            <a href="ajaxmywrite">내가 쓴 글</a>
                        </div>
                        <div class="cont">
                            <a href="ajaxmygood">좋아요</a>
                        </div>
                        <div class="cont">
                            <a href="ajaxmyscrap">스크랩</a>
                        </div>
                    </div>
                </div>
            </div>
            <!-- // 탭2 -->
            <div class="cont">
                <div class="jq_tabonoff comm_tab2">
                    <div class="jq_cont tab_cont">
                        <div class="cont">
                            <a href="alarm">알림</a>
                        </div>
                    </div>
                </div>
            </div>
            <!-- // 탭3 -->
            <div class="cont">
                <div class="jq_tabonoff comm_tab2">
                    <div class="jq_cont tab_cont">
                        <div class="cont">
                            <a href="write">글쓰기</a> 
                        </div>
                        
                    </div>
                </div>
            </div>
            <!-- // 탭4 -->
            <div class="cont">
                <div class="jq_tabonoff comm_tab2">
                    <div class="jq_cont tab_cont">
                        <div class="cont">
                            <a href="proupdatecheck?m_nick=${member.m_nick }">정보수정</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>