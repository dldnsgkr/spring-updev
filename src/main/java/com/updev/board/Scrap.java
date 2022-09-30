package com.updev.board;

public class Scrap {

	int s_num,b_num;
	String m_nick;
	int scrap_chk;
	public Scrap() {}
	public Scrap(int s_num, int b_num, String m_nick, int scrap_chk) {
		super();
		this.s_num = s_num;
		this.b_num = b_num;
		this.m_nick = m_nick;
		this.scrap_chk = scrap_chk;
	}
	public int getS_num() {
		return s_num;
	}
	public void setS_num(int s_num) {
		this.s_num = s_num;
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
	public int getScrap_chk() {
		return scrap_chk;
	}
	public void setScrap_chk(int scrap_chk) {
		this.scrap_chk = scrap_chk;
	}
	
	
	
}
