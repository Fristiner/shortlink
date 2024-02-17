package com.tianTech.shortlink.admin.controller;
/*
 * {@code @author} ma
 * {@code @date} 2024-02-14 9:32
 * {@code @project} shortlink
 *
 */


import com.tianTech.shortlink.admin.common.convention.errorcode.BaseErrorCode;
import com.tianTech.shortlink.admin.common.convention.result.Result;
import com.tianTech.shortlink.admin.common.convention.result.Results;
import com.tianTech.shortlink.admin.dto.req.UserLoginReqDTO;
import com.tianTech.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.tianTech.shortlink.admin.dto.req.UserUpdateReqDTO;
import com.tianTech.shortlink.admin.dto.resp.UserLoginRespDTO;
import com.tianTech.shortlink.admin.dto.resp.UserRespDTO;
import com.tianTech.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.springframework.web.bind.annotation.*;

/**
 * {@code @projectName:}    shortlink
 * {@code @package:}        com.tianTech.shortlink.admin.controller
 * {@code @className:}      UserController
 * {@code @author:}         ma
 * {@code @date:}           2024-02-14 9:32
 * {@code @description:}
 */
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    //    @RequestMapping("/demo")
    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;

    /**
     * 根据用户名查询用户信息，脱敏后的信息
     */
    @GetMapping("/api/short-link/v1/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable("username") String username) {
        UserRespDTO result = userService.getUserByUsername(username);
        Result<UserRespDTO> userRespDTOResult = new Result<>();
        if (result == null) {
            return new Result<UserRespDTO>().setCode(BaseErrorCode.USER_NAME_NOEXIST_ERROR.code())
                    .setMessage(BaseErrorCode.USER_NAME_NOEXIST_ERROR.message());

//            return Results.failure(BaseErrorCode.USER_NAME_NOEXIST_ERROR.code(),BaseErrorCode.USER_NAME_NOEXIST_ERROR.message());
        } else {
//            userRespDTOResult.setCode("0").setData(result);
//            return userRespDTOResult;
            return Results.success(result);
        }
    }

    @GetMapping("/api/short-link/actually/user/{username}")
    public Result<UserRespDTO> getUserActuallyByUsername(@PathVariable("username") String username) {
        UserRespDTO result = userService.getUserByUsername(username);
        Result<UserRespDTO> userRespDTOResult = new Result<>();
        if (result == null) {
            return new Result<UserRespDTO>().setCode(BaseErrorCode.USER_NAME_NOEXIST_ERROR.code())
                    .setMessage(BaseErrorCode.USER_NAME_NOEXIST_ERROR.message());

        } else {

            return Results.success(result);
        }
    }

    @GetMapping("/api/short-link/v1/user/has-username")
    public Result<Boolean> hasUsername(@RequestParam("username") String username) {
        return Results.success(userService.hasUsername(username));
    }

    /**
     * 注册用户
     *
     * @param requestParam
     * @return
     */
    @PostMapping("/api/short-link/v1/user")
    public Result<Void> register(@RequestBody UserRegisterReqDTO requestParam) {
        userService.register(requestParam);
        return Results.success();
    }

    @PutMapping("/api/short-link/v1/user")
    public Result<Void> update(@RequestBody UserUpdateReqDTO userUpdateReqDTO) {
        userService.update(userUpdateReqDTO);
        return Results.success();
    }

    @PostMapping("/api/short-link/v1/user/login")
    public Result<UserLoginRespDTO> login(@RequestBody UserLoginReqDTO requestParam) {
        UserLoginRespDTO userLoginRespDTO = userService.login(requestParam);
        return Results.success(userLoginRespDTO);
    }

    @GetMapping("/api/short-link/v1/user/check-login")
    public Result<Boolean> checkLogin(@RequestParam("username") String username, @RequestParam("token") String token) {
        Boolean is = userService.checkLogin(username, token);
        return Results.success(is);
    }


    /**
     * 用户退出登录
     *
     * @param username
     * @param token
     * @return
     */
    @DeleteMapping("/api/short-link/v1/user/check-login")
    public Result<Void> logout(@RequestParam("username") String username, @RequestParam("token") String token) {

        userService.logout(username, token);
        return Results.success();
    }

    //    private final Redisson
    @GetMapping("/demo")
    public String demo() {
//        userRegisterCachePenetrationBloomFilter.add("")
        for (int i = 0; i < 1000; i++) {
            userRegisterCachePenetrationBloomFilter.add("tom " + i);
        }
        int count = 0;
        for (int i = 0; i < 1000; i++) {
            if (userRegisterCachePenetrationBloomFilter.contains("bob" + i)) {
                count++;
            }
        }

        userRegisterCachePenetrationBloomFilter.add("1");
        System.out.println("userRegisterCachePenetrationBloomFilter.contains(\"1\") = " + userRegisterCachePenetrationBloomFilter.contains("1"));


        System.out.println(userRegisterCachePenetrationBloomFilter.contains("bob1"));
        System.out.println(userRegisterCachePenetrationBloomFilter.contains("bob2"));
        System.out.println(userRegisterCachePenetrationBloomFilter.contains("bob3"));
        System.out.println(userRegisterCachePenetrationBloomFilter.contains("bob4"));
        System.out.println(userRegisterCachePenetrationBloomFilter.contains("bob5"));


        System.out.println(userRegisterCachePenetrationBloomFilter.contains("tom1"));
        System.out.println(userRegisterCachePenetrationBloomFilter.contains("tom2"));
        System.out.println(userRegisterCachePenetrationBloomFilter.contains("tom3"));
        System.out.println(userRegisterCachePenetrationBloomFilter.contains("tom4"));
        System.out.println(userRegisterCachePenetrationBloomFilter.contains("tom5"));
        return null;
    }


}
