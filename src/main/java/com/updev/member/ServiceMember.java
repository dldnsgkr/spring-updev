package com.updev.member;


public interface ServiceMember {

	public void insert(String m_profile, String m_id, String m_pw, String m_nick, String m_name, String m_mail, String m_tel,
			String m_field, String m_jdate);

	public Signup loginselect(String id, String pw);

	public void profileupdate(String m_nick, String m_profile, String m_id, String m_pw, String m_name, String m_mail, String m_tel, String m_field, String up_nick);

	public Signup profileupdatecheck(String m_nick);

<<<<<<< HEAD
	public int test(String id);
=======
	public int idCheck(String m_id);

	public void profileboardupdate(String m_nick, String up_nick);

	public void balupdate(String m_nick, String up_nick);

	public void suupdate(String m_nick, String up_nick);

	public void profilereportupdate(String m_nick, String up_nick);

	public void albalupdate(String m_nick, String up_nick);

	public void alsuupdate(String m_nick, String up_nick);
>>>>>>> 96a067e3f4af5c77e75d27620587f9ac8cdd292b
	

}
