package com.updev.board;

public class Good {

	int g_num,b_num;
	String m_nick;
	int like_chk;
	public Good() {}
	public Good(int g_num, int b_num, String m_nick, int like_chk) {
		super();
		this.g_num = g_num;
		this.b_num = b_num;
		this.m_nick = m_nick;
		this.like_chk = like_chk;
	}
	public int getG_num() {
		return g_num;
	}
	public void setG_num(int g_num) {
		this.g_num = g_num;
	}
	public int getB_num() {
		return b_num;
	}
	public void setB_num(int b_num) {
		this.b_num = b_num;
	}
	public String getM_nick() {
		return m_nick;
	}
	public void setM_nick(String m_nick) {
		this.m_nick = m_nick;
	}
	public int getLike_chk() {
		return like_chk;
	}
	public void setLike_chk(int like_chk) {
		this.like_chk = like_chk;
	}
	
	
	
	
}
