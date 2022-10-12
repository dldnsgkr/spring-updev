package com.updev.board;

import java.text.DateFormat;
import java.text.ParseException;
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

/**
 * Handles requests for the application home page.
 * 
 * 
 */
@Controller
public class BoardController {
	
	@Autowired
	SqlSession sqlsession;
	
	//시작페이지
	
	@RequestMapping(value = "/")
	public String ko1(HttpServletRequest request,Model mo)
	{
		HttpSession session = request.getSession();
		
		String auto_login = String.valueOf(session.getAttribute("auto_login"));
		String a = (String)session.getAttribute("member_nick");
		System.out.println(auto_login+a);
		if(auto_login.equals("null") || auto_login.equals("0")) {
			session.setAttribute("auto_login", "0");
		} else {
			session.setAttribute("auto_login", "1");
		}
		if (auto_login.equals("1"))
		{
			String m_id = (String)session.getAttribute("id");
			String m_pw = (String)session.getAttribute("pw");
			ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
			Signup d = sm.loginselect(m_id, m_pw);
			int alarm_count = sm.alarmcount(d.getM_nick());
			 session.setAttribute("auto_login",auto_login);
	         session.setAttribute("member", d);
	         session.setAttribute("id", m_id);
	         session.setAttribute("pw", m_pw);
	         session.setAttribute("loginState", true);
	         session.setAttribute("member_nick", d.getM_nick());
	  		session.setAttribute("alarm_count", alarm_count);
		} else {
		String loginbefore = "unknown";
		session.setAttribute("loginState", false);
		session.setAttribute("member_nick", loginbefore);
		
		}
		
		ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
		
 		ArrayList<Board> pmpage=sb.popmain();
 		ArrayList<Board> smpage=sb.sharemain();
 		ArrayList<Board> qmpage=sb.questionmain();
 		ArrayList<Board> wmpage=sb.worrymain();
 		ArrayList<Board> nmpage=sb.noticemain();
 		ArrayList<Board> ampage=sb.qnamain();
 		
 		mo.addAttribute("popmpage",pmpage);
 		mo.addAttribute("sharempage",smpage);
 		mo.addAttribute("questionmpage",qmpage);
 		mo.addAttribute("worrympage",wmpage);
 		mo.addAttribute("noticempage",nmpage);
 		mo.addAttribute("qnampage",ampage);

		return "main";
	}
	
	//로고 클릭시 이동할 페이지
	@RequestMapping(value = "/index")
	public String index(Model mo,HttpServletRequest request)
	{	
 		HttpSession session = request.getSession();
 		ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
 		String member_nick = (String)session.getAttribute("member_nick");
 		int alarm_count = sm.alarmcount(member_nick);
        session.setAttribute("alarm_count", alarm_count);
        ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
 		ArrayList<Board> pmpage=sb.popmain();
 		ArrayList<Board> smpage=sb.sharemain();
 		ArrayList<Board> qmpage=sb.questionmain();
 		ArrayList<Board> wmpage=sb.worrymain();
 		ArrayList<Board> nmpage=sb.noticemain();
 		ArrayList<Board> ampage=sb.qnamain();
 		
 		mo.addAttribute("popmpage",pmpage);
 		mo.addAttribute("sharempage",smpage);
 		mo.addAttribute("questionmpage",qmpage);
 		mo.addAttribute("worrympage",wmpage);
 		mo.addAttribute("noticempage",nmpage);
 		mo.addAttribute("qnampage",ampage);
 		
		return "main";
	}
	
	
	//회원 마이페이지로 이동
	   @RequestMapping(value = "/myp")
	   public String ko8(Model mo,HttpServletRequest request)
	   {
		   HttpSession session = request.getSession();
		   ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
	 		String member_nick = (String)session.getAttribute("member_nick");
	 		int alarm_count = sm.alarmcount(member_nick);
	        session.setAttribute("alarm_count", alarm_count);
		   ServiceBoard ss = sqlsession.getMapper(ServiceBoard.class);
		   ArrayList<Board> dto = ss.mewrite(member_nick);
		   mo.addAttribute("list",dto);
		  return "mypage"; 
	   }
	   
