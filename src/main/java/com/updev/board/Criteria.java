package com.updev.board;

public class Criteria {
	private int pageNum;
	private int amount;
	private int startNum;
	
	private String sname;	//검색
	private String keyword;	//검색
	
	public Criteria() {
		//객체 생성시 기본 생성자를 호출하여 매개변수를 줘서 매개변수를 가지고 있는 생성자 함수 호출
		this(1, 10); //생성자에서 pageNum=1, amount=10으로 설정
	}
	
	// 기본 생성자 설정
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public int getStartNum() {
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
}
