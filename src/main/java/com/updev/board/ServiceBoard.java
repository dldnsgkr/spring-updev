package com.updev.board;

import java.util.ArrayList;

import com.updev.member.Signup;



public interface ServiceBoard {

	public void save(String b_cate, String b_kind, String b_title, String m_nick, String b_content, String b_file1,
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

	

	

}