	 //글 수정 작성
	      @RequestMapping(value = "/writeupdatecheck")
	      public String ko10(HttpServletRequest request,Model mo)
	      {
	    	  HttpSession session = request.getSession();
	    	  ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
		 		String member_nick = (String)session.getAttribute("member_nick");
		 		int alarm_count = sm.alarmcount(member_nick);
		        session.setAttribute("alarm_count", alarm_count);
		        
	    	 String b_kind = request.getParameter("b_kind");
	         int b_num = Integer.parseInt(request.getParameter("b_num"));
	         ServiceBoard ss = sqlsession.getMapper(ServiceBoard.class);
	         Board dao = ss.updatecheck(b_num);
	         mo.addAttribute("list",dao);
	         mo.addAttribute("b_kind",b_kind);
	         return "boardupdate";
	      }
	      
	      //글수정
	      @RequestMapping(value = "/writeupdate")
	      public String ko9(MultipartHttpServletRequest mul,HttpServletRequest request)
	      {
	    	  HttpSession session = request.getSession();
	    	  ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
		 		String member_nick = (String)session.getAttribute("member_nick");
		 		int alarm_count = sm.alarmcount(member_nick);
		        session.setAttribute("alarm_count", alarm_count);
	    	  
	         int b_num = Integer.parseInt(mul.getParameter("b_num"));
	         String b_cate = mul.getParameter("b_cate");
	         String b_kind = mul.getParameter("b_kind");
	         String b_title = mul.getParameter("b_title");
	         String m_nick = mul.getParameter("m_nick");
	         String b_content = mul.getParameter("b_content");
	         System.out.println(b_content);
	         MultipartFile f1 = mul.getFile("b_file1");
	         MultipartFile f2 = mul.getFile("b_file2");
	         String b_file1 = f1.getOriginalFilename();
	         String b_file2 = f2.getOriginalFilename();
	         ServiceBoard ss = sqlsession.getMapper(ServiceBoard.class);
	         ss.boardupdate(b_num,b_cate,b_kind,b_title,m_nick,b_content,b_file1,b_file2);

	         
	         return "redirect:ajaxmywrite";

	      }
	      
	      //글 작성 폼
	      @RequestMapping(value = "/write")
	      public String ko1(RedirectAttributes rattr,HttpServletRequest request,Model mo)
	      {
	    	  HttpSession session=request.getSession();
	    	  if((Boolean) session.getAttribute("loginState"))
				{
		    	  ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
			 		String member_nick = (String)session.getAttribute("member_nick");
			 		int alarm_count = sm.alarmcount(member_nick);
			        session.setAttribute("alarm_count", alarm_count);
	    		  
	    		  String b_kind = request.getParameter("b_kind");
	    		  session.setAttribute("b_kind", b_kind);
	    		  return "boardwrite";
				}
				else
				{
					rattr.addAttribute("result", "loginfail");
					return "redirect:login";
					
	     		}
	      }
	      
