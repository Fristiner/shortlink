package com.tianTech.shortlink.admin.utils;
/*
 * {@code @author} ma
 * {@code @date} 2024-02-17 14:50
 * {@code @project} shortlink
 *
 */


import java.security.SecureRandom;

/**
 * {@code @projectName:}    shortlink
 * {@code @package:}        com.tianTech.shortlink.admin.utils
 * {@code @className:}      RandomGenerator
 * {@code @author:}         ma
 * {@code @date:}           2024-02-17 14:50
 * {@code @description:}
 */
public final class RandomGenerator {

    private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * 生成随机分组ID
     *
     * @return 分组ID
     */
    public static String generateRandom() {
        return generateRandom(6);
    }

    /**
     * 生成随机分组ID
     *
     * @param length 生成多少位
     * @return 分组ID
     */
    public static String generateRandom(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }
        return sb.toString();
    }
}
