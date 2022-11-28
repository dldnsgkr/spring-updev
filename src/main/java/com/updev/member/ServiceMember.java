package com.updev.member;

import java.util.ArrayList;

import com.updev.board.Alarm;
import com.updev.board.Board;
import com.updev.board.PageDTO;

public interface ServiceMember {

	public void member_info_insert(String m_id, String m_pw, String m_nick, String m_name, String m_mail, String m_tel,
			String m_field, String m_jdate);

	public Signup login_checking(String id, String pw);

	public void profileupdate(String m_nick, String m_id, String m_pw, String m_name, String m_mail, String m_tel, String m_field, String up_nick);

	public Signup profile_update_check(String m_nick);

	public int id_duplicate_test(String id);

	public int idCheck(String m_id);

	public void profileboardupdate(String m_nick, String up_nick);
 
	public void profilereportupdate(String m_nick, String up_nick);

	public void albalupdate(String m_nick, String up_nick);

	public void alsuupdate(String m_nick, String up_nick);

	public String find_id(String name, String mail);

	public int find_pw(String name, String mail); //카운트(1) if else로 이름과 이메일로 계정 찾기

	public int find_mpw(String id, String pw); //카운트(1) if else로 해당 계정의 비밀번호가 맞는지 확인하기

	public void update_pw(String pw, String npw);

	public int nickname_duplicate_test(String nick);

	public int nickCheck(String m_nick);
	
	public int nicktest2(String nick);
	
	public int nickCheck2(String up_nick);

	public void report_insert(String r_status, String r_reason, int b_num);

	public ArrayList<Board> ajaxmywrite(String nick);

	public ArrayList<Board> ajaxmygood(String nick);

	public ArrayList<Board> ajaxmyscrap(String nick);

	public String gradeselect(String m_id, String m_pw);

	public String dateselect(String m_id, String m_pw);

	public void outtime_update(String id);

	public ArrayList<Alarm> ajaxmyalarm(String nick);

	public int alarmcount(String m_nick);

	public void alarmchk(int a_num);

	public Signup login_cookie(String cookie_m_id);
	
	public int mytotal(String nick);
	public ArrayList<Board> mypage(PageDTO dto);
	
	public int goodtotal(String nick);
	public ArrayList<Board> goodpage(PageDTO dto);


	public Signup find_idandpw_use_email(String email);


	public int scraptotal(String nick);
	public ArrayList<Board> scrappage(PageDTO dto);

	public void gradechange(String a, String b);

}
