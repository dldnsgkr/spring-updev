package com.updev.board;

import java.util.ArrayList;

import com.updev.member.Signup;


public interface ServiceBoard {

	public void writesave(String b_cate, String b_kind, String b_title, String m_nick, String b_content, String b_file1,
			String b_file2, String b_tag);

	public ArrayList<Board> mewrite(String a);

	public Signup myinfo(String m_nick);

	public void boardupdate(int b_num, String b_cate, String b_kind, String b_title, String m_nick, String b_content,  String b_tag, String b_file1, String b_file2);

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


	public void readcnt(int num);

	public Good howgood(int b_num, String nick);

	public void blikeup(String b_num, String m_nick, int chk);

	public void blikedown(String b_num, String m_nick);

	
}






