package com.curly.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 菜单类
 *
 * @author broWsJle
 * @since 2022-11-13
 */
@Data
@TableName("menu")
@ApiModel(value="MenuEntity对象", description="")
public class MenuEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "菜单标题")
    private String title;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "菜单类型（1：后台管理菜单；2：blog菜单）")
    private Integer type;

    @ApiModelProperty(value = "上级菜单id（默认无上级：0）")
    private Integer pid;

    @ApiModelProperty(value = "菜单路径")
    private String path;

    @ApiModelProperty(value = "排序")
    private Integer order;

}
