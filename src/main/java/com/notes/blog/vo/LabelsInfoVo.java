package com.notes.blog.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Create by HeLongJun on 2021/7/20 17:30
 *
 * @author Administrator
 * @Description: 标签对外服务信息
 */
@Data
public class LabelsInfoVo implements Serializable {

    /**
     * 标签ID
     */
    private String labelId;

    /**
     * 标签名称
     */
    private String labelName;

    /**
     * 热门标签（推荐标签）
     */
    private boolean popular;

    public LabelsInfoVo(String labelId, String labelName, boolean popular) {
        this.labelId = labelId;
        this.labelName = labelName;
        this.popular = popular;
    }

    public LabelsInfoVo(String labelId, String labelName) {
        this.labelId = labelId;
        this.labelName = labelName;
    }

    public LabelsInfoVo() {
    }
}
