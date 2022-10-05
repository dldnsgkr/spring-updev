package com.updev.board;

public class Reply {
	public int re_num,b_num;
	public String m_nick,re_content,re_wdate;
	public int b_replycnt;
	
	public Reply() {
		super();
	}
	
	public Reply(int re_num, int b_num, String m_nick, String re_content, String re_wdate, int b_replycnt) {
		super();
		this.re_num = re_num;
		this.b_num = b_num;
		this.m_nick = m_nick;
		this.re_content = re_content;
		this.re_wdate = re_wdate;
		this.b_replycnt = b_replycnt;
	}


	public int getRe_num() {
		return re_num;
	}
	
	public void setRe_num(int re_num) {
		this.re_num = re_num;
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
	
	public String getRe_content() {
		return re_content;
	}
	
	public void setRe_content(String re_content) {
		this.re_content = re_content;
	}
	
	public String getRe_wdate() {
		return re_wdate;
	}
	
	public void setRe_wdate(String re_wdate) {
		this.re_wdate = re_wdate;
	}

	public int getB_replycnt() {
		return b_replycnt;
	}

	public void setB_replycnt(int b_replycnt) {
		this.b_replycnt = b_replycnt;
	}

}
