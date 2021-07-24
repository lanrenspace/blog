package com.notes.blog.repository;

import com.notes.blog.entity.AboutConfig;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Create by HeLongJun on 2021/7/23 19:28
 *
 * @author lanrenspace@163.com
 * @Description:
 */
@Repository
public interface AboutConfigRepository extends R2dbcRepository<AboutConfig, Integer> {


    /**
     * 获取关于我们配置的第一条数据
     *
     * @return
     */
    @Query("SELECT * FROM c_about_config WHERE del_flag = 0 ORDER BY create_time desc limit 1")
    Mono<AboutConfig> findAboutConfigLimitFirst();
}
