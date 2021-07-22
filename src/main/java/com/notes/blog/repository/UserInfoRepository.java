package com.notes.blog.repository;

import com.notes.blog.entity.UserInfo;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Create by HeLongJun on 2021/7/21 12:07
 *
 * @author Administrator
 * @Description:
 */
@Repository
public interface UserInfoRepository extends ReactiveCrudRepository<UserInfo, Integer> {


    /**
     * 判断用户是否存在
     *
     * @param account
     * @return
     */
    @Query("SELECT * FROM user_info WHERE del_flag = 0 AND account = :account")
    Mono<UserInfo> findByAccount(String account);
}
