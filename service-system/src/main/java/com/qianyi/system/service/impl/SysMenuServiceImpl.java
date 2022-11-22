package com.qianyi.system.service.impl;

import com.qianyi.model.system.SysMenu;
import com.qianyi.system.mapper.SysMenuMapper;
import com.qianyi.system.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
//菜单列表
    @Override
    public List<SysMenu> findNodes() {
        return null;
    }
}
