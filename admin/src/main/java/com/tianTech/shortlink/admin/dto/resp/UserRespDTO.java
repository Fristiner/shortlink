package com.tianTech.shortlink.admin.dto.resp;
/*
 * {@code @author} ma
 * {@code @date} 2024-02-14 9:35
 * {@code @project} shortlink
 *
 */


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tianTech.shortlink.admin.common.serializer.PhoneDesensitizationSerializer;
import lombok.Data;

/**
 * {@code @projectName:}    shortlink
 * {@code @package:}        com.tianTech.shortlink.admin.dto.resp
 * {@code @className:}      UserRespDTO
 * {@code @author:}         ma
 * {@code @date:}           2024-02-14 9:35
 * {@code @description:}
 */
@Data
public class UserRespDTO {
    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;


    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号
     */
    @JsonSerialize(using = PhoneDesensitizationSerializer.class)
    private String phone;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 注销时间戳
     */
    private Long deletionTime;
}
