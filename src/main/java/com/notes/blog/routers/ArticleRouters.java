package com.notes.blog.routers;

import com.notes.blog.handler.ArticleHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

/**
 * Create by HeLongJun on 2021/7/23 20:34
 *
 * @author lanrenspace@163.com
 * @Description:
 */
@Configuration
public class ArticleRouters {


    /**
     * restful 服务
     *
     * @param articleHandler
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> articleRouter(ArticleHandler articleHandler) {
        return RouterFunctions
                // 初始化创建文章服务
                .route(POST("/articles/initCreate").and(accept(MediaType.APPLICATION_JSON)), articleHandler::initCreateArticle);
    }
}
