package com.tianTech.shortlink.project.service;
/*
 * {@code @author} ma
 * {@code @date} 2024-02-19 9:28
 * {@code @project} shortlink
 *
 */

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tianTech.shortlink.project.dao.entity.ShortLinkDO;
import com.tianTech.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.tianTech.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.tianTech.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.tianTech.shortlink.project.dto.resp.ShortLinkPageRespDTO;

public interface ShortLinkService extends IService<ShortLinkDO> {

    /**
     * 创建短链接
     *
     * @param requestParam
     * @return
     */
    ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam);

    /**
     * 短链接分组查询
     *
     * @param requestParam
     * @return
     */
    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO requestParam);
}
