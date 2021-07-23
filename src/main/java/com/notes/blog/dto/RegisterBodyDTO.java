package com.notes.blog.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Create by HeLongJun on 2021/7/21 11:44
 *
 * @author lanrenspace@163.com
 * @Description: 注册请求参数
 */
@Data
public class RegisterBodyDTO implements Serializable {

    /**
     * 登陆账号
     */
    private String account;


    /**
     * 登陆密码
     */
    private String pwd;


    /**
     * 邀请码
     */
    private String code;
}
