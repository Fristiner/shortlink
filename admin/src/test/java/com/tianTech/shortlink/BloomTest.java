package com.tianTech.shortlink;
/*
 * {@code @author} ma
 * {@code @date} 2024-02-14 19:47
 * {@code @project} shortlink
 *
 */


import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * {@code @projectName:}    shortlink
 * {@code @package:}        com.tianTech.shortlink
 * {@code @className:}      BloomTest
 * {@code @author:}         ma
 * {@code @date:}           2024-02-14 19:47
 * {@code @description:}
 */

@SpringBootTest
@SpringBootConfiguration
@RequiredArgsConstructor
public class BloomTest {

//    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;


//    private final RedissonClient redissonClient;

    @Test
    void testBool() {
        System.out.println("hello");
    }
}
