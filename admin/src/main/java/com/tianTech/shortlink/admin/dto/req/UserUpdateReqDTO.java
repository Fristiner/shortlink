package com.tianTech.shortlink.admin.dto.req;
/*
 * {@code @author} ma
 * {@code @date} 2024-02-15 18:40
 * {@code @project} shortlink
 *
 */


import lombok.Data;

/**
 * {@code @projectName:}    shortlink
 * {@code @package:}        com.tianTech.shortlink.admin.dto.req
 * {@code @className:}      UserUpdateReqDTO
 * {@code @author:}         ma
 * {@code @date:}           2024-02-15 18:40
 * {@code @description:}
 */
@Data
public class UserUpdateReqDTO {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String mail;

}
