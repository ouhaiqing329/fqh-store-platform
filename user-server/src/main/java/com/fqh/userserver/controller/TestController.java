package com.fqh.userserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController {



    @GetMapping("/**")
    public String defaultPath(HttpServletRequest request){
        return "hello world, uri:" + request.getRequestURI();
    }

}
