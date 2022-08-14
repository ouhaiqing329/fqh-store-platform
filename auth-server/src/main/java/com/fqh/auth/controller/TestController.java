package com.fqh.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gateway/auth")
public class TestController {

    @GetMapping("/test")
    public void test(){
        System.out.println("test");
    }

}
