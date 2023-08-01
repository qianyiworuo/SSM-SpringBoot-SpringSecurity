package com.qianyi.security.custom;

import com.qianyi.common.utils.MD5;
import org.springframework.security.crypto.password.PasswordEncoder;

public class LoginMd5PasswordEncoder implements PasswordEncoder {
	@Override
	public String encode(CharSequence charSequence) {
		return MD5.MD5Lower(charSequence.toString());
	}

	/***
	 *
	 * @param charSequence 用户输入的密码
	 * @param s 数据库中的密码
	 * @return
	 */
	@Override
	public boolean matches(CharSequence charSequence, String s) {
		return s.equals(MD5.MD5Lower(charSequence.toString()));
	}
}
