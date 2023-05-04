package com.qianyi.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qianyi.model.system.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qianyi.model.vo.SysUserQueryVo;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author qianyi
 * @since 2022-11-10
 */
public interface SysUserService extends IService<SysUser> {

    //用户列表
    IPage<SysUser> selectPage(Page<Object> params, SysUserQueryVo sysUserQueryVo);

    //更改用户状态
    boolean updateStatus(Long id, Integer status);

    //username查询
    SysUser getByUsername(String username);

    //根据用户名称获取用户信息（基本信息、菜单权限、按钮权限数据）
    Map<String, Object> getUserInfo(String username);
}
