package com.curly.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.curly.common.data.BasicModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author broWsJle
 * @date 2022/11/13 21:54
 */
@Data
@TableName("role")
@ApiModel(value="RoleEntity对象", description="")
public class RoleEntity extends BasicModel {

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 角色名
     */
    @ApiModelProperty(value = "角色名")
    private String roleName;
    /**
     * 角色编码
     */
    @ApiModelProperty(value = "角色编码")
    private String roleCode;
}
