package com.fqh.auth.service.access;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fqh.auth.common.converter.AcResourceConverter;
import com.fqh.auth.common.enums.ResourceTypeEnum;
import com.fqh.auth.common.req.AcResourceReq;
import com.fqh.auth.config.handle.ServiceException;
import com.fqh.auth.entity.AcResource;
import com.fqh.auth.mapper.AcResourceMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author ouhai
 * @description 针对表【ac_resource(权限详情表)】的数据库操作Service实现
 * @createDate 2024-12-10 15:28:23
 */
@Service
public class AcResourceServiceImpl extends ServiceImpl<AcResourceMapper, AcResource>
        implements AcResourceService {

    @Override
    public Boolean save(AcResourceReq req) {

        //判断是否为一级菜单
        if (StringUtils.hasText(req.getParentId())){
            //功能不可以新建一级菜单
            if (ResourceTypeEnum.FUNCTION.getCode() == req.getType()){
                throw new ServiceException("一级菜单不能新增功能权限！");
            }
            AcResource acResource = AcResourceConverter.convert(req);
        }


        return null;
    }

    @Override
    public Boolean delete(String id) {
        return null;
    }
}




