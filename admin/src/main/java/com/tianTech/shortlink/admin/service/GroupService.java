package com.tianTech.shortlink.admin.service;
/*
 * {@code @author} ma
 * {@code @date} 2024-02-17 14:44
 * {@code @project} shortlink
 *
 */


import com.baomidou.mybatisplus.extension.service.IService;
import com.tianTech.shortlink.admin.dao.entity.GroupDO;
import com.tianTech.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import com.tianTech.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;

import java.util.List;

/**
 * {@code @projectName:}    shortlink
 * {@code @package:}        com.tianTech.shortlink.admin.service
 * {@code @className:}      GroupService
 * {@code @author:}         ma
 * {@code @date:}           2024-02-17 14:44
 * {@code @description:}
 */
public interface GroupService extends IService<GroupDO> {
    void saveGroup(String groupName);


    List<ShortLinkGroupRespDTO> listGroup();


    void updateGroup(ShortLinkGroupUpdateReqDTO requestParam);


    void deleteGroup(String gid);
}
