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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.updev.board.Board;
import com.updev.board.ServiceBoard;
import com.updev.member.Signup;

@Controller
public class AdminController {
	
	@Autowired
	SqlSession sqlsession;
	
	/*������ �̵�*/
	// ������ ���������� �̵�
	@RequestMapping(value = "/admin_mypage")
	public String admin_mypage(HttpServletRequest request){
		// ���� ����
		HttpSession session = request.getSession();
		// ���ǿ��� ������ id ������
		String admin_id = (String)session.getAttribute("id"); // admin
		
		// ������ id�� ���� ���� - ������ ������ �������� ����ҰŶ�
		session.setAttribute("admin_id", admin_id); // admin
		
		// ���ǿ��� ������ nick ������
		String admin_nick = (String)session.getAttribute("member_nick"); // ������
		
		// ������ nick�� ���� ���� - ������ ������ �������� ����ҰŶ�
		session.setAttribute("admin_nick", admin_nick); // ������
		
		return "admin_mypage";
	}
	// ���������� - �������� ������ �̵�
	@RequestMapping(value = "/admin_infoupdate")
	public String admin_infoupdate(HttpServletRequest request, Model model){
		// ���� ����
		HttpSession session = request.getSession();
		// ���ǿ��� ������ id ������
		String admin_id = (String)session.getAttribute("admin_id"); // admin
		
		ServiceAdmin sa = sqlsession.getMapper(ServiceAdmin.class);
		
		Signup s = sa.admin_infoupdate_select(admin_id);
		
		model.addAttribute("admin", s);
		
		return "admin_infoupdate";
	}
	// ���������� - ���� �� ������ �̵�
	@RequestMapping(value = "/admin_mylist")
	public String admin_mylist(HttpServletRequest request, Model model){
		// ���� ����
		HttpSession session = request.getSession();
		// ���ǿ��� ������ nick ������
		String admin_nick = (String)session.getAttribute("admin_nick"); // ������
				
		ServiceAdmin sa = sqlsession.getMapper(ServiceAdmin.class);
		ArrayList<Board> list = sa.admin_mywrite_select(admin_nick);
		
		model.addAttribute("list", list);
		
		return "admin_mylist";
	}
	// ���������� - ���� �˶� ������ �̵�
	@RequestMapping(value = "/admin_myalarm")
	public String admin_myalarm(HttpServletRequest request, Model model){
		return "admin_myalarm";
	}
	// �Խ��ǰ��� - ���� �Խ��� ���� ������ �̵�
	@RequestMapping(value = "/notice_manage")
	public String noticemanage(HttpServletRequest request, Model model){
		String b_kind = "����";
		ServiceAdmin sa = sqlsession.getMapper(ServiceAdmin.class);
		ArrayList<Board> list = sa.board_manage_select(b_kind);
		model.addAttribute("board", list);
		return "notice_manage";
	}
	// �Խ��ǰ��� - �������� �Խ��� ���� ������ �̵�
	@RequestMapping(value = "/infoshare_manage")
	public String infosharemanage(HttpServletRequest request, Model model){
		String b_kind = "��������";
		ServiceAdmin sa = sqlsession.getMapper(ServiceAdmin.class);
		ArrayList<Board> list = sa.board_manage_select(b_kind);
		model.addAttribute("board", list);
		return "infoshare_manage";
	}
	// �Խ��ǰ��� - ������ �Խ��� ���� ������ �̵�
	@RequestMapping(value = "/intellectual_manage")
	public String intellectualmanage(HttpServletRequest request, Model model){
		String b_kind = "������";
		ServiceAdmin sa = sqlsession.getMapper(ServiceAdmin.class);
		ArrayList<Board> list = sa.board_manage_select(b_kind);
		model.addAttribute("board", list);
		return "intellectual_manage";
	}
	// �Խ��ǰ��� - ��λ��� �Խ��� ���� ������ �̵�
	@RequestMapping(value = "/counseling_manage")
	public String counselingmanage(HttpServletRequest request, Model model){
		String b_kind = "��λ���";
		ServiceAdmin sa = sqlsession.getMapper(ServiceAdmin.class);
		ArrayList<Board> list = sa.board_manage_select(b_kind);
		model.addAttribute("board", list);
		return "counseling_manage";
	}
	// �Խ��ǰ��� - Q&A �Խ��� ���� ������ �̵�
	@RequestMapping(value = "/qna_manage")
	public String qnamanage(HttpServletRequest request, Model model){
		String b_kind = "Q&A";
		ServiceAdmin sa = sqlsession.getMapper(ServiceAdmin.class);
		ArrayList<Board> list = sa.board_manage_select(b_kind);
		model.addAttribute("board", list);
		return "qna_manage";
	}
	// �Ű� ���� - �Ű� ���� ������ �̵�
	@RequestMapping(value = "/report_manage")
	public String reportmanage(HttpServletRequest request, Model model){
		ServiceAdmin sa = sqlsession.getMapper(ServiceAdmin.class);
		ArrayList<Board> list = sa.report_manage_select();
		model.addAttribute("board", list);
		return "report_manage";
	}
	// ȸ�� ���� - ȸ�� ���� ������ �̵�
	@RequestMapping(value = "/member_manage")
	public String membermanage(HttpServletRequest request, Model model){
		ServiceAdmin sa = sqlsession.getMapper(ServiceAdmin.class);
		ArrayList<Board> list = sa.member_manage_select();
		model.addAttribute("board", list);
		return "member_manage";
	}
	
