package com.fqh.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 权限详情表
 */
@Data
@TableName(value = "ac_resource")
public class AcResource implements Serializable {

    /**
     *
     */
    private String id;
    /**
     * 父级Id
     */
    private String parentId;
    /**
     * 菜单/功能名称
     */
    private String name;
    /**
     * 菜单/功能编码
     */
    private String code;
    /**
     * 资源类型 0-菜单；1-功能
     */
    private Integer type;
    /**
     * 是否禁用 1-启用 0-禁用
     */
    private Integer enabled;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 图标
     */
    private String icon;
    /**
     * 更新时间
     */
    private LocalDateTime modifyTime;
    /**
     * 插入时间
     */
    private LocalDateTime createdTime;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 更新人
     */
    private String modifier;
    /**
     * 删除标识 1-已删除 | 2-正常
     */
    private Integer deleteMark;


}
