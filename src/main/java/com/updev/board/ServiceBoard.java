package com.updev.board;

import java.util.ArrayList;

import com.updev.member.Signup;


public interface ServiceBoard {

	public void writesave(String b_cate, String b_kind, String b_title, String m_nick, String b_content, String b_file1,
			String b_file2);

	public ArrayList<Board> mewrite(String a);

	public Signup myinfo(String m_nick);

	public void boardupdate(int b_num, String b_cate, String b_kind, String b_title, String m_nick, String b_content, String b_file1, String b_file2);

	public Board updatecheck(int b_num);

	public ArrayList<Board> bo1(String a1);

	public ArrayList<Board> bo2(String b1);

	public ArrayList<Board> bo3(String c1);

	public ArrayList<Board> bo4(String d1);

	public ArrayList<Board> bo5(String e1);

	public void delete(int b_num);

	public ArrayList<Board> noticeboardtable(String notice);

	public ArrayList<Board> shareboardtable(String share);

	public ArrayList<Board> questionboardtable(String question);

	public ArrayList<Board> worryboardtable(String worry);

	public ArrayList<Board> qnaboardtable(String qna);
	
	public Board boarddetail(int b_num);
	
	public void reportboardupdate(int b_num);
	
	public void replysave(int b_num, String m_nick, String re_content);
	
	public Reply replyview(int b_num);
	
	//∆‰¿Ã¬°
	public int sharetotal();
	public ArrayList<Board> sharepage(PageDTO dto);
	
	public int questiontotal();
	public ArrayList<Board> questionpage(PageDTO dto);
	
	public int worrytotal();
	public ArrayList<Board> worrypage(PageDTO dto);
	
	public int qnatotal();
	public ArrayList<Board> qnapage(PageDTO dto);
	
	public int noticetotal();
	public ArrayList<Board> noticepage(PageDTO dto);
	
	public int poptotal();
	public ArrayList<Board> poppage(PageDTO dto);
	
	public int searchcnt(String keyword);
	public ArrayList<Board> tsearchpage(PageDTO dto);
	
	public int replytotal(int b_num);
	public ArrayList<Reply> replypage(PageDTO dto);

	public void readcnt(int num);

	public Good howgood(int b_num, String nick);

	public void blikeup(int b_num, String m_nick, int chk);

	public void blikedown(int b_num, String m_nick, int like_chk);

	public void likecntup(int num);

	public void likecntdown(int num);

	public void scrap(int b_num, String m_nick, int chk);

	public void scrapcancel(int b_num, String m_nick, int scrap_chk);

	public Scrap howscrap(int b_num, String nick);
	
	public ArrayList<Board> popmain();
	
	public ArrayList<Board> sharemain();
	
	public ArrayList<Board> questionmain();
	
	public ArrayList<Board> worrymain();
	
	public ArrayList<Board> noticemain();
	
	public ArrayList<Board> qnamain();

	public void goodalarm(int num, String m_nick, String su_nick, String a_content, int alarm_chk, int a_existence);

	public Alarm howalramexistence(int b_num, String nick, String su_nick);
	
	public int howgo(int b_num, String m_nick, String su_nick);

	public void regood(int b_num, String m_nick);

	public void rescrap(int b_num, String m_nick);

	public int howsc(int b_num, String m_nick, String su_nick);
}