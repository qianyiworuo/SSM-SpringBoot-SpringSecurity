package com.qianyi.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qianyi.common.result.Result;
import com.qianyi.model.system.SysRole;
import com.qianyi.model.vo.SysRoleQueryVo;
import com.qianyi.system.exception.QianyiException;
import com.qianyi.system.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(tags = "角色管理接口")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 查询所有方法
     * @return
     */
    @ApiOperation("查询所有角色接口")
    @GetMapping("/findAll")
    public Result findAll(){
        try{
            int a = 10/0;
        }catch (QianyiException e){
            throw new QianyiException(1998,"自定义异常处理");
        }
        List<SysRole> roleList = sysRoleService.list();
        return Result.ok(roleList);
    }

    /**
     * 删除单个方法
     * @param id
     * @return
     */
    @ApiOperation("删除单个角色接口")
    @DeleteMapping("/remove/{id}")
    //restful风格
    public Result remove(
            @ApiParam(name = "id", value = "用户ID",required = true)
            @PathVariable Long id){
        boolean isSuccess = sysRoleService.removeById(id);
        if(isSuccess){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    /**
     * 条件分页查询角色接口
     * @return
     */
    @ApiOperation("条件分页查询角色接口")
    @GetMapping("/{page}/{limit}")
    public Result WrapperQueryPage(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value= "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "roleQueryVo", value = "条件对象", required = true)
            SysRoleQueryVo sysRoleQueryVo){
        //创建MybatisPlus分页对象
        Page<Object> pageParam = new Page<>(page, limit);
        IPage<SysRole> pageModel = sysRoleService.selectPage(pageParam, sysRoleQueryVo);
        Result<IPage<SysRole>> result = Result.ok(pageModel);
        return  result;
    }

    /**
     * 新增角色接口
     * @param sysRole
     * @return
     */
    @ApiOperation("新增角色接口")
    @PostMapping("/save")
    public Result saveRole(@RequestBody SysRole sysRole){
        boolean isSuccess = sysRoleService.save(sysRole);
        if(isSuccess){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }
    /**
     * 修改-根据ID查询
     */
    @ApiOperation("根据ID查询角色接口")
    @GetMapping("/findRoleById/{id}")
    public Result findRoleById(
            @ApiParam(name = "id", value = "用户ID", required = true)
            @PathVariable Long id){
        SysRole sysRole = sysRoleService.getById(id);
        return Result.ok(sysRole);
    }
    /**
     * 修改-执行修改动作
     */
    @ApiOperation("根据ID修改角色接口")
    @PutMapping("/update")
    public Result updateById(@RequestBody SysRole sysRole){
        boolean isSuccess = sysRoleService.updateById(sysRole);
        if(isSuccess){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }
    /**
     * 批量删除
     * json数组----Java的List集合
     */
    @ApiOperation("批量删除角色接口")
    @DeleteMapping("/batchRemove")
    public Result batchRemove(@RequestBody List<Long> ids){
        boolean isSuccess = sysRoleService.removeByIds(ids);
        if(isSuccess){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }
}
