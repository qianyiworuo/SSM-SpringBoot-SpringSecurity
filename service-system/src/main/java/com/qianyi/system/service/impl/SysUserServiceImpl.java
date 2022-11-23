package com.qianyi.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qianyi.model.system.SysUser;
import com.qianyi.model.vo.SysRoleQueryVo;
import com.qianyi.model.vo.SysUserQueryVo;
import com.qianyi.system.mapper.SysUserMapper;
import com.qianyi.system.service.SysUserService;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
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
}
