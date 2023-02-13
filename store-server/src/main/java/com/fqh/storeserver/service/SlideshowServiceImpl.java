package com.fqh.storeserver.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fqh.storeserver.entity.SlideshowEntity;
import com.fqh.storeserver.mapper.SlideshowMapper;
import com.fqh.storeserver.response.SlideshowResp;
import com.fqh.utils.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;

@Service
public class SlideshowServiceImpl implements SlideshowService{
    @Autowired
    private SlideshowMapper slideshowMapper;

    @Override
    public List<SlideshowResp> list(String serviceCode) {
        List<SlideshowEntity> slideshowEntityList = slideshowMapper.selectList(Wrappers.<SlideshowEntity>lambdaQuery().eq(SlideshowEntity::getServiceCode, serviceCode));
        if (CollectionUtils.isEmpty(slideshowEntityList)){
            return new ArrayList<>();
        }
        List<SlideshowResp> list = new ArrayList<>();
        slideshowEntityList.forEach(slideshowEntity -> {
            SlideshowResp slideshowResp = new SlideshowResp();
            BeanUtils.copyProperties(slideshowEntity,slideshowResp);
            list.add(slideshowResp);
        });
        return list;
    }
}
