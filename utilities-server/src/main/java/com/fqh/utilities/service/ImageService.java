package com.fqh.utilities.service;

import com.fqh.utilities.entity.ImageEntity;

public interface ImageService {

    /**
     * 查找一个图片内容
     *
     * @param id id
     * @return {@link ImageEntity}
     */
    ImageEntity selectOne(Long id);

    /**
     * 保存
     *
     * @param imageEntity 图像实体
     * @return boolean
     */
    boolean save(ImageEntity imageEntity);
}
