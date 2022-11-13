package com.curly.admin.controller;

import com.curly.admin.mapper.UserMapper;
import com.curly.common.model.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author broWsJle
 * @date 2022/11/13 21:33
 */
@RestController
@MapperScan("com.curly.admin.mapper")
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable("id") Integer id) {

        return userMapper.selectById(id);
    }

    @GetMapping("/getUser")
    public List<User> getUser() {

        return userMapper.selectList(null);
    }
}
