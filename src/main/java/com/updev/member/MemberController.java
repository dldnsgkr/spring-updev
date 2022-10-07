package com.updev.member;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
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

import com.updev.board.Board;
import com.updev.board.ServiceBoard;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MemberController {
	
	@Autowired
	SqlSession sqlsession;
	
	// 회원가입폼으로 이동
	@RequestMapping(value = "/signup")
	public String member()
	{
		return "signup";
	}
	
	// 로그인폼으로 이동
		@RequestMapping(value = "/login")
		public String lo()
		{
			return "login";
		}

		//아이디,비밀번호 찾기 폼으로 이동
		@RequestMapping(value = "/findidpw")
		public String find()
		{
			return "find_idpw";
		}

		
	//회원가입
	@RequestMapping(value = "/insert")
	   public String insert(HttpServletRequest request)//회원가입 저장
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
	      sm.insert(m_id,m_pw,m_nick,m_name,m_mail,m_tel,m_field,m_grade);
	      return "redirect:index";
	   }
	   
	//로그인 저장기능
	@RequestMapping(value="/loginact", method = RequestMethod.POST)
	   public ModelAndView ko6(HttpServletRequest request , RedirectAttributes rattr) 
	   {//db에 회원가입한 아이디 비밀번호가 맞는지 확인하는곳(로그인중)
	      //정보가 맞지 않다면 로그인창으로 보냄
		HttpSession session = request.getSession();
		  Date now = new Date();
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String formatedNow = formatter.format(now);
	      ModelAndView mav=new ModelAndView();   
	      String m_id = request.getParameter("m_id");
	      String m_pw = request.getParameter("m_pw");
	      String auto_login = request.getParameter("auto_login");
	      if(auto_login == null)
	      {
	    	  auto_login = "0";
	      } else {
	    	  auto_login = "1";
	      }
	      ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
	      Signup d = sm.loginselect(m_id, m_pw);
	      if(d==null) {
	    	  //아이디 비밀번호가 일치한 데이터가 없다면
	    	  rattr.addAttribute("check", "nodata");
	    	  mav.setViewName("redirect:login");
	      } else {
	    	  
	    	  String grade = d.getM_grade();
	    	  String outtime = d.getM_outtime();
	    	  String date1 = outtime;
		      String date2 = formatedNow;
	    	  int result = date1.compareTo(date2);
	    	  /*
		      if(result == 0)
		          System.out.println("동일한 날짜");
		      else if (result < 0)
		          System.out.println("date1은 date2 이전 날짜");
		      else
		          System.out.println("date1은 date2 이후 날짜");
		          */
	    	  if((grade.equals("회원") || grade.equals("관리자")) && result < 0)
	    	  {//로그인한 회원에 관해 세션 정의
	    		 session.setAttribute("auto_login",auto_login);
	 	         session.setAttribute("member", d);
	 	         session.setAttribute("id", m_id);
	 	         session.setAttribute("pw", m_pw);
	 	         session.setAttribute("loginState", true);
	 	         session.setAttribute("member_nick", d.getM_nick());
<<<<<<< HEAD
=======
	 	         //session.setMaxInactiveInterval(30);
>>>>>>> 219000830c9be5ac72c97eef624291724606fcf0
	 	         mav.setViewName("redirect:index");

	    	  } else {
	    		  rattr.addAttribute("gradecheck", "badgrade");
		    	  mav.setViewName("redirect:index");
	    	  }
	      }

	      return mav;
	   }
	   
	//로그아웃
	   @RequestMapping(value="/logout")
	   public String ko7(HttpServletRequest request) {

		   HttpSession session=request.getSession();
		   ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
		   String id = (String)session.getAttribute("id");
		   sm.outtimeupdate(id);//로그아웃한 시간
		   
		   //세션 삭제,재정의
		   String loginbefore = "unknown";
	         session.removeAttribute("member");
	         session.removeAttribute("loginState");
	         session.removeAttribute("id");
	         session.removeAttribute("pw");
	         session.removeAttribute("member_nick");
	         session.removeAttribute("auto_login");
	         session.setAttribute("loginState",false);
	         session.setAttribute("member_nick", loginbefore);
	         session.setAttribute("auto_login",0);
	         
	      return "redirect:index";
	   }
	   
	   
	  //프로필 수정
	   @RequestMapping(value = "/proupdate")
	   public String ko9(HttpServletRequest request)
	   {
		   String up_nick = request.getParameter("up_nick");//새로 바꾼 닉네임
		   String m_nick = request.getParameter("m_nick");//키값이 될 닉네임(기존 닉네임)
		   String m_id = request.getParameter("m_id");
		   String m_pw = request.getParameter("m_pw");
		   String m_name = request.getParameter("m_name");
		   String m_mail = request.getParameter("m_mail");
		   String m_tel = request.getParameter("m_tel");
		   String m_field = request.getParameter("m_field");
		   ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
		   sm.profileupdate(m_nick,m_id,m_pw,m_name,m_mail,m_tel,m_field,up_nick);
		   sm.profileboardupdate(m_nick,up_nick);
		   sm.balupdate(m_nick,up_nick);
		   sm.suupdate(m_nick,up_nick);
		   sm.profilereportupdate(m_nick,up_nick);
		   sm.albalupdate(m_nick,up_nick);
		   sm.alsuupdate(m_nick,up_nick);
		   
		   	return "redirect:logout";
		   
	   }
	   
	   //아이디 중복검사
	   @RequestMapping(value = "/idtest", method = RequestMethod.GET, 
			   produces = "application/text; charset=utf8")
	   @ResponseBody
		public String test(HttpServletRequest request, Model model) throws UnsupportedEncodingException{
		   request.setCharacterEncoding("UTF-8");
		   String jsoninfo=request.getParameter("jsoninfo");
		   JSONParser jsonparse = new JSONParser();
		   String msg = null;
		   try {
				JSONObject jobj = (JSONObject)jsonparse.parse(jsoninfo);
				String id=(String) jobj.get("id");
				
				ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
				
				int s = sm.idtest(id);
				
				if (s!=0) {
					msg="사용중인 아이디 입니다. 다시 입력 해주세요.";
				}
				
				System.out.println(msg);
//				model.addAttribute("test",s);
				model.addAttribute("msg",msg);
				
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
		   return msg;
	   }
				
	   //닉네임 중복검사 
	   @RequestMapping(value="/nicktest", method = RequestMethod.POST, produces = "application/text; charset=UTF-8")
	   @ResponseBody
	   public String nicktest(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		   request.setCharacterEncoding("utf-8");
		   String jo=request.getParameter("jsoninfo");
		   JSONParser jsonparse = new JSONParser();
		   String nickmsg = null;
		   try {
				JSONObject jobj = (JSONObject)jsonparse.parse(jo);
				String nick=(String) jobj.get("nick");
				
				ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
				
				int s = sm.nicktest(nick);
				
				if (s!=0) {
					nickmsg=" *사용중인 닉네임입니다. 다시 입력 해주세요.";
				}
				
				System.out.println(nickmsg);
				model.addAttribute("nickmsg",nickmsg);
				
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return nickmsg;
	   }
	   
	 //프로필 수정 닉네임 중복체크
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
					nickmsg=" *사용중인 닉네임입니다. 다시 입력 해주세요.";
				}
				
				System.out.println(nickmsg);
				model.addAttribute("nickmsg",nickmsg);
				
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return nickmsg;
	   }

	   //아이디 찾기
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
			   System.out.println(id+"나는 아이디");
			   
		   }catch (Exception e) {
			   // TODO: handle exception
		   }
		   return id;
	   }
	   
	   //비밀번호 찾기
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
					return "있음";
				}else {
					return "없음";
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
			   return "있음";
		   }else {
			   return "없음";
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
			   
			   System.out.println(pw + "나는 기존 비밀번호1");
			   System.out.println(npw + "나는 변경 비밀번호13342");

			   ServiceMember sm = sqlsession.getMapper(ServiceMember.class);
			   
			   
			   sm.update_pw(pw, npw);
			   
		   } catch (ParseException e) {
			   e.printStackTrace();
		   }
			
	   }
	   
	 //게시물 신고페이지
	 	@RequestMapping(value = "/boardreportpage")
	     public String ko1(HttpServletRequest request,Model mo,RedirectAttributes rattr)
	     {
	 		//로그인이 되어있지 않다면 로그인쪽으로 보내는 기능
	 		//로그인이 되어있다면 신고 페이지로
	 		HttpSession session=request.getSession();
			if((Boolean) session.getAttribute("loginState"))
			{
	    	 int b_num = Integer.parseInt(request.getParameter("b_num"));
	    	 String b_title = request.getParameter("b_title");
	    	 mo.addAttribute("b_num",b_num);
	    	 mo.addAttribute("b_title",b_title);
	    	 return "boardreport";
			}
			else
			{
				rattr.addAttribute("result", "loginfail");
				return "redirect:login";
			}
	     }
	 	
	 	//게시물 신고
	 	@RequestMapping(value = "/breport")
	 	public String ko2(MultipartHttpServletRequest mul,HttpServletRequest request)
	 	{
	 		String r_status = "처리전";
	 		String a = mul.getParameter("r_reason");//기타 제외 데이터
	 		String b = mul.getParameter("otherreason");//기타 기입데이터
	 		String r_reason = "";
	 		MultipartFile f = mul.getFile("r_file1");
	        String r_file1 = f.getOriginalFilename();
	        
	        //기타를 선택했다면 b객체에 있는 기타 작성데이터를 r_reason 객체에 넣는다
	        if(a.equals("etc"))
	        {
	        	r_reason = b;
	        	
	        	
	        } else {
	        	r_reason = a;
	        }
	 		int b_num = Integer.parseInt(request.getParameter("b_num"));
	 		 ServiceMember ss = sqlsession.getMapper(ServiceMember.class);
	    	 ss.reportinsert(r_status,r_reason,r_file1,b_num);
	    	 ServiceBoard sb = sqlsession.getMapper(ServiceBoard.class);
	    	 sb.reportboardupdate(b_num);
	 		return "redirect:index";
	 	}
	 	
	 	//프로필 수정 체크
		   @RequestMapping(value = "/proupdatecheck")
		   public String ko8(HttpServletRequest request,Model mo)
		   {
			   HttpSession session = request.getSession();
			   String id = (String)session.getAttribute("id");
			   	ServiceMember ss = sqlsession.getMapper(ServiceMember.class);
				Signup dao = ss.profileupdatecheck(id);
				mo.addAttribute("list",dao);
			   return "memberinfoupdate";
		   }
	   
	 	//프로필 수정 ajax
	 	@ResponseBody
		@RequestMapping( value = "/myinfoupdate", method = RequestMethod.GET)
		public String jjoinsave(HttpServletRequest request, RedirectAttributes rattr) throws IOException
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
				
				ss.profileupdate(m_nick,m_id,m_pw,m_name,m_mail,m_tel,m_field,up_nick);
				ss.profileboardupdate(m_nick,up_nick);
				ss.balupdate(m_nick,up_nick);
				ss.suupdate(m_nick,up_nick);
				ss.profilereportupdate(m_nick,up_nick);
				ss.albalupdate(m_nick,up_nick);
				ss.alsuupdate(m_nick,up_nick);
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
					
			return "index";
		}
	 	//내가 쓴 글 
		   @RequestMapping(value = "/ajaxmywrite")
		   public String ko5(HttpServletRequest request,Model mo)
		   {
			   HttpSession session = request.getSession();
			   String nick = (String)session.getAttribute("member_nick");
			 
			   ServiceMember ss = sqlsession.getMapper(ServiceMember.class);
			   ArrayList<Board> dao = ss.ajaxmywrite(nick);
			   mo.addAttribute("list",dao);
			   return "memwrite";
		   }
		   



		 //내가 좋아요한 글
		   @RequestMapping(value = "/ajaxmygood")
		   public String ko6(HttpServletRequest request,Model mo)

		   {
			   HttpSession session = request.getSession();
			   String nick = (String)session.getAttribute("member_nick");
			 
			   	ServiceMember ss = sqlsession.getMapper(ServiceMember.class);
				ArrayList<Board> dao = ss.ajaxmygood(nick);
				mo.addAttribute("list",dao);
			   return "memgood";
		   }
		   

		   
		 //내가 스크랩 글 
		   @RequestMapping(value = "/ajaxmyscrap")
		   public String ko7(HttpServletRequest request,Model mo)
		   {
			   HttpSession session = request.getSession();
			   String nick = (String)session.getAttribute("member_nick");
			 
			   	ServiceMember ss = sqlsession.getMapper(ServiceMember.class);
				ArrayList<Board> dao = ss.ajaxmyscrap(nick);
				mo.addAttribute("list",dao);
			   return "memscrap";
		   }

}
	   

