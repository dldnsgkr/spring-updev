package com.updev.admin;

import com.updev.member.Signup;

public interface ServiceAdmin {

	public Signup infoupdate(String m_id);

	public Signup info_update(String profile, String pw, String nick, String name, String mail, String tel, String id);

}
