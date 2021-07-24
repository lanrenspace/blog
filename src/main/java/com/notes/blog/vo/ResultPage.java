package com.notes.blog.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Create by HeLongJun on 2021/7/24 16:42
 *
 * @author lanrenspace@163.com
 * @Description: 分页数据返回格式
 */
@Data
public class ResultPage<T> implements Serializable {

    /**
     * 总条数
     */
    private long total;

    /**
     * 数据
     */
    private List<T> rows;

    public ResultPage() {

    }

    public ResultPage(List<T> rows, long total) {
        this.total = total;
        this.rows = rows;
    }
}