	/*���*/
	// ���������� > ���� ���� > ����
	@RequestMapping(value = "/admin_infoupdate_update", method = RequestMethod.POST)
	public String admin_info_update(HttpServletRequest request) throws Exception{
		
		// ���� ����
		HttpSession session = request.getSession();
		// ���ǿ��� ������ id ������
		String admin_id = (String)session.getAttribute("id"); // admin
		
		// ������ id�� ���� ���� - ������ ������ �������� ����ҰŶ�
		session.setAttribute("admin_id", admin_id); // admin
		
		// ���ǿ��� ������ nick ������
		String admin_nick = (String)session.getAttribute("member_nick"); // ������
		
		// ������ nick�� ���� ���� - ������ ������ �������� ����ҰŶ�
		session.setAttribute("admin_nick", admin_nick); // ������

		String jo = request.getParameter("jsoninfo");		
		JSONParser jsonparse = new JSONParser();
		
		try {
			JSONObject jobj = (JSONObject)jsonparse.parse(jo);
			String pw=(String) jobj.get("pw");
			String name=(String) jobj.get("name");
			String mail=(String) jobj.get("mail");
			String tel=(String) jobj.get("tel");
			String field=(String) jobj.get("field");
			
			ServiceAdmin sa = sqlsession.getMapper(ServiceAdmin.class);
			
			sa.admin_infoupdate_update(pw,admin_nick,name,mail,tel,field,admin_id);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return "redirect:admin_infoupdate";
	}
	
	// ���������� > ���� �� > ���� �� �� > ��ȸ
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/admin_mywrite_select", method = RequestMethod.POST,
	produces = "application/text; charset=UTF-8")
	public String admin_mywrite_select(HttpServletRequest request) throws IOException{
		// ���� ����
		HttpSession session = request.getSession();
		
		// ���ǿ��� ������ nick ������
		String admin_nick = (String)session.getAttribute("admin_nick"); // ������
		
		JSONArray array = new JSONArray();
		JSONObject total = new JSONObject();
		ServiceAdmin sa= sqlsession.getMapper(ServiceAdmin.class);
		ArrayList<Board> list=sa.admin_mywrite_select(admin_nick);
		
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
	// ���������� > ���� �� > ���� ���ƿ� �� �� > ��ȸ
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/admin_mylike_select", method = RequestMethod.POST,
	produces = "application/text; charset=UTF-8")
	public String admin_mylike_select(HttpServletRequest request) throws IOException{
		// ���� ����
		HttpSession session = request.getSession();
		
		// ���ǿ��� ������ nick ������
		String admin_nick = (String)session.getAttribute("admin_nick"); // ������
		
		JSONArray array = new JSONArray();
		JSONObject total = new JSONObject();
		ServiceAdmin sa= sqlsession.getMapper(ServiceAdmin.class);
		ArrayList<Board> list=sa.admin_mylike_select(admin_nick);
		
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
	// ���������� > ���� �� > ���� ��ũ�� �� �� > ��ȸ
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/admin_myscrap_select", method = RequestMethod.POST,
	produces = "application/text; charset=UTF-8")
	public String admin_myscrap_select(HttpServletRequest request) throws IOException{
		// ���� ����
		HttpSession session = request.getSession();
		
		// ���ǿ��� ������ nick ������
		String admin_nick = (String)session.getAttribute("admin_nick"); // ������
		
		JSONArray array = new JSONArray();
		JSONObject total = new JSONObject();
		ServiceAdmin sa= sqlsession.getMapper(ServiceAdmin.class);
		ArrayList<Board> list=sa.admin_myscrap_select(admin_nick);
		
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
	// ���������� > ���� �� > ���� �� �� > ����
	@RequestMapping(value = "/admin_mylist_update", method = RequestMethod.POST)
	public String admin_mylist_update(MultipartHttpServletRequest mul,HttpServletRequest request) throws Exception{
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
        return "redirect:admin_mylist";
	}
	// ���������� > ���� �� > ���� �� �� > ����
	@RequestMapping(value = "/admin_mylist_delete", method = RequestMethod.POST)
	public String admin_mylist_delete(HttpServletRequest request, Model model) throws Exception{
		String jo = request.getParameter("jsoninfo");		
		JSONParser jsonparse = new JSONParser();
		try {
			JSONObject jobj = (JSONObject)jsonparse.parse(jo);
			int b_num=Integer.parseInt(String.valueOf(jobj.get("b_num")));
			ServiceAdmin sa = sqlsession.getMapper(ServiceAdmin.class);
			sa.admin_mylist_delete(b_num);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "redirect:admin_mylist";
	}
	// ���������� > ���� �� > ���� ���ƿ� �� �� > ���ƿ� ���
	@RequestMapping(value = "/admin_mylike_cancel", method = RequestMethod.POST)
	public String admin_mylike_cancel(HttpServletRequest request, Model model) throws Exception{
		HttpSession session = request.getSession();
		String admin_nick = (String)session.getAttribute("admin_nick"); // ������
		String jo = request.getParameter("jsoninfo");		
		JSONParser jsonparse = new JSONParser();
		try {
			JSONObject jobj = (JSONObject)jsonparse.parse(jo);
			System.out.println(jobj + "���� jobj");
			int b_num=Integer.parseInt(String.valueOf(jobj.get("b_num")));
			System.out.println(b_num + "���� b_num");
			ServiceAdmin sa = sqlsession.getMapper(ServiceAdmin.class);
			sa.admin_mylike_cancel(b_num,admin_nick);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return "redirect:admin_mylist";
	}
	// ���������� > ���� �� > ���� ��ũ�� �� �� > ��ũ�� ���
	@RequestMapping(value = "/admin_myscrap_cancel", method = RequestMethod.POST)
	public String admin_myscrap_cancel(HttpServletRequest request, Model model) throws Exception{
		HttpSession session = request.getSession();
		String admin_nick = (String)session.getAttribute("admin_nick"); // ������
		String jo = request.getParameter("jsoninfo");		
		JSONParser jsonparse = new JSONParser();
		try {
			JSONObject jobj = (JSONObject)jsonparse.parse(jo);
			System.out.println(jobj + "���� jobj");
			int b_num=Integer.parseInt(String.valueOf(jobj.get("b_num")));
			System.out.println(b_num + "���� b_num");
			ServiceAdmin sa = sqlsession.getMapper(ServiceAdmin.class);
			sa.admin_myscrap_cancel(b_num,admin_nick);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "redirect:admin_mylist";
	}
	// ���������� > �Ű����> ó���Ϸ� ����
	@RequestMapping(value = "/report_manage_update", method = RequestMethod.POST)
	public String report_manage_update(HttpServletRequest request, Model model) throws Exception{
		String r_status = "ó���Ϸ�";
		String jo = request.getParameter("jsoninfo");		
		JSONParser jsonparse = new JSONParser();
		try {
			JSONObject jobj = (JSONObject)jsonparse.parse(jo);
			System.out.println(jobj + "���� jobj");
			int r_num=Integer.parseInt(String.valueOf(jobj.get("r_num")));
			System.out.println(r_num + "���� b_num");
			ServiceAdmin sa = sqlsession.getMapper(ServiceAdmin.class);
			sa.report_manage_update(r_status,r_num);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "redirect:report_manage";
	}
	// ���������� > ȸ������ > ����
		@RequestMapping(value = "/member_manage_delete", method = RequestMethod.POST)
		public String member_manage_delete(HttpServletRequest request, Model model) throws Exception{
			String jo = request.getParameter("jsoninfo");		
			JSONParser jsonparse = new JSONParser();
			try {
				JSONObject jobj = (JSONObject)jsonparse.parse(jo);
				int m_num=Integer.parseInt(String.valueOf(jobj.get("m_num")));
				ServiceAdmin sa = sqlsession.getMapper(ServiceAdmin.class);
				sa.member_manage_delete(m_num);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return "redirect:admin_mylist";
		}
	// test ������
	@RequestMapping(value = "/test")
	public String test(){
		return "test";
	}
}