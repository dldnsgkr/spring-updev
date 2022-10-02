package com.updev.admin;

public class Report {

	int r_num;
	String r_status,r_reason,r_file1;
	int b_num;
	String m_nick;
	
	public Report() {}
	
	public Report(int r_num, String r_status, String r_reason, String r_file1, int b_num, String m_nick) {
		super();
		this.r_num = r_num;
		this.r_status = r_status;
		this.r_reason = r_reason;
		this.r_file1 = r_file1;
		this.b_num = b_num;
		this.m_nick = m_nick;
	}
	
	public int getR_num() {
		return r_num;
	}
	public void setR_num(int r_num) {
		this.r_num = r_num;
	}
	public String getR_status() {
		return r_status;
	}
	public void setR_status(String r_status) {
		this.r_status = r_status;
	}
	public String getR_reason() {
		return r_reason;
	}
	public void setR_reason(String r_reason) {
		this.r_reason = r_reason;
	}
	public String getR_file1() {
		return r_file1;
	}
	public void setR_file1(String r_file1) {
		this.r_file1 = r_file1;
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
	
}
