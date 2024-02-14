package com.tianTech.shortlink.admin;
/*
 * {@code @author} ma
 * {@code @date} 2024-02-14 9:10
 * {@code @project} shortlink
 *
 */


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * {@code @projectName:}    shortlink
 * {@code @package:}        com.tianTech.shortlink.admin
 * {@code @className:}      ShortlinkAdminApplication
 * {@code @author:}         ma
 * {@code @date:}           2024-02-14 9:10
 * {@code @description:}
 */
@SpringBootApplication
@MapperScan("com.tianTech.shortlink.admin.dao.mapper")
public class ShortlinkAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShortlinkAdminApplication.class, args);
    }
}
