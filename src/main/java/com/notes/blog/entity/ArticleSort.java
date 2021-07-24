package com.notes.blog.entity;

import com.notes.blog.base.DataEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Create by HeLongJun on 2021/7/24 14:56
 *
 * @author lanrenspace@163.com
 * @Description: 文章分类关联信息
 */
@Data
@Table("b_article_sort")
public class ArticleSort extends DataEntity {

    /**
     * 关联ID
     */
    @Id
    private Integer asId;


    /**
     * 文章ID
     */
    private Integer articleId;


    /**
     * 分类ID
     */
    private Integer sortId;


    public ArticleSort() {

    }

    public ArticleSort(Integer articleId, Integer sortId) {
        this.articleId = articleId;
        this.sortId = sortId;
    }
}
