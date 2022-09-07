package com.fqh.orderserver.controller;

import com.fqh.orderserver.request.OrderSaveRequest;
import com.fqh.orderserver.service.OrderManagementService;
import com.fqh.utils.response.BaseResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单管理
 *
 * @author ouhaiqing
 * @date 2022/9/7 10:55
 */
@RestController
@Api(tags = "订单管理")
@RequestMapping("/order/management")
public class OrderManagementController {

    @Autowired
    private OrderManagementService orderManagementService;

    @PostMapping("/save")
    @ApiOperation(value = "新增订单")
    public BaseResponseResult<Void> save(@RequestBody OrderSaveRequest request){
//        orderManagementService.save(request);
        return BaseResponseResult.success();
    }

}
