package com.silv.api.controller;

import com.aliyuncs.exceptions.ClientException;
import com.silv.api.dao.EMailDao;
import com.silv.api.model.Result;
import com.silv.api.service.EMailService;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("email")
public class EMailController {

    @Autowired
    private EMailService mailService;

    @Autowired
    private EMailDao eMailDao;

    /**
     * 阿里发送邮件
     *
     * @throws Exception
     */
    @PostMapping("ali-single-send")
    public Result sendEMail(@RequestParam String receiveEmail) throws ClientException {
        return this.mailService.sendEMail(receiveEmail);
    }

    /**
     * 搜狐普通发送邮件
     *
     * @throws Exception
     */
    @PostMapping("sohu-common")
    public Result sendCommon(@RequestParam String receiveEmail, @RequestParam String subject, @RequestParam String emailContent) throws IOException {

        final String url = "http://api.sendcloud.net/apiv2/mail/send";

        Result result = this.mailService.sendCommon(url, receiveEmail, subject, emailContent);
        return result;

    }

    /**
     * 搜狐模板发送邮件
     *
     * @throws Exception
     */
    @PostMapping("sohu-complate")
    public void sendComplate(@RequestParam String receiveEmail, @RequestParam String subject, @RequestParam String emailContent, @RequestParam String template) throws IOException {

        final String url = "http://sendcloud.sohu.com/webapi/mail.send_template.json";

        final String apiUser = "jjJJ_test_5gb4aS ";
        final String apiKey = "UlfSuHUe43V2v1he";
        final String fromEmail = "test@dream-pan.top";
        final String fromName = "陈瑶";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("api_user", apiUser));
        params.add(new BasicNameValuePair("api_key", apiKey));
        params.add(new BasicNameValuePair("to", receiveEmail));
        params.add(new BasicNameValuePair("template_invoke_name", template));
        params.add(new BasicNameValuePair("from", fromEmail));
        params.add(new BasicNameValuePair("fromName", fromName));
        params.add(new BasicNameValuePair("subject", subject));
        params.add(new BasicNameValuePair("use_maillist", "true"));
        params.add(new BasicNameValuePair("resp_email_id", "true")); // 是否返回. 有多个收件人时, 会返回 emailId 的列表

        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        HttpResponse response = httpClient.execute(httpPost);

        //
        httpPost.releaseConnection();
    }
}
