package com.notes.blog.routers;

import com.notes.blog.handler.AuthenticateHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

/**
 * Create by HeLongJun on 2021/7/21 12:22
 *
 * @author Administrator
 * @Description: 认证路由
 */
@Configuration
public class AuthenticateRouters {


    /**
     * restful 服务
     *
     * @param authenticateHandler
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> authRouter(AuthenticateHandler authenticateHandler) {
        return RouterFunctions.route(POST("/auth/register").and(accept(MediaType.APPLICATION_JSON)), authenticateHandler::register)
                .andRoute(POST("/auth/login").and(accept(MediaType.APPLICATION_JSON)), authenticateHandler::login);
    }
}