	      //글 작성
	      @RequestMapping(method = RequestMethod.POST,value = "/writesave")
	         public String ko2(MultipartHttpServletRequest mul,HttpServletRequest request)
	         {
	    	  HttpSession session = request.getSession();
	    	  ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
		 		String member_nick = (String)session.getAttribute("member_nick");
		 		int alarm_count = sm.alarmcount(member_nick);
		        session.setAttribute("alarm_count", alarm_count);
	    	  
	            String b_cate = mul.getParameter("b_cate");
	            String b_kind = mul.getParameter("b_kind");
	            String b_title = mul.getParameter("b_title");
	            String m_nick = mul.getParameter("m_nick");
	            String b_content = mul.getParameter("b_content");
	            MultipartFile f1 = mul.getFile("b_file1");
	            MultipartFile f2 = mul.getFile("b_file2");
	            String b_file1 = f1.getOriginalFilename();
	            String b_file2 = f2.getOriginalFilename();
	            ServiceBoard ss = sqlsession.getMapper(ServiceBoard.class);
	            ss.writesave(b_cate,b_kind,b_title,m_nick,b_content,b_file1,b_file2);
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
		      public String ko11(HttpServletRequest request,Model mo)
		      {
	        	 HttpSession session = request.getSession();
		    	  ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
			 		String member_nick = (String)session.getAttribute("member_nick");
			 		int alarm_count = sm.alarmcount(member_nick);
			        session.setAttribute("alarm_count", alarm_count);
	        	 
	        	 String b_kind = request.getParameter("b_kind");
		         int b_num = Integer.parseInt(request.getParameter("b_num"));
		         ServiceBoard ss = sqlsession.getMapper(ServiceBoard.class);
		         ss.delete(b_num);
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
	     		ServiceBoard ss = sqlsession.getMapper(ServiceBoard.class);
	     		ss.readcnt(num);
	     	}
	         
	         //알림 읽은 여부 체크
	         @RequestMapping(value = "/alarmcheck")
	         public ModelAndView alarm_read_check(HttpServletRequest request)
	         {
	             int b_num = Integer.parseInt(request.getParameter("b_num"));
	        	 int a_num = Integer.parseInt(request.getParameter("a_num"));
	        	 ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
	        	 sm.alarmchk(a_num);
	        	 ModelAndView mav = new ModelAndView();
	                 mav.addObject("b_num", b_num);
	                 mav.setViewName("redirect:detail");
	             
	             
	             return mav;
	         }
	         
	         
	         //게시물 detail
	         @RequestMapping(value = "/detail")
	         public String ko17(HttpServletRequest request,Model mo, PageDTO dto, Criteria cri)
	         {
		     	 HttpSession session = request.getSession();
		    	  ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
			 		String member_nick = (String)session.getAttribute("member_nick");
			 		int alarm_count = sm.alarmcount(member_nick);
			        session.setAttribute("alarm_count", alarm_count);
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
	        	 
	        	 Board member = sb.boarddetail(b_num);
	        	 Good good = sb.howgood(b_num,member_nick);
	        	 int total = sb.replytotal(b_num);
	        	 Scrap scrap = sb.howscrap(b_num,member_nick);
	        	 
	        	 
		    		
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
	        	 mo.addAttribute("list",member);
	        	 mo.addAttribute("llist",good);
	        	 mo.addAttribute("slist",scrap);
	        	 return "detailboard";
	         }
	         
	         public void Replycnt(int num) {
	     		ServiceBoard ss = sqlsession.getMapper(ServiceBoard.class);
	     		ss.replycnt(num);
	     	}
	         
	         //댓글
	         @RequestMapping(value = "/replysave")
	         public ModelAndView reply(HttpServletRequest request,Model mo) {
	        	 
	        	 HttpSession session = request.getSession();
		    	  ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
			 		String member_nick = (String)session.getAttribute("member_nick");
			 		int alarm_count = sm.alarmcount(member_nick);
			        session.setAttribute("alarm_count", alarm_count);
	        	 
	        	 int b_num=Integer.parseInt(request.getParameter("b_num"));
	        	 String m_nick=request.getParameter("m_nick");
	        	 String su_nick=request.getParameter("m_id");
	        	 System.out.println(su_nick);
	        	 String re_content=request.getParameter("re_content");
	        	 String b_kind = request.getParameter("b_kind");
	        	 String b_title = request.getParameter("b_title");
	        	 String a_content = b_kind+" 게시판 "+b_title+" 글에 "+m_nick+"님이 댓글을 달았습니다.";
	        	 int alarm_chk = 1;
	        	 int a_existence = 3;
	        	 ServiceBoard ss = sqlsession.getMapper(ServiceBoard.class);
	        	 
	        	 ss.replysave(b_num, m_nick, re_content);
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
			 		String member_nick = (String)session.getAttribute("member_nick");
			 		int alarm_count = sm.alarmcount(member_nick);
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
			 		String member_nick = (String)session.getAttribute("member_nick");
			 		int alarm_count = sm.alarmcount(member_nick);
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
			 		String member_nick = (String)session.getAttribute("member_nick");
			 		int alarm_count = sm.alarmcount(member_nick);
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
			 		String member_nick = (String)session.getAttribute("member_nick");
			 		int alarm_count = sm.alarmcount(member_nick);
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
			 		String member_nick = (String)session.getAttribute("member_nick");
			 		int alarm_count = sm.alarmcount(member_nick);
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
	     		ServiceBoard ss = sqlsession.getMapper(ServiceBoard.class);
	     		ss.likecntup(num);
	     	}
	     	
	     	//알림 생성
	     	public void createalarm(int num, String m_nick, String su_nick, String a_content, int alarm_chk, int a_existence)
	     	{
	     		ServiceBoard ss = sqlsession.getMapper(ServiceBoard.class);
	     		ss.goodalarm(num,m_nick,su_nick,a_content,alarm_chk,a_existence);
	     	}
	     	
	     	//좋아요 
	     	@RequestMapping(value = "/goodup",method = RequestMethod.POST)
	     	public String ko20(HttpServletRequest request,RedirectAttributes rattr)
	     	{
	     		HttpSession session=request.getSession();
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
				String su_nick=(String) jobj.get("m_id");//받는사람
				String b_title=(String) jobj.get("b_title");
				String b_kind=(String) jobj.get("b_kind");
				String a_content = b_kind+" 게시판 "+b_title+" 글에 "+m_nick+"님이 좋아요를 눌렀습니다.";
				int alarm_chk = 1;
				int a_existence = 1;
				ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
				int a = sb.howgo(b_num,m_nick,su_nick);
				if(a == 1)
				{
					sb.regood(b_num, m_nick);
				} else {
					sb.blikeup(b_num,m_nick,chk);
					createalarm(b_num,m_nick,su_nick,a_content,alarm_chk,a_existence);
				}
				likecntup(b_num);
				
	    		} catch (org.json.simple.parser.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return "redirect:index";
				}
				else
				{
					rattr.addAttribute("result", "loginfail");
					return "redirect:login";
				}
	     	}
	     	
