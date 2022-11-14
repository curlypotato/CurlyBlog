package com.curly.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * CurlyBlog项目启动入口
 *
 * 跨 module引入 问题：@ComponentScan({"com.curly.**"})
 * Field redisUtils in com.curly.admin.service.impl.UserServiceImpl
 * required a bean of type 'com.curly.common.util.RedisUtils' that could not be found.
 * @author broWsJle
 * @date 2022/11/13 20:43
 */
@SpringBootApplication
@EnableSwagger2
@ComponentScan({"com.curly.**"})
public class AdminApplication {

    public static void main(String[] args) {

        SpringApplication.run(AdminApplication.class, args);
    }

}
