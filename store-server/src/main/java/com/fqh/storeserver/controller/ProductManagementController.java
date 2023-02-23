package com.fqh.storeserver.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.fqh.storeserver.entity.ProductEntity;
import com.fqh.storeserver.service.ProductManagementService;
import com.fqh.utils.response.BaseResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * product management controller
 *
 * @author ouhaiqing
 * @date 2022/9/7 9:59
 */

@RestController
@Api(tags = "商品管理")
@RequestMapping("/store/product")
public class ProductManagementController {

    @Autowired
    private ProductManagementService productManagementService;

    @ApiOperation(value = "获取一个商品详情")
    @GetMapping("/getDetail/{productid}")
    public BaseResponseResult<ProductEntity> getDetail(@PathVariable("productid") Long productid){

        return BaseResponseResult.success();
    }


    @ApiOperation(value = "下单一个商品")
    @PostMapping("/addOrder")
    public BaseResponseResult<ProductEntity> addOrder(@RequestBody ProductEntity productEntity){
        int flagNum = productManagementService.addOrder(productEntity);
        if (flagNum == -1){
            return BaseResponseResult.error("商品已售罄！");
        }else if (flagNum == -2){
            return BaseResponseResult.error("系统繁忙，请稍后再试！");
        }
        return BaseResponseResult.success();
    }





}
