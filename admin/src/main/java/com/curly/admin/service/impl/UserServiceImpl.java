package com.curly.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.curly.admin.mapper.UserMapper;
import com.curly.admin.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.curly.admin.vo.GetUserVo;
import com.curly.admin.vo.UserLoginVo;
import com.curly.common.model.UserEntity;
import com.curly.common.util.EncryptUtil;
import com.curly.common.util.TokenUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author broWsJle
 * @since 2022-11-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserLoginVo login(String userName, String passWord) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",userName);
        queryWrapper.eq("password",passWord);
        UserEntity user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            return null;
        } else {
            UserEntity userDetailBo = new UserEntity();
            BeanUtils.copyProperties(user,userDetailBo);
            UserLoginVo userLoginVo = new UserLoginVo();
            userLoginVo.setUserDetailBo(userDetailBo);
            //包装token
            String token = TokenUtils.sign(user);
            userLoginVo.setToken(token);
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
