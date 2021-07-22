package com.notes.blog.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Create by HeLongJun on 2021/7/21 19:19
 *
 * @author Administrator
 * @Description:
 */
@Data
public class LoginResponseVO implements Serializable {

    /**
     * 用户账号
     */
    private String account;

    /**
     * 权限
     */
    private String authorities;


    /**
     * token
     */
    private String token;

    public LoginResponseVO(String account, String authorities, String token) {
        this.account = account;
        this.authorities = authorities;
        this.token = token;
    }
}
