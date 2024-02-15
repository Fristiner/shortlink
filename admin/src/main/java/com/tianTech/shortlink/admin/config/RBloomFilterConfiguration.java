package com.tianTech.shortlink.admin.config;
/*
 * {@code @author} ma
 * {@code @date} 2024-02-14 18:04
 * {@code @project} shortlink
 *
 */


import com.tianTech.shortlink.admin.common.constant.RedisCacheConstant;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * {@code @projectName:}    shortlink
 * {@code @package:}        com.tianTech.shortlink.admin.config
 * {@code @className:}      RBloomFilterConfiguration
 * {@code @author:}         ma
 * {@code @date:}           2024-02-14 18:04
 * {@code @description:}
 */
@Configuration
public class RBloomFilterConfiguration {
    /**
     * 布隆过滤器
     */
    @Bean
    public RBloomFilter<String> userRegisterCachePenetrationBloomFilter(RedissonClient redissonClient) {
        RBloomFilter<String> cachePenetrationBloomFilter = redissonClient.getBloomFilter(RedisCacheConstant.BLOOM_FILTER_USER_NAME);
        cachePenetrationBloomFilter.tryInit(1000000L, 0.001);
        return cachePenetrationBloomFilter;
    }


}
