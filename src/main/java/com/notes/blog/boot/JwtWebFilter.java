package com.notes.blog.boot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.notes.blog.vo.ResultData;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * Create by HeLongJun on 2021/7/21 18:52
 *
 * @author Administrator
 * @Description:
 */
@Component
@Slf4j
@AllArgsConstructor
public class JwtWebFilter implements WebFilter {

    private final JwtSigner jwtSigner;

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        ServerHttpRequest request = serverWebExchange.getRequest();
        ServerHttpResponse response = serverWebExchange.getResponse();
        String path = request.getPath().value();
        if (path.contains("/auth/login") || path.contains("/auth/register")) {
            return webFilterChain.filter(serverWebExchange);
        }
        String auth = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (auth == null) {
            return this.writeErrorMessage(response, HttpStatus.NOT_ACCEPTABLE, "没有携带token");
        } else if (!auth.startsWith(jwtSigner.getTokenPrefix())) {
            return this.writeErrorMessage(response, HttpStatus.NOT_ACCEPTABLE, "token 没有以" + jwtSigner.getTokenPrefix() + "开始");
        }
        String token = auth.replace(jwtSigner.getTokenPrefix(), "");
        try {
            serverWebExchange.getAttributes().put("token", token);
        } catch (Exception e) {
            return this.writeErrorMessage(response, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return webFilterChain.filter(serverWebExchange);
    }


    /**
     * 设置错误消息
     *
     * @param response
     * @param status
     * @param msg
     * @return
     * @throws JsonProcessingException
     * @throws UnsupportedEncodingException
     */
    protected Mono<Void> writeErrorMessage(ServerHttpResponse response, HttpStatus status, String msg) throws JsonProcessingException, UnsupportedEncodingException {
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper mapper = new ObjectMapper();
        String body = mapper.writeValueAsString(new ResultData(status.value(), msg, null));
        DataBuffer dataBuffer = response.bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(dataBuffer));
    }
}
