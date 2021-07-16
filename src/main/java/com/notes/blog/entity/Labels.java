package com.notes.blog.entity;

import com.notes.blog.base.DataEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Create by HeLongJun on 2021/7/16 10:13
 *
 * @author Administrator
 * @Description: 标签信息
 */
@Data
@Table("b_labels")
public class Labels extends DataEntity {

    /**
     * 主键ID
     */
    @Id
    private Integer labelId;


    /**
     * 标签名称
     */
    private String labelName;


    /**
     * 标签别名
     */
    private String labelAlias;


    /**
     * 标签描述
     */
    private String labelDescription;


    /**
     * 标签文章总数
     */
    private Integer articleCount;

    /**
     * 热门标签（推荐标签）
     */
    private Boolean popular;

    /**
     * 租户编号
     */
    private String tenantCode;
}
