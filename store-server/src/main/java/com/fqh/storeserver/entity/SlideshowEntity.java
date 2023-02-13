package com.fqh.storeserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "slideshow")
public class SlideshowEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String serviceCode;

    private String url;

    private Integer ranking;

    private String createUser;

    private Date createTime;
}
