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
import com.tianTech.shortlink.admin.dto.resp.UserRespDTO;
import com.tianTech.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 根据用户名查询用户信息，脱敏后的信息
     */
    @GetMapping("/api/shortlink/v1/user/{username}")
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

    @GetMapping("/api/shortlink/actually/user/{username}")
    public Result<UserRespDTO> getUserActuallyByUsername(@PathVariable("username") String username) {
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

}
