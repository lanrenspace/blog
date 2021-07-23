package com.notes.blog.handler;

import com.notes.blog.base.RedisKeys;
import com.notes.blog.entity.AboutConfig;
import com.notes.blog.repository.AboutConfigRepository;
import com.notes.blog.vo.ResultData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Create by HeLongJun on 2021/7/23 19:28
 *
 * @author lanrenspace@163.com
 * @Description:
 */
@RequiredArgsConstructor
@Component
public class AboutConfigHandler {

    private final AboutConfigRepository aboutConfigRepository;
    private final ReactiveRedisTemplate reactiveRedisTemplate;


    /**
     * 加载关于我们数据信息
     *
     * @return
     */
    public Mono<ServerResponse> load(ServerRequest request) {
        return reactiveRedisTemplate.hasKey(RedisKeys.ABOUT_INFO).flatMap(hasKey -> {
            if ((Boolean) hasKey) {
                return ResultData.getSuccess(reactiveRedisTemplate.opsForValue().get(RedisKeys.ABOUT_INFO));
            } else {
                return setAboutConfig();
            }
        }).switchIfEmpty(setAboutConfig());
    }


    /**
     * 设置 about config
     *
     * @return
     */
    private Mono<ServerResponse> setAboutConfig() {
        Flux<AboutConfig> all = aboutConfigRepository.findAll();
        reactiveRedisTemplate.opsForValue().set(RedisKeys.ABOUT_INFO, all);
        reactiveRedisTemplate.expire(RedisKeys.ABOUT_INFO, Duration.ofSeconds(60));
        return ResultData.getSuccess(all);
    }
}
