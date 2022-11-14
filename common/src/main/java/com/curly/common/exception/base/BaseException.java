package com.curly.common.exception.base;

import lombok.Data;

/**
 * @author broWsJle
 * @date 2022/11/14 18:26
 */
@Data
public class BaseException extends RuntimeException {

    private String code;

    private String message;

    public BaseException() {
        super();
    }

    // 自定义异常构造方法
    public BaseException (BaseErrorEnum baseErrorEnum) {
        super (baseErrorEnum.getCode());
        this.code = baseErrorEnum.getCode();
        this.message = baseErrorEnum.getMessage();
    }

}
