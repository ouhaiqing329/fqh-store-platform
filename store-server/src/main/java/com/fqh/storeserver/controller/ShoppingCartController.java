package com.fqh.storeserver.controller;

import com.fqh.utils.response.BaseResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 购物车控制器
 *
 * @author ouhaiqing
 * @date 2022/9/7 10:49
 */
@RestController
@Api(tags = "购物车管理")
@RequestMapping("/store/cart")
public class ShoppingCartController {


    @ApiOperation(value = "添加一个商品到购物车")
    @PostMapping("/save")
    public BaseResponseResult<Void> save(){

        return BaseResponseResult.success();
    }

    @ApiOperation(value = "从购物车移除一个商品")
    @PostMapping("/remove")
    public BaseResponseResult<Void> remove(){

        return BaseResponseResult.success();
    }



}
