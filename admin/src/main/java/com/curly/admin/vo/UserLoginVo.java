package com.curly.admin.vo;

import com.curly.common.model.UserEntity;
import lombok.Data;

/**
 * @author broWsJle
 * @date 2022/11/14 16:47
 */
@Data
public class UserLoginVo extends UserEntity{

    private UserEntity userDetailBo;

    private String token;
}
