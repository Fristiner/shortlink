package com.tianTech.shortlink.admin.common.serializer;
/*
 * {@code @author} ma
 * {@code @date} 2024-02-14 14:41
 * {@code @project} shortlink
 *
 */


import cn.hutool.core.util.DesensitizedUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * {@code @projectName:}    shortlink
 * {@code @package:}        com.tianTech.shortlink.admin.common.serializer
 * {@code @className:}      IdCardDesensitizationSerializer
 * {@code @author:}         ma
 * {@code @date:}           2024-02-14 14:41
 * {@code @description:}
 */

/**
 * 身份证信息的脱敏处理
 */
public class IdCardDesensitizationSerializer extends JsonSerializer<String> {
    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        String idCardNum = DesensitizedUtil.idCardNum(value, 4, 4);
        gen.writeString(idCardNum);
    }
}
