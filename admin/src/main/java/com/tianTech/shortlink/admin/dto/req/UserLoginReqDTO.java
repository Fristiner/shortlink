package com.tianTech.shortlink.admin.dto.req;
/*
 * {@code @author} ma
 * {@code @date} 2024-02-17 9:36
 * {@code @project} shortlink
 *
 */


import lombok.Data;

/**
 * {@code @projectName:}    shortlink
 * {@code @package:}        com.tianTech.shortlink.admin.dto.req
 * {@code @className:}      UserLoginReqDTO
 * {@code @author:}         ma
 * {@code @date:}           2024-02-17 9:36
 * {@code @description:}
 */
/*
用户请求登录参数
 */
@Data
public class UserLoginReqDTO {
    private String username;
    private String password;

}
