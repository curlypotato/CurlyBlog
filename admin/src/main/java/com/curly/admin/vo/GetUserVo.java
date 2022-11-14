package com.curly.admin.vo;

import com.curly.common.data.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author broWsJle
 * @date 2022/11/14 17:06
 */
@Data
@ApiModel("获取用户列表传入VO类")
public class GetUserVo extends PageParam implements Serializable{

    private static final long serialVersionUID = 289718324172L;

    @ApiModelProperty(value = "用户名")
    private String username;
}
