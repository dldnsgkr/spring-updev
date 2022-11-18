package com.updev.board;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.updev.admin.ServiceAdmin;
import com.updev.member.ServiceMember;
import com.updev.member.Signup;

@Controller
public class BoardController {
	
	@Autowired
	SqlSession sqlsession;
	
	//시작페이지
	
	@RequestMapping(value = "/")
	public String mainpage(HttpServletRequest request,Model mo,
			@CookieValue(value="m_id" , required=false) String cookie_m_id) {
		HttpSession session = request.getSession();
		
		if(cookie_m_id != null) {
			ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
			
			Date now = new Date();
			  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			  String formatedNow = formatter.format(now);
			
		      Signup signup = sm.login_cookie(cookie_m_id);
		      String grade = signup.getM_grade();//등급
	    	  String outtime = signup.getM_outtime();
	    	  String date1 = outtime;//마지막 로그아웃 시간
		      String date2 = formatedNow;//현재시간
		      
	    	  int result = date1.compareTo(date2);
		      if((grade.equals("회원") || grade.equals("관리자")) && result < 0)
	    	  {//로그인한 회원에 관해 세션 정의
	 	        session.setAttribute("member", signup);
	 	        session.setAttribute("m_id", signup.getM_id());
	 	        session.setAttribute("m_pw", signup.getM_pw());
	 	        session.setAttribute("loginState", true);
	 	        session.setAttribute("member_nick", signup.getM_nick());
	 	        
	 	        String id = (String)session.getAttribute("m_id");
		 		int alarm_count = sm.alarmcount(id);		        
		 		session.setAttribute("alarm_count", alarm_count);
	    	  }
		      ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
				
		 		ArrayList<Board> popmypage=sb.popmain();
		 		ArrayList<Board> sharemypage=sb.sharemain();
		 		ArrayList<Board> questionmypage=sb.questionmain();
		 		ArrayList<Board> worrymypage=sb.worrymain();
		 		ArrayList<Board> noticemypage=sb.noticemain();
		 		ArrayList<Board> qnamypage=sb.qnamain();
		 		
		 		mo.addAttribute("popmpage",popmypage);
		 		mo.addAttribute("sharempage",sharemypage);
		 		mo.addAttribute("questionmpage",questionmypage);
		 		mo.addAttribute("worrympage",worrymypage);
		 		mo.addAttribute("noticempage",noticemypage);
		 		mo.addAttribute("qnampage",qnamypage);
		      return "main";
		}
		//비회원의 기본정보
		String login_before_nick = "unknown";
		String login_before_id = "unknown";
		
		session.setAttribute("loginState", false);
		session.setAttribute("member_nick", login_before_nick);
		session.setAttribute("m_id", login_before_id);
		
		ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
		
 		ArrayList<Board> popmypage=sb.popmain();
 		ArrayList<Board> sharemypage=sb.sharemain();
 		ArrayList<Board> questionmypage=sb.questionmain();
 		ArrayList<Board> worrymypage=sb.worrymain();
 		ArrayList<Board> noticemypage=sb.noticemain();
 		ArrayList<Board> qnamypage=sb.qnamain();
 		
 		mo.addAttribute("popmpage",popmypage);
 		mo.addAttribute("sharempage",sharemypage);
 		mo.addAttribute("questionmpage",questionmypage);
 		mo.addAttribute("worrympage",worrymypage);
 		mo.addAttribute("noticempage",noticemypage);
 		mo.addAttribute("qnampage",qnamypage);

		return "main";
	}
	
