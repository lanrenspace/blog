package com.notes.blog.base;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Create by HeLongJun on 2021/7/16 16:22
 *
 * @author Administrator
 * @Description: 业务实体基类
 */
@Data
public class DataEntity implements Serializable {

    /**
     * 创建用户
     */
    private Integer createBy;


    /**
     * 创建时间
     */
    private LocalDateTime createTime;


    /**
     * 更新用户
     */
    private Integer updateBy;


    /**
     * 更新用户
     */
    private LocalDateTime updateTime;


    /**
     * 逻辑删除
     */
    private Integer delFlag;
}
