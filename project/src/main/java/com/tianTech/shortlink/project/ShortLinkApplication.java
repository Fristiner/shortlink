package com.tianTech.shortlink.project;
/*
 * {@code @author} ma
 * {@code @date} 2024-02-19 9:56
 * {@code @project} shortlink
 *
 */


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * {@code @projectName:}    shortlink
 * {@code @package:}        com.tianTech.shortlink.project
 * {@code @className:}      ShortLinkApplication
 * {@code @author:}         ma
 * {@code @date:}           2024-02-19 9:56
 * {@code @description:}
 */
@SpringBootApplication
@MapperScan("com.tianTech.shortlink.project.dao.mapper")
public class ShortLinkApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShortLinkApplication.class, args);
        
    }
}
