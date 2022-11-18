package com.updev.member;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.updev.board.Alarm;
import com.updev.board.Board;
import com.updev.board.ServiceBoard;

@Controller
public class MemberController {
	
	@Autowired
	SqlSession sqlsession;
	
	// ȸ������������ �̵�
	@RequestMapping(value = "/signup")
	public String signupform()
	{
		return "signup";
	}
	
	// �α��������� �̵�
		@RequestMapping(value = "/login")
		public String loginform()
		{
			return "login";
		}

		//���̵�,��й�ȣ ã�� ������ �̵�
		@RequestMapping(value = "/findidpw")
		public String find_idpw_form()
		{
			return "find_idpw";
		}

		
	//ȸ������
	@RequestMapping(value = "/member_info_insert")
	   public String member_info_insert(HttpServletRequest request)//ȸ������ ����
	   {
	      String m_id = request.getParameter("m_id");
	      String m_pw = request.getParameter("m_pw");
	      String m_nick = request.getParameter("m_nick");
	      String m_name = request.getParameter("m_name");
	      String m_mail = request.getParameter("m_mail");
	      String m_tel = request.getParameter("m_tel");
	      String m_field = request.getParameter("m_field");
	      String m_grade = request.getParameter("m_grade");
	      ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
	      sm.member_info_insert(m_id,m_pw,m_nick,m_name,m_mail,m_tel,m_field,m_grade);
	      return "redirect:index";
	   }
	   
	//�α��� ������
	@RequestMapping(value="/loginact", method = RequestMethod.POST)
	   public ModelAndView logincheck(HttpServletRequest request , RedirectAttributes rattr, HttpServletResponse response) 
	   {//db�� ȸ�������� ���̵� ��й�ȣ�� �´��� Ȯ���ϴ°�(�α�����)
	      //������ ���� �ʴٸ� �α���â���� ����
		HttpSession session = request.getSession();
		//���ó�¥ ���ϴ� ��
		  Date now = new Date();
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String formatedNow = formatter.format(now);
		  
	      ModelAndView mav=new ModelAndView();   
	      String m_id = request.getParameter("m_id");
	      String m_pw = request.getParameter("m_pw");
	      String autologin = request.getParameter("autologin");
	      ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
	      Signup signup = sm.login_checking(m_id, m_pw);
	      if(signup==null) {
	    	  //���̵� ��й�ȣ�� ��ġ�� �����Ͱ� ���ٸ�
	    	  rattr.addAttribute("check", "nodata");
	    	  mav.setViewName("redirect:login");
	      } else {
	    	  String grade = signup.getM_grade();//���
	    	  String outtime = signup.getM_outtime();
	    	  String date1 = outtime;//������ �α׾ƿ� �ð�
		      String date2 = formatedNow;//����ð�
		      
	    	  int result = date1.compareTo(date2);
	    	  /* ���� ���ڿ� ���� ���
		      if(result == 0)
		          System.out.println("������ ��¥");
		      else if (result < 0)
		          System.out.println("date1�� date2 ���� ��¥");
		      else
		          System.out.println("date1�� date2 ���� ��¥");
		          */
	    	  
	    	  if((grade.equals("ȸ��") || grade.equals("������")) && result < 0)
	    	  {//�α����� ȸ���� ���� ���� ����
	 	        session.setAttribute("member", signup);
	 	        session.setAttribute("m_id", m_id);
	 	        session.setAttribute("m_pw", m_pw);
	 	        session.setAttribute("loginState", true);
	 	        session.setAttribute("member_nick", signup.getM_nick());
	 	        
	 	        String id = (String)session.getAttribute("m_id");
		 		int alarm_count = sm.alarmcount(id);		        
		 		session.setAttribute("alarm_count", alarm_count);
		 		
		 		if(autologin != null) {
		 		Cookie cookie = new Cookie("m_id",id);
		 		cookie.setDomain("localhost");
		 		cookie.setPath("/");
		 		cookie.setMaxAge(60*60*24*7);
		 		response.addCookie(cookie);
		 		}
		 		mav.setViewName("redirect:index");
	    	  } else {
	    		  rattr.addAttribute("gradecheck", "badgrade");
		    	  mav.setViewName("redirect:index");
	    	  }
	      }

	      return mav;
	   }
	   
