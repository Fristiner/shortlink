package com.tianTech.shortlink.admin.common.convention.result;
/*
 * {@code @author} ma
 * {@code @date} 2024-02-14 10:03
 * {@code @project} shortlink
 *
 */


import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * {@code @projectName:}    shortlink
 * {@code @package:}        com.tianTech.shortlink.admin.common.convention.result
 * {@code @className:}      Result
 * {@code @author:}         ma
 * {@code @date:}           2024-02-14 10:03
 * {@code @description:}
 */
@Data
@Accessors(chain = true)
public class Result<T> implements Serializable {

    /**
     * 正确返回码
     */
    public static final String SUCCESS_CODE = "0";
    @Serial
    private static final long serialVersionUID = 5679018624309023727L;
    /**
     * 返回码
     */
    private String code;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 请求ID
     */
    private String requestId;

    public boolean isSuccess() {
        return SUCCESS_CODE.equals(code);
    }
}