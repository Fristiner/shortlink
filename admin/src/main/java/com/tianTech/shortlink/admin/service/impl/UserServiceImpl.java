package com.tianTech.shortlink.admin.service.impl;
/*
 * {@code @author} ma
 * {@code @date} 2024-02-14 9:30
 * {@code @project} shortlink
 *
 */


import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tianTech.shortlink.admin.common.constant.RedisCacheConstant;
import com.tianTech.shortlink.admin.common.convention.exception.ClientException;
import com.tianTech.shortlink.admin.common.enums.UserErrorCodeEnum;
import com.tianTech.shortlink.admin.dao.entity.UserDO;
import com.tianTech.shortlink.admin.dao.mapper.UserMapper;
import com.tianTech.shortlink.admin.dto.req.UserLoginReqDTO;
import com.tianTech.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.tianTech.shortlink.admin.dto.req.UserUpdateReqDTO;
import com.tianTech.shortlink.admin.dto.resp.UserLoginRespDTO;
import com.tianTech.shortlink.admin.dto.resp.UserRespDTO;
import com.tianTech.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
    private final StringRedisTemplate stringRedisTemplate;

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

    /**
     * 修改用户
     *
     * @param requestParam 修改用户请求参数
     */
    @Override
    public void update(UserUpdateReqDTO requestParam) {
        //TODO：验证当前用户名是否为登录用户

        LambdaUpdateWrapper<UserDO> lambdaUpdateWrapper = Wrappers.lambdaUpdate(UserDO.class)
                .eq(UserDO::getUsername, requestParam.getUsername());
        baseMapper.update(BeanUtil.toBean(requestParam, UserDO.class), lambdaUpdateWrapper);

    }

    /**
     * 用户登录接口
     *
     * @param requestParam 用户登录请求参数
     * @return 用户登录返回参数
     */
    @Override
    public UserLoginRespDTO login(UserLoginReqDTO requestParam) {
        //1.验证用户名和密码是否存在
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, requestParam.getUsername())
                .eq(UserDO::getPassword, requestParam.getPassword())
                .eq(UserDO::getDelFlag, 0);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null) {
            throw new ClientException("用户不存在");
        }
        String key = RedisCacheConstant.USER_LOGIN_USERNAME + requestParam.getUsername();
        Boolean aBoolean = stringRedisTemplate.hasKey(key);
        if (aBoolean != null && aBoolean) {
            throw new ClientException("用户已经登录");
        }
        // 2.将数据添加到redis中
        // uuid 作为token
        String uuid = UUID.randomUUID().toString();
//        stringRedisTemplate.opsForValue().set(uuid, JSON.toJSONString(userDO), 30, TimeUnit.SECONDS);
//        Map<String,Object> userInfoMap = new HashMap<>();
//        userInfoMap.put("token",)
        stringRedisTemplate.opsForHash().put(key,
                uuid, JSON.toJSONString(userDO));
        stringRedisTemplate.expire(key, RedisCacheConstant.USER_LOGIN_TOKEN_TIME, TimeUnit.MINUTES);
        return new UserLoginRespDTO(uuid);
    }

    /**
     * 检查用户是否登录
     *
     * @param token 用户token
     * @return 用户是否登录
     */
    @Override
    public Boolean checkLogin(String username, String token) {
//        return stringRedisTemplate.hasKey(token);
        String key = RedisCacheConstant.USER_LOGIN_USERNAME + username;
        return stringRedisTemplate.opsForHash().get(key, token) != null;
    }

    @Override
    public void logout(String username, String token) {
        // 删掉token数据
        String key = RedisCacheConstant.USER_LOGIN_USERNAME + username;
        // 验证是否登录
        if (checkLogin(username, token)) {
            stringRedisTemplate.delete(key);
            return;
        }
        throw new ClientException("用户Token不存在或未登录");
//        stringRedisTemplate
    }
}
