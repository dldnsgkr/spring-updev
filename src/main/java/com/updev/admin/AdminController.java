package com.updev.admin;

import java.io.IOException;
import java.util.ArrayList;

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

import com.updev.board.Board;
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
	/**/
	// 정보수정 페이지 이동
	@RequestMapping(value = "/infoupdate")
	public String infoupdate(HttpServletRequest request, Model model){
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		//System.out.println(nick+"nick");
		ServiceAdmin sa = sqlsession.getMapper(ServiceAdmin.class);
		
		Signup s = sa.infoupdate(id);
		
		session.setAttribute("m_nick", s.getM_nick());
		
		
		model.addAttribute("admin", s);
		return "infoupdate";
	}
	// 정보수정
	@RequestMapping(value = "/info_update", method = RequestMethod.POST)
	public String info_update(HttpServletRequest request, Model model) throws Exception{
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");

		String jo = request.getParameter("jsoninfo");		
		JSONParser jsonparse = new JSONParser();
		
		try {
			JSONObject jobj = (JSONObject)jsonparse.parse(jo);
			String pw=(String) jobj.get("pw");
			String nick=(String) jobj.get("nick");
			String name=(String) jobj.get("name");
			String mail=(String) jobj.get("mail");
			String tel=(String) jobj.get("tel");
			String field=(String) jobj.get("field");
			
			ServiceAdmin sa = sqlsession.getMapper(ServiceAdmin.class);
			
			sa.info_update(pw,nick,name,mail,tel,field,id);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return "redirect:infoupdate";
	}
	/**/
	// 마이 글 페이지 이동
	@RequestMapping(value = "/mylist")
	public String mylist(HttpServletRequest request, Model model){
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
	
		return "mylist";
	}
	/*
	@RequestMapping(value = "/mywrite")
	public String mylist_write(HttpServletRequest request, Model model){
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ServiceAdmin sa = sqlsession.getMapper(ServiceAdmin.class);
		
		//Signup s = sa.mylist_write(id);
		//model.addAttribute("signup", s);
		return "mylist";
	}
	*/
	
	
	
	// 내가 쓴 글 조회
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/mywrite", method = RequestMethod.POST,
			produces = "application/text; charset=UTF-8")//불러오기
	public String mywrite(HttpServletRequest request,HttpServletResponse response,Model mo) throws IOException{
			
			HttpSession session = request.getSession();
			
			
			String m_nick = (String)session.getAttribute("m_nick");
			
			System.out.println(m_nick);
			JSONArray array = new JSONArray();
			JSONObject total = new JSONObject();
			ServiceAdmin ss= sqlsession.getMapper(ServiceAdmin.class);
			ArrayList<Board> list=ss.mywrite(m_nick);
			
//			int b_num, String b_cate, String b_kind, String b_title, String m_nick, String b_wdate,
//	         String b_content, int b_likecnt, int b_readcnt, int b_group, int b_step, int b_indent, String b_tag,
//	         String b_file1, String b_file2, int b_report) {
			
			for(int i=0;i<list.size();i++) {
				JSONObject member = new JSONObject();
				int b_num =list.get(i).getB_num();
				String b_cate =list.get(i).getB_cate();
				String b_kind =list.get(i).getB_kind();
				String b_title =list.get(i).getB_title();
				String nick =list.get(i).getM_nick();
				String b_wdate =list.get(i).getB_wdate();
				String b_content =list.get(i).getB_content();
				int b_likecnt =list.get(i).getB_likecnt();
				int b_readcnt =list.get(i).getB_readcnt();
				int b_group =list.get(i).getB_group();
				int b_step =list.get(i).getB_step();
				int b_indent =list.get(i).getB_indent();
				String b_tag =list.get(i).getB_tag();
				String b_file1 =list.get(i).getB_file1();
				String b_file2 =list.get(i).getB_file2();
				int b_report =list.get(i).getB_report();
				member.put("b_num", b_num);
				member.put("b_cate", b_cate);
				member.put("b_kind", b_kind);
				member.put("b_title", b_title);
				member.put("m_nick", nick);
				member.put("b_wdate", b_wdate);
				member.put("b_content", b_content);
				member.put("b_likecnt", b_likecnt);
				member.put("b_readcnt", b_readcnt);
				member.put("b_group", b_group);
				member.put("b_step", b_step);
				member.put("b_indent", b_indent);
				member.put("b_tag", b_tag);
				member.put("b_file1", b_file1);
				member.put("b_file2", b_file2);
				member.put("b_report", b_report);
				array.add(member);				
			}
			total.put("members", array);
			String jsoninfo = total.toJSONString();
			System.out.println(jsoninfo);
		return jsoninfo;
	
	}
	
	// 내가 좋아요 한 글 조회
		@SuppressWarnings("unchecked")
		@ResponseBody
		@RequestMapping(value="/mylike", method = RequestMethod.POST,
				produces = "application/text; charset=UTF-8")//불러오기
		public String mylike(HttpServletRequest request,HttpServletResponse response,Model mo) throws IOException{
				
				HttpSession session = request.getSession();
				
				
				String m_nick = (String)session.getAttribute("m_nick");
				
				System.out.println(m_nick);
				JSONArray array = new JSONArray();
				JSONObject total = new JSONObject();
				ServiceAdmin ss= sqlsession.getMapper(ServiceAdmin.class);
				ArrayList<Board> list=ss.mylike(m_nick);
				
//				int b_num, String b_cate, String b_kind, String b_title, String m_nick, String b_wdate,
//		         String b_content, int b_likecnt, int b_readcnt, int b_group, int b_step, int b_indent, String b_tag,
//		         String b_file1, String b_file2, int b_report) {
				
				for(int i=0;i<list.size();i++) {
					JSONObject member = new JSONObject();
					int b_num =list.get(i).getB_num();
					String b_cate =list.get(i).getB_cate();
					String b_kind =list.get(i).getB_kind();
					String b_title =list.get(i).getB_title();
					String nick =list.get(i).getM_nick();
					String b_wdate =list.get(i).getB_wdate();
					String b_content =list.get(i).getB_content();
					int b_likecnt =list.get(i).getB_likecnt();
					int b_readcnt =list.get(i).getB_readcnt();
					int b_group =list.get(i).getB_group();
					int b_step =list.get(i).getB_step();
					int b_indent =list.get(i).getB_indent();
					String b_tag =list.get(i).getB_tag();
					String b_file1 =list.get(i).getB_file1();
					String b_file2 =list.get(i).getB_file2();
					int b_report =list.get(i).getB_report();
					member.put("b_num", b_num);
					member.put("b_cate", b_cate);
					member.put("b_kind", b_kind);
					member.put("b_title", b_title);
					member.put("m_nick", nick);
					member.put("b_wdate", b_wdate);
					member.put("b_content", b_content);
					member.put("b_likecnt", b_likecnt);
					member.put("b_readcnt", b_readcnt);
					member.put("b_group", b_group);
					member.put("b_step", b_step);
					member.put("b_indent", b_indent);
					member.put("b_tag", b_tag);
					member.put("b_file1", b_file1);
					member.put("b_file2", b_file2);
					member.put("b_report", b_report);
					array.add(member);				
				}
				total.put("members", array);
				String jsoninfo = total.toJSONString();
				System.out.println(jsoninfo);
			return jsoninfo;
		
		}
		// 내가 스크랩 한 글 조회
		@SuppressWarnings("unchecked")
		@ResponseBody
		@RequestMapping(value="/myscrap", method = RequestMethod.POST,
				produces = "application/text; charset=UTF-8")//불러오기
		public String myscrap(HttpServletRequest request,HttpServletResponse response,Model mo) throws IOException{
				
				HttpSession session = request.getSession();
				
				String m_nick = (String)session.getAttribute("m_nick");
				
				System.out.println(m_nick);
				JSONArray array = new JSONArray();
				JSONObject total = new JSONObject();
				ServiceAdmin ss= sqlsession.getMapper(ServiceAdmin.class);
				ArrayList<Board> list=ss.myscrap(m_nick);
				
//				int b_num, String b_cate, String b_kind, String b_title, String m_nick, String b_wdate,
//		         String b_content, int b_likecnt, int b_readcnt, int b_group, int b_step, int b_indent, String b_tag,
//		         String b_file1, String b_file2, int b_report) {
				
				for(int i=0;i<list.size();i++) {
					JSONObject member = new JSONObject();
					int b_num =list.get(i).getB_num();
					String b_cate =list.get(i).getB_cate();
					String b_kind =list.get(i).getB_kind();
					String b_title =list.get(i).getB_title();
					String nick =list.get(i).getM_nick();
					String b_wdate =list.get(i).getB_wdate();
					String b_content =list.get(i).getB_content();
					int b_likecnt =list.get(i).getB_likecnt();
					int b_readcnt =list.get(i).getB_readcnt();
					int b_group =list.get(i).getB_group();
					int b_step =list.get(i).getB_step();
					int b_indent =list.get(i).getB_indent();
					String b_tag =list.get(i).getB_tag();
					String b_file1 =list.get(i).getB_file1();
					String b_file2 =list.get(i).getB_file2();
					int b_report =list.get(i).getB_report();
					member.put("b_num", b_num);
					member.put("b_cate", b_cate);
					member.put("b_kind", b_kind);
					member.put("b_title", b_title);
					member.put("m_nick", nick);
					member.put("b_wdate", b_wdate);
					member.put("b_content", b_content);
					member.put("b_likecnt", b_likecnt);
					member.put("b_readcnt", b_readcnt);
					member.put("b_group", b_group);
					member.put("b_step", b_step);
					member.put("b_indent", b_indent);
					member.put("b_tag", b_tag);
					member.put("b_file1", b_file1);
					member.put("b_file2", b_file2);
					member.put("b_report", b_report);
					array.add(member);				
				}
				total.put("members", array);
				String jsoninfo = total.toJSONString();
				System.out.println(jsoninfo);
			return jsoninfo;
		
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
	
	/**/
	@RequestMapping(value = "/boardmanage")
	public String boardmanage(HttpServletRequest request, Model model){
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ServiceAdmin sa = sqlsession.getMapper(ServiceAdmin.class);
		
		//Signup s = sa.infoupdate(id);
		return "boardmanage";
	}
}
