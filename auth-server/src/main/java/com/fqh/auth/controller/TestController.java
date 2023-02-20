package com.fqh.auth.controller;

import com.fqh.utils.response.BaseResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gateway/auth")
public class TestController {

    @GetMapping("/test")
    public BaseResponseResult<Void> test(){
        System.out.println("test");
        return BaseResponseResult.success();
    }

}
