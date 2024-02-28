package com.tianTech.shortlink.project.config;
/*
 * {@code @author} ma
 * {@code @date} 2024-02-14 18:04
 * {@code @project} shortlink
 *
 */


import com.tianTech.shortlink.project.common.constant.RedisCacheConstant;
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
     * 防止短链接创建查询数据库
     */
    @Bean
    public RBloomFilter<String> shortUrlCreatPenetrationBloomFilter(RedissonClient redissonClient) {
        RBloomFilter<String> cachePenetrationBloomFilter = redissonClient.getBloomFilter(RedisCacheConstant.BLOOM_FILTER_SHORTLINK_URL);
        cachePenetrationBloomFilter.tryInit(10000000L, 0.01);
        return cachePenetrationBloomFilter;
    }


}
