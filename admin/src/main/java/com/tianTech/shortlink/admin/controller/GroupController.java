package com.tianTech.shortlink.admin.controller;
/*
 * {@code @author} ma
 * {@code @date} 2024-02-17 14:46
 * {@code @project} shortlink
 *
 */


import com.tianTech.shortlink.admin.common.convention.result.Result;
import com.tianTech.shortlink.admin.common.convention.result.Results;
import com.tianTech.shortlink.admin.dto.req.ShortLinkGroupSaveReqDTO;
import com.tianTech.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import com.tianTech.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;
import com.tianTech.shortlink.admin.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * {@code @projectName:}    shortlink
 * {@code @package:}        com.tianTech.shortlink.admin.controller
 * {@code @className:}      GroupController
 * {@code @author:}         ma
 * {@code @date:}           2024-02-17 14:46
 * {@code @description:}
 */
@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;


    @PostMapping("/api/short-link/v1/group")
    public Result<Void> save(@RequestBody ShortLinkGroupSaveReqDTO shortLinkGroupSaveReqDTO) {
        groupService.saveGroup(shortLinkGroupSaveReqDTO.getName());
        return Results.success();
    }

    @GetMapping("/api/short-link/v1/group")
    public Result<List<ShortLinkGroupRespDTO>> listGroup() {
        List<ShortLinkGroupRespDTO> shortLinkGroupRespDTOS =
                groupService.listGroup();
        return Results.success(shortLinkGroupRespDTOS);
    }

    /**
     * 修改短链接分组
     *
     * @param requestParam 请求参数
     * @return void
     */
    @PutMapping("/api/short-link/v1/group")
    public Result<Void> updateGroup(@RequestBody ShortLinkGroupUpdateReqDTO requestParam) {
        groupService.updateGroup(requestParam);
        return Results.success();
    }

    @DeleteMapping("/api/short-link/v1/group")
    public Result<Void> deleteGroup(@RequestParam("gid") String gid) {
        groupService.deleteGroup(gid);
        return Results.success();
    }


}
