package com.qianyi.system.exception;

import com.qianyi.common.result.Result;
import com.qianyi.common.result.ResultCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QianyiException extends RuntimeException{
    private Integer code;
    private String message;
    /**
     * 通过状态码和错误消息创建异常对象
     * @param code
     * @param message
     */
//    public QianyiException(Integer code, String message) {
//        super(message);
//        this.code = code;
//        this.message = message;
//    }
    /**
     * 接收枚举类型对象
     * //@param resultCodeEnum
     */
//    public QianyiException(ResultCodeEnum resultCodeEnum) {
//        super(resultCodeEnum.getMessage());
//        this.code = resultCodeEnum.getCode();
//        this.message = resultCodeEnum.getMessage();
//    }

    @Override
    public String toString() {
        return "GuliException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }

    public void printStackTrace() {

    }
}
