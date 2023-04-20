package com.qianyi.system.controller;

import com.qianyi.common.result.Result;
import com.qianyi.model.vo.LoginVo;
import com.qianyi.system.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Api(tags = "登录主页接口")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 登录接口
     * @return
     */
    @ApiOperation("登录接口")
    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo){
        //1.根据username查询数据


        //2.数据是否为空

        //3.判断密码是否正确

        //4.判断用户是否可用

        //5.根据userid和username生成token字符串，通过map返回
        //{"code":20000,"data":{"token":"admin-token"}}
        HashMap<String, Object> loginMap = new HashMap<>();
        loginMap.put("token", "admin-token");
        return Result.ok(loginMap);
    }

    /**
     * 获取用户信息接口
     * @return
     */
    @ApiOperation("获取用户信息接口")
    @GetMapping("info")
    public Result info(){
        //{"code":20000,
        // "data":{"roles":["admin"],
        // "introduction":"I am a super administrator",
        // "avatar":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",
        // "name":"Super Admin"}}
        HashMap<String, Object> infoMap = new HashMap<>();
        infoMap.put("roles","[admin]");
        infoMap.put("introduction","厚礼蟹，王德发");
        infoMap.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        infoMap.put("name","厚礼蟹");
        return Result.ok(infoMap);
    }
}
