package com.notes.blog.handler;

import com.notes.blog.entity.Labels;
import com.notes.blog.exceptions.SubmitDataNullException;
import com.notes.blog.repository.LabelsRepository;
import com.notes.blog.utils.TenantUtils;
import com.notes.blog.utils.UserUtil;
import com.notes.blog.vo.LabelsInfoVo;
import com.notes.blog.vo.ResultData;
import org.springframework.data.domain.Sort;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Create by HeLongJun on 2021/7/16 11:46
 *
 * @author lanrenspace@163.com
 * @Description:
 */
@Component
public class LabelsHandler {

    private final LabelsRepository labelsRepository;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;

    public LabelsHandler(LabelsRepository labelsRepository, R2dbcEntityTemplate r2dbcEntityTemplate) {
        this.labelsRepository = labelsRepository;
        this.r2dbcEntityTemplate = r2dbcEntityTemplate;
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

    /**
     * 获取所有标签
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> getAllLabels(ServerRequest request) {
        String token = TenantUtils.validRequestToken(request);
        if (token == null) {
            return ResultData.getNoPermission();
        }
        Query query = Query.query(Criteria.where("tenantCode").is(token)).sort(Sort.by(Sort.Direction.DESC, "createTime"));
        return r2dbcEntityTemplate.select(query, Labels.class).collectList().flatMap(labels -> {
            List<LabelsInfoVo> collect = labels.parallelStream().map(cLabels ->
                    new LabelsInfoVo(cLabels.getLabelId(), cLabels.getLabelName(), cLabels.getPopular())).collect(Collectors.toList());
            return ResultData.getSuccess(collect);
        }).switchIfEmpty(ResultData.getSuccess(Mono.empty()));
    }


    public Mono<ServerResponse> deleteLabelsById(ServerRequest request) {
        return ResultData.getSuccess(UserUtil.getUser());
    }

}
