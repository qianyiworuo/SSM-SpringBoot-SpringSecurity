package com.qianyi.system.controller;


import com.qianyi.common.result.Result;
import com.qianyi.model.system.SysMenu;
import com.qianyi.system.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author qianyi
 * @since 2022-11-18
 */
@Api(tags = "菜单管理接口")
@RestController
@RequestMapping("/admin/system/sysMenu")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;

    @ApiOperation("查询菜单列表(树形)接口")
    @GetMapping("/findNodes")
    public Result findNodes(){
       List<SysMenu> menuList = sysMenuService.findNodes();

        return Result.ok(menuList);
    }
    @ApiOperation("添加菜单接口")
    @PostMapping("/save")
    public Result saveMenu(@RequestBody SysMenu sysMenu){
        boolean isSuccess = sysMenuService.save(sysMenu);
        if (isSuccess){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }
    @ApiOperation("查询单个菜单接口")
    @GetMapping("/getById/{id}")
    public Result getById(@PathVariable Long id){
        SysMenu sysMenu = sysMenuService.getById(id);
        return Result.ok(sysMenu);
    }
    @ApiOperation("修改单个菜单接口")
    @PutMapping("/updateById")
    public Result updateById(@RequestBody SysMenu sysMenu){
        boolean isSuccess = sysMenuService.updateById(sysMenu);
        if (isSuccess){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }
    @ApiOperation("批量删除菜单接口")
    @DeleteMapping("/removeIds/{ids}")
    public Result removeIds(@PathVariable List<SysMenu> ids){
        boolean isSuccess = sysMenuService.removeByIds(ids);
        if (isSuccess){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }
    @ApiOperation("删除菜单接口")
    @DeleteMapping("/remove/{id}")
    public Result removeById(@PathVariable Long id){
        boolean isSuccess = sysMenuService.removeMenuById(id);
        if (isSuccess){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }
    @ApiOperation("根据角色查询菜单接口")
    @GetMapping("/toAssign/{roleId}")
    public Result toAssign(@PathVariable Long roleId){
        List<SysMenu> list = sysMenuService.findSysMenuByRoleId(roleId);
        return Result.ok(list);
    }
}

