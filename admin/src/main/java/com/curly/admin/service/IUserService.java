package com.curly.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.curly.admin.vo.GetUserVo;
import com.curly.admin.vo.UserLoginVo;
import com.curly.common.model.UserEntity;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author broWsJle
 * @since 2022-11-14
 */
public interface IUserService extends IService<UserEntity> {

    UserLoginVo login(String userName, String passWord);

    IPage<UserEntity> getUserList(GetUserVo getUserVo);

    Object addUser(UserEntity user);
}

