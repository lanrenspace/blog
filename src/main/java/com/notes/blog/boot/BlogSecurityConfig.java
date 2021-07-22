package com.notes.blog.boot;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * Create by HeLongJun on 2021/7/20 18:33
 *
 * @author Administrator
 * @Description:
 */
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@AllArgsConstructor
public class BlogSecurityConfig {

    private final SecurityContextRepository securityRepository;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http, JwtWebFilter jwtWebFilter) {

        return http
                .authorizeExchange()
                .pathMatchers("/auth/login", "/auth/register").permitAll()
                .pathMatchers("/v3/api-docs/**", "/swagger-resources/configuration/ui",
                        "/swagger-resources", "/swagger-resources/configuration/security",
                        "/swagger-ui.html", "/css/**", "/js/**", "/images/**", "/webjars/**", "**/favicon.ico", "/index").permitAll()
                .anyExchange().authenticated()
                .and()
                // 这里注意执行位置一定要在securityContextRepository
                .addFilterAfter(jwtWebFilter, SecurityWebFiltersOrder.FIRST)
                .securityContextRepository(securityRepository)
                .formLogin().disable()
                .httpBasic().disable()
                .csrf().disable()
                .logout().disable()
                .build();
    }
}
