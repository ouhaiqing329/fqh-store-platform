package com.fqh.orderserver.service;

import com.fqh.utils.response.UserInfo;

/**
 * es文档服务
 *
 * @author fqh
 * @date 2022/08/13
 */
public interface EsDocumentService {

    void save();

    UserInfo list(Long userId);
}
