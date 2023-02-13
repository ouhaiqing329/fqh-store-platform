package com.fqh.storeserver.controller;

import com.fqh.storeserver.response.SlideshowResp;
import com.fqh.storeserver.service.SlideshowService;
import com.fqh.utils.response.BaseResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "轮播图管理")
@RequestMapping("/store/slide")
public class SlideshowController {

    @Autowired
    private SlideshowService slideshowService;

    @ApiOperation(value = "根据业务编码获取轮播图信息")
    @GetMapping("/list/{serviceCode}")
    public BaseResponseResult<List<SlideshowResp>> list(@PathVariable("serviceCode") String serviceCode) {
        return BaseResponseResult.success(slideshowService.list(serviceCode));
    }
}
