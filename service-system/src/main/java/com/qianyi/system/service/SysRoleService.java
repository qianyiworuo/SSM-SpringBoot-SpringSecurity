package com.qianyi.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qianyi.model.system.SysRole;
import com.qianyi.model.vo.SysRoleQueryVo;


public interface SysRoleService extends IService<SysRole> {
    IPage<SysRole> selectPage(Page<Object> pageParam, SysRoleQueryVo sysRoleQueryVo);
}
