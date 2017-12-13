package com.silv.api.controller;

import com.aliyuncs.exceptions.ClientException;
import com.silv.api.model.Result;
import com.silv.api.service.EMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("email")
public class EMailController {

    @Autowired
    private EMailService mailService;

    /**
     * 发送邮件
     *
     * @throws Exception
     */
    @PostMapping("send")
    public Result sendEMail(@RequestParam String receiveEmail) throws ClientException {
        return this.mailService.sendEMail(receiveEmail);
    }

}
