package com.curly.common.exception.base;

/**
 * @author broWsJle
 * @date 2022/11/14 18:09
 */
public enum BaseErrorEnum implements BaseErrorInfoInterface {

    // 定义异常枚举值
    SUCCESS("200", "成功！"),
    BODY_NOT_MATCH("400", "数据格式不匹配！"),
    NOT_FOUND("404", "访问资源不存在！"),
    INTERNAL_SERVER_ERROR("500", "服务器内部错误！"),
    SERVER_BUSY("503", "服务器正忙，请稍后再试！"),
    REQUEST_METHOD_SUPPORT_ERROR("10001", "当前请求方法不支持！"),
    REQUEST_DATA_NULL("10002", "当前请求参数为空！"),
    USER_NOT_EXISTS("10003", "该用户不存在！"),
    USER_INVALID("10004", "当前登录信息已失效，请重新登录！"),
    PASSWORD_ERROR("10005", "密码错误！"),
    USER_NAME_LOCK("10006", "该账号已被锁定！");

    private String code;

    private String message;

    BaseErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
