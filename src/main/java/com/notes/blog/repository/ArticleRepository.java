package com.notes.blog.repository;

import com.notes.blog.entity.Article;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * Create by HeLongJun on 2021/7/24 14:26
 *
 * @author lanrenspace@163.com
 * @Description:
 */
@Repository
public interface ArticleRepository extends R2dbcRepository<Article, Integer> {


    /**
     * 获取文章信息分页
     *
     * @param pageSize
     * @param pageNumber
     * @return
     */
    @Query("SELECT * FROM b_article WHERE del_flag = 0 ORDER BY create_time DESC LIMIT (:pageNumber - 1) * :pageSize,:pageNumber")
    Flux<Article> findAllPage(Integer pageSize, Integer pageNumber);
}
