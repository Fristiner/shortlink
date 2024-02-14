package com.tianTech.shortlink.admin.service;
/*
 * {@code @author} ma
 * {@code @date} 2024-02-14 9:30
 * {@code @project} shortlink
 *
 */

import com.baomidou.mybatisplus.extension.service.IService;
import com.tianTech.shortlink.admin.dao.entity.UserDO;
import com.tianTech.shortlink.admin.dto.resp.UserRespDTO;

public interface UserService extends IService<UserDO> {

    /*
    
     */
    UserRespDTO getUserByUsername(String username);
}
