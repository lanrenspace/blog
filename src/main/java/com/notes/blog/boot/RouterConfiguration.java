package com.notes.blog.boot;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * Create by HeLongJun on 2021/7/24 17:58
 *
 * @author lanrenspace@163.com
 * @Description:
 */
@Configuration
public class RouterConfiguration implements WebFluxConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").maxAge(3600);
    }
}
