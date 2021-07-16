package com.notes.blog.repository;

import com.notes.blog.entity.Labels;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Create by HeLongJun on 2021/7/16 10:50
 *
 * @author Administrator
 * @Description:
 */
@Repository
public interface LabelsRepository extends ReactiveCrudRepository<Labels, Integer> {
}
