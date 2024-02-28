package com.tianTech.shortlink.admin.dto.req;
/*
 * {@code @author} ma
 * {@code @date} 2024-02-17 17:01
 * {@code @project} shortlink
 *
 */


import lombok.Data;

/**
 * {@code @projectName:}    shortlink
 * {@code @package:}        com.tianTech.shortlink.admin.dto.req
 * {@code @className:}      ShortLinkGroupUpdateReqDTO
 * {@code @author:}         ma
 * {@code @date:}           2024-02-17 17:01
 * {@code @description:}
 */
@Data
public class ShortLinkGroupUpdateReqDTO {
    /*
    分组标识
     */
    private String gid;
    private String name;
}
