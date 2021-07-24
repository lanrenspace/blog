package com.notes.blog.dto;

import com.notes.blog.entity.Article;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Create by HeLongJun on 2021/7/24 14:44
 *
 * @author lanrenspace@163.com
 * @Description: 提交文章数据
 */
@Data
public class PublishArticleBodyDTO implements Serializable {

    /**
     * 文章内容
     */
    private Article article;


    /**
     * 栏目（大通栏）
     */
    private String optionValues;


    /**
     * 文章分类
     */
    private List<Integer> sorts;


    /**
     * 文章标签
     */
    private List<String> labels;


    /**
     * 定时发布的日期时间
     */
    private LocalDateTime publishTime;


    /**
     * 文章类型
     */
    private Integer preFrom;
}
