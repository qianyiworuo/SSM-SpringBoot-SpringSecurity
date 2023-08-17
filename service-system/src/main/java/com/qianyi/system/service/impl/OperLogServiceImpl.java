package com.qianyi.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qianyi.model.system.SysOperLog;
import com.qianyi.model.vo.SysOperLogQueryVo;
import com.qianyi.system.mapper.SysLoginLogMapper;
import com.qianyi.system.mapper.SysOperLogMapper;
import com.qianyi.system.service.OperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperLogServiceImpl extends ServiceImpl<SysOperLogMapper,SysOperLog> implements OperLogService {
    @Autowired
    private SysOperLogMapper sysOperLogMapper;
    @Override
    public void saveSysLog(SysOperLog sysOperLog) {
        sysOperLogMapper.insert(sysOperLog);

    }

    @Override
    public IPage<SysOperLog> selectPage(Page<Object> pageParam, SysOperLogQueryVo sysOperLogQueryVo) {
        IPage<SysOperLog> pageModel = baseMapper.selectPage(pageParam, sysOperLogQueryVo);
        return pageModel;
    }
}
