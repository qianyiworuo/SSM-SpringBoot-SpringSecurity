package com.qianyi.system.service.impl;

import com.qianyi.model.system.SysUser;
import com.qianyi.security.custom.LoginUser;
import com.qianyi.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserDetailsService {
	@Autowired
	private SysUserService sysUserService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//查询用户信息
		SysUser sysUser = sysUserService.getByUsername(username);
		if (sysUser == null) {
			throw new UsernameNotFoundException("用户名不存在！");
		}
		if(sysUser.getStatus().intValue() == 0){
			throw new RuntimeException("该用户已禁用！");
		}
		//TODO 查询对应权限信息

		//把数据封装成UserDetils对象
		return new LoginUser(sysUser, Collections.emptyList());
	}
}
