package com.notes.blog.routers;

import com.notes.blog.handler.AboutConfigHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

/**
 * Create by HeLongJun on 2021/7/23 20:34
 *
 * @author lanrenspace@163.com
 * @Description:
 */
@Configuration
public class AboutConfigRouters {


    /**
     * restful 服务
     *
     * @param aboutConfigHandler
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> aboutConfigRouter(AboutConfigHandler aboutConfigHandler) {
        return RouterFunctions.route(GET("/v1/aboutInfo").and(accept(MediaType.APPLICATION_JSON)), aboutConfigHandler::load);
    }
}
