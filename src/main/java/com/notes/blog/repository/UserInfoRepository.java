package com.notes.blog.repository;

import com.notes.blog.entity.UserInfo;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Create by HeLongJun on 2021/7/21 12:07
 *
 * @author lanrenspace@163.com
 * @Description:
 */
@Repository
public interface UserInfoRepository extends R2dbcRepository<UserInfo, Integer> {


    /**
     * 判断用户是否存在
     *
     * @param account
     * @return
     */
    @Query("SELECT * FROM user_info WHERE del_flag = 0 AND account = :account")
    Mono<UserInfo> findByAccount(String account);
}
