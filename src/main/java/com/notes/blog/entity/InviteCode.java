package com.notes.blog.entity;

import com.notes.blog.base.DataEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * Create by HeLongJun on 2021/7/21 11:50
 *
 * @author Administrator
 * @Description: 邀请码
 */
@Data
public class InviteCode extends DataEntity {

    /**
     * 主键ID
     */
    @Id
    private Integer id;

    /**
     * 邀请码
     */
    private String code;


    /**
     * 是否能够重复使用
     */
    private Integer reuse;
}
