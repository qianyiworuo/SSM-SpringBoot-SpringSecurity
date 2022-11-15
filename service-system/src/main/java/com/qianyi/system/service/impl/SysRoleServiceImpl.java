package com.qianyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qianyi.model.system.SysRole;
import com.qianyi.model.system.SysUserRole;
import com.qianyi.model.vo.AssginRoleVo;
import com.qianyi.model.vo.SysRoleQueryVo;
import com.qianyi.system.mapper.SysRoleMapper;
import com.qianyi.system.mapper.SysUserRoleMapper;
import com.qianyi.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    //条件分页查询
    @Override
    public IPage<SysRole> selectPage(Page<Object> pageParam, SysRoleQueryVo sysRoleQueryVo) {
        IPage<SysRole> pageModel = baseMapper.selectPage(pageParam, sysRoleQueryVo);
        return pageModel;
    }
//根据用户ID获取用户已分配的角色，获取所有角色信息
    @Override
    public Map<String, Object> getRoleByUserId(Long userId) {
        //1.获取所有角色信息
        List<SysRole> allRoles = baseMapper.selectList(null);
        //2.根据用户ID获取用户已分配的角色
        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<>();
        //根据用户id查询
        queryWrapper.eq("user_id",userId);
        //获取用户已分配的角色
        List<SysUserRole> userRoleList = sysUserRoleMapper.selectList(queryWrapper);
        //获取用户已分配的角色id
        List<Long> userRoleIds = new ArrayList<>();
        for (SysUserRole userRole: userRoleList) {
            Long roleId = userRole.getRoleId();
            userRoleIds.add(roleId);
        }
        //创建返回的Map
        HashMap<String, Object> returnMap = new HashMap<>();
        returnMap.put("allRoles", allRoles);
        returnMap.put("userRoleIds", userRoleIds);
        return returnMap;
    }
//根据用户分配角色
    @Override
    public void doAssign(AssginRoleVo assginRoleVo) {
        //1.根据用户id删除用户已分配的角色id
        String userId = assginRoleVo.getUserId();
        Long lUserId = Long.valueOf(userId);
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", lUserId);
        sysUserRoleMapper.delete(wrapper);
        //2.获取当前用户要的分配的角色id集合
        List<String> roleIdList = assginRoleVo.getRoleIdList();
        //3.遍历集合，将角色id插用户角色表中
        for (String RoleId: roleIdList) {
            Long lRoleId = Long.valueOf(RoleId);
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(lRoleId);
            sysUserRole.setUserId(lUserId);
            sysUserRoleMapper.insert(sysUserRole);
        }
    }
}
