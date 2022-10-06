package com.updev.admin;

public class Criteria {
	private int pageNum;
	private int amount;
	private int startNum;
	
	private String sname;	//�˻�
	private String keyword;	//�˻�
	
	public Criteria() {
		//��ü ������ �⺻ �����ڸ� ȣ���Ͽ� �Ű������� �༭ �Ű������� ������ �ִ� ������ �Լ� ȣ��
		this(1, 10); //�����ڿ��� pageNum=1, amount=10���� ����
	}
	
	// �⺻ ������ ����
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