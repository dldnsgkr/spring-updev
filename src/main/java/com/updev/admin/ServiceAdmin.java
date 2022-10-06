package com.updev.admin;

import java.util.ArrayList;

import com.updev.board.Board;
import com.updev.member.Signup;

public interface ServiceAdmin {

	public Signup infoupdate(String m_id);

	public void info_update(String pw, String nick, String name, String mail, String tel, String field,
			String id);

	public void mylist_write();

	public ArrayList<Board> mywrite(String m_nick);

	public ArrayList<Board> mylike(String m_nick);

	public ArrayList<Board> myscrap(String m_nick);

	public void mylist_delete(int b_num);

	public ArrayList<Board> noticeboardmanage(String notice);

	public void admin_infoupdate_update(String pw, String nick, String name, String mail, String tel, String field,
			String id);
	
	public ArrayList<Board> admin_mywrite_select(String admin_nick);
	public ArrayList<Board> admin_mylike_select(String admin_nick);
	public ArrayList<Board> admin_myscrap_select(String admin_nick);

	public Signup admin_infoupdate_select(String admin_id);

	public ArrayList<Board> board_manage_select(String b_kind);

	public ArrayList<Board> report_manage_select();

	public ArrayList<Board> member_manage_select();

	public void admin_mylist_delete(int b_num);

	public void admin_mylike_cancel(int b_num, String admin_nick);

	public void admin_myscrap_cancel(int b_num, String admin_nick);

	public void member_manage_delete(int m_num);

	public void report_manage_update(String r_status, int r_num);

	public ArrayList<Alarm> admin_myalarm_select(String admin_nick);

	public void board_manage_delete(int b_num);

}
