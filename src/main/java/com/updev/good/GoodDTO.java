package com.updev.good;

public class GoodDTO {
	int g_num,b_num;
	String m_nick;
	int likechk;
	
	public GoodDTO() {
		super();
	}
	
	public GoodDTO(int g_num, int b_num, String m_nick, int likechk) {
		super();
		this.g_num = g_num;
		this.b_num = b_num;
		this.m_nick = m_nick;
		this.likechk = likechk;
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
	public int getLikechk() {
		return likechk;
	}
	public void setLikechk(int likechk) {
		this.likechk = likechk;
	}
	
}
