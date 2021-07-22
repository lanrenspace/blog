package com.notes.blog.exceptions;

import com.alibaba.fastjson.JSON;
import com.notes.blog.vo.ResultData;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

/**
 * Create by HeLongJun on 2021/7/22 9:46
 *
 * @author Administrator
 * @Description: 全局异常处理
 */
@Component
@Order(-2)
public class GlobalExceptionHandler implements WebExceptionHandler {


    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        if (ex instanceof RequestNoPermissionException) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
        }
        String errorMsg = erroToString(ex);
        DataBuffer dataBuffer = response.bufferFactory().wrap(JSON.toJSONBytes(ResultData.error(errorMsg)));
        return response.writeWith(Mono.just(dataBuffer));
    }

    /**
     * 错误信息转换
     *
     * @param ex
     * @return
     */
    private String erroToString(Throwable ex) {
        if (ex instanceof SubmitDataNullException) {
            SubmitDataNullException submitDataNullException = (SubmitDataNullException) ex;
            return submitDataNullException.getMessage();
        } else {
            ex.printStackTrace();
            return ex.getMessage();
        }
    }
}
