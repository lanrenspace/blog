package com.notes.blog.boot;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.validation.constraints.Digits;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Create by HeLongJun on 2021/7/21 19:04
 *
 * @author lanrenspace@163.com
 * @Description:
 */
@Component
@AllArgsConstructor
@Slf4j
public class JwtAuthenticationManager implements ReactiveAuthenticationManager {

    private final JwtSigner jwtSigner;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.just(authentication)
                .map(auth -> jwtSigner.parseToken(auth.getCredentials().toString()))
                .log()
                .onErrorResume(e -> {
                    log.error("验证token时发生错误，错误类型为： {}，错误信息为： {}", e.getClass(), e.getMessage());
                    return Mono.empty();
                })
                .map(claims -> new UsernamePasswordAuthenticationToken(
                        claims.getSubject(),
                        null,
                        Stream.of(claims.get(jwtSigner.getAuthoritiesTag()))
                                .peek(info -> log.info("auth权限信息 {}", info))
                                .map(it -> (List<Map<String, String>>) it)
                                .flatMap(it -> it.stream()
                                        .map(i -> i.get("authority"))
                                        .map(SimpleGrantedAuthority::new))
                                .collect(Collectors.toList())
                ));
    }
}
