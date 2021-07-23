package com.notes.blog.repository;

import com.notes.blog.entity.ArticleEncryption;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

/**
 * Create by HeLongJun on 2021/7/23 15:24
 *
 * @author lanrenspace@163.com
 * @Description:
 */
@Repository
public interface ArticleEncryptionRepository extends R2dbcRepository<ArticleEncryption, Integer> {
}
