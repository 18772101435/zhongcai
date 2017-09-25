package com.silv.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
// import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by yao on 2017/9/22.
 */
@SpringBootApplication
// @EnableEurekaServer
public class App {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }
}
