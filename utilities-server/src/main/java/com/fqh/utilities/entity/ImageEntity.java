package com.fqh.utilities.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 图像实体
 *
 * @author fqh
 * @date 2023/01/07
 */
@Data
@TableName("image")
public class ImageEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 图片名字
     */
    private String imgName;


    /**
     * 图片类型
     */
    private String type;

    /**
     * 图片大小 m
     */
    private Double size;

    /**
     * 存储路径
     */
    private String storePath;


    /**
     * 上传用户
     */
    private Long uploadUser;


    /**
     * 登录地址
     */
    private Long loginAddress;


    /**
     * 创建时间
     */
    private Date createTime;


}
