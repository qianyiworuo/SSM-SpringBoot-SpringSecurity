package com.qianyi.system.service;

import com.qianyi.model.system.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author qianyi
 * @since 2022-11-18
 */
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> findNodes();
}