	//�α׾ƿ�
	   @RequestMapping(value="/logout")
	   public String logoutact(HttpServletRequest request,HttpServletResponse response) {

		   HttpSession session=request.getSession();
		   ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
		   String id = (String)session.getAttribute("m_id");
		   sm.outtime_update(id);//�α׾ƿ��� �ð�
		   
		   Cookie cookie = new Cookie("m_id", null);  // ��Ű ���� null�� ����
		   cookie.setMaxAge(0);  // ���� ����ð��� 0���� ����
		   cookie.setPath("/");
		   response.addCookie(cookie);
		   
		   
		   //���� ����,������
		   String loginbefore = "unknown";
		   String loginbeforeid = "unknown";
	         session.removeAttribute("member");
	         session.removeAttribute("loginState");
	         session.removeAttribute("m_id");
	         session.removeAttribute("m_pw");
	         session.removeAttribute("member_nick");
	         session.removeAttribute("auto_login");
	         session.removeAttribute("alarm_count");
	         session.setAttribute("loginState",false);
	         session.setAttribute("member_nick", loginbefore);
	         session.setAttribute("m_id", loginbeforeid);
	         
	      return "redirect:index";
	   }
	   
	   
	  //������ ����
	   @RequestMapping(value = "/proupdate")
	   public String profile_update(HttpServletRequest request)
	   {
		   HttpSession session = request.getSession();
		   
		   //���� ���ε� �� �˸�
	    	  ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
	    	  String id = (String)session.getAttribute("m_id");
		 		int alarm_count = sm.alarmcount(id);		        
		 		session.setAttribute("alarm_count", alarm_count);
		   
		   String up_nick = request.getParameter("up_nick");//���� �ٲ� �г���
		   String m_nick = request.getParameter("m_nick");//Ű���� �� �г���(���� �г���)
		   String m_id = request.getParameter("m_id");
		   String m_pw = request.getParameter("m_pw");
		   String m_name = request.getParameter("m_name");
		   String m_mail = request.getParameter("m_mail");
		   String m_tel = request.getParameter("m_tel");
		   String m_field = request.getParameter("m_field");
		   
		   //���� �г��Ӱ� �ٸ����̺� �г����� �����Ѱ͵��� �ٲ� �г������� ���δ� ���� 
		   sm.profileupdate(m_nick,m_id,m_pw,m_name,m_mail,m_tel,m_field,up_nick);
		   sm.profileboardupdate(m_nick,up_nick);
		   sm.profilereportupdate(m_nick,up_nick);
		   sm.albalupdate(m_nick,up_nick);
		   sm.alsuupdate(m_nick,up_nick);
		   
		   	return "redirect:logout";
		   
	   }
	   