	//로고 클릭시 이동할 페이지
	@RequestMapping(value = "/index")
	public String indexpage(Model mo,HttpServletRequest request)
	{	
 		HttpSession session = request.getSession();
 		ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
 		
 		//로그인을 한경우 신규 알림이 페이지 혹은 기능을 동작할때 매번 count된다
 		String m_id = (String)session.getAttribute("m_id");
 		int alarm_count = sm.alarmcount(m_id);
 		session.setAttribute("alarm_count", alarm_count);
 		
 		
        ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
        ArrayList<Board> popmypage=sb.popmain();
 		ArrayList<Board> sharemypage=sb.sharemain();
 		ArrayList<Board> questionmypage=sb.questionmain();
 		ArrayList<Board> worrymypage=sb.worrymain();
 		ArrayList<Board> noticemypage=sb.noticemain();
 		ArrayList<Board> qnamypage=sb.qnamain();
 		
 		mo.addAttribute("popmpage",popmypage);
 		mo.addAttribute("sharempage",sharemypage);
 		mo.addAttribute("questionmpage",questionmypage);
 		mo.addAttribute("worrympage",worrymypage);
 		mo.addAttribute("noticempage",noticemypage);
 		mo.addAttribute("qnampage",qnamypage);
 		
		return "main";
	}
	
	
	//로그인한 닉네임을 이용해 회원 마이페이지로 이동
	   @RequestMapping(value = "/myp")
	   public String mypage(Model mo,HttpServletRequest request)
	   {
		   HttpSession session = request.getSession();
		   
		   ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
		   String m_id = (String)session.getAttribute("m_id");
	 	   int alarm_count = sm.alarmcount(m_id);
	       session.setAttribute("alarm_count", alarm_count);
		   
	       String member_nick = (String)session.getAttribute("member_nick");
		   ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
		   ArrayList<Board> board = sb.mywrite(member_nick);
		   mo.addAttribute("list",board);
		   
		  return "mypage"; 
	   }
	   
	 //글 수정 작성
	      @RequestMapping(value = "/writeupdatecheck")
	      public String updatecheck_mywriteporm(HttpServletRequest request,Model mo)
	      {
	    	  HttpSession session = request.getSession();
	    	  
	    	  ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
		 		String m_id = (String)session.getAttribute("m_id");
		 		int alarm_count = sm.alarmcount(m_id);
		 		session.setAttribute("alarm_count", alarm_count);
		 		
		     //수정하려는 글에 b_kind를 매번 맞게 뿌려주기 위함
	    	 String b_kind = request.getParameter("b_kind");
	    	 
	    	 //넘겨받은 계시글 고유번호로 수정할 글을 찾음
	         int b_num = Integer.parseInt(request.getParameter("b_num"));
	         ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
	         Board board = sb.board_update_check(b_num);
	         mo.addAttribute("list",board);
	         mo.addAttribute("b_kind",b_kind);
	         
	         return "boardupdate";
	      }
	      
	      //글수정
	      @RequestMapping(value = "/writeupdate")
	      public String update_mywrite(MultipartHttpServletRequest mul,HttpServletRequest request)
	      {
	    	 HttpSession session = request.getSession();
	    	  
	    	 ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
	    	 String m_id = (String)session.getAttribute("m_id");
	    	 int alarm_count = sm.alarmcount(m_id);
	    	 session.setAttribute("alarm_count", alarm_count);
	    	 
	    	 //파일 수정도 있기에 multipart사용
	         int b_num = Integer.parseInt(mul.getParameter("b_num"));
	         String b_cate = mul.getParameter("b_cate");
	         String b_kind = mul.getParameter("b_kind");
	         String b_title = mul.getParameter("b_title");
	         String m_nick = mul.getParameter("m_nick");
	         String b_content = mul.getParameter("b_content");
	         MultipartFile file1 = mul.getFile("b_file1");
	         MultipartFile file2 = mul.getFile("b_file2");
	         String b_file1 = file1.getOriginalFilename();
	         String b_file2 = file2.getOriginalFilename();
	         ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
	         sb.boardupdate(b_num,b_cate,b_kind,b_title,m_nick,b_content,b_file1,b_file2);

	         
	         return "redirect:ajaxmywrite";

	      }
	      
	      //글 작성 폼
	      @RequestMapping(value = "/write")
	      public String writeporm(RedirectAttributes rattr,HttpServletRequest request,Model mo)
	      {
	    	  HttpSession session=request.getSession();
	    	  //로그인을 했는지여부
	    	  if((Boolean) session.getAttribute("loginState"))
				{
		    	  ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
		    	  String m_id = (String)session.getAttribute("m_id");
		    	  int alarm_count = sm.alarmcount(m_id);
			      session.setAttribute("alarm_count", alarm_count);
	    		  
			      //글쓰려 하는 게시글의 b_kind를 받아 맞게 뿌려줌
	    		  String b_kind = request.getParameter("b_kind");
	    		  session.setAttribute("b_kind", b_kind);
	    		  return "boardwrite";
				}
				else
				{
					//로그인을 하지않았다면 로그인창으로
					rattr.addAttribute("loginresult", "loginfail");
					return "redirect:login";
					
	     		}
	      }
	      
