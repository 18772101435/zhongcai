package com.silv.api.controller;

import com.silv.api.dto.UserDto;
import com.silv.api.model.DutyArrangeDetails;
import com.silv.api.model.Result;
import com.silv.api.service.DutyArrangeService;
import com.silv.api.service.UserService;
import com.silv.api.util.DateUtils;
import com.silv.api.util.ResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by yao on 2017/12/20.
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DutyArrangeService dutyArrangeService;

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

    @PostMapping(value = "/getUser")
    public Result getUser(@Validated UserDto userDto, BindingResult result) {
        Map<String, Object> map = new HashMap<>();
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return ResultUtil.error(map);
        }
        long size = userDto.getFile().getSize();
        map.put("name", userDto.getName());
        map.put("password", userDto.getPassword());
        return ResultUtil.success(map);
    }


}
