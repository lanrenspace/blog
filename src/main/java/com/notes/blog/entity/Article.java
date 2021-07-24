package com.notes.blog.entity;

import com.notes.blog.base.DataEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by HeLongJun on 2021/7/24 14:19
 *
 * @author lanrenspace@163.com
 * @Description: 文章信息
 */
@Data
@Table("b_article")
public class Article extends DataEntity {

    /**
     * 文章ID
     */
    @Id
    private Integer articleId;

    /**
     * 发表用户ID
     */
    private Integer userId;


    /**
     * 文章标题
     */
    private String articleTitle;


    /**
     * 文章内容
     */
    private String articleContent;

    /**
     * 摘要信息
     */
    private String contentShort;

    /**
     * 封面图片
     */
    private String coverImage;

    /**
     * 浏览量
     */
    private Integer articleViews;


    /**
     * 评论总数
     */
    private Integer articleCommentCount;


    /**
     * 发表日期
     */
    private LocalDateTime articleDatetime;


    /**
     * 点赞总数
     */
    private Integer articleLikeCount;

    /**
     * 字数
     */
    private Integer wordCount;


    /**
     * 审核状态
     * 1.审核中
     * 2.审核不通过
     * 3.审核通过
     */
    private Integer reviewStatus;


    /**
     * 审核通过/不通过信息
     */
    private String reviewMsg;

    /**
     * 文章状态
     * 1.已发布
     * 2.未发布
     * 3.以提交审核
     * 4.等待定时发布中
     */
    private Integer status;

    /**
     * 定时发布时间
     */
    private LocalDateTime timed;

    /**
     * 定时发布表达式
     */
    private String timedCron;

    /**
     * 展现形式
     * 1.公开
     * 2.加密
     * 3.私密（仅发表人可见）
     */
    private Integer preFrom;

    /**
     * 关联的附件ID
     */
    private List<String> files;

    /**
     * 大栏目对应值
     */
    private String optionValues;

    /**
     * 租户编码
     */
    private String tenantCode;

    public List<String> getFiles() {
        if (this.files == null) {
            this.files = new ArrayList<>();
        }
        return files;
    }
}
