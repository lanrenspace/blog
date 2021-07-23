package com.notes.blog.entity;

import com.notes.blog.base.DataEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Create by HeLongJun on 2021/7/23 19:23
 *
 * @author lanrenspace@163.com
 * @Description: 关于我们配置信息
 */
@Data
@Table("c_about_config")
public class AboutConfig extends DataEntity {

    /**
     * 主键ID
     */
    @Id
    private Integer id;

    /**
     * 关于我们
     */
    private String aboutUs;


    /**
     * 联系方式
     */
    private String contactDetails;


    /**
     * 免责声明
     */
    private String disclaimer;


    /**
     * 版权说明
     */
    private String copyrightDesc;


    /**
     * 隐私及评论
     */
    private String privacyAndComment;
}
