package com.qianyi.system.exception;

import com.qianyi.common.result.Result;
import com.qianyi.common.result.ResultCodeEnum;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;



@ControllerAdvice
public class ExceptionDeal {
    //全局异常处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail().message("执行全局异常处理！");
    }
    //特定异常处理
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e){
        e.printStackTrace();
        return Result.fail().message("执行特定异常处理！");
    }
    //自定义异常处理
    @ExceptionHandler(QianyiException.class)
    @ResponseBody
    public Result error(QianyiException e){
        e.printStackTrace();
        return Result.fail().message(e.getMessage()).code(e.getCode());
    }
    //spring security异常处理
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public Result error(AccessDeniedException e) throws AccessDeniedException{
        return Result.fail().code(ResultCodeEnum.PERMISSION.getCode()).message("没有操作权限!");
    }
}
