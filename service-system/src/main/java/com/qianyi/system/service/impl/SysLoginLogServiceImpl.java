package com.qianyi.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qianyi.model.system.SysLoginLog;
import com.qianyi.model.vo.SysLoginLogQueryVo;
import com.qianyi.security.service.LoginLogService;
import com.qianyi.system.mapper.SysLoginLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysLoginLogServiceImpl extends ServiceImpl<SysLoginLogMapper, SysLoginLog> implements LoginLogService {

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
	//条件分页查询
	@Override
	public IPage<SysLoginLog> selectPage(Page<Object> pageParam, SysLoginLogQueryVo sysLoginLogQueryVo) {
		IPage<SysLoginLog> pageModel = baseMapper.selectPage(pageParam, sysLoginLogQueryVo);
		return pageModel;
	}
}
