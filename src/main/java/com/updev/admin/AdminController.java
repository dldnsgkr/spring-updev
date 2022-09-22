package com.updev.admin;

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

import com.updev.member.Signup;

@Controller
public class AdminController {
	
	@Autowired
	SqlSession sqlsession;
	@RequestMapping(value = "/slick_slide")
	public String slick_slide(HttpServletRequest request){
		return "slick_slide";
	}
	@RequestMapping(value = "/admin")
	public String admin(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		String ses = (String)session.getAttribute("id");
		
		System.out.println(ses);
		return "admin";
	}
	// 정보수정 페이지 이동
	@RequestMapping(value = "/infoupdate")
	public String infoupdate(HttpServletRequest request, Model model){
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ServiceAdmin sa = sqlsession.getMapper(ServiceAdmin.class);
		
		Signup s = sa.infoupdate(id);
		model.addAttribute("admin", s);
		return "infoupdate";
	}
	// 정보수정
	@RequestMapping(value = "/info_update")
	public String info_update(HttpServletRequest request, Model model){
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");

		String jo=request.getParameter("jsoninfo");		
	                                              	JSONParser jsonparse = new JSONParser();
		
		try {
			JSONObject jobj = (JSONObject)jsonparse.parse(jo);
			String profile=(String) jobj.get("profile");
			String pw=(String) jobj.get("pw");
			String nick=(String) jobj.get("nick");
			String name=(String) jobj.get("name");
			String mail=(String) jobj.get("mail");
			String tel=(String) jobj.get("tel");
			
			ServiceAdmin sa = sqlsession.getMapper(ServiceAdmin.class);
			
			Signup s = sa.info_update(profile,pw,nick,name,mail,tel,id);
			model.addAttribute("admin", s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return "redirect:infoupdate";
	}
	@RequestMapping(value = "/mylist")
	public String mylist(HttpServletRequest request, Model model){
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ServiceAdmin sa = sqlsession.getMapper(ServiceAdmin.class);
		
		Signup s = sa.infoupdate(id);
		model.addAttribute("signup", s);
		return "mylist";
	}
	@RequestMapping(value = "/myalarm")
	public String myalarm(HttpServletRequest request, Model model){
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ServiceAdmin sa = sqlsession.getMapper(ServiceAdmin.class);
		
		Signup s = sa.infoupdate(id);
		model.addAttribute("signup", s);
		return "myalarm";
	}
	@RequestMapping(value = "/mymessage")
	public String mymessage(HttpServletRequest request, Model model){
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ServiceAdmin sa = sqlsession.getMapper(ServiceAdmin.class);
		
		Signup s = sa.infoupdate(id);
		model.addAttribute("signup", s);
		return "mymessage";
	}
}
