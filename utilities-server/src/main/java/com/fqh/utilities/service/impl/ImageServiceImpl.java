package com.fqh.utilities.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fqh.utilities.entity.ImageEntity;
import com.fqh.utilities.mapper.ImageMapper;
import com.fqh.utilities.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl  implements ImageService {

    @Autowired
    private ImageMapper imageMapper;


    @Override
    public ImageEntity selectOne(Long id) {
        return imageMapper.selectOne(Wrappers.<ImageEntity>lambdaQuery().eq(ImageEntity::getId, id));
    }

    @Override
    public boolean save(ImageEntity imageEntity) {
        return imageMapper.insert(imageEntity) > 0;
    }
}