	   //ȸ������ ���̵� �ߺ��˻�
	   @RequestMapping(value = "/id_duplicate_test", method = RequestMethod.GET, 
			   produces = "application/text; charset=UTF-8")
	   @ResponseBody
		public String test(HttpServletRequest request, Model model) throws UnsupportedEncodingException{
		   request.setCharacterEncoding("UTF-8");
		   String jsoninfo=request.getParameter("jsoninfo");
		   JSONParser jsonparse = new JSONParser();
		   String id_availability = null;
		   try {
				JSONObject jobj = (JSONObject)jsonparse.parse(jsoninfo);
				String id=(String) jobj.get("id");
				
				ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
				
				int s = sm.id_duplicate_test(id);
				
				if (s!=0) {
					id_availability="������� ���̵� �Դϴ�. �ٽ� �Է� ���ּ���.";
				}
				
				System.out.println(id_availability);
//				model.addAttribute("test",s);
				model.addAttribute("id_availability",id_availability);
				
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
		   return id_availability;
	   }
				
	   //ȸ������ �г��� �ߺ��˻� 
	   @RequestMapping(value="/nickname_duplicate_test", method = RequestMethod.POST, produces = "application/text; charset=UTF-8")
	   @ResponseBody
	   public String nicktest(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		   request.setCharacterEncoding("utf-8");
		   String jo=request.getParameter("jsoninfo");
		   JSONParser jsonparse = new JSONParser();
		   String nickname__availability = null;
		   try {
				JSONObject jobj = (JSONObject)jsonparse.parse(jo);
				String nick=(String) jobj.get("nick");
				
				ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
				
				int s = sm.nickname_duplicate_test(nick);
				
				if (s!=0) {
					nickname__availability=" ������� �г����Դϴ�. �ٽ� �Է� ���ּ���.";
				}
				
				System.out.println(nickname__availability);
				model.addAttribute("nickname__availability",nickname__availability);
				
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return nickname__availability;
	   }
	   
	 //������ ���� �г��� �ߺ�üũ
	   @RequestMapping(value="/nicktest2", method = RequestMethod.POST, produces = "application/text; charset=UTF-8")
	   @ResponseBody
	   public String nicktest2(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		   System.out.println(11111);
		   request.setCharacterEncoding("utf-8");
		   String jsoninfo=request.getParameter("jsoninfo");
		   JSONParser jsonparse = new JSONParser();
		   String nickmsg = null;
		   try {
				JSONObject jobj = (JSONObject)jsonparse.parse(jsoninfo);
				String nick=(String) jobj.get("up_nick");
				
				ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
				
				int s = sm.nicktest2(nick);
				
				if (s!=0) {
					nickmsg=" *������� �г����Դϴ�. �ٽ� �Է� ���ּ���.";
				}
				
				
				
				System.out.println(nickmsg);
				model.addAttribute("nickmsg",nickmsg);
				
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return nickmsg;
	   }

	   //���̵� ã��
	   @RequestMapping(value = "/find_id", method = RequestMethod.POST)
	   @ResponseBody
	   public String find_id(HttpServletRequest request)
	   {
		   
		   String jsoninfo = request.getParameter("jsoninfo");		
		   JSONParser jsonparse = new JSONParser();
		   
		   String id =null;
		   try {
			   JSONObject jobj = (JSONObject)jsonparse.parse(jsoninfo);
			   String name=(String) jobj.get("name");
			   String mail=(String) jobj.get("mail");
			   
			   System.out.println(name + mail);
			   
			   ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
			   
			   id = sm.find_id(name, mail);
			   System.out.println(id+"���� ���̵�");
			   
		   }catch (Exception e) {
			   // TODO: handle exception
		   }
		   return id;
	   }
	   
	   //��й�ȣ ã��
	   @ResponseBody
	   @RequestMapping(value="/find_pw", method = RequestMethod.POST,
	         produces = "application/text; charset=UTF-8")
	   public String find_pw(HttpServletRequest request, Model model) throws UnsupportedEncodingException
	   {
		   int c= 0;
		   //String mpw =null;
		   String jsoninfo = request.getParameter("jsoninfo");	
		   request.setCharacterEncoding("utf-8");
		   JSONParser jsonparse = new JSONParser();
		   	try {
		
				JSONObject jobj = (JSONObject)jsonparse.parse(jsoninfo);
				String name=(String) jobj.get("name");
				String mail=(String) jobj.get("mail");
				
				ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
				
				c = sm.find_pw(name, mail);
				
		   	}catch (Exception e) {
		   	}
				if (c==1) {
					return "����";
				}else {
					return "����";
				}
		
	   }

	   //
	   @ResponseBody
	   @RequestMapping(value="/find_mpw", method = RequestMethod.POST,
	   produces = "application/text; charset=UTF-8")
	   public String find_mpw(HttpServletRequest request, Model model) throws UnsupportedEncodingException
	   {
		   int p= 0;
		   String jsoninfo = request.getParameter("jsoninfo");	
		   request.setCharacterEncoding("utf-8");
		   JSONParser jsonparse = new JSONParser();
		   try {
			   
			   JSONObject jobj = (JSONObject)jsonparse.parse(jsoninfo);
			   String id=(String) jobj.get("id");
			   String pw=(String) jobj.get("pw");
			   
			   ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
			   
			   p = sm.find_mpw(id, pw);
			   System.out.println(id+pw);
			   
		   }catch (Exception e) {
		   }
		   if (p==1) {
			   return "����";
		   }else {
			   return "����";
		   }
		   
	   }
	   
	   @ResponseBody
	   @RequestMapping(value = "/update_pw", method = RequestMethod.POST)
	   public void update_pw(HttpServletRequest request)
	   {
   
		   String jsoninfo = request.getParameter("jsoninfo");		
		   JSONParser jsonparse = new JSONParser();
		   
		   System.out.println(jsoninfo+"jsoninfo");
		   		   
			try {
			   JSONObject jobj = (JSONObject)jsonparse.parse(jsoninfo);
			   String pw=(String) jobj.get("pw");
			   String npw=(String) jobj.get("npw");
			   String nick=(String) jobj.get("nick");
			   
			   System.out.println(pw + "���� ���� ��й�ȣ1");
			   System.out.println(npw + "���� ���� ��й�ȣ13342");

			   ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
			   
			   
			   sm.update_pw(pw, npw);
			   
		   } catch (ParseException e) {
			   e.printStackTrace();
		   }
			
	   }
	   
	 //�Խù� �Ű�������
	 	@RequestMapping(value = "/boardreportpage")
	     public String board_reportpage(HttpServletRequest request,Model mo,RedirectAttributes rattr)
	     {
	 		//�α����� �Ǿ����� �ʴٸ� �α��������� ������ ���
	 		//�α����� �Ǿ��ִٸ� �Ű� ��������
	 		HttpSession session=request.getSession();
			if((Boolean) session.getAttribute("loginState"))
			{
		    	  ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
		    	  String m_id = (String)session.getAttribute("m_id");
			 		int alarm_count = sm.alarmcount(m_id);			        
			 		session.setAttribute("alarm_count", alarm_count);	
			 
			 		//�⺻ �ڷ�
	    	 int b_num = Integer.parseInt(request.getParameter("b_num"));
	    	 String b_title = request.getParameter("b_title");
	    	 mo.addAttribute("b_num",b_num);
	    	 mo.addAttribute("b_title",b_title);
	    	 return "boardreport";
			}
			else
			{
				//�α����� �ȵǾ��ִٸ� �α��� ��������
				rattr.addAttribute("result", "loginfail");
				return "redirect:login";
			}
	     }
	 	
	 	//�Խù� �Ű�
	 	@RequestMapping(value = "/breport")
	 	public String board_reportact(MultipartHttpServletRequest mul,HttpServletRequest request)
	 	{
	 		String r_status = "ó����";
	 		String r__reason = mul.getParameter("r_reason");//��Ÿ ���� ������
	 		String otherreason = mul.getParameter("otherreason");//��Ÿ ���Ե�����
	 		String r_reason = "";
	 		MultipartFile f = mul.getFile("r_file1");
	        String r_file1 = f.getOriginalFilename();
	        
	        //��Ÿ�� �����ߴٸ� b��ü�� �ִ� ��Ÿ �ۼ������͸� r_reason ��ü�� �ִ´�
	        if(r__reason.equals("etc"))
	        {
	        	r_reason = otherreason;
	        } else {
	        	r_reason = r__reason;
	        }
	        
	 		int b_num = Integer.parseInt(request.getParameter("b_num"));
	 		 ServiceMember ss = sqlsession.getMapper(ServiceMember.class);
	    	 ss.report_insert(r_status,r_reason,r_file1,b_num);//�Ű��� ���� insert
	    	 ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
	    	 sb.report_board_update(b_num);//�Խñ� �Ű�Ƚ��
	 		return "redirect:index";
	 	}
	 	
	 	//������ ���� üũ
		   @RequestMapping(value = "/proupdatecheck")
		   public String profile(HttpServletRequest request,Model mo)
		   {
			   HttpSession session = request.getSession();
			   
		    	  ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
			 		String m_id = (String)session.getAttribute("m_id");
			 		int alarm_count = sm.alarmcount(m_id);			       
			 		session.setAttribute("alarm_count", alarm_count);
			 		
			 		//����������
				Signup signup = sm.profile_update_check(m_id);
				mo.addAttribute("list",signup);
			   return "memberinfoupdate";
		   }
	   
	 	//������ ���� ajax
	 	@ResponseBody
		@RequestMapping( value = "/myinfoupdate", method = RequestMethod.GET)
		public String profile_update_ajax(HttpServletRequest request, RedirectAttributes rattr) throws IOException
		{
			String jo=request.getParameter("jsoninfo");
			JSONParser jsonparse = new JSONParser();
			JSONObject jobj;
			try {
				jobj = (JSONObject)jsonparse.parse(jo);
				
				System.out.println(jobj);
				String m_nick=(String) jobj.get("m_nick");
				String m_id=(String) jobj.get("m_id");
				String up_nick=(String) jobj.get("up_nick");
				String m_pw=(String) jobj.get("m_pw");
				String m_name=(String) jobj.get("m_name");
				String m_mail=(String) jobj.get("m_mail");
				String m_tel=(String) jobj.get("m_tel");
				String m_field=(String) jobj.get("m_field");
				System.out.println(m_field);
				ServiceMember ss=sqlsession.getMapper(ServiceMember.class);
				
				//���� �г��Ӱ� �ٸ����̺� �г����� �����Ѱ͵��� �ٲ� �г������� ���δ� ���� 
				ss.profileupdate(m_nick,m_id,m_pw,m_name,m_mail,m_tel,m_field,up_nick);
				ss.profileboardupdate(m_nick,up_nick);
				ss.profilereportupdate(m_nick,up_nick);
				ss.albalupdate(m_nick,up_nick);
				ss.alsuupdate(m_nick,up_nick);
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
					
			return "index";
		}
	 	//���� �� �� 
		   @RequestMapping(value = "/ajaxmywrite")
		   public String mywrite_ajax(HttpServletRequest request,Model mo)
		   {
			   HttpSession session = request.getSession();
			   
		    	  ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
			 		String m_id = (String)session.getAttribute("m_id");
			 		int alarm_count = sm.alarmcount(m_id);
			 		session.setAttribute("alarm_count", alarm_count);
			 		
			 		String member_nick = (String)session.getAttribute("member_nick");
			   ArrayList<Board> dao = sm.ajaxmywrite(member_nick);
			   mo.addAttribute("list",dao);
			   return "memwrite";
		   }
		   



		 //���� ���ƿ��� ��
		   @RequestMapping(value = "/ajaxmygood")
		   public String my_like_ajax(HttpServletRequest request,Model mo)

		   {
			   HttpSession session = request.getSession();
		    	  ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
			 		String m_id = (String)session.getAttribute("m_id");
			 		int alarm_count = sm.alarmcount(m_id);
			        session.setAttribute("alarm_count", alarm_count);
			        
			        String member_nick = (String)session.getAttribute("member_nick");
				ArrayList<Board> dao = sm.ajaxmygood(member_nick);
				mo.addAttribute("list",dao);
			   return "memgood";
		   }
		   

		   
		 //���� ��ũ�� �� 
		   @RequestMapping(value = "/ajaxmyscrap")
		   public String my_scrap_ajax(HttpServletRequest request,Model mo)
		   {
			   HttpSession session = request.getSession();
		    	  ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
			 		String m_id = (String)session.getAttribute("m_id");
			 		int alarm_count = sm.alarmcount(m_id);
			        session.setAttribute("alarm_count", alarm_count);
			        
			        String member_nick = (String)session.getAttribute("member_nick");
				ArrayList<Board> dao = sm.ajaxmyscrap(member_nick);
				mo.addAttribute("list",dao);
			   return "memscrap";
		   }
		   
		   //���� �˸�
		   @RequestMapping(value = "/alarm")
	     	public String my_alarm_ajax(HttpServletRequest request,Model mo) {
			   HttpSession session = request.getSession();
			   
		    	  ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
			 		String m_id = (String)session.getAttribute("m_id");
			 		int alarm_count = sm.alarmcount(m_id);
			        session.setAttribute("alarm_count", alarm_count);
			        
				ArrayList<Alarm> alarm = sm.ajaxmyalarm(m_id);
				mo.addAttribute("list",alarm);
	     		return "alarm";
	     	}

}
	   

