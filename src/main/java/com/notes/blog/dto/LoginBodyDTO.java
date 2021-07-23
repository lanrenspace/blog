package com.notes.blog.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Create by HeLongJun on 2021/7/21 19:13
 *
 * @author lanrenspace@163.com
 * @Description: 登陆请求信息
 */
@Data
public class LoginBodyDTO implements Serializable {

    /**
     * 登陆账号
     */
    private String account;

    /**
     * 登陆密码
     */
    private String pwd;
}
