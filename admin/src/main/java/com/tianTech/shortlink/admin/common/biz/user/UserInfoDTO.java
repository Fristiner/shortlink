package com.tianTech.shortlink.admin.common.biz.user;
/*
 * {@code @author} ma
 * {@code @date} 2024-02-17 15:36
 * {@code @project} shortlink
 *
 */


import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {@code @projectName:}    shortlink
 * {@code @package:}        com.tianTech.shortlink.admin.common.biz.user
 * {@code @className:}      UserInfo
 * {@code @author:}         ma
 * {@code @date:}           2024-02-17 15:36
 * {@code @description:}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoDTO {

    /**
     * 用户 ID
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 用户 Token
     */
    private String token;

    public UserInfoDTO(String userId, String username, String realName) {
        this.userId = userId;
        this.username = username;
        this.realName = realName;
    }
}
