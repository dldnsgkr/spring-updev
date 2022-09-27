package com.updev.report;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ReportController {

	@Autowired
	SqlSession sqlsession;
	
	//게시물 신고페이지
 	@RequestMapping(value = "/boardreportpage")
     public String ko1(HttpServletRequest request,Model mo,RedirectAttributes rattr)
     {
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
 		String a = mul.getParameter("r_reason");
 		String b = mul.getParameter("otherreason");
 		String r_reason = "";
 		MultipartFile f = mul.getFile("r_file1");
        String r_file1 = f.getOriginalFilename();
        if(a.equals("etc"))
        {
        	r_reason = b;
        } else {
        	r_reason = a;
        }
 		int b_num = Integer.parseInt(request.getParameter("b_num"));
 		 ServiceReport ss = sqlsession.getMapper(ServiceReport.class);
    	 ss.reportinsert(r_status,r_reason,r_file1,b_num);
 		return "redirect:index";
 	}
	
}
