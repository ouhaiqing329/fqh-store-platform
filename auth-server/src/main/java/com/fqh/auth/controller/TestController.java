package com.fqh.auth.controller;

import com.fqh.auth.config.annotations.WebLog;
import com.fqh.utils.response.BaseResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/print")
    @WebLog
    public BaseResponseResult<Void> test(){
        System.out.println("print ok");
        return BaseResponseResult.success();
    }

}
