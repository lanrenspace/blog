package com.notes.blog.handler;

import com.notes.blog.boot.JwtSigner;
import com.notes.blog.dto.LoginBodyDTO;
import com.notes.blog.dto.RegisterBodyDTO;
import com.notes.blog.entity.UserInfo;
import com.notes.blog.exceptions.SubmitDataNullException;
import com.notes.blog.repository.InviteCodeRepository;
import com.notes.blog.repository.UserInfoRepository;
import com.notes.blog.utils.TenantUtils;
import com.notes.blog.vo.LoginResponseVO;
import com.notes.blog.vo.ResultData;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Create by HeLongJun on 2021/7/21 11:24
 *
 * @author Administrator
 * @Description: 认证相关处理
 */
@RequiredArgsConstructor
@Component
public class AuthenticateHandler {

    private final InviteCodeRepository inviteCodeRepository;
    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    private final JwtSigner jwtSigner;


    /**
     * 登陆
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> login(ServerRequest request) {
        return request.bodyToMono(LoginBodyDTO.class).flatMap(loginBodyDTO -> {
            if (StringUtils.isEmpty(loginBodyDTO.getAccount()) || StringUtils.isEmpty(loginBodyDTO.getPwd())) {
                return Mono.error(new SubmitDataNullException("登陆账号或登陆密码不能为空!"));
            }
            return userInfoRepository.findByAccount(loginBodyDTO.getAccount()).filter(userInfo -> passwordEncoder.matches(loginBodyDTO.getPwd(), userInfo.getPwd()))
                    .flatMap(userInfo -> {
                        LoginResponseVO loginResponseVO = new LoginResponseVO(userInfo.getAccount(), userInfo.getAuthorities().toString(), jwtSigner.generateToken(userInfo));
                        return ResultData.getSuccess(loginResponseVO);
                    })
                    .switchIfEmpty(ResultData.getError("登陆失败,账号不存在或密码错误!"));
        });
    }

    /**
     * 注册
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> register(ServerRequest request) {
        return request.bodyToMono(RegisterBodyDTO.class).flatMap(registerBodyDTO -> {
            // 校验参数
            if (StringUtils.isEmpty(registerBodyDTO.getAccount())
                    || StringUtils.isEmpty(registerBodyDTO.getPwd())
                    || StringUtils.isEmpty(registerBodyDTO.getCode())) {
                return Mono.error(new SubmitDataNullException("登陆账号、登陆密码或邀请码不能为空!"));
            }
            if (registerBodyDTO.getPwd().trim().length() < 6) {
                return Mono.error(new SubmitDataNullException("登陆密码长度不能小于6位!"));
            }
            // 校验邀请码
            return inviteCodeRepository.findByCode(registerBodyDTO.getCode()).flatMap(inviteCode -> {
                // 校验用户
                return userInfoRepository.findByAccount(registerBodyDTO.getAccount()).flatMap(userInfo1 -> ResultData.getError("账号已存在!"))
                        .switchIfEmpty(createAccount(registerBodyDTO));
            }).switchIfEmpty(ResultData.getError("邀请码错误!"));
        }).switchIfEmpty(ResultData.getError("注册信息不能为空!"));
    }


    /**
     * 创建账号
     *
     * @param registerBodyDTO
     * @return
     */
    private Mono<ServerResponse> createAccount(RegisterBodyDTO registerBodyDTO) {
        UserInfo userInfo = new UserInfo();
        userInfo.setAccount(registerBodyDTO.getAccount());
        userInfo.setPwd(passwordEncoder.encode(registerBodyDTO.getPwd()));
        userInfo.setInviteCode(registerBodyDTO.getCode());
        userInfo.setTenantCode(TenantUtils.generateCode());
        return userInfoRepository.save(userInfo).flatMap(userInfo1 -> ResultData.getSuccess(userInfo1));
    }
}
