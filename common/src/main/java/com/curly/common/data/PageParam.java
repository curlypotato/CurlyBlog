package com.curly.common.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author broWsJle
 * @date 2022/11/14 16:59
 */
@Data
@ApiModel("分页参数")
public class PageParam {

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页", required = true)
    private Integer currentPage = 1;

    /**
     * 每页显示条数
     */
    @ApiModelProperty(value = "每页显示条数", required = true)
    private Integer pageSize = 10;
}
