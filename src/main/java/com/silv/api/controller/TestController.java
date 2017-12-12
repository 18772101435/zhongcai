package com.silv.api.controller;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by yao on 2017/12/7.
 */
public class TestController {
    public static void main(String[] args) {
        System.out.println(new Date());
        System.out.println(new Date().getTime());
        System.out.println(new Timestamp((new Date()).getTime()));
        System.out.println(new Timestamp((new Date()).getTime()+1*60*1000));
        System.out.println(new Timestamp((new Date()).getTime()+1*60*1000).after(new Timestamp(System.currentTimeMillis())));
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        System.out.println("ts:::"+ts);

    }
}
