package com.tianTech.shortlink.admin.common.enums;
/*
 * {@code @author} ma
 * {@code @date} 2024-02-14 10:30
 * {@code @project} shortlink
 *
 */

import com.tianTech.shortlink.admin.common.convention.errorcode.IErrorCode;

public enum UserErrorCodeEnum implements IErrorCode {

    USER_NULL("B000200", "用户记录不存在");

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
