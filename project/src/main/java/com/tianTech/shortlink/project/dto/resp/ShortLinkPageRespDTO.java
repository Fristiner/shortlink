package com.tianTech.shortlink.project.dto.resp;
/*
 * {@code @author} ma
 * {@code @date} 2024-02-28 9:46
 * {@code @project} shortlink
 *
 */


import lombok.Data;

import java.sql.Date;

/**
 * {@code @projectName:}    shortlink
 * {@code @package:}        com.tianTech.shortlink.project.dto.resp
 * {@code @className:}      ShortLinkPageRespDTO
 * {@code @author:}         ma
 * {@code @date:}           2024-02-28 9:46
 * {@code @description:}
 */
@Data
public class ShortLinkPageRespDTO {
    /**
     * 域名
     */
    private String domain;

    /**
     * 短链接
     */
    private String shortUri;

    /**
     * 完整短链接
     */
    private String fullShortUrl;

    /**
     * 原始链接
     */
    private String originUrl;

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 有效期类型 0：永久有效 1：自定义
     */
    private Integer validDateType;

    /**
     * 有效期
     */
    private Date validDate;

    /**
     * 描述
     */
    private String describe;

    /**
     * 网站标识
     */
    private String favicon;

}
