package com.curly.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * CurlyBlog项目启动入口
 *
 * @author broWsJle
 * @date 2022/11/13 20:43
 */
@SpringBootApplication
@EnableSwagger2
public class AdminApplication {

    public static void main(String[] args) {

        SpringApplication.run(AdminApplication.class, args);
    }

}