	      //글 작성
	      @RequestMapping(method = RequestMethod.POST,value = "/writesave")
	         public String write(MultipartHttpServletRequest mul,HttpServletRequest request)
	         {
	    	  HttpSession session = request.getSession();
	    	  
	    	  ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
	    	  String m_id = (String)session.getAttribute("m_id");
	    	  int alarm_count = sm.alarmcount(m_id);
		      session.setAttribute("alarm_count", alarm_count);
	    	  
		    //파일 등록도 있기에 multipart사용
	            String b_cate = mul.getParameter("b_cate");
	            String b_kind = mul.getParameter("b_kind");
	            String b_title = mul.getParameter("b_title");
	            String m_nick = mul.getParameter("m_nick");
	            String b_content = mul.getParameter("b_content");
	            MultipartFile file1 = mul.getFile("b_file1");
	            MultipartFile file2 = mul.getFile("b_file2");
	            String b_file1 = file1.getOriginalFilename();
	            String b_file2 = file2.getOriginalFilename();
	            ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
	            sb.writesave(b_cate,b_kind,b_title,m_nick,b_content,b_file1,b_file2);
	            
	            //글 작성이 끝나면 작성한 글의 b_kind에 맞는 페이지로 간다
	            if(b_kind.equals("공지"))
		         {
		        	 return "redirect:noticepage";
		         } else if(b_kind.equals("정보공유")) {
		        	 return "redirect:sharepage";
		         } else if(b_kind.equals("고민상담소")) {
		        	 return "redirect:worrypage";
		         } else if(b_kind.equals("지식인")) {
		        	 return "redirect:questionpage";
		         } else if(b_kind.equals("QNA")) {
		        	 return "redirect:qnapage";
		         } else {
		        	 return "redirect:ajaxmywrite";
		         }
	         }
	      		//글 삭제
	         @RequestMapping(value = "/writedelete")
		      public String delte(HttpServletRequest request,Model mo)
		      {
	        	 HttpSession session = request.getSession();
		    	ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
		    	String m_id = (String)session.getAttribute("m_id");
			 	int alarm_count = sm.alarmcount(m_id);
			        session.setAttribute("alarm_count", alarm_count);
	        	 
	        	 String b_kind = request.getParameter("b_kind");
		         int b_num = Integer.parseInt(request.getParameter("b_num"));
		         ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
		         sb.boarddelete(b_num);
		         
		       //글 삭제가 끝나면 작성한 글의 b_kind에 맞는 페이지로 간다
		         if(b_kind.equals("공지"))
		         {
		        	 return "redirect:noticepage";
		         } else if(b_kind.equals("정보공유")) {
		        	 return "redirect:sharepage";
		         } else if(b_kind.equals("고민상담소")) {
		        	 return "redirect:worrypage";
		         } else if(b_kind.equals("지식인")) {
		        	 return "redirect:questionpage";
		         } else if(b_kind.equals("QNA")) {
		        	 return "redirect:qnapage";
		         } else {
		        	 return "redirect:ajaxmywrite";
		         }
		      }

	         
	         //조회수
	         public void Readcnt(int num) {
	     		ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
	     		sb.readcnt(num);
	     	}
	         
