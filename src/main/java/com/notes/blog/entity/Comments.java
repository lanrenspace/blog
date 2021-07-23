package com.notes.blog.entity;

import com.notes.blog.base.DataEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * Create by HeLongJun on 2021/7/23 15:36
 *
 * @author lanrenspace@163.com
 * @Description: 评论表
 */
@Data
@Table("b_comments")
public class Comments extends DataEntity {

    /**
     * 评论编号
     */
    @Id
    private Integer commentId;


    /**
     * 用户ID编号
     */
    private Integer userId;


    /**
     * 邮箱
     */
    private String email;


    /**
     * 昵称(无账号状态下填写)
     */
    private String nickName;


    /**
     * 头像(无账号状态下自动生成)
     */
    private String image;


    /**
     * 文章ID
     */
    private Integer articleId;


    /**
     * 点赞总数
     */
    private Integer commentLikeCount;


    /**
     * 评论日期
     */
    private LocalDateTime commentDatetime;


    /**
     * 评论内容
     */
    private String commentContent;


    /**
     * 父级评论ID
     */
    private Integer parentCommentId;


    /**
     * 租户编码
     */
    private String tenantCode;
}
