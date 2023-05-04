package com.qianyi.system.service;

import com.qianyi.model.system.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qianyi.model.vo.AssginMenuVo;
import com.qianyi.model.vo.RouterVo;

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

    boolean removeMenuById(Long id);

    List<SysMenu> findSysMenuByRoleId(Long roleId);

    void doAssign(AssginMenuVo assginMenuVo);

    //根据userid查询菜单权限
    List<RouterVo> getUserMenuList(Long userId);
    //根据userid查询按钮权限
    List<String> getUserButtonList(Long userId);
}
