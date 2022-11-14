package com.curly.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.curly.admin.mapper.UserMapper;
import com.curly.admin.service.IUserService;
import com.curly.admin.vo.GetUserVo;
import com.curly.common.data.BaseResponse;
import com.curly.common.data.RespGenerator;
import com.curly.common.model.UserEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

/**
 * @author broWsJle
 * @date 2022/11/13 21:33
 */
@RestController
@MapperScan("com.curly.admin.mapper")
@RequestMapping("user")
@Api(tags = "用户接口")
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "登录")
    @GetMapping("/login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", paramType = "String"),
            @ApiImplicitParam(name = "passWord", value = "密码", paramType = "String")
    })
    public BaseResponse<HashMap> login(@RequestParam(value = "userName") String userName,
                                       @RequestParam(value = "passWord") String passWord) {
        return RespGenerator.returnOK(userService.login(userName,passWord));
    }

    @ApiOperation(value = "登出")
    @PostMapping("/logout")
    public BaseResponse<Integer> logout() {
        return RespGenerator.returnOK("退出成功");
    }

    @ApiOperation(value = "获取用户列表信息")
    @PostMapping("/getUserList")
    public BaseResponse<IPage<UserEntity>> getUserList(@RequestBody GetUserVo getUserVo) {
        return RespGenerator.returnOK(userService.getUserList(getUserVo));
    }

    @ApiOperation(value = "新增用户")
    @PostMapping("/addUser")
    public BaseResponse<Integer> addUser(UserEntity user) {
        return RespGenerator.returnOK(userService.addUser(user));
    }
//    @GetMapping("/getUserById/{id}")
//    public UserEntity getUserById(@PathVariable("id") Integer id) {
//
//        return userMapper.selectById(id);
//    }
//
//    @GetMapping("/getUser")
//    public List<UserEntity> getUser() {
//
//        return userMapper.selectList(null);
//    }
}
