package com.qianyi.security.filter;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qianyi.common.result.Result;
import com.qianyi.common.result.ResultCodeEnum;
import com.qianyi.common.utils.IpUtil;
import com.qianyi.common.utils.JwtHelper;
import com.qianyi.common.utils.ResponseUtil;
import com.qianyi.model.system.SysLoginLog;
import com.qianyi.model.vo.LoginVo;
import com.qianyi.security.custom.LoginUser;
import com.qianyi.security.service.LoginLogService;
import org.springframework.data.redis.core.RedisTemplate;
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
	private RedisTemplate redisTemplate;
	private LoginLogService loginLogService;
	//构造方法
	public TokenLoginFilter(AuthenticationManager authenticationManager, RedisTemplate redisTemplate, LoginLogService loginLogService) {
		this.redisTemplate = redisTemplate;
		this.loginLogService = loginLogService;
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
		LoginUser loginUser = (LoginUser) auth.getPrincipal();
		//生成token
		String token = JwtHelper.createToken(loginUser.getSysUser().getId(), loginUser.getSysUser().getUsername());
		//保存权限数据
		redisTemplate.opsForValue().set(loginUser.getUsername(), JSON.toJSONString(loginUser.getAuthorities()));
		//保存登录数据
		loginLogService.LoginLogRecord(loginUser.getSysUser().getUsername(), IpUtil.getIpAddress(request), 1, "登录成功");
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
