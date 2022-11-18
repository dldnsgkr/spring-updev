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
	
	//����������
	
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
		      String grade = signup.getM_grade();//���
	    	  String outtime = signup.getM_outtime();
	    	  String date1 = outtime;//������ �α׾ƿ� �ð�
		      String date2 = formatedNow;//����ð�
		      
	    	  int result = date1.compareTo(date2);
		      if((grade.equals("ȸ��") || grade.equals("������")) && result < 0)
	    	  {//�α����� ȸ���� ���� ���� ����
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
		//��ȸ���� �⺻����
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
	
	//�ΰ� Ŭ���� �̵��� ������
	@RequestMapping(value = "/index")
	public String indexpage(Model mo,HttpServletRequest request)
	{	
 		HttpSession session = request.getSession();
 		ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
 		
 		//�α����� �Ѱ�� �ű� �˸��� ������ Ȥ�� ����� �����Ҷ� �Ź� count�ȴ�
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
	
	
	//�α����� �г����� �̿��� ȸ�� ������������ �̵�
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
	   
	 //�� ���� �ۼ�
	      @RequestMapping(value = "/writeupdatecheck")
	      public String updatecheck_mywriteporm(HttpServletRequest request,Model mo)
	      {
	    	  HttpSession session = request.getSession();
	    	  
	    	  ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
		 		String m_id = (String)session.getAttribute("m_id");
		 		int alarm_count = sm.alarmcount(m_id);
		 		session.setAttribute("alarm_count", alarm_count);
		 		
		     //�����Ϸ��� �ۿ� b_kind�� �Ź� �°� �ѷ��ֱ� ����
	    	 String b_kind = request.getParameter("b_kind");
	    	 
	    	 //�Ѱܹ��� ��ñ� ������ȣ�� ������ ���� ã��
	         int b_num = Integer.parseInt(request.getParameter("b_num"));
	         ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
	         Board board = sb.board_update_check(b_num);
	         mo.addAttribute("list",board);
	         mo.addAttribute("b_kind",b_kind);
	         
	         return "boardupdate";
	      }
	      
	      //�ۼ���
	      @RequestMapping(value = "/writeupdate")
	      public String update_mywrite(MultipartHttpServletRequest mul,HttpServletRequest request)
	      {
	    	 HttpSession session = request.getSession();
	    	  
	    	 ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
	    	 String m_id = (String)session.getAttribute("m_id");
	    	 int alarm_count = sm.alarmcount(m_id);
	    	 session.setAttribute("alarm_count", alarm_count);
	    	 
	    	 //���� ������ �ֱ⿡ multipart���
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
	      
	      //�� �ۼ� ��
	      @RequestMapping(value = "/write")
	      public String writeporm(RedirectAttributes rattr,HttpServletRequest request,Model mo)
	      {
	    	  HttpSession session=request.getSession();
	    	  //�α����� �ߴ�������
	    	  if((Boolean) session.getAttribute("loginState"))
				{
		    	  ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
		    	  String m_id = (String)session.getAttribute("m_id");
		    	  int alarm_count = sm.alarmcount(m_id);
			      session.setAttribute("alarm_count", alarm_count);
	    		  
			      //�۾��� �ϴ� �Խñ��� b_kind�� �޾� �°� �ѷ���
	    		  String b_kind = request.getParameter("b_kind");
	    		  session.setAttribute("b_kind", b_kind);
	    		  return "boardwrite";
				}
				else
				{
					//�α����� �����ʾҴٸ� �α���â����
					rattr.addAttribute("loginresult", "loginfail");
					return "redirect:login";
					
	     		}
	      }
	      
	      //�� �ۼ�
	      @RequestMapping(method = RequestMethod.POST,value = "/writesave")
	         public String write(MultipartHttpServletRequest mul,HttpServletRequest request)
	         {
	    	  HttpSession session = request.getSession();
	    	  
	    	  ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
	    	  String m_id = (String)session.getAttribute("m_id");
	    	  int alarm_count = sm.alarmcount(m_id);
		      session.setAttribute("alarm_count", alarm_count);
	    	  
		    //���� ��ϵ� �ֱ⿡ multipart���
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
	            
	            //�� �ۼ��� ������ �ۼ��� ���� b_kind�� �´� �������� ����
	            if(b_kind.equals("����"))
		         {
		        	 return "redirect:noticepage";
		         } else if(b_kind.equals("��������")) {
		        	 return "redirect:sharepage";
		         } else if(b_kind.equals("��λ���")) {
		        	 return "redirect:worrypage";
		         } else if(b_kind.equals("������")) {
		        	 return "redirect:questionpage";
		         } else if(b_kind.equals("QNA")) {
		        	 return "redirect:qnapage";
		         } else {
		        	 return "redirect:ajaxmywrite";
		         }
	         }
	      		//�� ����
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
		         
		       //�� ������ ������ �ۼ��� ���� b_kind�� �´� �������� ����
		         if(b_kind.equals("����"))
		         {
		        	 return "redirect:noticepage";
		         } else if(b_kind.equals("��������")) {
		        	 return "redirect:sharepage";
		         } else if(b_kind.equals("��λ���")) {
		        	 return "redirect:worrypage";
		         } else if(b_kind.equals("������")) {
		        	 return "redirect:questionpage";
		         } else if(b_kind.equals("QNA")) {
		        	 return "redirect:qnapage";
		         } else {
		        	 return "redirect:ajaxmywrite";
		         }
		      }

	         
	         //��ȸ��
	         public void Readcnt(int num) {
	     		ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
	     		sb.readcnt(num);
	     	}
	         
	         //�˸� ���� ���� üũ
	         @RequestMapping(value = "/alarmcheck")
	         public ModelAndView alarm_read_check(HttpServletRequest request)
	         {
	             int b_num = Integer.parseInt(request.getParameter("b_num"));
	        	 int a_num = Integer.parseInt(request.getParameter("a_num"));
	        	 ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
	        	 sm.alarmchk(a_num);
	        	 
	        	 //b_num�� detail�� ���� ���� ������� �ϱ⶧���� ModelAndView�� ���
	        	 ModelAndView mav = new ModelAndView();
	                 mav.addObject("b_num", b_num);
	                 mav.setViewName("redirect:detail");
	             
	             
	             return mav;
	         }
	         
	         
	         //�Խù� detail
	         @RequestMapping(value = "/detail")
	         public String detailporm(HttpServletRequest request,Model mo, PageDTO dto, Criteria cri)
	         {
		     	 HttpSession session = request.getSession();
		    	  ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
			 		
			 		String m_id = (String)session.getAttribute("m_id");
			 		int alarm_count = sm.alarmcount(m_id);
			        session.setAttribute("alarm_count", alarm_count);
			        
			     String member_nick = (String)session.getAttribute("member_nick");
			     
			     //���ƿ� Ȥ�� ��ũ���� ������ ���ư��°� �ƴ϶� 
			     //�� ������ Ȥ�� �˸����� detail �������� ������ ��ȸ���� �����ϰԲ� ����
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
	        	 
	        	 //detail ��ȣ�� �´� �Խñ��� �����´�
	        	 Board board = sb.boarddetail(b_num);
	        	 
	        	 //
	        	 String board_nick = board.getM_nick();
	        	 Signup signup = sb.boarddetailid(board_nick);
	        	 
	        	 //�ش� �α����� ����� �ش�Խñۿ� ���ƿ並 �������� Ȯ��
	        	 Good good = sb.how_many_good(b_num,member_nick);
	        	 
	        	 
	        	 int total = sb.replytotal(b_num);
	        	 
	        	//�ش� �α����� ����� �ش�Խñۿ� ���ƿ並 �������� Ȯ��
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
	         
	         //���
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
	        	 String a_content = b_kind+" �Խ��� "+b_title+" �ۿ� "+m_nick+"���� ����� �޾ҽ��ϴ�.";
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
	         
	         
	        //��������¡
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
	     	
	     	//�������� ����¡
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
	     	
	     	
	     	//������ ����¡
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
	     	
	     	//��λ��� ����¡
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
	     	
	     	//Q&A ����¡
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

	    	
	     	
	     	//���ƿ� ����
	     	public void likecntup(int num)
	     	{
	     		ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
	     		sb.likecntup(num);
	     	}
	     	
	     	//�˸� ����
	     	public void createalarm(int num, String m_nick, String m_id, String a_content, int alarm_chk, int a_existence)
	     	{
	     		ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
	     		sb.makealarm(num,m_nick,m_id,a_content,alarm_chk,a_existence);
	     	}
	     	
	     	//���ƿ� 
	     	@RequestMapping(value = "/goodup",method = RequestMethod.POST)
	     	public String good(HttpServletRequest request,RedirectAttributes rattr)
	     	{
	     		HttpSession session=request.getSession();
	     		
	     		//�α��ο���
				if((Boolean) session.getAttribute("loginState"))
				{
	     		int chk = 1;
	     		String jo=request.getParameter("jsoninfo");		
	    		JSONParser jsonparse = new JSONParser();
	    		JSONObject jobj;
	    		try {
					jobj = (JSONObject)jsonparse.parse(jo);
				int b_num=Integer.parseInt(String.valueOf(jobj.get("b_num")));
				String m_nick=(String) jobj.get("m_nick");//�α��� �ϰ� ����� �����ѻ��
				String m_id=(String) jobj.get("m_id");//�޴»��
				String b_title=(String) jobj.get("b_title");
				String b_kind=(String) jobj.get("b_kind");
				String a_content = b_kind+" �Խ��� "+b_title+" �ۿ� "+m_nick+"���� ���ƿ並 �������ϴ�.";
				int alarm_chk = 1;
				int a_existence = 1;
				ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
				
				//�ش� �Խñۿ� �α����� ����� ���ƿ並 ���� ������ִ��� count
				int a = sb.are_you_chkgood(b_num,m_nick);
				if(a == 1)
				{
					//���� �̹� �����ͺ��̽��� �ش� ���ǿ� �´� �����Ͱ� �ִٸ� like_chk�� 1�� �ٲ۴�
					sb.regood(b_num, m_nick);
				} else {
					//���� �����ͺ��̽��� insert�Ѵ�
					sb.likeup(b_num,m_nick,chk);
					createalarm(b_num,m_nick,m_id,a_content,alarm_chk,a_existence);
				}
				//���ƿ�� 1����
				likecntup(b_num);
				
	    		} catch (org.json.simple.parser.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return "redirect:index";
				}
				else
				{//�α����� �����ʾҴٸ� �α���â����
					rattr.addAttribute("result", "loginfail");
					return "redirect:login";
				}
	     	}
	     	
	     	//���ƿ� ����
	     	public void likecntdown(int num)
	     	{
	     		ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
	     		sb.likecntdown(num);
	     	}
	     	
	     	//���ƿ� ���
	     	@RequestMapping(value = "/gooddown",method = RequestMethod.POST)
	     	public String good_cancel(HttpServletRequest request,RedirectAttributes rattr)
	     	{
	     		HttpSession session=request.getSession();
	     		//�α��ο���
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
				
				//������ �ִ� �����Ϳ� like_chk�� 0���� �����Ѵ�
				sb.likedown(b_num,m_nick,like_chk);
				
				//���ƿ�� 1����
				likecntdown(b_num);
	    		} catch (org.json.simple.parser.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return "redirect:index";
				}
				else
				{//�α����� �����ʾҴٸ� �α���â����
					rattr.addAttribute("result", "loginfail");
					return "redirect:login";
				}
	     	}
	     	
	     	//��ũ��
	     	@RequestMapping(value = "/scrap",method = RequestMethod.POST)
	     	public String scrap(HttpServletRequest request,RedirectAttributes rattr)
	     	{
	     		HttpSession session=request.getSession();
	     		
	     		//�α��ο���
				if((Boolean) session.getAttribute("loginState"))
				{
	     		int chk = 1;
	     		String jo=request.getParameter("jsoninfo");		
	    		JSONParser jsonparse = new JSONParser();
	    		JSONObject jobj;
	    		try {
	    			jobj = (JSONObject)jsonparse.parse(jo);
	    			int b_num=Integer.parseInt(String.valueOf(jobj.get("b_num")));
					String m_nick=(String) jobj.get("m_nick");//�α��� �ϰ� ����� �����ѻ��
					String m_id=(String) jobj.get("m_id");//�޴»��
					String b_title=(String) jobj.get("b_title");
					String b_kind=(String) jobj.get("b_kind");
					String a_content = b_kind+" �Խ��� "+b_title+" �ۿ� "+m_nick+"���� ��ũ���� �߽��ϴ�.";
					int alarm_chk = 1;
					int a_existence = 2;
					ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
					
					//�ش� �Խñۿ� �α����� ����� ��ũ���� ���� ������ִ��� count
					int a = sb.are_you_chkcrap(b_num,m_nick);
					if(a == 1)
					{
						//���� �̹� �����ͺ��̽��� �ش� ���ǿ� �´� �����Ͱ� �ִٸ� crap_chk�� 1�� �ٲ۴�
						sb.rescrap(b_num, m_nick);
					} else {
						//���� �����ͺ��̽��� insert�Ѵ�
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
				{//�α����� �����ʾҴٸ� �α���â����
					rattr.addAttribute("result", "loginfail");
					return "redirect:login";
					
	     		}
	     	}
	     	
	     	//��ũ�� ���
	     	@RequestMapping(value = "/scrapcancel",method = RequestMethod.POST)
	     	public String scrap_cancel(HttpServletRequest request,RedirectAttributes rattr)
	     	{
	     		HttpSession session=request.getSession();
	     		
	     		//�α��ο���
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
				
				//������ �ִ� �����Ϳ� like_chk�� 0���� �����Ѵ�
				sb.scrapcancel(b_num,m_nick,scrap_chk);
	    		} catch (org.json.simple.parser.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return "redirect:index";
				}
				else
				{//�α����� �����ʾҴٸ� �α���â����
					rattr.addAttribute("result", "loginfail");
					return "redirect:login";
				}
	     	}
	     	
	     	// ���� detail�� ������ ����ġ�� ����
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
	        	
	        	//b_num�� 0�̶��(������ �����ϴ°��� �Ұ���)
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
	        
	         

	     	//�α�� ����¡
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
	     	
	     	//�˻�
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
