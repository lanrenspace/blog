package com.notes.blog.repository;

import com.notes.blog.entity.AboutConfig;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

/**
 * Create by HeLongJun on 2021/7/23 19:28
 *
 * @author lanrenspace@163.com
 * @Description:
 */
@Repository
public interface AboutConfigRepository extends R2dbcRepository<AboutConfig, Integer> {
}
