package com.fqh.storeserver.service;

import com.fqh.storeserver.response.SlideshowResp;

import java.util.List;

public interface SlideshowService {

    /**
     * 列表
     *
     * @param serviceCode 服务代码
     * @return {@link SlideshowResp}
     */
    List<SlideshowResp> list(String serviceCode);
}
