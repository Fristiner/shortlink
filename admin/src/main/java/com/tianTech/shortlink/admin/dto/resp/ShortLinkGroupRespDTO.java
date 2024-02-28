package com.tianTech.shortlink.admin.dto.resp;
/*
 * {@code @author} ma
 * {@code @date} 2024-02-17 15:09
 * {@code @project} shortlink
 *
 */


import lombok.Data;

/**
 * {@code @projectName:}    shortlink
 * {@code @package:}        com.tianTech.shortlink.admin.dto.resp
 * {@code @className:}      ShortLinkGroupRespDTO
 * {@code @author:}         ma
 * {@code @date:}           2024-02-17 15:09
 * {@code @description:}
 */

/**
 * 短链接分组返回实体
 */
@Data
public class ShortLinkGroupRespDTO {
    /**
     * 分组标识
     */
    private String gid;

    /**
     * 分组名称
     */
    private String name;

    /**
     * 创建分组用户名
     */
    private String username;

    /**
     * 分组排序
     */
    private Integer sortOrder;
}
