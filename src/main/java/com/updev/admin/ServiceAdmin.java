package com.updev.admin;

import com.updev.member.Signup;

public interface ServiceAdmin {

	public Signup infoupdate(String m_id);

	public void info_update(String pw, String nick, String name, String mail, String tel, String field,
			String id);

	public void mylist_write();

}
