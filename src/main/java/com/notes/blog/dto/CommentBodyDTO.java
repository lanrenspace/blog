package com.notes.blog.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Create by HeLongJun on 2021/7/23 15:46
 *
 * @author lanrenspace@163.com
 * @Description:
 */
@Data
public class CommentBodyDTO implements Serializable {

    /**
     * email
     */
    private String email;


    /**
     * 昵称
     */
    private String nickName;


    /**
     * 文章ID
     */
    private Integer articleId;


    /**
     * 评论内容
     */
    private String commentContent;


    /**
     * 上级评论ID
     */
    private Integer parentCommentId;
}
