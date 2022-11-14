package com.curly.admin.controller;

import com.curly.common.data.BaseResponse;
import com.curly.common.data.RespGenerator;
import com.curly.common.exception.base.BaseErrorEnum;
import com.curly.common.exception.base.BaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 测试接口
 * @author broWsJle
 * @date 2022/11/14 18:55
 */
@RestController
@RequestMapping("test")
@Api(tags = "测试接口")
public class TestController {

    @ApiOperation(value = "自定义异常测试")
    @GetMapping("/error")
    public BaseResponse<HashMap> login(@RequestParam(value = "userName", required = false) String userName,
                                       @RequestParam(value = "passWord", required = false) String passWord) {

        System.out.println("调用成功，"+userName+","+passWord);
        if (userName==null) {
            throw new BaseException(BaseErrorEnum.USER_NOT_EXISTS);
        } else {
            return RespGenerator.returnOK("调用成功，"+userName+","+passWord);
        }

    }
}
