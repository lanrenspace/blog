package com.notes.blog.callback;

import com.notes.blog.base.Constant;
import com.notes.blog.base.DataEntity;
import org.reactivestreams.Publisher;
import org.springframework.core.annotation.Order;
import org.springframework.data.r2dbc.mapping.event.BeforeConvertCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Create by HeLongJun on 2021/7/22 11:33
 *
 * @author Administrator
 * @Description: 业务实体对象保存前的回调
 */
@Order(1)
@Component
public class EntitySaveCallback implements BeforeConvertCallback<DataEntity> {
    @Override
    public Publisher<DataEntity> onBeforeConvert(DataEntity entity, SqlIdentifier table) {
        if (ObjectUtils.isEmpty(entity.getCreateTime())) {
            entity.setCreateTime(LocalDateTime.now());
        }
        if (ObjectUtils.isEmpty(entity.getDelFlag())) {
            entity.setDelFlag(Constant.FALSE);
        }
        return Mono.just(entity);
    }
}
