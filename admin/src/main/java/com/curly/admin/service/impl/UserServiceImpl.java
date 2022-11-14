package com.curly.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.curly.admin.mapper.UserMapper;
import com.curly.admin.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.curly.admin.vo.GetUserVo;
import com.curly.admin.vo.UserLoginVo;
import com.curly.common.exception.GlobalExceptionHandler;
import com.curly.common.exception.base.BaseErrorEnum;
import com.curly.common.exception.base.BaseException;
import com.curly.common.model.UserEntity;
import com.curly.common.util.EncryptUtil;
import com.curly.common.util.RedisUtils;
import com.curly.common.util.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author broWsJle
 * @since 2022-11-14
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtils redisUtils;

    /** 锁定前次数 */
    public static final Integer LOCKEDNUMBER = 3;

    @Override
    public UserLoginVo login(String userName, String passWord) {

        // 判断该账户是否被锁定
        String errorNumber;
        if (redisUtils.get("errorNumber") != null) {
            errorNumber = redisUtils.get("errorNumber");
        } else {
            errorNumber = "0";
        }

        if (Integer.parseInt(errorNumber)>=LOCKEDNUMBER) {
            log.error(userName + "账户已被锁定");
            throw new BaseException(BaseErrorEnum.USER_NAME_LOCK);
        }

        //查询条件
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",userName);
//        queryWrapper.eq("password",passWord);
        UserEntity user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            log.error(userName + "用户登录失败");
            throw new BaseException(BaseErrorEnum.USER_NOT_EXISTS);
        } else if (!Objects.equals(user.getPassword(),passWord)) {
            // 密码错误
            Integer result = Integer.parseInt(errorNumber) + 1;
            errorNumber = result.toString();
            if (redisUtils.get("errorNumber") != null) {
                redisUtils.getAndSet("errorNumber", errorNumber);
            } else {
                redisUtils.set("errorNumber", errorNumber);
            }
            throw new BaseException(BaseErrorEnum.PASSWORD_ERROR);
        } else {
            UserEntity userDetailBo = new UserEntity();
            BeanUtils.copyProperties(user,userDetailBo);
            UserLoginVo userLoginVo = new UserLoginVo();
            userLoginVo.setUserDetailBo(userDetailBo);
            //包装token
            String token = TokenUtils.sign(user);
            //token存入redis
            redisUtils.set("token",token,30, TimeUnit.MINUTES);
            userLoginVo.setToken(token);
            log.info(userName + "用户登录成功！");
            return userLoginVo;
        }

    }

    @Override
    public IPage<UserEntity> getUserList(GetUserVo getUserVo) {
        // 新建查询条件
        QueryWrapper<UserEntity> query = new QueryWrapper<>();
        query.like("username", getUserVo.getUsername());
        //新建分页条件
        Page<UserEntity> p = new Page<>(getUserVo.getCurrentPage(),getUserVo.getPageSize());
        IPage<UserEntity> userIPage = userMapper.selectPage(p, query);

        userIPage.setTotal(userIPage.getRecords().size());
        return userIPage;
    }

    @Override
    public Object addUser(UserEntity user) {

        // 密码加密
        if (user.getPassword() != null && user.getPassword() != "") {
            user.setSalt(RandomStringUtils.randomAlphabetic(8));
            user.setPassword(EncryptUtil.md5(user.getSalt() + user.getPassword()));
        } else {
            user.setPassword(null);
        }

        int count;

        // id为空,则添加数据
        if (user.getId() == null) {
            count = userMapper.insert(user);
        } else {

            // 更新分类

            // 设置默认值
            UserEntity updateUser = userMapper.selectById(user.getId());
            if (user.getPassword() == null) {
                user.setPassword(updateUser.getPassword());
                user.setSalt(updateUser.getSalt());
            }
            user.setCreateTime(updateUser.getCreateTime());
            user.setUpdateTime(new Date());
            QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id",updateUser.getId());
            count = userMapper.update(user,queryWrapper);
        }

        if (count == 1) {
            return "新增成功";
        }

        return "新增失败";
    }
}
