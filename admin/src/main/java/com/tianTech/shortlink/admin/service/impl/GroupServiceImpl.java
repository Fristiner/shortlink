package com.tianTech.shortlink.admin.service.impl;
/*
 * {@code @author} ma
 * {@code @date} 2024-02-17 14:45
 * {@code @project} shortlink
 *
 */


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tianTech.shortlink.admin.dao.entity.GroupDO;
import com.tianTech.shortlink.admin.dao.mapper.GroupMapper;
import com.tianTech.shortlink.admin.service.GroupService;
import org.springframework.stereotype.Service;

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
}
