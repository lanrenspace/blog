package com.notes.blog.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

/**
 * Create by HeLongJun on 2021/7/16 13:14
 *
 * @author Administrator
 * @Description: 分类
 */
@Data
@Table("b_sorts")
public class Sorts implements Serializable {


    /**
     * 主键ID
     */
    @Id
    private String sortId;


    /**
     * 分类名称
     */
    private String sortName;


    /**
     * 分类别名
     */
    private String sortAlias;


    /**
     * 分类描述
     */
    private String sortDescription;

    /**
     * 父级类型ID
     */
    private String parentSortId;


    /**
     * 租户编码
     */
    private String tenantCode;

    /**
     * 排序
     */
    private Integer order;

    /**
     * 文章总数
     */
    private Integer articleCount;

    /**
     * 前台是否显示
     * 1.Y 显示
     * 2.N 不显示
     */
    private Boolean show;

    /**
     * 原分类名称
     */
    @Transient
    private String originalSortName;

    public String getOriginalSortName() {
        if (originalSortName == null) {
            this.originalSortName = this.sortName;
        }
        return originalSortName;
    }
}
