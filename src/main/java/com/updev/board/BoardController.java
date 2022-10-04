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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.updev.member.ServiceMember;
import com.updev.member.Signup;

/**
 * Handles requests for the application home page.
 */
@Controller
public class BoardController {
	
	@Autowired
	SqlSession sqlsession;
	
	
	@RequestMapping(value = "/")
	public String ko1(HttpServletRequest request,Model mo)
	{
		String q = "unknown";
		HttpSession session = request.getSession();
		session.setAttribute("loginState", false);
		session.setAttribute("member_nick", q);
		ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
		
 		ArrayList<Board> pmpage=sb.popmain();
 		ArrayList<Board> smpage=sb.sharemain();
 		ArrayList<Board> qmpage=sb.questionmain();
 		ArrayList<Board> wmpage=sb.worrymain();
 		ArrayList<Board> nmpage=sb.noticemain();
 		ArrayList<Board> ampage=sb.qnamain();
 		
 		mo.addAttribute("popmpage",pmpage);
 		mo.addAttribute("popmpage",smpage);
 		mo.addAttribute("popmpage",qmpage);
 		mo.addAttribute("popmpage",wmpage);
 		mo.addAttribute("popmpage",nmpage);
 		mo.addAttribute("popmpage",ampage);

		return "main";
	}
	
	@RequestMapping(value = "/index")
	public String index(Model mo)
	{	
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
		   String nick = (String)session.getAttribute("member_nick");
		   ServiceBoard ss = sqlsession.getMapper(ServiceBoard.class);
		   ArrayList<Board> dto = ss.mewrite(nick);
		   mo.addAttribute("list",dto);
		  return "mypage"; 
	   }
	   
	 //글 수정 작성
	      @RequestMapping(value = "/writeupdatecheck")
	      public String ko10(HttpServletRequest request,Model mo)
	      {
	            int b_num = Integer.parseInt(request.getParameter("b_num"));
	            ServiceBoard ss = sqlsession.getMapper(ServiceBoard.class);
	         Board dao = ss.updatecheck(b_num);
	         mo.addAttribute("list",dao);
	         return "boardupdate";
	      }
	      
	      //글수정
	      @RequestMapping(value = "/writeupdate")
	      public String ko9(MultipartHttpServletRequest mul,HttpServletRequest request)
	      {
	         int b_num = Integer.parseInt(mul.getParameter("b_num"));
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
	         ss.boardupdate(b_num,b_cate,b_kind,b_title,m_nick,b_content,b_file1,b_file2);
	         return "redirect:index";
	      }
	      
	      //글 작성 폼
	      @RequestMapping(value = "/write")
	      public String ko1()
	      {
	         return "boardwrite";
	      }
	      //글 작성
	      @RequestMapping(method = RequestMethod.POST,value = "/writesave")
	         public String ko2(MultipartHttpServletRequest mul,HttpServletRequest request)
	         {
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
	            return "redirect:myp";
	         }
	      		//글 삭제
	         @RequestMapping(value = "/writedelete")
		      public String ko11(HttpServletRequest request,Model mo)
		      {
		            int b_num = Integer.parseInt(request.getParameter("b_num"));
		           ServiceBoard ss = sqlsession.getMapper(ServiceBoard.class);
		         ss.delete(b_num);
		         return "redirect:myp";
		      }
	         
	         //조회수
	         public void Readcnt(int num) {
	     		ServiceBoard ss = sqlsession.getMapper(ServiceBoard.class);
	     		ss.readcnt(num);
	     	}
	         
