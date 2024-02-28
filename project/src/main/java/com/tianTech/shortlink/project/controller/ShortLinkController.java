package com.tianTech.shortlink.project.controller;
/*
 * {@code @author} ma
 * {@code @date} 2024-02-19 9:30
 * {@code @project} shortlink
 *
 */


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tianTech.shortlink.project.common.convention.result.Result;
import com.tianTech.shortlink.project.common.convention.result.Results;
import com.tianTech.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.tianTech.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.tianTech.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.tianTech.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import com.tianTech.shortlink.project.service.ShortLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@code @projectName:}    shortlink
 * {@code @package:}        com.tianTech.shortlink.project.controller
 * {@code @className:}      ShortLinkController
 * {@code @author:}         ma
 * {@code @date:}           2024-02-19 9:30
 * {@code @description:}
 */

@RestController
@RequiredArgsConstructor
public class ShortLinkController {

    private final ShortLinkService shortLinkService;

    /**
     * 创建短链接
     *
     * @return
     */

    @PostMapping("/api/short-link/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
//        requestParam.getCreatedType();

        return Results.success(shortLinkService.createShortLink(requestParam));
    }

    /**
     * 分页数据查询短链接
     *
     * @param requestParam 请求参数
     * @return 查询结果
     */
    @GetMapping("/api/short-link/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(@RequestBody ShortLinkPageReqDTO requestParam) {
        IPage<ShortLinkPageRespDTO> shortLinkPageRespDTOIPage = shortLinkService.pageShortLink(requestParam);
        return Results.success(shortLinkPageRespDTOIPage);
//        return null;
    }
}
