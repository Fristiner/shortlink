package com.tianTech.shortlink.admin.service.impl;
/*
 * {@code @author} ma
 * {@code @date} 2024-02-17 14:45
 * {@code @project} shortlink
 *
 */


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tianTech.shortlink.admin.common.biz.user.UserHolder;
import com.tianTech.shortlink.admin.dao.entity.GroupDO;
import com.tianTech.shortlink.admin.dao.mapper.GroupMapper;
import com.tianTech.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import com.tianTech.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;
import com.tianTech.shortlink.admin.service.GroupService;
import com.tianTech.shortlink.admin.utils.RandomGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@code @projectName:}    shortlink
 * {@code @package:}        com.tianTech.shortlink.admin.service.impl
 * {@code @className:}      GroupServiceImpl
 * {@code @author:}         ma
 * {@code @date:}           2024-02-17 14:45
 * {@code @description:}
 */
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {
    @Override
    public void saveGroup(String groupName) {
        String gid;
        do {
            gid = RandomGenerator.generateRandom();
        } while (!hasGid(gid));
        String username = UserHolder.getUsername();
        GroupDO build = GroupDO.builder()
                .gid(gid)
                .name(groupName)
                .sortOrder(0)
                .username(username)
                .build();
        baseMapper.insert(build);
    }

    /**
     * 查询用户短链接分组集合
     *
     * @return 返回分组
     */
    @Override
    public List<ShortLinkGroupRespDTO> listGroup() {
        // TODO：从当前请求中获取用户名
        String username = UserHolder.getUsername();
        LambdaQueryWrapper<GroupDO> groupDOLambdaQueryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getDelFlag, 0)
                .eq(GroupDO::getUsername, username)
                .orderByDesc(GroupDO::getSortOrder, GroupDO::getUpdateTime);
        List<GroupDO> groupDOS = baseMapper.selectList(groupDOLambdaQueryWrapper);
        return BeanUtil.copyToList(groupDOS, ShortLinkGroupRespDTO.class);
    }

    @Override
    public void updateGroup(ShortLinkGroupUpdateReqDTO requestParam) {
        String username = UserHolder.getUsername();
        LambdaUpdateWrapper<GroupDO> updateWrapper = Wrappers.lambdaUpdate(GroupDO.class)
                .eq(GroupDO::getUsername, username)
                .eq(GroupDO::getDelFlag, 0)
                .eq(GroupDO::getGid, requestParam.getGid());
        GroupDO groupDO = new GroupDO();
        groupDO.setName(requestParam.getName());
        baseMapper.update(groupDO, updateWrapper);
    }

    @Override
    public void deleteGroup(String gid) {
        String username = UserHolder.getUsername();

        LambdaUpdateWrapper<GroupDO> updateWrapper = Wrappers.lambdaUpdate(GroupDO.class)
                .eq(GroupDO::getUsername, username)
                .eq(GroupDO::getDelFlag, 0)
                .eq(GroupDO::getGid, gid);
        GroupDO groupDO = new GroupDO();
        groupDO.setDelFlag(1);
        baseMapper.update(groupDO, updateWrapper);
    }

    private boolean hasGid(String gid) {
        String username = UserHolder.getUsername();
        LambdaQueryWrapper<GroupDO> lambdaQueryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getDelFlag, 0)
                .eq(GroupDO::getGid, gid)
                .eq(GroupDO::getUsername, username);
        GroupDO groupDO = baseMapper.selectOne(lambdaQueryWrapper);
        return groupDO == null;
    }
}
