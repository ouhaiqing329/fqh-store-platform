package com.fqh.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @GetMapping("/config")
    public ModelAndView toIndex(){
        return new ModelAndView("configcenter/index");
    }

    @GetMapping("/sso/login")
    public ModelAndView toLogin(){
        return new ModelAndView("login");
    }
}
