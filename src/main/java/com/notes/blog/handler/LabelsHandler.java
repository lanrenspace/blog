package com.notes.blog.handler;

import com.notes.blog.entity.Labels;
import com.notes.blog.exceptions.SubmitDataNullException;
import com.notes.blog.repository.LabelsRepository;
import com.notes.blog.vo.ResultData;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * Create by HeLongJun on 2021/7/16 11:46
 *
 * @author Administrator
 * @Description:
 */
@Component
public class LabelsHandler {

    private final LabelsRepository labelsRepository;

    public LabelsHandler(LabelsRepository labelsRepository) {
        this.labelsRepository = labelsRepository;
    }


    /**
     * 添加标签信息
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> createLabels(ServerRequest request) {
        Mono<Labels> bLabelsMono = request.bodyToMono(Labels.class).flatMap(labels -> {
            if (labels.getLabelName() == null) {
                return Mono.error(new SubmitDataNullException("标签名称不能为空!"));
            }
            return Mono.just(labels);
        });
        Mono<ServerResponse> serverResponseMono = bLabelsMono.flatMap(insertLabels -> {
            Mono<Labels> insert = labelsRepository.save(insertLabels);
            return ResultData.getSuccess(insert.map(ResultData::ok));
        });
        return serverResponseMono.switchIfEmpty(ResultData.getError(ResultData.error("标签信息不能为空!")));
    }
}
