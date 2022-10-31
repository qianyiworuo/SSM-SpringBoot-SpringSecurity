package com.qianyi.system.test;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qianyi.model.system.SysRole;
import com.qianyi.system.service.SysRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class SysRoleServiceTest {
    @Autowired
    private SysRoleService sysRoleService;
    @Test
    public void FindAll(){
        List<SysRole> list = sysRoleService.list();
        System.out.println(list);
    }
    @Test
    public void AddOne(){
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("零基础");
        sysRole.setRoleCode("SYSTEM");
        sysRole.setDescription("系统管理员");
        boolean save = sysRoleService.save(sysRole);
        if(save){
            System.out.println(sysRole.getId());
        }
    }
    @Test
    public void DeleteOne(){
        boolean remove = sysRoleService.removeById(6);
        if(remove){
            System.out.println("删除成功");
        }
    }
    @Test
    public void DeleteBatch(){
        boolean b = sysRoleService.removeByIds(Arrays.asList(2, 4));
        if(b){
            System.out.println("批量修改成功");
        }
    }
    @Test
    public void UpdateOne(){
        SysRole role = sysRoleService.getById(5);
        role.setRoleName("零基础");
        boolean updateById = sysRoleService.updateById(role);
        if(updateById){
            System.out.println("修改成功");
        }
    }
    @Test
    public void wrapperQuery(){
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        QueryWrapper<SysRole> like = wrapper.like("ROLE_NAME", "零");
        Map<String, Object> map = sysRoleService.getMap(like);
        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Object> entry = iterator.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(value);
        }
        System.out.println(map);
    }
    @Test
    public void QueryWrapper(){
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        QueryWrapper<SysRole> eq = wrapper.eq("ROLE_CODE", "SYSTEM");
        List<SysRole> list = sysRoleService.list(eq);
        System.out.println(list);
    }
}
