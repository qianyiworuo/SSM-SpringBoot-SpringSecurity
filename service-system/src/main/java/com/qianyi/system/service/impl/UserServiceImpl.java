package com.qianyi.system.service.impl;

import com.qianyi.model.system.SysUser;
import com.qianyi.security.custom.LoginUser;
import com.qianyi.system.service.SysMenuService;
import com.qianyi.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysMenuService sysMenuService;

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
		//获取用户权限信息
		List<String> userPremsList = sysMenuService.getUserButtonList(sysUser.getId());
		//声明SimpleGrantedAuthority集合
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		//将用户权限信息放入集合中
		for (String prem: userPremsList) {
			authorities.add(new SimpleGrantedAuthority(prem));
		}
		//把数据封装成UserDetils对象
		return new LoginUser(sysUser, authorities);
	}
}
