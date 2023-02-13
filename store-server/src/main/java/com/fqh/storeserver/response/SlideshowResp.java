package com.fqh.storeserver.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SlideshowResp {

    @ApiModelProperty(value = "轮播图Id")
    private Long id;

    @ApiModelProperty(value = "地址")
    private String url;

    @ApiModelProperty(value = "排序")
    private Integer ranking;
}
