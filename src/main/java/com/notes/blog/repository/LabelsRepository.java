package com.notes.blog.repository;

import com.notes.blog.entity.Labels;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

/**
 * Create by HeLongJun on 2021/7/16 10:50
 *
 * @author lanrenspace@163.com
 * @Description:
 */
@Repository
public interface LabelsRepository extends R2dbcRepository<Labels, Integer> {
}
