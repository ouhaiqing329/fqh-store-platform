package com.fqh.userserver.controller;

import com.fqh.userserver.entity.User;
import com.fqh.userserver.service.UserInfoService;
import com.fqh.utils.response.BaseResponseResult;
import com.fqh.utils.response.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 提供认证服务接口
 *
 * @author fqh
 * @date 2022/08/14
 */
@RestController
@RequestMapping("/user/auth")
public class AuthController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/getUserInfo")
    public BaseResponseResult<UserInfo> getUserInfo(String username){
        return userInfoService.getUserInfo(username);
    }

}
