package com.updev.admin;

import java.io.File;
import java.io.UnsupportedEncodingException;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.updev.member.Signup;

@Controller
public class AdminController {
	
	@Autowired
	SqlSession sqlsession;
	
	private static final String CURR_IMAGE_REPO_PATH = 
	"C:\\Users\\3-13\\Desktop\\project\\updev\\src\\main\\webapp\\resources\\images";
	
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
	// �������� ������ �̵�
	@RequestMapping(value = "/infoupdate")
	public String infoupdate(HttpServletRequest request, Model model){
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ServiceAdmin sa = sqlsession.getMapper(ServiceAdmin.class);
		
		Signup s = sa.infoupdate(id);
		model.addAttribute("admin", s);
		return "infoupdate";
	}
	// ��������
	@RequestMapping(value = "/info_update", method = RequestMethod.POST)
	public String info_update( MultipartHttpServletRequest multi,
			HttpServletRequest request, Model model) throws Exception{
		
		multi.setCharacterEncoding("utf-8");
		
		Map map = new HashMap();
		
		List fileList= fileProcess(multi);
		
		map.put("fileList", fileList);	
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");

		String jo=request.getParameter("jsoninfo");		
		JSONParser jsonparse = new JSONParser();
		
		try {
			JSONObject jobj = (JSONObject)jsonparse.parse(jo);
			String profile=(String) jobj.get("profile");
			profile = profile.substring(12,profile.length());
			String pw=(String) jobj.get("pw");
			String nick=(String) jobj.get("nick");
			String name=(String) jobj.get("name");
			String mail=(String) jobj.get("mail");
			String tel=(String) jobj.get("tel");
			String field=(String) jobj.get("field");
			
			ServiceAdmin sa = sqlsession.getMapper(ServiceAdmin.class);
			
			sa.info_update(profile,pw,nick,name,mail,tel,field,id);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return "redirect:infoupdate";
	}
	
	private List<String> fileProcess(MultipartHttpServletRequest multipartRequest) throws Exception{
		List<String> fileList= new ArrayList<String>();
		//÷�ε� ȭ���� �̸��� ���������� �����´�
		Iterator<String> fileNames = multipartRequest.getFileNames();
		// ��ü�� ����� ���ϵ��� �Ѱ��� �и�
		while(fileNames.hasNext()){
			
			String fileName = fileNames.next();
			//������ ���ε� �ϴ� ȭ�� ���� �������̽�
			MultipartFile mFile = multipartRequest.getFile(fileName);
			//���ε� �Ǵ� ������ �̸�
			String originalFileName=mFile.getOriginalFilename();
			fileList.add(originalFileName);
			
			File file = new File(CURR_IMAGE_REPO_PATH +"\\"+ fileName);
			
			//�ӽ÷� ����� multipartFile�� ���� ���Ϸ� ����
			mFile.transferTo(new File(CURR_IMAGE_REPO_PATH +"\\"+ originalFileName)); 
			}
		return fileList;//ȭ�ϵ��� ��� 
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