	         //알림 읽은 여부 체크
	         @RequestMapping(value = "/alarmcheck")
	         public ModelAndView alarm_read_check(HttpServletRequest request)
	         {
	             int b_num = Integer.parseInt(request.getParameter("b_num"));
	        	 int a_num = Integer.parseInt(request.getParameter("a_num"));
	        	 ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
	        	 sm.alarmchk(a_num);
	        	 
	        	 //b_num을 detail로 갈떄 같이 보내줘야 하기때문에 ModelAndView를 사용
	        	 ModelAndView mav = new ModelAndView();
	                 mav.addObject("b_num", b_num);
	                 mav.setViewName("redirect:detail");
	             
	             
	             return mav;
	         }
	         
	         
	         //게시물 detail
	         @RequestMapping(value = "/detail")
	         public String detailporm(HttpServletRequest request,Model mo, PageDTO dto, Criteria cri)
	         {
		     	 HttpSession session = request.getSession();
		    	  ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
			 		
			 		String m_id = (String)session.getAttribute("m_id");
			 		int alarm_count = sm.alarmcount(m_id);
			        session.setAttribute("alarm_count", alarm_count);
			        
			     String member_nick = (String)session.getAttribute("member_nick");
			     
			     //좋아요 혹은 스크랩을 했을때 돌아가는게 아니라 
			     //각 페이지 혹은 알림에서 detail 페이지로 들어오면 조회수가 증가하게끔 만듬
	        	 String recieve_read = request.getParameter("receiveread");
	        	 
	        	 int b_num = Integer.parseInt(request.getParameter("b_num"));
	        	 String su_nick = request.getParameter("su_nick");
	        	 String nowPage=request.getParameter("nowPage");
		    	 String cntPerPage=request.getParameter("cntPerPage");
		    	 
	        	 session.setAttribute("b_num", b_num);
	        	 session.setAttribute("su_nick", su_nick);
	        	 if(recieve_read != null)
	        	 {
	        	 Readcnt(b_num);
	        	 }
	        	 ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
	        	 
	        	 //detail 번호에 맞는 게시글을 가져온다
	        	 Board board = sb.boarddetail(b_num);
	        	 
	        	 //
	        	 String board_nick = board.getM_nick();
	        	 Signup signup = sb.boarddetailid(board_nick);
	        	 
	        	 //해당 로그인한 사람이 해당게시글에 좋아요를 눌렀는지 확인
	        	 Good good = sb.how_many_good(b_num,member_nick);
	        	 
	        	 
	        	 int total = sb.replytotal(b_num);
	        	 
	        	//해당 로그인한 사람이 해당게시글에 좋아요를 눌렀는지 확인
	        	 Scrap scrap = sb.how_many_scrap(b_num,member_nick);
	        	 
	        	 
		    		
	        	 if(nowPage == null && cntPerPage == null) {
		    		nowPage="1";
		    		cntPerPage="10";
	        	 } else if(nowPage==null) {
		    		nowPage="1";
	        	 } else if(cntPerPage==null) {
		    		cntPerPage="10";
	        	 }
		    		
	        	 dto=new PageDTO(cri,total,Integer.parseInt(nowPage),Integer.parseInt(cntPerPage),b_num);
	        	 mo.addAttribute("page1",dto);
	        	 mo.addAttribute("page2",cri);
	        	 mo.addAttribute("repage",sb.replypage(dto));
	        	 mo.addAttribute("list",board);
	        	 mo.addAttribute("llist",good);
	        	 mo.addAttribute("slist",scrap);
	        	 mo.addAttribute("signup",signup);
	        	 return "detailboard";
	         }
	         
	         public void Replycnt(int num) {
	     		ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
	     		sb.replycnt(num);
	     	}
	         
	         //댓글
	         @RequestMapping(value = "/replysave")
	         public ModelAndView reply(HttpServletRequest request,Model mo) {
	        	 
	        	 HttpSession session = request.getSession();
		    	  ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
			 		String m_id = (String)session.getAttribute("m_id");
			 		int alarm_count = sm.alarmcount(m_id);
			        session.setAttribute("alarm_count", alarm_count);
	        	 
	        	 int b_num=Integer.parseInt(request.getParameter("b_num"));
	        	 String m_nick=request.getParameter("m_nick");
	        	 String su_nick=request.getParameter("m_id");
	        	 String re_content=request.getParameter("re_content");
	        	 String b_kind = request.getParameter("b_kind");
	        	 String b_title = request.getParameter("b_title");
	        	 String a_content = b_kind+" 게시판 "+b_title+" 글에 "+m_nick+"님이 댓글을 달았습니다.";
	        	 int alarm_chk = 1;
	        	 int a_existence = 3;
	        	 ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
	        	 
	        	 sb.replysave(b_num, m_nick, re_content);
	        	 createalarm(b_num,m_nick,su_nick,a_content,alarm_chk,a_existence);
	        	 
	        	 Replycnt(b_num);
	        	 
	        	 ModelAndView mav = new ModelAndView();
	             
	             if(b_num == 0) {
	                 mav.setViewName("redirect:index");
	             } else {
	                 mav.addObject("b_num", b_num);
	                 mav.setViewName("redirect:detail");
	             }
	             
	             return mav;
	         }
	         
	         
	        //공지페이징
	     	@RequestMapping(value="/noticepage")
	    	public String page1(HttpServletRequest request, PageDTO dto, Model mo, Criteria cri) {
	     		
	     		HttpSession session = request.getSession();
		    	  ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
		    	  String m_id = (String)session.getAttribute("m_id");
			 		int alarm_count = sm.alarmcount(m_id);
			        session.setAttribute("alarm_count", alarm_count);
	     		
	    		String nowPage=request.getParameter("nowPage");
	    		String cntPerPage=request.getParameter("cntPerPage");
	    		ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
	    		int total = sb.noticetotal();
	    		
	    		if(nowPage == null && cntPerPage == null) {
	    			nowPage="1";
	    			cntPerPage="15";
	    		} else if(nowPage==null) {
	    			nowPage="1";
	    		} else if(cntPerPage==null) {
	    			cntPerPage="15";
	    		}
	    		

	    		dto=new PageDTO(cri,total,Integer.parseInt(nowPage),Integer.parseInt(cntPerPage));
	    		mo.addAttribute("page1",dto);
	    		mo.addAttribute("page2",cri);
	    		mo.addAttribute("bpage1",sb.noticepage(dto));
	    		
	    		
	    		return "noticepage";
	    	}
	     	
