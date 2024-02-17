package com.tianTech.shortlink.admin.dao.entity;
/*
 * {@code @author} ma
 * {@code @date} 2024-02-17 14:42
 * {@code @project} shortlink
 *
 */


import com.baomidou.mybatisplus.annotation.TableName;
import com.tianTech.shortlink.admin.common.database.BaseDO;
import lombok.*;

/**
 * {@code @projectName:}    shortlink
 * {@code @package:}        com.tianTech.shortlink.admin.dao.entity
 * {@code @className:}      GroupDO
 * {@code @author:}         ma
 * {@code @date:}           2024-02-17 14:42
 * {@code @description:}
 */


@EqualsAndHashCode(callSuper = true)
@Data
@TableName("t_group")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupDO extends BaseDO {

    /**
     * id
     */
    private Long id;

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 分组名称
     */
    private String name;

    /**
     * 创建分组用户名
     */
    private String username;

    /**
     * 分组排序
     */
    private Integer sortOrder;
}

