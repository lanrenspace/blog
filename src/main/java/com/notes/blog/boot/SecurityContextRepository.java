package com.notes.blog.boot;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Create by HeLongJun on 2021/7/21 19:00
 *
 * @author Administrator
 * @Description:
 */
@Component
@AllArgsConstructor
@Slf4j
public class SecurityContextRepository implements ServerSecurityContextRepository {

    private final JwtAuthenticationManager jwtAuthenticationManager;

    @Override
    public Mono<Void> save(ServerWebExchange serverWebExchange, SecurityContext securityContext) {
        return Mono.empty();
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange serverWebExchange) {
        String token = serverWebExchange.getAttribute("token");
        return jwtAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(token, token)).map(SecurityContextImpl::new);
    }
}
