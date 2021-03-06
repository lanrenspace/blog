package com.notes.blog.repository;

import com.notes.blog.entity.Sorts;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

/**
 * Create by HeLongJun on 2021/7/16 13:19
 *
 * @author lanrenspace@163.com
 * @Description:
 */
@Repository
public interface SortsRepository extends R2dbcRepository<Sorts, String> {
}
