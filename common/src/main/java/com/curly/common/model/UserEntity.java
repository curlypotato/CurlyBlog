package com.curly.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.curly.common.data.BasicModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 用户信息
 * @author broWsJle
 * @date 2022/11/13 21:20
 */
@Data
@TableName("user")
@ApiModel(value="UserEntity对象", description="")
public class UserEntity extends BasicModel {

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;
    /**
     * 用户性别（0：未知，1：男，2：女）
     */
    @ApiModelProperty(value = "用户性别（0：未知，1：男，2：女）")
    private Integer sex;
    /**
     * 用户年龄
     */
    @ApiModelProperty(value = "用户年龄")
    private Integer age;
    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像")
    private String avatar;
}
