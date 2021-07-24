package com.notes.blog.utils;

import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Optional;

/**
 * Create by HeLongJun on 2021/7/24 16:24
 *
 * @author lanrenspace@163.com
 * @Description: 分页参数
 */
public class PageParams {

    /**
     * 当前页
     */
    private static String PAGE_NUMBER = "pageNumber";

    /**
     * 每页大小
     */
    private static String PAGE_SIZE = "pageSize";

    /**
     * 默认页大小
     */
    public static String DEFAULT_NUMBER_SIZE = "1";

    /**
     * 默认每页多少条
     */
    public static String DEFAULT_PAGE_SIZE = "10";

    /**
     * 获取pageSkip
     *
     * @param serverRequest
     * @return
     */
    public static Integer getPageSkip(ServerRequest serverRequest) {
        int pageNumber = getPageNumber(serverRequest) - 1;
        int pageSize = getPageSize(serverRequest);
        return pageNumber * pageSize;
    }


    /**
     * 从reques中获取pageNumber
     *
     * @param serverRequest
     * @return
     */
    public static int getPageNumber(ServerRequest serverRequest) {
        Optional<String> pageNumber = serverRequest.queryParam(PAGE_NUMBER);
        return Integer.parseInt(pageNumber.orElse(DEFAULT_NUMBER_SIZE));
    }

    /**
     * 从request中获取pageSize
     *
     * @param serverRequest
     * @return
     */
    public static int getPageSize(ServerRequest serverRequest) {
        Optional<String> pageSize = serverRequest.queryParam(PAGE_SIZE);
        return Integer.parseInt(pageSize.orElse(DEFAULT_PAGE_SIZE));
    }
}
