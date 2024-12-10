package com.fqh.auth.common.req;


import io.swagger.annotations.ApiModelProperty;

import lombok.Data;


@Data
public class AcResourceReq {

    /**
     * 父级Id
     */
    @ApiModelProperty(value = "父级Id")
    private String parentId;

    /**
     * 菜单/功能名称
     */
    @ApiModelProperty(value = "菜单/功能名称")
    private String name;

    /**
     * 菜单/功能编码
     */
    @ApiModelProperty(value = "菜单/功能编码")
    private String code;

    /**
     * 资源类型 1-菜单；0-功能
     */
    @ApiModelProperty(value = "资源类型 1-菜单；0-功能")
    private Integer type;

    /**
     * 是否禁用 1-启用 0-禁用
     */
    @ApiModelProperty(value = "是否禁用 1-启用 0-禁用")
    private Integer enabled;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;

    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    private String icon;

}
