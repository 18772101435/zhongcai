package com.silv.api.controller;

import com.silv.api.model.Result;
import com.silv.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by yao on 2017/12/20.
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    /**
     * 添加用户
     *
     * @throws Exception
     */
    @PostMapping("add")
    public Result addUser(@RequestParam String name, @RequestParam String password) throws IOException, NoSuchAlgorithmException {
        Result result = this.userService.addUser(name, password);
        return result;
    }

    /**
     * 登录获取token时对user进行校验
     *
     * @throws Exception
     */
    @PostMapping("check")
    public Result checkPassword(@RequestParam String name, @RequestParam String password) throws IOException, NoSuchAlgorithmException {
        Result result = this.userService.checkUser(name, password);
        return result;
    }

    /**
     * 查询所有用户信息
     *
     * @throws Exception
     */
    @PostMapping("all")
    public Result queryAll() {
        Result result = this.userService.queryAll();
        return result;
    }


}
