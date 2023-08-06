package com.qianyi.security.service;


public interface LoginLogService {
	public void LoginLogRecord(String username, String ipAddr, Integer status, String msg );
}
