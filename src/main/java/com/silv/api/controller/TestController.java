package com.silv.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @PostMapping
    public String getPhones(@RequestParam String[] phones) {
        String a = "";
        for (int i = 0; i < phones.length; i++) {
            a = a + phones[i] + ",";
        }
        return a.substring(0, a.length() - 1);
    }
}
