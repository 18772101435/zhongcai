package com.silv.api.service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.silv.api.dao.EMailDao;
import com.silv.api.enums.StatusEnum;
import com.silv.api.model.Email;
import com.silv.api.model.Result;
import com.silv.api.util.ResultUtil;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class EMailService {
    @Value("${ali.accessKeyId}")
    private String accessKeyId;
    @Value("${ali.accessKeySecret}")
    private String accessKeySecret;

    @Value("${ali.mail.accountName}")
    private String accountName;
    @Value("${ali.mail.tagName}")
    private String tagName;
    @Value("${ali.mail.fromAlias}")
    private String sender;
    @Value("${ali.mail.subject}")
    private String theme;

    @Value("${sohu.apiUser}")
    private String apiUser;
    @Value("${sohu.apiKey}")
    private String apiKey;
    @Value("${sohu.from}")
    private String fromEmail;
    @Value("${sohu.fromName}")
    private String fromName;

    @Autowired
    private EMailDao eMailDao;

    private static Logger logger = LoggerFactory.getLogger(EMailService.class);

    public Result sendEMail(String receiveEmail) throws ClientException, RuntimeException {
        // 如果是除杭州region外的其它region（如新加坡、澳洲Region），需要将下面的"cn-hangzhou"替换为"ap-southeast-1"、或"ap-southeast-2"。
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        String emailContent = "尊敬的用户:您的验证码为" + String.valueOf(((Math.random() * 9 + 1) * 100000)).substring(0, 6) + "，该验证码1分钟有效，请注意查收！谢谢！";
        Result result = null;
        try {
            request.setAccountName(accountName); // 控制台的发信地址
            request.setFromAlias(sender); // 发信人（公司）名称
            request.setAddressType(1);
            request.setTagName(tagName); // 控制台的邮件标签
            request.setReplyToAddress(true);
            request.setToAddress(receiveEmail); // 收件人邮箱
            request.setSubject(theme); // 邮件标题
            request.setHtmlBody(emailContent); // 邮件内容
            SingleSendMailResponse httpResponse = client.getAcsResponse(request);

            result = this.saveEmail(receiveEmail, StatusEnum.VALID.getValue(), emailContent);
        } catch (ServerException e) {
            e.printStackTrace();
            return this.saveEmail(receiveEmail, StatusEnum.INVALID.getValue(), null);
        } catch (ClientException e) {
            logger.error("错误日志:{}", e);
            return this.saveEmail(receiveEmail, StatusEnum.INVALID.getValue(), null);
        }
        return result;
    }

    public Result saveEmail(String receiveEmail, int status, String emailContent) {
        Email mail = new Email();
        mail.setSender(sender);
        mail.setSendEmail(accountName);
        mail.setReceiveEmail(receiveEmail);
        mail.setTheme(theme);
        mail.setStatus(status);
        mail.setContent(emailContent);
        mail.setCreateTime(new Timestamp(System.currentTimeMillis()));
        this.eMailDao.save(mail);
        if (status == 0)
            return ResultUtil.error(0, "error");
        return ResultUtil.success(mail);
    }

    public Result sendCommon(String url, String receiveEmail, String subject, String emailContent) throws IOException {

        HttpPost httpPost = new HttpPost(url);
        HttpClient httpClient = new DefaultHttpClient();

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("apiUser", apiUser));
        params.add(new BasicNameValuePair("apiKey", apiKey));
        params.add(new BasicNameValuePair("to", receiveEmail));
        params.add(new BasicNameValuePair("from", fromEmail));
        params.add(new BasicNameValuePair("fromName", fromName));
        params.add(new BasicNameValuePair("subject", subject));
        params.add(new BasicNameValuePair("html", emailContent));

        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        HttpResponse response = httpClient.execute(httpPost);

        Result result = ResultUtil.error(0, "邮件发送失败" + response.getStatusLine());
        // 处理响应
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            // 正常返回, 解析返回数据
            Email mail = new Email();
            mail.setSender(fromName);
            mail.setSendEmail(fromEmail);
            mail.setReceiveEmail(receiveEmail);
            mail.setTheme(subject);
            mail.setStatus(1);
            mail.setContent(emailContent);
            mail.setCreateTime(new Timestamp(System.currentTimeMillis()));
            mail.setResponseCode(String.valueOf(response.getStatusLine()));
            this.eMailDao.save(mail);

            result = ResultUtil.success(mail);
        }
        httpPost.releaseConnection();
        return result;
    }
}
