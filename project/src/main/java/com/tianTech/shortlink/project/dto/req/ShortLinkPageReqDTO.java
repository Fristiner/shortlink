package com.tianTech.shortlink.project.dto.req;
/*
 * {@code @author} ma
 * {@code @date} 2024-02-28 9:44
 * {@code @project} shortlink
 *
 */


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tianTech.shortlink.project.dao.entity.ShortLinkDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * {@code @projectName:}    shortlink
 * {@code @package:}        com.tianTech.shortlink.project.dto.req
 * {@code @className:}      ShortLinkPageReqDTO
 * {@code @author:}         ma
 * {@code @date:}           2024-02-28 9:44
 * {@code @description:}
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class ShortLinkPageReqDTO extends Page<ShortLinkDO> {
    /**
     * 分组标识
     */
    private String gid;

}
