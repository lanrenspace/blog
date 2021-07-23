package com.notes.blog.entity;

import com.notes.blog.base.DataEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Create by HeLongJun on 2021/7/23 15:19
 *
 * @author lanrenspace@163.com
 * @Description: 文章对应加密密匙
 */
@Data
@Table("b_article_encryption")
public class ArticleEncryption extends DataEntity {

    /**
     * id
     */
    @Id
    private Integer aeId;

    /**
     * 文章ID
     */
    private Integer articleId;

    /**
     * 密匙
     */
    private String encryption;

    /**
     * 生效类型
     * 1. permanent 永久有效
     * 2. single 单次有效
     */
    private Integer effectiveType;
}