	     	//정보공유 페이징
	     	@RequestMapping(value="/sharepage")
	    	public String page2(HttpServletRequest request, PageDTO dto, Model mo, Criteria cri, Board bdto) {
	    		
	     		HttpSession session = request.getSession();
		    	  ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
		    	  String m_id = (String)session.getAttribute("m_id");
			 		int alarm_count = sm.alarmcount(m_id);
			        session.setAttribute("alarm_count", alarm_count);
	     		
	     		String nowPage=request.getParameter("nowPage");
	    		String cntPerPage=request.getParameter("cntPerPage");
	    		
	    		
	    		ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
	    		int total = sb.sharetotal();
	    		
	    		
	    		if(nowPage == null && cntPerPage == null) {
	    			nowPage="1";
	    			cntPerPage="15";
	    		} else if(nowPage==null) {
	    			nowPage="1";
	    		} else if(cntPerPage==null) {
	    			cntPerPage="15";
	    		}
	    		
	    		dto=new PageDTO(cri,total,Integer.parseInt(nowPage),Integer.parseInt(cntPerPage));
	    		mo.addAttribute("page1",dto);
	    		mo.addAttribute("page2",cri);
	    		mo.addAttribute("bpage1",sb.sharepage(dto));
	    		
	    		return "sharepage";
	    	}
	     	
	     	
	     	//지식인 페이징
	     	@RequestMapping(value="/questionpage")
	    	public String page3(HttpServletRequest request, PageDTO dto, Model mo, Criteria cri) {
	     		
	     		HttpSession session = request.getSession();
		    	  ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
		    	  String m_id = (String)session.getAttribute("m_id");
			 		int alarm_count = sm.alarmcount(m_id);
			        session.setAttribute("alarm_count", alarm_count);
	     		
	    		String nowPage=request.getParameter("nowPage");
	    		String cntPerPage=request.getParameter("cntPerPage");
	    		ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
	    		int total = sb.questiontotal();
	    		
	    		
	    		if(nowPage == null && cntPerPage == null) {
	    			nowPage="1";
	    			cntPerPage="15";
	    		} else if(nowPage==null) {
	    			nowPage="1";
	    		} else if(cntPerPage==null) {
	    			cntPerPage="15";
	    		}
	    		

	    		dto=new PageDTO(cri,total,Integer.parseInt(nowPage),Integer.parseInt(cntPerPage));
	    		mo.addAttribute("page1",dto);
	    		mo.addAttribute("page2",cri);
	    		mo.addAttribute("bpage1",sb.questionpage(dto));
	    		
	    		
	    		return "questionpage";
	    	}
	     	
