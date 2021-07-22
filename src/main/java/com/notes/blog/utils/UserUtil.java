package com.notes.blog.utils;

import com.notes.blog.entity.UserInfo;
import io.jsonwebtoken.Jwt;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * Create by HeLongJun on 2021/7/22 11:55
 *
 * @author Administrator
 * @Description: 用户获取工具
 */
public class UserUtil {

    /**
     * token类型头
     */
    private static String tokenType = "Bearer ";


    /**
     * 获取当前用户(从上下文中获取)
     *
     * @return
     */
    public static Mono<Authentication> getUser() {
        Mono<SecurityContext> context = ReactiveSecurityContextHolder.getContext();
        Mono<Authentication> authentication = context.filter(c -> c.getAuthentication() != null)
                .map(SecurityContext::getAuthentication);
        return authentication;
    }
}
