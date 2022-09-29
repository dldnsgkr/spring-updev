package com.updev.member;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MemberController {
	
	@Autowired
	SqlSession sqlsession;
	
	// ȸ������������ �̵�
	@RequestMapping(value = "/signup")
	public String member()
	{
		return "signup";
	}
	
	// �α��������� �̵�
		@RequestMapping(value = "/login")
		public String lo()
		{
			return "login";
		}

		//���̵�,��й�ȣ ã�� ������ �̵�
		@RequestMapping(value = "/findidpw")
		public String find()
		{
			return "find_idpw";
		}

		
	
	@RequestMapping(value = "/insert")
	   public String insert(HttpServletRequest request)//ȸ������ ����
	   {
	      String m_id = request.getParameter("m_id");
	      String m_pw = request.getParameter("m_pw");
	      String m_nick = request.getParameter("m_nick");
	      String m_name = request.getParameter("m_name");
	      String m_mail = request.getParameter("m_mail");
	      String m_tel = request.getParameter("m_tel");
	      String m_field = request.getParameter("m_field");
	      String m_grade = request.getParameter("m_grade");
	      ServiceMember ss = sqlsession.getMapper(ServiceMember.class);
	      ss.insert(m_id,m_pw,m_nick,m_name,m_mail,m_tel,m_field,m_grade);
	      return "redirect:index";
	   }
	   
	   @RequestMapping(value="/loginact", method = RequestMethod.POST)
	   public ModelAndView ko6(HttpServletRequest request , RedirectAttributes rattr) 
	   {//db�� ȸ�������� ���̵� ��й�ȣ�� �´��� Ȯ���ϴ°�(�α�����)
	      //������ ���� �ʴٸ� �α���â���� ����
	      ModelAndView mav=new ModelAndView();   
	      String m_id = request.getParameter("m_id");
	      String m_pw = request.getParameter("m_pw");
	      ServiceMember ss = sqlsession.getMapper(ServiceMember.class);
	      Signup d = ss.loginselect(m_id, m_pw);
	      if(d!=null) {
	         HttpSession session = request.getSession();
	         session.setAttribute("member", d);
	         session.setAttribute("id", m_id);
	         session.setAttribute("loginState", true);
	         session.setAttribute("id", m_id);
	         session.setMaxInactiveInterval(300);
	         mav.setViewName("redirect:index");
	      }
	      else {
	         rattr.addAttribute("check", "nodata");
	         mav.setViewName("redirect:signup");
	      }
	      return mav;
	   }
	   
	   @RequestMapping(value="/logout")
	   public String ko7(HttpServletRequest request) {
	         HttpSession session=request.getSession();
	         session.removeAttribute("member");
	         session.removeAttribute("loginState");
	         session.setAttribute("loginState",false);
	      return "redirect:index";
	   }
	   /*
	   //������ ���� üũ
	   @RequestMapping(value = "/proupdatecheck")
	   public String ko8(HttpServletRequest request,Model mo)
	   {
		   String m_nick = request.getParameter("m_nick");
		   	ServiceMember ss = sqlsession.getMapper(ServiceMember.class);
			Signup dao = ss.profileupdatecheck(m_nick);
			mo.addAttribute("list",dao);
		   return "memberinfoupdate";
	   }
	   
	  //������ ����
	   @RequestMapping(value = "/proupdate")
	   public String ko9(HttpServletRequest request,MultipartHttpServletRequest mul)
	   {
		   String up_nick = mul.getParameter("up_nick");
		   String m_nick = mul.getParameter("m_nick");
		   MultipartFile a = mul.getFile("m_profile");
		   String m_profile = a.getOriginalFilename();
		   String m_id = mul.getParameter("m_id");
		   String m_pw = mul.getParameter("m_pw");
		   String m_name = mul.getParameter("m_name");
		   String m_mail = mul.getParameter("m_mail");
		   String m_tel = mul.getParameter("m_tel");
		   String m_field = mul.getParameter("m_field");
		   	ServiceMember ss = sqlsession.getMapper(ServiceMember.class);
		   	ss.profileupdate(m_nick,m_profile,m_id,m_pw,m_name,m_mail,m_tel,m_field,up_nick);
		   ss.profileboardupdate(m_nick,up_nick);
		   ss.balupdate(m_nick,up_nick);
		   ss.suupdate(m_nick,up_nick);
		   ss.profilereportupdate(m_nick,up_nick);
		   ss.albalupdate(m_nick,up_nick);
		   ss.alsuupdate(m_nick,up_nick);
		   
		   	return "redirect:logout";
		   
	   }
	   */
	   //���̵� �ߺ��˻�
	   @RequestMapping(value = "/test", method = RequestMethod.GET, 
			   produces = "application/text; charset=utf8")
	   @ResponseBody
		public String test(HttpServletRequest request, Model model) throws UnsupportedEncodingException{
		   request.setCharacterEncoding("UTF-8");
		   String jo=request.getParameter("jsoninfo");	
		   JSONParser jsonparse = new JSONParser();
		   String msg = null;
		   try {
				JSONObject jobj = (JSONObject)jsonparse.parse(jo);
				String id=(String) jobj.get("id");
				
				ServiceMember sa = sqlsession.getMapper(ServiceMember.class);
				
				int s = sa.test(id);
				
				if (s!=0) {
					msg="������� ���̵� �Դϴ�. �ٽ� �Է� ���ּ���.";
				}
				
				System.out.println(msg);
//				model.addAttribute("test",s);
				model.addAttribute("msg",msg);
				
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
		   return msg;
	   }
				
	   //�г��� �ߺ��˻� 
	   @RequestMapping(value="/nicktest", method = RequestMethod.POST, produces = "application/text; charset=UTF-8")
	   @ResponseBody
	   public String nicktest(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		   request.setCharacterEncoding("utf-8");
		   String jo=request.getParameter("jsoninfo");
		   JSONParser jsonparse = new JSONParser();
		   String nickmsg = null;
		   String id =null;
		   try {
				JSONObject jobj = (JSONObject)jsonparse.parse(jo);
				String nick=(String) jobj.get("nick");
				
				ServiceMember sa = sqlsession.getMapper(ServiceMember.class);
				
				int s = sa.nicktest(nick);
				
				if (s!=0) {
					nickmsg="������� �г����Դϴ�. �ٽ� �Է� ���ּ���.";
				}
				
				System.out.println(nickmsg);
				model.addAttribute("nickmsg",nickmsg);
				
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return nickmsg;
	   }

	   @RequestMapping(value = "/find_id", method = RequestMethod.POST)
	   @ResponseBody
	   public String find_id(HttpServletRequest request)
	   {
		   
		   String jo = request.getParameter("jsoninfo");		
		   JSONParser jsonparse = new JSONParser();
		   
		   String id =null;
		   try {
			   JSONObject jobj = (JSONObject)jsonparse.parse(jo);
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
	   
	   @ResponseBody
	   @RequestMapping(value="/find_pw", method = RequestMethod.POST,
	         produces = "application/text; charset=UTF-8")
	   public String find_pw(HttpServletRequest request, Model model) throws UnsupportedEncodingException
	   {
		   int c= 0;
		   //String mpw =null;
		   String jo = request.getParameter("jsoninfo");	
		   request.setCharacterEncoding("utf-8");
		   JSONParser jsonparse = new JSONParser();
		   	try {
		
				JSONObject jobj = (JSONObject)jsonparse.parse(jo);
				String name=(String) jobj.get("name");
				String mail=(String) jobj.get("mail");
				
				ServiceMember sp = sqlsession.getMapper(ServiceMember.class);
				
				c = sp.find_pw(name, mail);
				
		   	}catch (Exception e) {
		   	}
				if (c==1) {
					return "����";
				}else {
					return "����";
				}
		
	   }

	   @ResponseBody
	   @RequestMapping(value="/find_mpw", method = RequestMethod.POST,
	   produces = "application/text; charset=UTF-8")
	   public String find_mpw(HttpServletRequest request, Model model) throws UnsupportedEncodingException
	   {
		   int p= 0;
		   String jo = request.getParameter("jsoninfo");	
		   request.setCharacterEncoding("utf-8");
		   JSONParser jsonparse = new JSONParser();
		   try {
			   
			   JSONObject jobj = (JSONObject)jsonparse.parse(jo);
			   String id=(String) jobj.get("id");
			   String pw=(String) jobj.get("pw");
			   
			   ServiceMember snp = sqlsession.getMapper(ServiceMember.class);
			   
			   p = snp.find_mpw(id, pw);
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
   
		   String jo = request.getParameter("jsoninfo");		
		   JSONParser jsonparse = new JSONParser();
		   
		   System.out.println(jo+"jsoninfo");
		   		   
			try {
			   JSONObject jobj = (JSONObject)jsonparse.parse(jo);
			   String pw=(String) jobj.get("pw");
			   String npw=(String) jobj.get("npw");
			   String nick=(String) jobj.get("nick");
			   
			   System.out.println(pw + "���� ���� ��й�ȣ1");
			   System.out.println(npw + "���� ���� ��й�ȣ13342");

			   ServiceMember snp = sqlsession.getMapper(ServiceMember.class);
			   
			   
			   snp.update_pw(pw, npw);
			   
		   } catch (ParseException e) {
			   e.printStackTrace();
		   }
			
	   }

}
	   

