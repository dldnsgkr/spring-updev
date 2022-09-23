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
		String a1 = "공지";
		String b1 = "정보 공유";
		String c1 = "질문창고";
		String d1 = "고민상담소";
		String e1 = "Q&A";
		String f1 = "인기 조회수";
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
		String a1 = "공지";
		String b1 = "정보 공유";
		String c1 = "질문창고";
		String d1 = "고민상담소";
		String e1 = "Q&A";
		String f1 = "인기 조회수";
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
	
	
	//회원 마이페이지로 이동
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
	         String b_tag = mul.getParameter("b_tag");
	         MultipartFile f1 = mul.getFile("b_file1");
	            MultipartFile f2 = mul.getFile("b_file2");
	            String b_file1 = f1.getOriginalFilename();
	            
	            String b_file2 = f2.getOriginalFilename();
	         ServiceBoard ss = sqlsession.getMapper(ServiceBoard.class);
	         ss.boardupdate(b_num,b_cate,b_kind,b_title,m_nick,b_content,b_tag,b_file1,b_file2);
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
	            String b_tag = mul.getParameter("b_tag");
	            ServiceBoard ss = sqlsession.getMapper(ServiceBoard.class);
	            ss.save(b_cate,b_kind,b_title,m_nick,b_content,b_tag,b_file1,b_file2);
	            return "redirect:index";
	         }
	      		//글 삭제
	         @RequestMapping(value = "/writedelete")
		      public String ko11(HttpServletRequest request,Model mo)
		      {
		            int b_num = Integer.parseInt(request.getParameter("b_num"));
		           ServiceBoard ss = sqlsession.getMapper(ServiceBoard.class);
		         ss.delete(b_num);
		         return "index";
		      }
	         
	         //공지사항 폼
	         @RequestMapping(value = "/notice")
		      public String ko12(Model mo)
		      {
	        	 String notice = "공지";
	        	 ServiceBoard ss = sqlsession.getMapper(ServiceBoard.class);
		         ArrayList<Board> list = ss.noticeboardtable(notice);
		         mo.addAttribute("lista",list);
		         return "noticepage";
		      }
	         
	       // 정보 공유 폼
	         @RequestMapping(value = "/share")
		      public String ko13(Model mo)
		      {
	        	 String share = "정보 공유";
	        	 ServiceBoard ss = sqlsession.getMapper(ServiceBoard.class);
	        	 ArrayList<Board> list = ss.shareboardtable(share);
	        	 mo.addAttribute("listb",list);
		         return "sharepage";
		      }
	         
	       //질문창고 폼
	         @RequestMapping(value = "/question")
		      public String ko14(Model mo)
		      {
	        	 String question = "질문창고";
	        	 ServiceBoard ss = sqlsession.getMapper(ServiceBoard.class);
	        	 ArrayList<Board> list = ss.questionboardtable(question);
	        	 mo.addAttribute("listc",list);
		         return "questionpage";
		      }
	         
	       //고민상담소 폼
	         @RequestMapping(value = "/worry")
		      public String ko15(Model mo)
		      {
	        	 String worry = "고민상담소";
	        	 ServiceBoard ss = sqlsession.getMapper(ServiceBoard.class);
	        	 ArrayList<Board> list = ss.worryboardtable(worry);
	        	 mo.addAttribute("listd",list);
		         return "worrypage";
		      }
	         
	       //Q&A 폼
	         @RequestMapping(value = "/qna")
		      public String ko16(Model mo)
		      {
	        	 String qna = "Q&A";
	        	 ServiceBoard ss = sqlsession.getMapper(ServiceBoard.class);
	        	 ArrayList<Board> list = ss.qnaboardtable(qna);   
	        	 mo.addAttribute("liste",list);
		         return "qnapage";
		      }
	         
	       //게시물 detail
	         @RequestMapping(value = "/detail")
	         public String ko17(HttpServletRequest request,Model mo)
	         {
	        	 int b_num = Integer.parseInt(request.getParameter("b_num"));
	        	 ServiceBoard ss = sqlsession.getMapper(ServiceBoard.class);
	        	 Board member = ss.boarddetail(b_num);
	        	 mo.addAttribute("list",member);
	        	 return "detailboard";
	         }
	         
	         
	
	
}
