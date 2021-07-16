package com.notes.blog.callback;

import com.notes.blog.entity.Labels;
import org.reactivestreams.Publisher;
import org.springframework.core.annotation.Order;
import org.springframework.data.r2dbc.mapping.event.BeforeConvertCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * Create by HeLongJun on 2021/7/16 13:52
 *
 * @author Administrator
 * @Description:
 */
@Order(1)
@Component
public class LabelsSaveCallback implements BeforeConvertCallback<Labels> {

    @Override
    public Publisher<Labels> onBeforeConvert(Labels labels, SqlIdentifier sqlIdentifier) {
        if (ObjectUtils.isEmpty(labels.getCreateTime())) {
            labels.setCreateTime(new Date());
        }
        return Mono.just(labels);
    }
}
