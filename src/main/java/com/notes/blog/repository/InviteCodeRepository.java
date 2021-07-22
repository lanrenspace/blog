package com.notes.blog.repository;

import com.notes.blog.entity.InviteCode;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Create by HeLongJun on 2021/7/21 11:51
 *
 * @author Administrator
 * @Description:
 */
@Repository
public interface InviteCodeRepository extends ReactiveCrudRepository<InviteCode, Integer> {


    /**
     * 根据验证码获取验证码信息
     *
     * @param code
     * @return
     */
    @Query("SELECT * FROM invite_code WHERE del_flag = 0 AND code = :code")
    Mono<InviteCode> findByCode(String code);
}
