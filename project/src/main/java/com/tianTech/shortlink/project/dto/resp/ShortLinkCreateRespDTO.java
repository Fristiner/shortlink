package com.tianTech.shortlink.project.dto.resp;
/*
 * {@code @author} ma
 * {@code @date} 2024-02-19 9:42
 * {@code @project} shortlink
 *
 */


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {@code @projectName:}    shortlink
 * {@code @package:}        com.tianTech.shortlink.project.dto.resp
 * {@code @className:}      ShortLinkCreateRespDTO
 * {@code @author:}         ma
 * {@code @date:}           2024-02-19 9:42
 * {@code @description:}
 */
/*
短链接创建响应对象
 */

@Data
//    @RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShortLinkCreateRespDTO {

    /**
     * 分组信息
     */
    private String gid;

    /**
     * 原始链接
     */
    private String originUrl;

    /**
     * 短链接
     */
    private String fullShortUrl;
}
