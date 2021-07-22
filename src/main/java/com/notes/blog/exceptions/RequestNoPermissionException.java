package com.notes.blog.exceptions;

/**
 * @ClassName: RequestNoPermissionException
 * @Author: 1603020074@qq.com
 * @Date: 2019/7/23 10:59
 * @Version: 1.0
 * @Description: 请求无权限异常
 **/
public class RequestNoPermissionException extends RuntimeException {

    public RequestNoPermissionException(String errorMsg) {
        super(errorMsg);
    }
}