	     	//고민상담소 페이징
	     	@RequestMapping(value="/worrypage")
	    	public String page4(HttpServletRequest request, PageDTO dto, Model mo, Criteria cri) {
	     		
	     		HttpSession session = request.getSession();
		    	  ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
		    	  String m_id = (String)session.getAttribute("m_id");
			 		int alarm_count = sm.alarmcount(m_id);
			        session.setAttribute("alarm_count", alarm_count);
	     		
	    		String nowPage=request.getParameter("nowPage");
	    		String cntPerPage=request.getParameter("cntPerPage");
	    		ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
	    		int total = sb.worrytotal();
	    		
	    		if(nowPage == null && cntPerPage == null) {
	    			nowPage="1";
	    			cntPerPage="15";
	    		} else if(nowPage==null) {
	    			nowPage="1";
	    		} else if(cntPerPage==null) {
	    			cntPerPage="15";
	    		}
	    		

	    		dto=new PageDTO(cri,total,Integer.parseInt(nowPage),Integer.parseInt(cntPerPage));
	    		mo.addAttribute("page1",dto);
	    		mo.addAttribute("page2",cri);
	    		mo.addAttribute("bpage1",sb.worrypage(dto));
	    		
	    		
	    		return "worrypage";
	    	}
	     	
	     	//Q&A 페이징
	     	@RequestMapping(value="/qnapage")
	    	public String page5(HttpServletRequest request, PageDTO dto, Model mo, Criteria cri) {
	     		
	     		HttpSession session = request.getSession();
		    	  ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
		    	  String m_id = (String)session.getAttribute("m_id");
			 		int alarm_count = sm.alarmcount(m_id);
			        session.setAttribute("alarm_count", alarm_count);
	     		
	    		String nowPage=request.getParameter("nowPage");
	    		String cntPerPage=request.getParameter("cntPerPage");
	    		ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
	    		int total = sb.qnatotal();
	    		
	    		if(nowPage == null && cntPerPage == null) {
	    			nowPage="1";
	    			cntPerPage="15";
	    		} else if(nowPage==null) {
	    			nowPage="1";
	    		} else if(cntPerPage==null) {
	    			cntPerPage="15";
	    		}
	    		

	    		dto=new PageDTO(cri,total,Integer.parseInt(nowPage),Integer.parseInt(cntPerPage));
	    		mo.addAttribute("page1",dto);
	    		mo.addAttribute("page2",cri);
	    		mo.addAttribute("bpage1",sb.qnapage(dto));
	    		
	    		
	    		return "qnapage";

	    	} 	

	    	
	     	
	     	//좋아요 증가
	     	public void likecntup(int num)
	     	{
	     		ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
	     		sb.likecntup(num);
	     	}
	     	
	     	//알림 생성
	     	public void createalarm(int num, String m_nick, String m_id, String a_content, int alarm_chk, int a_existence)
	     	{
	     		ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
	     		sb.makealarm(num,m_nick,m_id,a_content,alarm_chk,a_existence);
	     	}
	     	
