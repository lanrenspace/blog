package com.notes.blog.base;

/**
 * Create by HeLongJun on 2021/7/22 10:19
 *
 * @author lanrenspace@163.com
 * @Description: 常量
 */
public interface Constant {

    /**
     * 真
     */
    int TRUE = 1;

    /**
     * 假
     */
    int FALSE = 0;

    /**
     * 文章状态
     */
    interface ArticleStatus {
        /**
         * 已发布
         */
        int STATUS_PUBLISHED = 1;

        /**
         * 未发布
         */
        int STATUS_UNPUBISHED = 2;


        /**
         * 审核中
         */
        int STATUS_REVIEWING = 3;


        /**
         * 等待定时发布中
         */
        int STATUS_TIMED = 4;
    }
}
