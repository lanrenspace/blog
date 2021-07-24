package com.notes.blog.entity;

import com.notes.blog.base.DataEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Create by HeLongJun on 2021/7/24 15:01
 *
 * @author lanrenspace@163.com
 * @Description: 文章标签关联信息
 */
@Data
@Table("b_article_label")
public class ArticleLabel extends DataEntity {

    /**
     * 关联ID
     */
    @Id
    private Integer alId;

    /**
     * 文章ID
     */
    private Integer articleId;


    /**
     * 标签ID
     */
    private Integer labelId;

    public ArticleLabel() {

    }

    public ArticleLabel(Integer articleId, Integer labelId) {
        this.articleId = articleId;
        this.labelId = labelId;
    }
}
