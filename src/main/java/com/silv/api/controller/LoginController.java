package com.silv.api.controller;

import com.silv.api.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yao on 2017/9/25.
 */
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping(value="/login")
    public String login(){
        this.loginService.login();
        return "调用数据库！！";
    }
    @RequestMapping(value="/add")
    public String add(){
        this.loginService.add();
        return "添加一个人！！";
    }
}
