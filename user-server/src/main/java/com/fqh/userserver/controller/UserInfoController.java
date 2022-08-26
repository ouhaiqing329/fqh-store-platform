package com.fqh.userserver.controller;

import com.fqh.userserver.request.UserInfoReq;
import com.fqh.userserver.service.UserInfoService;
import com.fqh.utils.response.BaseResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户信息控制器
 *
 * @author ouhaiqing
 * @date 2022/8/23 9:58
 */
@RestController
@Api(value = "用户信息",tags = "用户信息")
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 注册用户
     */
    @ApiOperation(value = "注册用户")
    @PostMapping("/register")
    public BaseResponseResult<Void> registerUser(UserInfoReq request){
        boolean flag = userInfoService.register(request);
        if (!flag){
            BaseResponseResult.error();
        }
        return BaseResponseResult.success();
    }
}
