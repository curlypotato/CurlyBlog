package com.curly.common.exception;

import com.curly.common.data.BaseResponse;
import com.curly.common.data.RespGenerator;
import com.curly.common.exception.base.BaseErrorEnum;
import com.curly.common.exception.base.BaseException;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.logging.Log;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author broWsJle
 * @date 2022/11/14 18:32
 */
public class GlobalExceptionHandler {

    /** 静态变量，系统日志 */
    private static final Log logger = LogFactory.getLog(GlobalExceptionHandler.class);

    // 处理自定义异常
    @ExceptionHandler(value = BaseException.class)
    public BaseResponse<Object> baseExceptionHandler(BaseException e) {
        logger.error("发生业务异常！原因是：" + e.getMessage());
        return RespGenerator.returnError(e.getCode(),e.getMessage());
    }

    // 处理空指针异常
    @ExceptionHandler(value = NullPointerException.class)
    public BaseResponse<Object> exceptionHandler(NullPointerException e) {
        logger.error("发生空指针异常！原因是：" + e.getMessage());
        return RespGenerator.returnError(BaseErrorEnum.BODY_NOT_MATCH);
    }

    // 处理自定义异常
    @ExceptionHandler(value = Exception.class)
    public BaseResponse<Object> exceptionHandler(Exception e) {
        logger.error("未知异常！原因是：" + e.getMessage());
        return RespGenerator.returnError(BaseErrorEnum.INTERNAL_SERVER_ERROR);
    }

}
