package com.tianTech.shortlink.admin.common.interceptor;
/*
 * {@code @author} ma
 * {@code @date} 2024-02-17 15:56
 * {@code @project} shortlink
 *
 */


import com.alibaba.fastjson2.JSON;
import com.tianTech.shortlink.admin.common.biz.user.UserHolder;
import com.tianTech.shortlink.admin.common.biz.user.UserInfoDTO;
import com.tianTech.shortlink.admin.common.constant.RedisCacheConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * {@code @projectName:}    shortlink
 * {@code @package:}        com.tianTech.shortlink.admin.common.interceptor
 * {@code @className:}      UserTokenInterceptor
 * {@code @author:}         ma
 * {@code @date:}           2024-02-17 15:56
 * {@code @description:}
 */

public class UserTokenInterceptor implements HandlerInterceptor {

    private final StringRedisTemplate stringRedisTemplate;


    public UserTokenInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        return HandlerInterceptor.super.preHandle(request, response, handler);
        // 1.获取用户token 和 username
        String username = request.getHeader("username");
        String token = request.getHeader("token");
        // 2.基于token 获取redis中用户
        Object userInfoStr = stringRedisTemplate.opsForHash().get(RedisCacheConstant.USER_LOGIN_USERNAME + username, token);

        if (userInfoStr == null) {
            response.setStatus(401);
            return false;
        }
        // 3. 如果不等于null
        UserInfoDTO userInfoDTO = JSON.parseObject(userInfoStr.toString(), UserInfoDTO.class);
        UserHolder.setUser(userInfoDTO);
        return true;
        // 4.
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        UserHolder.removeUser();
    }
}
