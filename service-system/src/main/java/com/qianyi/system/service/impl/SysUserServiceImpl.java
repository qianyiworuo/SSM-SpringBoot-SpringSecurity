package com.qianyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qianyi.model.system.SysUser;
import com.qianyi.model.vo.RouterVo;
import com.qianyi.model.vo.SysRoleQueryVo;
import com.qianyi.model.vo.SysUserQueryVo;
import com.qianyi.system.mapper.SysUserMapper;
import com.qianyi.system.service.SysMenuService;
import com.qianyi.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    SysMenuService sysMenuService;
    @Override
    public IPage<SysUser> selectPage(Page<Object> params, SysUserQueryVo sysUserQueryVo) {
        IPage<SysUser> pageModel = baseMapper.selectPage(params, sysUserQueryVo);
        return pageModel;
    }

    @Override
    public boolean updateStatus(Long id, Integer status) {
        SysUser sysUser = baseMapper.selectById(id);
        sysUser.setStatus(status);
        int nCount = baseMapper.updateById(sysUser);
        if(nCount > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public SysUser getByUsername(String username) {
//        LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.eq(SysUser::getUsername, username);
//        SysUser sysUser = sysUserMapper.selectOne(lambdaQueryWrapper);
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        return sysUser;
    }

    //根据用户名称获取用户信息（基本信息、菜单权限、按钮权限数据）
    @Override
    public Map<String, Object> getUserInfo(String username) {
        //根据username查询用户基本信息
        SysUser sysUser = this.getByUsername(username);
        Long userId = sysUser.getId();
        //根据userid查询菜单权限
        List<RouterVo> routerVoList = sysMenuService.getUserMenuList(userId);

        //根据userid查询用户权限
        List<String> buttonList = sysMenuService.getUserButtonList(userId);
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", username);
        result.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        result.put("roles", "admin");
        //菜单权限数据
        result.put("routers", routerVoList);

        //按钮权限数据
        result.put("buttons", buttonList);

        return result;
    }
}
