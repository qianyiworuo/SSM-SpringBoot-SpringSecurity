package com.qianyi.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qianyi.model.system.SysOperLog;
import com.qianyi.model.vo.SysOperLogQueryVo;

public interface OperLogService extends IService<SysOperLog> {
    public void saveSysLog(SysOperLog sysOperLog);

    IPage<SysOperLog> selectPage(Page<Object> pageParam, SysOperLogQueryVo sysOperLogQueryVo);
}
