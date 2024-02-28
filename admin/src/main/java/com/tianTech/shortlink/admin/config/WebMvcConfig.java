package com.tianTech.shortlink.admin.config;
/*
 * {@code @author} ma
 * {@code @date} 2024-02-17 16:06
 * {@code @project} shortlink
 *
 */


import com.tianTech.shortlink.admin.common.interceptor.UserTokenInterceptor;
import lombok.RequiredArgsConstructor;
import org.assertj.core.util.Lists;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * {@code @projectName:}    shortlink
 * {@code @package:}        com.tianTech.shortlink.admin.config
 * {@code @className:}      WebMvcConfig
 * {@code @author:}         ma
 * {@code @date:}           2024-02-17 16:06
 * {@code @description:}
 */
@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private final StringRedisTemplate stringRedisTemplate;

    private final List<String> IGNORE_PATH = Lists.newArrayList(
            "/api/short-link/v1/user/login",
            "/api/short-link/v1/user",
            "/api/short-link/v1/user/check-login",
            "/api/short-link/v1/user/getToken"
    );

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserTokenInterceptor(stringRedisTemplate))
                .excludePathPatterns(IGNORE_PATH)
//                .addPathPatterns("/*")
                .order(1);
    }
}
