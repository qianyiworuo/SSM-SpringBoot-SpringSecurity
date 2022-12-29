package com.qianyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qianyi.common.utils.MenuHelper;
import com.qianyi.model.system.SysMenu;
import com.qianyi.model.system.SysRoleMenu;
import com.qianyi.system.exception.QianyiException;
import com.qianyi.system.mapper.SysMenuMapper;
import com.qianyi.system.mapper.SysRoleMenuMapper;
import com.qianyi.system.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author qianyi
 * @since 2022-11-18
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;
    private SysRoleMenuMapper sysRoleMenuMapper;
/**
 * 菜单列表
 */
    @Override
    public List<SysMenu> findNodes() {
        //获取所有菜单
        List<SysMenu> menuList = sysMenuMapper.selectList(null);
        //转换菜单数据
        List<SysMenu> ResultList = MenuHelper.buildTree(menuList);
        return ResultList;
    }
/**
 * 删除菜单列表
 */
    @Override
    public boolean removeMenuById(Long id) {
        //判断菜单下是否还有子菜单
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id" , id);
        Integer count = sysMenuMapper.selectCount(wrapper);
        if(count > 0){
            throw new QianyiException(201, "请先删除子菜单");
        }
        int nCount = sysMenuMapper.deleteById(id);
        if(nCount > 0){
            return true;
        }
        return false;
    }

    /**
     * 根据角色ID获取权限列表
     * @param roleId
     * @return
     */
    @Override
    public List<SysMenu> findSysMenuByRoleId(Long roleId) {
        //1.获取所有状态为1的菜单
        //QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        //queryWrapper.eq("status", 1);
        LambdaQueryWrapper<SysMenu> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysMenu::getStatus,1);
        List<SysMenu> menuList = baseMapper.selectList(lambdaQueryWrapper);
        //2.根据roleId查询角色已经分配的菜单列表
        LambdaQueryWrapper<SysRoleMenu> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
        lambdaQueryWrapper1.eq(SysRoleMenu::getRoleId, roleId);
        List<SysRoleMenu> roleMenuList = sysRoleMenuMapper.selectList(lambdaQueryWrapper1);
        //3.根据第二步获取角色分配的的菜单id
        for (SysRoleMenu sysRoleMenu: roleMenuList) {
            Long menuId = sysRoleMenu.getMenuId();
            //Long menuIdL = Long.parseLong(menuId);
            //4.数据处理：isSelect 如果角色分配的菜单id与所有菜单中id相同，即为菜单选中isSelect = true;反之为 false。
            for (SysMenu sysMenu: menuList) {
                if(menuId.equals(sysMenu.getId())){
                    sysMenu.setSelect(true);
                }
            }
        };
        //5.转换成树形结构MenuHelper方法
        List<SysMenu> menus = MenuHelper.buildTree(menuList);
        return menus;
    }
}
