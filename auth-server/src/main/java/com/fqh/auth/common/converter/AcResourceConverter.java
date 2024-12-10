package com.fqh.auth.common.converter;

import com.fqh.auth.common.req.AcResourceReq;
import com.fqh.auth.entity.AcResource;

public class AcResourceConverter {
    public static AcResource convert(AcResourceReq req) {
        AcResource acResource = new AcResource();
        acResource.setParentId(req.getParentId());
        acResource.setName(req.getName());
        acResource.setCode(req.getCode());
        acResource.setType(req.getType());
        acResource.setEnabled(req.getEnabled());
        acResource.setSort(req.getSort());
        acResource.setIcon(req.getIcon());

        return acResource;
    }
}