	     	//좋아요 감소
	     	public void likecntdown(int num)
	     	{
	     		ServiceBoard ss = sqlsession.getMapper(ServiceBoard.class);
	     		ss.likecntdown(num);
	     	}
	     	
	     	//좋아요 취소
	     	@RequestMapping(value = "/gooddown",method = RequestMethod.POST)
	     	public String ko21(HttpServletRequest request,RedirectAttributes rattr)
	     	{
	     		HttpSession session=request.getSession();
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
				sb.blikedown(b_num,m_nick,like_chk);
				likecntdown(b_num);
	    		} catch (org.json.simple.parser.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return "redirect:index";
				}
				else
				{
					rattr.addAttribute("result", "loginfail");
					return "redirect:login";
				}
	     	}
	     	
	     	//스크랩
	     	@RequestMapping(value = "/scrap",method = RequestMethod.POST)
	     	public String ko23(HttpServletRequest request,RedirectAttributes rattr)
	     	{
	     		HttpSession session=request.getSession();
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
					String su_nick=(String) jobj.get("m_id");//받는사람
					String b_title=(String) jobj.get("b_title");
					String b_kind=(String) jobj.get("b_kind");
					String a_content = b_kind+" 게시판 "+b_title+" 글에 "+m_nick+"님이 스크랩을 했습니다.";
					int alarm_chk = 1;
					int a_existence = 2;
					ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
					
					int a = sb.howsc(b_num,m_nick,su_nick);
					if(a == 1)
					{
						sb.rescrap(b_num, m_nick);
					} else {
						sb.scrap(b_num,m_nick,chk);
						createalarm(b_num,m_nick,su_nick,a_content,alarm_chk,a_existence);
					}
					
	    			} catch (org.json.simple.parser.ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
	    			}
					return "redirect:index";
					
					
				}
				else
				{
					rattr.addAttribute("result", "loginfail");
					return "redirect:login";
					
	     		}
	     	}
	     	
	     	//스크랩 취소
	     	@RequestMapping(value = "/scrapcancel",method = RequestMethod.POST)
	     	public String ko22(HttpServletRequest request,RedirectAttributes rattr)
	     	{
	     		HttpSession session=request.getSession();
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
				sb.scrapcancel(b_num,m_nick,scrap_chk);
	    		} catch (org.json.simple.parser.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return "redirect:index";
				}
				else
				{
					rattr.addAttribute("result", "loginfail");
					return "redirect:login";
				}
	     	}
	     	
	     	//게시물 detail
	         @RequestMapping(value = "/detailajax")
	         public ModelAndView ko21(HttpServletRequest request,Model mo)
	         {
		     	 HttpSession session = request.getSession();
		     	
		    	  ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
			 		String member_nick = (String)session.getAttribute("member_nick");
			 		int alarm_count = sm.alarmcount(member_nick);
			        session.setAttribute("alarm_count", alarm_count);
	        	 int b_num = (int)session.getAttribute("b_num");
	        	 String su_nick = (String)session.getAttribute("su_nick");
	        	 ServiceBoard ss = sqlsession.getMapper(ServiceBoard.class);
	        	 /*
	        	 Board member = ss.boarddetail(b_num);
	        	 Good good = ss.howgood(b_num,nick);
	        	 Scrap scrap = ss.howscrap(b_num, nick);
	        	 mo.addAttribute("list",member);
	        	 mo.addAttribute("llist",good);
	        	 mo.addAttribute("slist",scrap);
	        	 */
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
			 		String member_nick = (String)session.getAttribute("member_nick");
			 		int alarm_count = sm.alarmcount(member_nick);
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
	     	public String search2(HttpServletRequest request, PageDTO dto, Model mo, Criteria cri)
	     	{
	     		HttpSession session = request.getSession();
		    	  ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
			 		String member_nick = (String)session.getAttribute("member_nick");
			 		int alarm_count = sm.alarmcount(member_nick);
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
