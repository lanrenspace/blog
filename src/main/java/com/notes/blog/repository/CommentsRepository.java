package com.notes.blog.repository;

import com.notes.blog.entity.Comments;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

/**
 * Create by HeLongJun on 2021/7/23 15:43
 *
 * @author lanrenspace@163.com
 * @Description:
 */
@Repository
public interface CommentsRepository extends R2dbcRepository<Comments, Integer> {
}
