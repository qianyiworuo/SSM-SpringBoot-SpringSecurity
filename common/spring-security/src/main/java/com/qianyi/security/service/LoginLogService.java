package com.qianyi.security.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qianyi.model.system.SysLoginLog;
import com.qianyi.model.vo.SysLoginLogQueryVo;

public interface LoginLogService extends IService<SysLoginLog> {
	public void LoginLogRecord(String username, String ipAddr, Integer status, String msg );

	IPage<SysLoginLog> selectPage(Page<Object> pageParam, SysLoginLogQueryVo sysLoginLogQueryVo);
}
