<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="resources/js/mypage.js"></script>
<title>Insert title here</title>
   <link rel="stylesheet" type="text/css" href="./resources/css/mypage.css">
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
   <script type="text/javascript" src="./resources/js/mypage.js">
   </script>
</head>
<body>
<<<<<<< HEAD
<table>
<tr>
<div class="mypage_tabs">
   <div class="mypage_tab">
       <input type="radio" id="tab-1" name="tab-group-1">
       <label for="tab-1"><a href="proupdatecheck">마이 프로필</a></label>
       <div class="mypage_content">
           <span>프로필 수정을 누르면 나오는 거</span>
       </div> 
   </div>
    
   <div class="mypage_tab">
       <input type="radio" id="tab-2" name="tab-group-1">
       <label for="tab-2"><a href="myp">마이 글</a></label>
       <!-- 마이 글 안에 들어 가야 할 것 : 내가 쓴 글, 내가 좋아요한 글, 내가 스크랩한 글  -->
       <div class="mypage_content">
           <span>나의 글을 누르면 나오는 거</<span>
       </div> 
   </div>
    
   <div class="mypage_tab">
       <input type="radio" id="tab-3" name="tab-group-1">
       <label for="tab-3"><a href="#">마이알림</a></label>
       <div class="mypage_content">
           <span>마이알림을 누르면 나오는 거 </span>
       </div> 
   </div>
   <div class="mypage_tab">
       <input type="radio" id="tab-4" name="tab-group-1">
       <label for="tab-4"><a href="#">마이쪽지</a></label>
     
       <div class="mypage_content">
           <span>마이쪽지를 누르면 나오는 거 </span>
       </div> 
       <div class="mypage_tab">
       <input type="radio" id="tab-5" name="tab-group-1">
       <label for="tab-5"><button type="button" id="boardwrite">글 작성</button></label>
     
       <div class="mypage_content">
           <span>마이쪽지를 누르면 나오는 거 </span>
       </div> 
   </div>
   
    
</div>
</tr>
</table>
<table>
<tr>
	<th>번호</th>
	<th>분류</th>
	<th>제목</th>
	<th>작성일</th>
	<th>좋아요수</th>
	<th>조회수</th>
	<th>수정 / 삭제</th>
</tr>
<c:forEach items="${list }" var="a">
<tr>
	<td>${a.b_num }</td>
	<td>${a.b_kind }</td>
	<td>${a.b_title }</td>
	<td>${a.b_wdate }</td>
	<td>${a.b_likecnt }</td>
	<td>${a.b_readcnt }</td>
	<td>
	<input type="button" value="수정" onclick="location.href='writeupdatecheck?b_num=${a.b_num}'">&emsp;
	<input type="button" value="삭제" onclick="location.href='writedelete?b_num=${a.b_num}'"><!-- 정말 삭제하시겠습니까 유효성 띄우고 확인하면 삭제 되는 기능부탁합니다 -->
	</td>
</tr>
</c:forEach>
</table>
=======
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
>>>>>>> 4dbaa03edc00331476ed6bcb6d921256e373c163
</body>
</html>