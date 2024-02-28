package com.tianTech.shortlink.admin.service;
/*
 * {@code @author} ma
 * {@code @date} 2024-02-14 9:30
 * {@code @project} shortlink
 *
 */

import com.baomidou.mybatisplus.extension.service.IService;
import com.tianTech.shortlink.admin.dao.entity.UserDO;
import com.tianTech.shortlink.admin.dto.req.UserLoginReqDTO;
import com.tianTech.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.tianTech.shortlink.admin.dto.req.UserUpdateReqDTO;
import com.tianTech.shortlink.admin.dto.resp.UserLoginRespDTO;
import com.tianTech.shortlink.admin.dto.resp.UserRespDTO;

public interface UserService extends IService<UserDO> {

    /*

     */
    UserRespDTO getUserByUsername(String username);

    /**
     * 查询用户名是否存在
     *
     * @param username 用户名
     * @return 用户名存在返回true
     */
    Boolean hasUsername(String username);


    /**
     * 注册用户
     *
     * @param requestParam 注册用户请求参数
     */
    void register(UserRegisterReqDTO requestParam);


    void update(UserUpdateReqDTO requestParam);


    UserLoginRespDTO login(UserLoginReqDTO requestParam);

    Boolean checkLogin(String username, String token);


    void logout(String username, String token);


    String getToken(String username);
}
