package com.curly.common.data;

import com.curly.common.exception.base.BaseErrorEnum;

/**
 * @author broWsJle
 * @date 2022/11/14 14:56
 */
public class RespGenerator {
    /**
     * 接口调用成功时出参
     *
     * @param data
     *            接口返回数据
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static BaseResponse returnOK(Object data) {
        return new BaseResponse("200", "接口调用成功!", data);
    }

    /**
     * 调用失败
     *
     * @param code
     *            错误码
     * @param message
     *            错误信息
     * @return
     */
    public static BaseResponse<Object> returnError(String code, String message) {
        return new BaseResponse<Object>(code, message, null);
    }

    /**
     * 调用失败
     *
     * @param message
     *            错误信息
     * @return
     */
    public static BaseResponse<Object> returnError(String message) {
        return new BaseResponse<Object>("-1", message, null);
    }

    /**
     * 调用失败（自定义）
     *
     * @param errorEnum
     *            错误信息
     * @return
     */
    public static BaseResponse<Object> returnError(BaseErrorEnum errorEnum) {
        return new BaseResponse<Object>(errorEnum.getCode(), errorEnum.getMessage(), null);
    }



}
