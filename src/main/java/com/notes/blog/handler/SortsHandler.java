package com.notes.blog.handler;

import com.notes.blog.entity.Sorts;
import com.notes.blog.repository.SortsRepository;
import com.notes.blog.vo.ResultData;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Create by HeLongJun on 2021/7/16 13:21
 *
 * @author Administrator
 * @Description:
 */
@Component
public class SortsHandler {

    private final SortsRepository sortsRepository;

    public SortsHandler(SortsRepository sortsRepository) {
        this.sortsRepository = sortsRepository;
    }


    /**
     * 创建个人分类
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> createSort(ServerRequest request) {
        return request.bodyToMono(Sorts.class).flatMap(sorts -> {
            sorts.setOrder(100);
            sorts.setShow(true);
            return sortsRepository.save(sorts).flatMap(sortInfo -> ResultData.getSuccess(Mono.just(ResultData.ok(sortInfo))));
        }).switchIfEmpty(ResultData.getError(ResultData.error("分类信息不能为空!")));
    }
}
