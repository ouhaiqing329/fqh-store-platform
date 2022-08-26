package com.fqh.auth.controller;

import com.fqh.utils.response.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ouhaiqing
 * @date 2022/8/25 17:26
 */
@Controller
@RequestMapping("/auth")
public class LoginController {

    @GetMapping("/login/index")
    public String login(Model model){
        return "/login";
    }


    @PostMapping("/login")
    public String login(UserInfo userInfo){
        return "成功";
    }

}