	     	//좋아요 
	     	@RequestMapping(value = "/goodup",method = RequestMethod.POST)
	     	public String good(HttpServletRequest request,RedirectAttributes rattr)
	     	{
	     		HttpSession session=request.getSession();
	     		
	     		//로그인여부
				if((Boolean) session.getAttribute("loginState"))
				{
	     		int chk = 1;
	     		String jo=request.getParameter("jsoninfo");		
	    		JSONParser jsonparse = new JSONParser();
	    		JSONObject jobj;
	    		try {
					jobj = (JSONObject)jsonparse.parse(jo);
				int b_num=Integer.parseInt(String.valueOf(jobj.get("b_num")));
				String m_nick=(String) jobj.get("m_nick");//로그인 하고 기능을 실행한사람
				String m_id=(String) jobj.get("m_id");//받는사람
				String b_title=(String) jobj.get("b_title");
				String b_kind=(String) jobj.get("b_kind");
				String a_content = b_kind+" 게시판 "+b_title+" 글에 "+m_nick+"님이 좋아요를 눌렀습니다.";
				int alarm_chk = 1;
				int a_existence = 1;
				ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
				
				//해당 게시글에 로그인한 사람이 좋아요를 누른 기록이있는지 count
				int a = sb.are_you_chkgood(b_num,m_nick);
				if(a == 1)
				{
					//만약 이미 데이터베이스에 해당 조건에 맞는 데이터가 있다면 like_chk를 1로 바꾼다
					sb.regood(b_num, m_nick);
				} else {
					//새로 데이터베이스에 insert한다
					sb.likeup(b_num,m_nick,chk);
					createalarm(b_num,m_nick,m_id,a_content,alarm_chk,a_existence);
				}
				//좋아요수 1증가
				likecntup(b_num);
				
	    		} catch (org.json.simple.parser.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return "redirect:index";
				}
				else
				{//로그인을 하지않았다면 로그인창으로
					rattr.addAttribute("result", "loginfail");
					return "redirect:login";
				}
	     	}
	     	
	     	//좋아요 감소
	     	public void likecntdown(int num)
	     	{
	     		ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
	     		sb.likecntdown(num);
	     	}
	     	
	     	//좋아요 취소
	     	@RequestMapping(value = "/gooddown",method = RequestMethod.POST)
	     	public String good_cancel(HttpServletRequest request,RedirectAttributes rattr)
	     	{
	     		HttpSession session=request.getSession();
	     		//로그인여부
				if((Boolean) session.getAttribute("loginState"))
				{
					int like_chk = 0;
	     		String jo=request.getParameter("jsoninfo");		
	    		JSONParser jsonparse = new JSONParser();
	    		JSONObject jobj;
	    		try {
					jobj = (JSONObject)jsonparse.parse(jo);
				int b_num=Integer.parseInt(String.valueOf(jobj.get("b_num")));
				String m_nick=(String) jobj.get("m_nick");
				ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
				
				//기존에 있는 데이터에 like_chk를 0으로 수정한다
				sb.likedown(b_num,m_nick,like_chk);
				
				//좋아요수 1감소
				likecntdown(b_num);
	    		} catch (org.json.simple.parser.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return "redirect:index";
				}
				else
				{//로그인을 하지않았다면 로그인창으로
					rattr.addAttribute("result", "loginfail");
					return "redirect:login";
				}
	     	}
	     	
	     	//스크랩
	     	@RequestMapping(value = "/scrap",method = RequestMethod.POST)
	     	public String scrap(HttpServletRequest request,RedirectAttributes rattr)
	     	{
	     		HttpSession session=request.getSession();
	     		
	     		//로그인여부
				if((Boolean) session.getAttribute("loginState"))
				{
	     		int chk = 1;
	     		String jo=request.getParameter("jsoninfo");		
	    		JSONParser jsonparse = new JSONParser();
	    		JSONObject jobj;
	    		try {
	    			jobj = (JSONObject)jsonparse.parse(jo);
	    			int b_num=Integer.parseInt(String.valueOf(jobj.get("b_num")));
					String m_nick=(String) jobj.get("m_nick");//로그인 하고 기능을 실행한사람
					String m_id=(String) jobj.get("m_id");//받는사람
					String b_title=(String) jobj.get("b_title");
					String b_kind=(String) jobj.get("b_kind");
					String a_content = b_kind+" 게시판 "+b_title+" 글에 "+m_nick+"님이 스크랩을 했습니다.";
					int alarm_chk = 1;
					int a_existence = 2;
					ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
					
					//해당 게시글에 로그인한 사람이 스크랩을 누른 기록이있는지 count
					int a = sb.are_you_chkcrap(b_num,m_nick);
					if(a == 1)
					{
						//만약 이미 데이터베이스에 해당 조건에 맞는 데이터가 있다면 crap_chk를 1로 바꾼다
						sb.rescrap(b_num, m_nick);
					} else {
						//새로 데이터베이스에 insert한다
						sb.scrap(b_num,m_nick,chk);
						createalarm(b_num,m_nick,m_id,a_content,alarm_chk,a_existence);
					}
					
	    			} catch (org.json.simple.parser.ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
	    			}
					return "redirect:index";
					
					
				}
				else
				{//로그인을 하지않았다면 로그인창으로
					rattr.addAttribute("result", "loginfail");
					return "redirect:login";
					
	     		}
	     	}
	     	
	     	//스크랩 취소
	     	@RequestMapping(value = "/scrapcancel",method = RequestMethod.POST)
	     	public String scrap_cancel(HttpServletRequest request,RedirectAttributes rattr)
	     	{
	     		HttpSession session=request.getSession();
	     		
	     		//로그인여부
				if((Boolean) session.getAttribute("loginState"))
				{
					int scrap_chk = 0;
	     		String jo=request.getParameter("jsoninfo");		
	    		JSONParser jsonparse = new JSONParser();
	    		JSONObject jobj;
	    		try {
					jobj = (JSONObject)jsonparse.parse(jo);
				int b_num=Integer.parseInt(String.valueOf(jobj.get("b_num")));
				String m_nick=(String) jobj.get("m_nick");
				ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
				
				//기존에 있는 데이터에 like_chk를 0으로 수정한다
				sb.scrapcancel(b_num,m_nick,scrap_chk);
	    		} catch (org.json.simple.parser.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return "redirect:index";
				}
				else
				{//로그인을 하지않았다면 로그인창으로
					rattr.addAttribute("result", "loginfail");
					return "redirect:login";
				}
	     	}
	     	
	     	// 매핑 detail로 가기전 지나치는 구간
	         @RequestMapping(value = "/detailajax")
	         public ModelAndView detail_ajax(HttpServletRequest request,Model mo)
	         {
		     	HttpSession session = request.getSession();
		     	
		    	ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
		    	
		    	String m_id = (String)session.getAttribute("m_id");
			 	int alarm_count = sm.alarmcount(m_id);
			    session.setAttribute("alarm_count", alarm_count);
			    
	        	int b_num = (int)session.getAttribute("b_num");
	        	String su_nick = (String)session.getAttribute("su_nick");
	        	
	        	//b_num이 0이라면(조건이 부합하는것은 불가능)
	        	ModelAndView mav = new ModelAndView();
	        	if(b_num == 0) {
	                mav.setViewName("redirect:index");
	            } else {
	            	mav.addObject("su_nick", su_nick);
	                mav.addObject("b_num", b_num);
	                mav.setViewName("redirect:detail");
	            }
	             
	             return mav;
	        	 
	         }
	        
	         

	     	//인기글 페이징
	     	@RequestMapping(value="/poppage")
	     	public String page6(HttpServletRequest request, PageDTO dto, Model mo, Criteria cri) {
	     		
	     		HttpSession session = request.getSession();
		    	  ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
		    	  String m_id = (String)session.getAttribute("m_id");
			 		int alarm_count = sm.alarmcount(m_id);
			        session.setAttribute("alarm_count", alarm_count);
	     		
	     		String nowPage=request.getParameter("nowPage");
	     		String cntPerPage=request.getParameter("cntPerPage");
	     		ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
	     		int total = sb.poptotal();
	     		
	     		if(nowPage == null && cntPerPage == null) {
	     			nowPage="1";
	     			cntPerPage="15";
	     		} else if(nowPage==null) {
	     			nowPage="1";
	     		} else if(cntPerPage==null) {
	     			cntPerPage="15";
	     		}
	     		
	     		dto=new PageDTO(cri,total,Integer.parseInt(nowPage),Integer.parseInt(cntPerPage));
	     		mo.addAttribute("page1",dto);
	     		mo.addAttribute("page2",cri);
	     		mo.addAttribute("bpage1",sb.poppage(dto));
	     		
	     		return "poppage";
	     	}
	     	
	     	//검색
	     	@RequestMapping(value = "/search")
	     	public String searching(HttpServletRequest request, PageDTO dto, Model mo, Criteria cri)
	     	{
	     		HttpSession session = request.getSession();
		    	  ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
		    	  String m_id = (String)session.getAttribute("m_id");
			 		int alarm_count = sm.alarmcount(m_id);
			        session.setAttribute("alarm_count", alarm_count);
	     		
	     		ArrayList<Board> list = new ArrayList<Board>();
	     		String sname = request.getParameter("sname");
	     		String keyword = request.getParameter("keyword");
	     		String nowPage=request.getParameter("nowPage");
	     		String cntPerPage=request.getParameter("cntPerPage");
	     		
	     		ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
	     		/*
	     		 * 
	     		if(sname.equals("b_title"))
	     		{
	     			list = sb.titlesearch(keyword);
	     		} else {
	     			list = sb.contentsearch(keyword);
	     		}
	     		mo.addAttribute("list",list);
	     		 */
	     		
	     		int total = sb.searchcnt(keyword);
	     		
	     		if(nowPage == null && cntPerPage == null) {
	     			nowPage="1";
	     			cntPerPage="15";
	     		} else if(nowPage==null) {
	     			nowPage="1";
	     		} else if(cntPerPage==null) {
	     			cntPerPage="15";
	     		}
	     		
	     		
	     		dto=new PageDTO(cri,total,Integer.parseInt(nowPage),Integer.parseInt(cntPerPage),keyword);
	     		mo.addAttribute("page",dto);
	     		mo.addAttribute("page2",cri);
	     		mo.addAttribute("keyword", keyword);
	     		mo.addAttribute("paging",sb.tsearchpage(dto));
	     		
	     		return "search";
	     	}

}
