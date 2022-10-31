package com.qianyi.system.test;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.qianyi.model.system.SysRole;
import com.qianyi.system.mapper.SysRoleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.management.relation.RoleInfo;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class SysRoleMapperTest {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * 查询测试
     */
    @Test
    public void GetAll() {
        List<SysRole> roleList = sysRoleMapper.selectList(null);
        for (SysRole sysRole : roleList) {
            System.out.println(sysRole);
        }

    }

    /**
     * 插入测试
     */
    @Test
    public void AddOne() {
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("王五");
        sysRole.setRoleCode("SYSTEM");
        sysRole.setDescription("系统管理员");
        int nResult = sysRoleMapper.insert(sysRole);
        if (nResult > 0) {
            System.out.println("插入成功");
            System.out.println(sysRole.getId());
        }
    }
    /**
     * 修改测试
     */
    @Test
    public void UpdateOne(){
        SysRole updateRole = new SysRole();
        List<SysRole> roleList = sysRoleMapper.selectList(null);
        for (SysRole sysRole: roleList) {
            if(sysRole.getRoleName().equals("零基础")){
                updateRole = sysRole;
            }
        }
        System.out.println(updateRole);
        updateRole.setRoleName("测试用户");
        int nResult = sysRoleMapper.updateById(updateRole);
        if (nResult > 0){
            System.out.println("修改成功");
        }

    }
    /**
     * ID删除测试
     */
    @Test
    public void DeleteById(){
        int nResult = sysRoleMapper.deleteById(5);
        if(nResult > 0){
            System.out.println("删除成功");
        }
    }
    /**
     * 批量删除测试
     */
    @Test
    public void DeleteBatch(){
        int nResult = sysRoleMapper.deleteBatchIds(Arrays.asList(4,3));
        if(nResult > 0){
            System.out.println("删除成功");
        }
    }
    /**
     * 条件查询
     */
    @Test
    public void WrapperQuery(){
        //创建条件构造器对象
        QueryWrapper<SysRole> roleWrapper = new QueryWrapper<>();
        //设置条件
        //roleWrapper.eq("role_name","张三");
        roleWrapper.like("description", "普通");
        //调用查询方法
        List<SysRole> roleList = sysRoleMapper.selectList(roleWrapper);
        System.out.println(roleList);
    }
    /**
     * 条件删除
     */
    @Test
    public void WrapperDelete(){
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        QueryWrapper<SysRole> wrapper = queryWrapper.eq("role_name", "李四");
        int delete = sysRoleMapper.delete(wrapper);
        System.out.println("删除成功");
    }
}

