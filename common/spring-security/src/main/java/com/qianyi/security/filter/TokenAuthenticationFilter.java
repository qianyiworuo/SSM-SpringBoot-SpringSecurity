package com.qianyi.security.filter;

import com.alibaba.fastjson.JSON;
import com.qianyi.common.result.Result;
import com.qianyi.common.result.ResultCodeEnum;
import com.qianyi.common.utils.JwtHelper;
import com.qianyi.common.utils.ResponseUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

	private RedisTemplate redisTemplate;

	public TokenAuthenticationFilter(RedisTemplate redisTemplate1){

		this.redisTemplate = redisTemplate1;
	}
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		logger.info("uri:"+request.getRequestURI());
		//如果是登录接口，直接放行
		if("/admin/system/index/login".equals(request.getRequestURI())) {
			chain.doFilter(request, response);
			return;
		}

		UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
		if(null != authentication) {
			SecurityContextHolder.getContext().setAuthentication(authentication);
			chain.doFilter(request, response);
		} else {
			ResponseUtil.out(response, Result.build(null, ResultCodeEnum.PERMISSION));
		}
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		// token置于header里
		String token = request.getHeader("token");
		logger.info("token:"+token);
		if (!StringUtils.isEmpty(token)) {
			String useruame = JwtHelper.getUsername(token);
			logger.info("useruame:"+useruame);
			if (!StringUtils.isEmpty(useruame)) {
				//根据username从redis中获取用户权限
				String authoritiesString = (String) redisTemplate.opsForValue().get(useruame);
				//将用户权限数据转换为List集合
				List<Map> authoritiesList = JSON.parseArray(authoritiesString, Map.class);
				//声明SimpleGrantedAuthority权限集合对象
				List<SimpleGrantedAuthority> authorities  = new ArrayList<>();
				for (Map map: authoritiesList) {
					authorities.add(new SimpleGrantedAuthority((String)map.get("authority")));
				}
				return new UsernamePasswordAuthenticationToken(useruame, null, authorities);
			}
		}
		return null;
	}
}
