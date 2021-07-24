package com.notes.blog.handler;

import com.notes.blog.base.RedisKeys;
import com.notes.blog.repository.AboutConfigRepository;
import com.notes.blog.vo.ResultData;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.Duration;

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
     * log
     */
    private static Logger logger = LoggerFactory.getLogger(AboutConfigHandler.class);


    /**
     * 加载关于我们数据信息
     *
     * @return
     */
    public Mono<ServerResponse> load(ServerRequest request) {
        return reactiveRedisTemplate.hasKey(RedisKeys.ABOUT_INFO).flatMap(hasKey -> {
            if ((Boolean) hasKey) {
                return reactiveRedisTemplate.opsForValue().get(RedisKeys.ABOUT_INFO)
                        .flatMap(data -> ResultData.getSuccess(data));
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
        return aboutConfigRepository.findAboutConfigLimitFirst().flatMap(aboutConfig -> {
            ReactiveValueOperations reactiveValueOperations = reactiveRedisTemplate.opsForValue();
            reactiveValueOperations.set(RedisKeys.ABOUT_INFO, aboutConfig, Duration.ofSeconds(60L)).subscribe(l -> logger.info("redis storage data key:" + RedisKeys.ABOUT_INFO + " status:" + l));
            return ResultData.getSuccess(aboutConfig);
        }).switchIfEmpty(ResultData.getError("Not configured aboutInfo."));
    }
}
