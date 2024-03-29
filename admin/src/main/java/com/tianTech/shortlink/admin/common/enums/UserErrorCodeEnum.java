package com.tianTech.shortlink.admin.common.enums;
/*
 * {@code @author} ma
 * {@code @date} 2024-02-14 10:30
 * {@code @project} shortlink
 *
 */

import com.tianTech.shortlink.admin.common.convention.errorcode.IErrorCode;

public enum UserErrorCodeEnum implements IErrorCode {

    USER_NULL("B000200", "用户记录不存在"),
    USER_NAME_EXIST("B000202", "用户名已存在"),

    USER_EXIST("B000203", "用户记录已存在"),

    USER_SAVE_ERROR("B000204", "用户记录新增失败");


    private final String code;

    private final String message;

    UserErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
