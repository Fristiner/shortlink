package com.tianTech.shortlink.admin.common.constant;
/*
 * {@code @author} ma
 * {@code @date} 2024-02-14 19:55
 * {@code @project} shortlink
 *
 */


/**
 * {@code @projectName:}    shortlink
 * {@code @package:}        com.tianTech.shortlink.admin.common.constant
 * {@code @className:}      RedisConstant
 * {@code @author:}         ma
 * {@code @date:}           2024-02-14 19:55
 * {@code @description:}
 */
public class RedisCacheConstant {
    public final static String BLOOM_FILTER_USER_NAME = "bloom-filter";

    public final static String LOCK_USER_REGISTER_KEY = "short-link:lock_user-register:";

    public final static String USER_LOGIN_USERNAME = "login_";

    public final static Long USER_LOGIN_TOKEN_TIME = 30L;
}
