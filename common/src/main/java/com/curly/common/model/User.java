package com.curly.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 用户信息
 * @author broWsJle
 * @date 2022/11/13 21:20
 */
@Data
@TableName("user")
public class User extends BasicModel{

    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户性别（0：未知，1：男，2：女）
     */
    private Integer sex;
    /**
     * 用户年龄
     */
    private Integer age;
    /**
     * 用户头像
     */
    private String avatar;
}
