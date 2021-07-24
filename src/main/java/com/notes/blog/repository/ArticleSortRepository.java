package com.notes.blog.repository;

import com.notes.blog.entity.ArticleSort;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

/**
 * Create by HeLongJun on 2021/7/24 16:12
 *
 * @author lanrenspace@163.com
 * @Description:
 */
@Repository
public interface ArticleSortRepository extends R2dbcRepository<ArticleSort, Integer> {
}
