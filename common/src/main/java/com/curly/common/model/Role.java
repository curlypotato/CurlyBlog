package com.curly.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author broWsJle
 * @date 2022/11/13 21:54
 */
@Data
@TableName("role")
public class Role extends BasicModel{

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 角色名
     */
    private String roleName;
    /**
     * 角色编码
     */
    private String roleCode;
}
