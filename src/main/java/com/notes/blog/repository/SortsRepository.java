package com.notes.blog.repository;

import com.notes.blog.entity.Sorts;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Create by HeLongJun on 2021/7/16 13:19
 *
 * @author Administrator
 * @Description:
 */
@Repository
public interface SortsRepository extends ReactiveCrudRepository<Sorts, String> {
}
