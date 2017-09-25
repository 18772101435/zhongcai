package com.silv.api.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yao on 2017/9/22.
 */
@RestController
public class test1 {

    @RequestMapping(value="/hello")
    public String test(){
        return "index";
    }
}

