package com.updev.member;


public interface ServiceMember {

	public void insert(String m_id, String m_pw, String m_nick, String m_name, String m_mail, String m_tel,
			String m_field, String m_jdate);

	public Signup loginselect(String id, String pw);

	public void profileupdate(String m_nick, String m_profile, String m_id, String m_pw, String m_name, String m_mail, String m_tel, String m_field, String up_nick);

	public Signup profileupdatecheck(String m_nick);

	public int test(String id);

	public int idCheck(String m_id);

	public void profileboardupdate(String m_nick, String up_nick);
 
	public void balupdate(String m_nick, String up_nick);

	public void suupdate(String m_nick, String up_nick);

	public void profilereportupdate(String m_nick, String up_nick);

	public void albalupdate(String m_nick, String up_nick);

	public void alsuupdate(String m_nick, String up_nick);

	public String find_id(String name, String mail);

	public int find_pw(String name, String mail); //ī��Ʈ(1) if else�� �̸��� �̸��Ϸ� ���� ã��

	public int find_mpw(String id, String pw); //ī��Ʈ(1) if else�� �ش� ������ ��й�ȣ�� �´��� Ȯ���ϱ�

	public void update_pw(String pw, String npw);

	public int nicktest(String nick);

	public int nickCheck(String m_nick);

}
