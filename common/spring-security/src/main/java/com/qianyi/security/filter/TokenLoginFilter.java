package com.qianyi.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qianyi.common.result.Result;
import com.qianyi.common.result.ResultCodeEnum;
import com.qianyi.common.utils.JwtHelper;
import com.qianyi.common.utils.ResponseUtil;
import com.qianyi.model.vo.LoginVo;
import com.qianyi.security.custom.CustomUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 登录过滤器，继承UsernamePasswordAuthenticationFilter，对用户名密码进行登录校验
 * </p>
 */
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {
	//构造方法
	public TokenLoginFilter(AuthenticationManager authenticationManager) {
		this.setAuthenticationManager(authenticationManager);
		this.setPostOnly(false);
		//指定登录接口及提交方式，可以指定任意路径
		this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/admin/system/index/login", "POST"));
	}

	//获取登录信息认证方法
	@Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
            LoginVo loginVo = new ObjectMapper().readValue(req.getInputStream(), LoginVo.class);
            Authentication authenticationToken = new UsernamePasswordAuthenticationToken(loginVo.getUsername(), loginVo.getPassword());
            return this.getAuthenticationManager().authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

	//认证成功方法
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
											Authentication auth) throws IOException, ServletException {
		//获取认证对象
		CustomUser customUser = (CustomUser) auth.getPrincipal();
		//生成token
		String token = JwtHelper.createToken(customUser.getSysUser().getId(), customUser.getSysUser().getUsername());
		//返回
		Map<String, Object> map = new HashMap<>();
		map.put("token", token);
		ResponseUtil.out(response, Result.ok(map));
	}

	//认证失败方法
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
											  AuthenticationException e) throws IOException, ServletException {

		if(e.getCause() instanceof RuntimeException) {
			ResponseUtil.out(response, Result.build(null, 204, e.getMessage()));
		} else {
			ResponseUtil.out(response, Result.build(null, ResultCodeEnum.LOGIN_MOBLE_ERROR));
		}
	}

}
