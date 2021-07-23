package com.notes.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.notes.blog.base.DataEntity;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Create by HeLongJun on 2021/7/21 12:05
 *
 * @author lanrenspace@163.com
 * @Description: 用户信息
 */
@Data
public class UserInfo extends DataEntity implements UserDetails {

    /**
     * 用户ID
     */
    @Id
    private Integer id;

    /**
     * 用户账号
     */
    private String account;


    /**
     * 用户密码
     */
    @JsonIgnore
    private String pwd;


    /**
     * 使用邀请码
     */
    private String inviteCode;


    /**
     * 租户编码
     */
    private String tenantCode;

    @JsonIgnore
    private String authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (StringUtils.isEmpty(authorities)) {
            return new HashSet<>();
        }
        return Stream.of(authorities.split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return pwd;
    }

    @Override
    public String getUsername() {
        return account;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
