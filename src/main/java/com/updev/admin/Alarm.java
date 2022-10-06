package com.updev.admin;

public class Alarm {

	int a_num;
	int b_num;
	String m_nick,m_id,a_content,a_adate;
	
	public Alarm() {}

	public Alarm(int a_num, int b_num, String m_nick, String m_id, String a_content, String a_adate) {
		super();
		this.a_num = a_num;
		this.b_num = b_num;
		this.m_nick = m_nick;
		this.m_id = m_id;
		this.a_content = a_content;
		this.a_adate = a_adate;
	}

	public int getA_num() {
		return a_num;
	}

	public void setA_num(int a_num) {
		this.a_num = a_num;
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

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getA_content() {
		return a_content;
	}

	public void setA_content(String a_content) {
		this.a_content = a_content;
	}

	public String getA_adate() {
		return a_adate;
	}

	public void setA_adate(String a_adate) {
		this.a_adate = a_adate;
	}
	
}
