package com.qianyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qianyi.common.utils.MenuHelper;
import com.qianyi.model.system.SysMenu;
import com.qianyi.system.exception.QianyiException;
import com.qianyi.system.mapper.SysMenuMapper;
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
}
