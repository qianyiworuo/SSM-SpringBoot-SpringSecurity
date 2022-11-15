package com.qianyi.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qianyi.common.result.Result;
import com.qianyi.model.system.SysUser;
import com.qianyi.model.vo.SysUserQueryVo;
import com.qianyi.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author qianyi
 * @since 2022-11-10
 */
@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    @ApiOperation("条件查询用户接口")
    @GetMapping("/{page}/{limit}")
    public Result wrapperQueryPage(
            @PathVariable Long page,
            @PathVariable Long limit,
            SysUserQueryVo sysUserQueryVo
    ){
        //创建分页对象
        Page<Object> params = new Page<>(page, limit);
        IPage<SysUser> userIPage = sysUserService.selectPage(params, sysUserQueryVo);
        return Result.ok(userIPage);
    }
    @ApiOperation("新增用户接口")
    @PostMapping("/save")
    public Result saveUser(@RequestBody SysUser sysUser){
        boolean isSave = sysUserService.save(sysUser);
        if(isSave){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }
    @ApiOperation("根据ID查询用户")
    @GetMapping("/queryUserById/{id}")
    public Result queryUserById(@PathVariable Long id){
        SysUser sysUser = sysUserService.getById(id);
        return Result.ok(sysUser);
    }
    @ApiOperation("修改用户接口")
    @PutMapping("/update")
    public Result update(@RequestBody SysUser sysUser){
        boolean isUpdate = sysUserService.updateById(sysUser);
        if(isUpdate){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }
    @ApiOperation("根据ID删除用户")
    @DeleteMapping("/removeById/{id}")
    public Result removeById(@PathVariable Long id){
        boolean isRemove = sysUserService.removeById(id);
        if(isRemove){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }
    @ApiOperation("批量删除用户")
    @DeleteMapping("batchRemove/{idList}")
    public Result batchRemove(@PathVariable List<Long> idList){
        boolean isRemove = sysUserService.removeByIds(idList);
        if(isRemove){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }
    @ApiOperation("更新状态")
    @GetMapping("/updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable Long id, @PathVariable Integer status){
        boolean isUpdate = sysUserService.updateStatus(id, status);
        if(isUpdate){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

}

