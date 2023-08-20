package com.qianyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qianyi.model.system.SysOperLog;
import com.qianyi.model.vo.SysOperLogQueryVo;
import com.qianyi.system.mapper.SysOperLogMapper;
import com.qianyi.system.service.OperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class SysOperLogServiceImpl implements OperLogService {
	@Autowired
	private SysOperLogMapper sysOperLogMapper;
	@Override
	public void saveSysLog(SysOperLog sysOperLog) {
		sysOperLogMapper.insert(sysOperLog);
	}

	@Override
	public IPage<SysOperLog> selectPage(Long page, Long limit, SysOperLogQueryVo sysOperLogQueryVo) {
		Page<SysOperLog> pageParam = new Page<>(page,limit);
		//获取条件值
		String title = sysOperLogQueryVo.getTitle();
		String operName = sysOperLogQueryVo.getOperName();
		String createTimeBegin = sysOperLogQueryVo.getCreateTimeBegin();
		String createTimeEnd = sysOperLogQueryVo.getCreateTimeEnd();
		//封装参数
		LambdaQueryWrapper<SysOperLog> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		if(!StringUtils.isEmpty(title)){

			lambdaQueryWrapper.like(SysOperLog::getTitle,title);
		}
		if(!StringUtils.isEmpty(operName)){

			lambdaQueryWrapper.like(SysOperLog::getOperName,operName);
		}
		if(!StringUtils.isEmpty(createTimeBegin)){

			lambdaQueryWrapper.ge(SysOperLog::getCreateTime,createTimeBegin);
		}
		if(!StringUtils.isEmpty(createTimeEnd)){

			lambdaQueryWrapper.like(SysOperLog::getCreateTime,createTimeEnd);
		}

		Page<SysOperLog> sysOperLogPage = sysOperLogMapper.selectPage(pageParam, lambdaQueryWrapper);
		//调用mapper方法实现分页条件查询
		return sysOperLogPage;
	}
}
