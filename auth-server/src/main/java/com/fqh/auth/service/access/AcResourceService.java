package com.fqh.auth.service.access;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fqh.auth.common.req.AcResourceReq;
import com.fqh.auth.entity.AcResource;

/**
 * @author ouhai
 * @description 针对表【ac_resource(权限详情表)】的数据库操作Service
 * @createDate 2024-12-10 15:28:23
 */
public interface AcResourceService extends IService<AcResource> {


    /**
     * 保存资源
     *
     * @param req 要求事情
     * @return {@link Boolean }
     */
    Boolean save(AcResourceReq req);


    /**
     * 删除资源
     *
     * @param id id
     * @return {@link Boolean }
     */
    Boolean delete(String id);
}
