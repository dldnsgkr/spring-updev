package com.updev.board;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
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
		HttpSession session = request.getSession();
		session.setAttribute("loginState", false);
		ServiceBoard ss = sqlsession.getMapper(ServiceBoard.class);
		String a1 = "��λ���";
		String b1 = "���� ����";
		String c1 = "����â��";
		String d1 = "Q&A";
		String e1 = "����";
		String f1 = "�α� ��ȸ��";
		ArrayList<Board> a = ss.bo1(a1);
		ArrayList<Board> b = ss.bo2(b1);
		ArrayList<Board> c = ss.bo3(c1);
		ArrayList<Board> d = ss.bo4(d1);
		ArrayList<Board> e = ss.bo5(e1);
		
		mo.addAttribute("aa",a);
		mo.addAttribute("bb",b);
		mo.addAttribute("cc",c);
		mo.addAttribute("dd",d);
		mo.addAttribute("ee",e);
		return "main";
	}
	
	@RequestMapping(value = "/index")
	public String index(Model mo)
	{
		ServiceBoard ss = sqlsession.getMapper(ServiceBoard.class);
		String a1 = "��λ���";
		String b1 = "���� ����";
		String c1 = "����â��";
		String d1 = "Q&A";
		String e1 = "����";
		String f1 = "�α� ��ȸ��";
		ArrayList<Board> a = ss.bo1(a1);
		ArrayList<Board> b = ss.bo2(b1);
		ArrayList<Board> c = ss.bo3(c1);
		ArrayList<Board> d = ss.bo4(d1);
		ArrayList<Board> e = ss.bo5(e1);
		
		
		mo.addAttribute("aa",a);
		mo.addAttribute("bb",b);
		mo.addAttribute("cc",c);
		mo.addAttribute("dd",d);
		mo.addAttribute("ee",e);
		return "main";
	}
	
	
	//ȸ�� ������������ �̵�
	   @RequestMapping(value = "/myp")
	   public String ko8(Model mo,HttpServletRequest request)
	   {
		   String m_nick = request.getParameter("m_nick");
		   ServiceBoard ss = sqlsession.getMapper(ServiceBoard.class);
		   ArrayList<Board> dto = ss.mewrite(m_nick);
		   Signup dt = ss.myinfo(m_nick);
		   mo.addAttribute("list",dto);
		   mo.addAttribute("lista",dt);
		  return "mypage"; 
	   }
	   
	 //�� ���� �ۼ�
	      @RequestMapping(value = "/writeupdatecheck")
	      public String ko10(HttpServletRequest request,Model mo)
	      {
	            int b_num = Integer.parseInt(request.getParameter("b_num"));
	            ServiceBoard ss = sqlsession.getMapper(ServiceBoard.class);
	         Board dao = ss.updatecheck(b_num);
	         mo.addAttribute("list",dao);
	         return "boardupdate";
	      }
	      
	      //�ۼ���
	      @RequestMapping(value = "/writeupdate")
	      public String ko9(MultipartHttpServletRequest mul,HttpServletRequest request)
	      {
	         int b_num = Integer.parseInt(mul.getParameter("b_num"));
	         String b_cate = mul.getParameter("b_cate");
	         String b_kind = mul.getParameter("b_kind");
	         String b_title = mul.getParameter("b_title");
	         String m_nick = mul.getParameter("m_nick");
	         String b_content = mul.getParameter("b_content");
	         String b_tag = mul.getParameter("b_tag");
	         MultipartFile f1 = mul.getFile("b_file1");
	            MultipartFile f2 = mul.getFile("b_file2");
	            String b_file1 = f1.getOriginalFilename();
	            
	            String b_file2 = f2.getOriginalFilename();
	         ServiceBoard ss = sqlsession.getMapper(ServiceBoard.class);
	         ss.boardupdate(b_num,b_cate,b_kind,b_title,m_nick,b_content,b_tag,b_file1,b_file2);
	         return "redirect:index";
	      }
	      
	      //�� �ۼ� ��
	      @RequestMapping(value = "/write")
	      public String ko1()
	      {
	         return "boardwrite";
	      }
	      //�� �ۼ�
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
	            String b_tag = mul.getParameter("b_tag");
	            ServiceBoard ss = sqlsession.getMapper(ServiceBoard.class);
	            ss.save(b_cate,b_kind,b_title,m_nick,b_content,b_tag,b_file1,b_file2);
	            return "redirect:index";
	         }
	      		//�� ����
	         @RequestMapping(value = "/writedelete")
		      public String ko11(HttpServletRequest request,Model mo)
		      {
		            int b_num = Integer.parseInt(request.getParameter("b_num"));
		           ServiceBoard ss = sqlsession.getMapper(ServiceBoard.class);
		         ss.delete(b_num);
		         return "index";
		      }
	         
	         //�������� ��
	         @RequestMapping(value = "/notice")
		      public String ko12()
		      {
	        	 String notice = "����";
	        	 ServiceBoard ss = sqlsession.getMapper(ServiceBoard.class);
		         ss.noticeboardtable(notice);
		         return "noticepage";
		      }
	         
	       // ��
	         @RequestMapping(value = "/share")
		      public String ko13()
		      {
		         return "sharepage";
		      }
	         
	       //�������� ��
	         @RequestMapping(value = "/question")
		      public String ko14()
		      {
		         return "questionpage";
		      }
	         
	       //�������� ��
	         @RequestMapping(value = "/worry")
		      public String ko15()
		      {
		            
		         return "worrypage";
		      }
	         
	       //�������� ��
	         @RequestMapping(value = "/qna")
		      public String ko16()
		      {
		            
		         return "qnapage";
		      }
	
	
}
