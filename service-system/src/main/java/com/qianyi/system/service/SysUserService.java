package com.qianyi.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qianyi.model.system.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qianyi.model.vo.SysUserQueryVo;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author qianyi
 * @since 2022-11-10
 */
public interface SysUserService extends IService<SysUser> {

    IPage<SysUser> selectPage(Page<Object> params, SysUserQueryVo sysUserQueryVo);

    boolean updateStatus(Long id, Integer status);
}
