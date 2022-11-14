package com.curly.common.exception.base;

/**
 * 抽象类：提供自定义异常所需要的方法
 * @author broWsJle
 * @date 2022/11/14 18:06
 */
public interface BaseErrorInfoInterface {

    String getCode();

    String getMessage();
}
