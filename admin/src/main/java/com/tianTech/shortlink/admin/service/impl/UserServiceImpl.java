package com.tianTech.shortlink.admin.service.impl;
/*
 * {@code @author} ma
 * {@code @date} 2024-02-14 9:30
 * {@code @project} shortlink
 *
 */


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tianTech.shortlink.admin.common.convention.exception.ClientException;
import com.tianTech.shortlink.admin.common.enums.UserErrorCodeEnum;
import com.tianTech.shortlink.admin.dao.entity.UserDO;
import com.tianTech.shortlink.admin.dao.mapper.UserMapper;
import com.tianTech.shortlink.admin.dto.resp.UserRespDTO;
import com.tianTech.shortlink.admin.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * {@code @projectName:}    shortlink
 * {@code @package:}        com.tianTech.shortlink.admin.service.impl
 * {@code @className:}      UserServiceImpl
 * {@code @author:}         ma
 * {@code @date:}           2024-02-14 9:30
 * {@code @description:}
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    @Override
    public UserRespDTO getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        UserRespDTO result = new UserRespDTO();
//        return BeanUtils.copyProperties(u);
//        if (userDO != null) {
//            BeanUtils.copyProperties(userDO, result);
//            return result;
//        } else {
//            return null;
//        }
        if (userDO == null) {
            throw new ClientException(UserErrorCodeEnum.USER_NULL);
        }
        BeanUtils.copyProperties(userDO, result);
        return result;
//

    }
}
