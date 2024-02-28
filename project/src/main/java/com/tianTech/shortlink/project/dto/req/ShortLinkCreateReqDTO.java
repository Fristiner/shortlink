package com.tianTech.shortlink.project.dto.req;
/*
 * {@code @author} ma
 * {@code @date} 2024-02-19 9:38
 * {@code @project} shortlink
 *
 */


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * {@code @projectName:}    shortlink
 * {@code @package:}        com.tianTech.shortlink.project.dto.req
 * {@code @className:}      ShortLinkCreateReqDTO
 * {@code @author:}         ma
 * {@code @date:}           2024-02-19 9:38
 * {@code @description:}
 */
/*
短链接请求对象实体
 */
@Data
public class ShortLinkCreateReqDTO {

    /**
     * 域名
     */
    private String domain;

    /**
     * 原始链接
     */
    private String originUrl;

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 创建类型 0：接口创建 1：控制台创建
     */
    private Integer createdType;

    /**
     * 有效期类型 0：永久有效 1：自定义
     */
    private Integer validDateType;

    /**
     * 有效期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date validDate;

    /**
     * 描述
     */
    private String describe;

}
