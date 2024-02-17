package com.tianTech.shortlink.admin.dto.resp;
/*
 * {@code @author} ma
 * {@code @date} 2024-02-17 9:34
 * {@code @project} shortlink
 *
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {@code @projectName:}    shortlink
 * {@code @package:}        com.tianTech.shortlink.admin.dto.resp
 * {@code @className:}      UserLoginRespDTO
 * {@code @author:}         ma
 * {@code @date:}           2024-02-17 9:34
 * {@code @description:}
 */

/**
 * 用户登录
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRespDTO {
    /**
     * 用户token
     */
    private String token;

}
