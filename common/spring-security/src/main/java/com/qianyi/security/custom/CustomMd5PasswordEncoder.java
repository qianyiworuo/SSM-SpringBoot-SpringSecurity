//package com.qianyi.security.custom;
//
//import com.qianyi.common.utils.MD5;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
////自定义密码组件
//@Component
//public class CustomMd5PasswordEncoder implements PasswordEncoder {
//    @Override
//    public String encode(CharSequence rawPassword) {
//        return MD5.MD5Lower(rawPassword.toString());
//    }
//
//    @Override
//    public boolean matches(CharSequence rawPassword, String encodedPassword) {
//        return encodedPassword.equals(MD5.MD5Lower(rawPassword.toString()));
//    }
//}
