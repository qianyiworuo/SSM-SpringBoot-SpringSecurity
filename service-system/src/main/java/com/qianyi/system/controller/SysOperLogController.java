package com.qianyi.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qianyi.common.result.Result;
import com.qianyi.model.system.SysOperLog;
import com.qianyi.model.vo.SysOperLogQueryVo;
import com.qianyi.system.service.OperLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="用户操作日志接口")
@RestController
@RequestMapping("/admin/system/sysOperLog")
public class SysOperLogController {
    @Autowired
    private OperLogService operLogService;
    @ApiOperation("条件分页查询操作日志接口")
    @GetMapping("{page}/{limit}")
    public Result WrapperQueryPage(
            @ApiParam(name = "page",value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit",value = "每页记录数", required = true)
            @PathVariable Long limit,
            SysOperLogQueryVo sysOperLogQueryVo){
        //创建MybatisPlus分页对象
        Page<Object> pageParam = new Page<>(page, limit);
        IPage<SysOperLog> pageModel = operLogService.selectPage(pageParam, sysOperLogQueryVo);
        return Result.ok(pageModel);
    }

}
