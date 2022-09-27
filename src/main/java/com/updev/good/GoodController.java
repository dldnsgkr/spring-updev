package com.updev.good;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.updev.good.ServiceGood;
import com.updev.good.GoodDTO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class GoodController {
	
	@Autowired
	SqlSession sqlsession;

	@RequestMapping("/noticeDetail")
	public void noticeDetail(@RequestParam int g_num, Model model, HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		
		Map<String,Object> resultMap=null;
		Map<String,Object> g_numMap = new HashMap<>();
		int b_num=g_num;
		String m_nick=null;
		
		resultMap = ServiceGood.noticeDetail(b_num);
		g_numMap.put("b_num", b_num);
		g_numMap.put("m_nick", m_nick);
		
		Map<String,Object> likechkMap=ServiceGood.likechk(g_numMap);
	}
	
	         
	
	
}