	         //게시물 detail
	         @RequestMapping(value = "/detail")
	         public String ko17(HttpServletRequest request,Model mo, PageDTO dto, Criteria cri)
	         {
		     	 HttpSession session = request.getSession();
	        	 String nick = (String)session.getAttribute("member_nick");
	        	 int b_num = Integer.parseInt(request.getParameter("b_num"));
	        	 String nowPage=request.getParameter("nowPage");
		    	 String cntPerPage=request.getParameter("cntPerPage");
		    		
	        	 session.setAttribute("b_num", b_num);
	        	 Readcnt(b_num);
	        	 ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
	        	 
	        	 Board member = sb.boarddetail(b_num);
	        	 Good good = sb.howgood(b_num,nick);
	        	 int total = sb.replytotal(b_num);
	        	 Scrap scrap = sb.howscrap(b_num,nick);
		    		
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
	         
	         //댓글
	         @RequestMapping(value = "/replysave")
	         public ModelAndView reply(HttpServletRequest request,Model mo) {
	        	 
	        	 int b_num=Integer.parseInt(request.getParameter("b_num"));
	        	 String m_nick=request.getParameter("m_nick");
	        	 String re_content=request.getParameter("re_content");
	        	 
	        	 ServiceBoard ss = sqlsession.getMapper(ServiceBoard.class);
	        	 
	        	 ss.replysave(b_num, m_nick, re_content);
	        	 
	             ModelAndView mav = new ModelAndView();
	             
	             if(b_num == 0) {
	                 mav.setViewName("redirect:index");
	             } else {
	                 mav.addObject("b_num", b_num);
	                 mav.setViewName("redirect:detail");
	             }
	             
	             return mav;
	         }
	         
	         
	        //페이징
	     	@RequestMapping(value="/noticepage")
	    	public String page1(HttpServletRequest request, PageDTO dto, Model mo, Criteria cri) {
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
	     	
	     	@RequestMapping(value="/sharepage")
	    	public String page2(HttpServletRequest request, PageDTO dto, Model mo, Criteria cri) {
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
	     	
	     	@RequestMapping(value="/questionpage")
	    	public String page3(HttpServletRequest request, PageDTO dto, Model mo, Criteria cri) {
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
	     	
	     	@RequestMapping(value="/worrypage")
	    	public String page4(HttpServletRequest request, PageDTO dto, Model mo, Criteria cri) {
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
	     	
	     	@RequestMapping(value="/qnapage")
	    	public String page5(HttpServletRequest request, PageDTO dto, Model mo, Criteria cri) {
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
				String m_nick=(String) jobj.get("m_nick");
				ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
				sb.blikeup(b_num,m_nick,chk);
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
	     		String jo=request.getParameter("jsoninfo");		
	    		JSONParser jsonparse = new JSONParser();
	    		JSONObject jobj;
	    		try {
					jobj = (JSONObject)jsonparse.parse(jo);
				int b_num=Integer.parseInt(String.valueOf(jobj.get("b_num")));
				String m_nick=(String) jobj.get("m_nick");
				ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
				sb.blikedown(b_num,m_nick);
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
				String m_nick=(String) jobj.get("m_nick");
				ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
				sb.scrap(b_num,m_nick,chk);
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
	     	
	     	//스크랩 취소
	     	@RequestMapping(value = "/scrapcancel",method = RequestMethod.POST)
	     	public String ko22(HttpServletRequest request,RedirectAttributes rattr)
	     	{
	     		HttpSession session=request.getSession();
				if((Boolean) session.getAttribute("loginState"))
				{
	     		String jo=request.getParameter("jsoninfo");		
	    		JSONParser jsonparse = new JSONParser();
	    		JSONObject jobj;
	    		try {
					jobj = (JSONObject)jsonparse.parse(jo);
				int b_num=Integer.parseInt(String.valueOf(jobj.get("b_num")));
				String m_nick=(String) jobj.get("m_nick");
				ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
				sb.scrapcancel(b_num,m_nick);
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
	     	
	     	//게시물 detail
	         @RequestMapping(value = "/detailajax")
	         public ModelAndView ko21(HttpServletRequest request,Model mo)
	         {
		     	 HttpSession session = request.getSession();
	        	 String nick = (String)session.getAttribute("member_nick");
	        	 int b_num = (int)session.getAttribute("b_num");
	        	 ServiceBoard ss = sqlsession.getMapper(ServiceBoard.class);
	        	 
	        	 Board member = ss.boarddetail(b_num);
	        	 Good good = ss.howgood(b_num,nick);
	        	 Scrap scrap = ss.howscrap(b_num, nick);
	        	 mo.addAttribute("list",member);
	        	 mo.addAttribute("llist",good);
	        	 mo.addAttribute("slist",scrap);
	        	 ModelAndView mav = new ModelAndView();
	        	 
	        	 if(b_num == 0) {
	                 mav.setViewName("redirect:index");
	             } else {
	                 mav.addObject("b_num", b_num);
	                 mav.setViewName("redirect:detail");
	             }
	             
	             return mav;
	        	 
	         }
	         
	         @RequestMapping(value = "/alarm")
		     	public String alarm() {
	        	 
		     		return "";
		     	}
	         
	     	@RequestMapping(value = "/hh")
	     	public String hh() {
	     		return "search";
	     	}


	     	


	     	@RequestMapping(value="/poppage")
	     	public String page6(HttpServletRequest request, PageDTO dto, Model mo, Criteria cri) {
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
	     	
	     	
	     	@RequestMapping(value = "/search")
	     	public String search2(HttpServletRequest request, PageDTO dto, Model mo, Criteria cri)
	     	{
	     		ArrayList<Board> list = new ArrayList<Board>();
	     		String sname = request.getParameter("sname");
	     		String keyword = request.getParameter("keyword");
	     		String nowPage=request.getParameter("nowPage");
	     		String cntPerPage=request.getParameter("cntPerPage");
	     		
	     		ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
	     		/*
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
