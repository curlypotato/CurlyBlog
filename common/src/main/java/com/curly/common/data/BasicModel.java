package com.curly.common.data;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author broWsJle
 * @date 2022/11/13 21:52
 */
@Getter
public class BasicModel implements Serializable{
    /**
     * 未删除
     */
    public static final Integer NO_DELETE = 0;

    /**
     * 已删除
     */
    public static final Integer IS_DELETE = 1;

    @Setter
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 创建时间
     */
    @Setter
    private Date createTime;

    /**
     * 是否删除：0未删除 1已删除
     */
    private Integer deleted = 0;

    /**
     * 设置删除状态
     *
     * @param
     */
    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
    /**
     * 删除使用逻辑删除，status改为1
     *
     * @return
     */
    public BasicModel delete() {
        this.deleted = 1;
        return this;
    }
}
