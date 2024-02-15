package com.tianTech.shortlink.admin.service.impl;
/*
 * {@code @author} ma
 * {@code @date} 2024-02-14 9:30
 * {@code @project} shortlink
 *
 */


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tianTech.shortlink.admin.common.constant.RedisCacheConstant;
import com.tianTech.shortlink.admin.common.convention.exception.ClientException;
import com.tianTech.shortlink.admin.common.enums.UserErrorCodeEnum;
import com.tianTech.shortlink.admin.dao.entity.UserDO;
import com.tianTech.shortlink.admin.dao.mapper.UserMapper;
import com.tianTech.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.tianTech.shortlink.admin.dto.resp.UserRespDTO;
import com.tianTech.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
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
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

//    private final StringRedisTemplate stringRedisTemplate;

    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;
    private final RedissonClient redissonClient;

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

//        userRegisterCachePenetrationBloomFilter.add
        if (userDO == null) {
            throw new ClientException(UserErrorCodeEnum.USER_NULL);
        }
        BeanUtils.copyProperties(userDO, result);
        return result;
//

    }

    @Override
    public Boolean hasUsername(String username) {
//        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
//                .eq(UserDO::getUsername, username);
//        UserDO userDO = baseMapper.selectOne(queryWrapper);
//        return userDO == null;
        return userRegisterCachePenetrationBloomFilter.contains(username);
    }


    /**
     * 注册
     *
     * @param requestParam 注册用户请求参数
     */
    @Override
    public void register(UserRegisterReqDTO requestParam) {
        if (hasUsername(requestParam.getUsername())) {
            // 用户名以及存在
            throw new ClientException(UserErrorCodeEnum.USER_NAME_EXIST);
        }
        RLock lock = redissonClient.getLock(RedisCacheConstant.LOCK_USER_REGISTER_KEY + requestParam.getUsername());
        try {
            // 获取到锁之后
            if (lock.tryLock()) {
                int inserted = baseMapper.insert(BeanUtil.toBean(requestParam, UserDO.class));
                // 在布隆过滤器中添加数据
                if (inserted < 1) {
                    // 插入不成功
                    throw new ClientException(UserErrorCodeEnum.USER_SAVE_ERROR);
                }
                userRegisterCachePenetrationBloomFilter.add(requestParam.getUsername());
                return;
            }
            throw new ClientException(UserErrorCodeEnum.USER_NAME_EXIST);
        } finally {
            lock.unlock();
        }
    }
}
