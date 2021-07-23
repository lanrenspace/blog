package com.notes.blog.boot;

import com.notes.blog.entity.UserInfo;
import com.notes.blog.repository.UserInfoRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

/**
 * Create by HeLongJun on 2021/7/21 18:35
 *
 * @author lanrenspace@163.com
 * @Description:
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class JwtSigner {

    private final UserInfoRepository userInfoRepository;
    private final String key = "justAJwtSingleKey";
    private final String authorities = "authorities";
    private final String issuer = "identity";
    private final String TOKEN_PREFIX = "Bearer ";

    @Value("${jwt.expiration.duration}")
    private int duration;


    public String getAuthoritiesTag() {
        return authorities;
    }

    public String getIssuerTag() {
        return issuer;
    }

    public String getTokenPrefix() {
        return TOKEN_PREFIX;
    }


    public Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }


    public String generateToken(UserInfo userInfo) {
        return Jwts.builder().setSubject(userInfo.getAccount()).claim(authorities, userInfo.getAuthorities())
                .signWith(SignatureAlgorithm.HS256, key).setIssuer(issuer)
                .setExpiration(Date.from(Instant.now().plus(Duration.ofMinutes(duration))))
                .setIssuedAt(new Date(System.currentTimeMillis())).compact();
    }

    public String generateToken(String account) {
        return generateToken(Objects.requireNonNull(userInfoRepository.findByAccount(account).block()));
    }
}
