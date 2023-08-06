package com.qianyi.system.service.impl;

import com.qianyi.model.system.SysLoginLog;
import com.qianyi.security.service.LoginLogService;
import com.qianyi.system.mapper.SysLoginLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysLoginLogServiceImpl implements LoginLogService {

	@Autowired
	private SysLoginLogMapper sysLoginLogMapper;
	@Override
	public void LoginLogRecord(String username, String ipAddr, Integer status, String msg) {

		SysLoginLog sysLoginLog = new SysLoginLog();
		sysLoginLog.setUsername(username);
		sysLoginLog.setIpaddr(ipAddr);
		sysLoginLog.setStatus(status);
		sysLoginLog.setMsg(msg);
		sysLoginLogMapper.insert(sysLoginLog);

	}
}
