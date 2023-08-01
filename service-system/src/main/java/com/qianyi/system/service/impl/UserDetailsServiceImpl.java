//package com.qianyi.system.service.impl;
//
//import com.qianyi.model.system.SysUser;
//import com.qianyi.security.custom.CustomUser;
//import com.qianyi.system.service.SysUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import java.util.Collections;
//
////根据用户名查询用户信息
//@Component
//public class UserDetailsServiceImpl implements UserDetailsService {
//    @Autowired
//    private SysUserService sysUserService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        SysUser sysUser = sysUserService.getByUsername(username);
//        if(null == sysUser) {
//            throw new UsernameNotFoundException("用户名不存在！");
//        }
//
//        if(sysUser.getStatus().intValue() == 0) {
//            throw new RuntimeException("账号已停用");
//        }
//        return new CustomUser(sysUser, Collections.emptyList());
//    }
//}
