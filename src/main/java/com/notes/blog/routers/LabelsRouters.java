package com.notes.blog.routers;

import com.notes.blog.handler.LabelsHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

/**
 * Create by HeLongJun on 2021/7/16 11:48
 *
 * @author Administrator
 * @Description:
 */
@Configuration
public class LabelsRouters {

    /**
     * restful  服务
     *
     * @param labelsHandler
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> labelsRouter(LabelsHandler labelsHandler) {
        return RouterFunctions.route(POST("/labels").and(accept(MediaType.APPLICATION_JSON)), labelsHandler::createLabels)
                // 开放接口
                .andRoute(GET("/v1/labels/all").and(accept(MediaType.APPLICATION_JSON)), labelsHandler::getAllLabels)
                .andRoute(GET("/v1/labels/user").and(accept(MediaType.APPLICATION_JSON)), labelsHandler::deleteLabelsById);
    }
}
