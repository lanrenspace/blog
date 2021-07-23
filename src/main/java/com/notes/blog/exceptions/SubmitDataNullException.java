package com.notes.blog.exceptions;

/**
 * Create by HeLongJun on 2021/7/16 12:05
 *
 * @author lanrenspace@163.com
 * @Description: 提交数据为空异常
 */
public class SubmitDataNullException extends RuntimeException {

    public SubmitDataNullException(String errorMsg) {
        super(errorMsg);
    }
}
