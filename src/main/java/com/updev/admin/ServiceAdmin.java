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

}
