package com.updev.member;

import java.util.ArrayList;

import com.updev.board.Board;

public interface ServiceMember {

	public void insert(String m_id, String m_pw, String m_nick, String m_name, String m_mail, String m_tel,
			String m_field, String m_jdate);

	public Signup loginselect(String id, String pw);

	public void profileupdate(String m_nick, String m_id, String m_pw, String m_name, String m_mail, String m_tel, String m_field, String up_nick);

	public Signup profileupdatecheck(String m_nick);

	public int idtest(String id);

	public int idCheck(String m_id);

	public void profileboardupdate(String m_nick, String up_nick);
 
	public void balupdate(String m_nick, String up_nick);

	public void suupdate(String m_nick, String up_nick);

	public void profilereportupdate(String m_nick, String up_nick);

	public void albalupdate(String m_nick, String up_nick);

	public void alsuupdate(String m_nick, String up_nick);

	public String find_id(String name, String mail);

	public int find_pw(String name, String mail); //카운트(1) if else로 이름과 이메일로 계정 찾기

	public int find_mpw(String id, String pw); //카운트(1) if else로 해당 계정의 비밀번호가 맞는지 확인하기

	public void update_pw(String pw, String npw);

	public int nicktest(String nick);

	public int nickCheck(String m_nick);
	
	public int nicktest2(String nick);
	
	public int nickCheck2(String up_nick);

	public void reportinsert(String r_status, String r_reason, String r_file1, int b_num);

	public ArrayList<Board> ajaxmywrite(String nick);

	public ArrayList<Board> ajaxmygood(String nick);

	public ArrayList<Board> ajaxmyscrap(String nick);

	public String gradeselect(String m_id, String m_pw);

	public String dateselect(String m_id, String m_pw);

	public void outtimeupdate(String id);

}
