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
import com.silv.api.model.Email;
import com.silv.api.model.Result;
import com.silv.api.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class EMailService {
    @Value("${accessKeyId}")
    private String accessKeyId;
    @Value("${accessKeySecret}")
    private String accessKeySecret;

    @Value("${mail.accountName}")
    private String accountName;
    @Value("${mail.tagName}")
    private String tagName;
    @Value("${mail.fromAlias}")
    private String sender;
    @Value("${mail.subject}")
    private String theme;

    private static String code = String.valueOf(((Math.random() * 9 + 1) * 100000)).substring(0, 6);
    private static String emailContent = "尊敬的用户 : 您的验证码为" +code+"，该验证码1分钟有效，请注意查收！谢谢！";

    @Autowired
    private EMailDao eMailDao;

    public Result sendEMail(String receiveEmail) throws ClientException {
        // 如果是除杭州region外的其它region（如新加坡、澳洲Region），需要将下面的"cn-hangzhou"替换为"ap-southeast-1"、或"ap-southeast-2"。
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
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

            result = this.saveEmail(receiveEmail);
        } catch (ServerException e) {
            e.printStackTrace();
            result = ResultUtil.error(0, "ServerException");
        } catch (ClientException e) {
            e.printStackTrace();
            result = ResultUtil.error(0, "ClientException");
        }
        return result;
    }

    public Result saveEmail(String receiveEmail) {
        Email mail = new Email();
        mail.setSender(sender);
        mail.setSendEmail(accountName);
        mail.setReceiveEmail(receiveEmail);
        mail.setTheme(theme);
        mail.setContent(emailContent);
        mail.setCreateTime(new Timestamp(System.currentTimeMillis()));
        this.eMailDao.save(mail);
        return ResultUtil.success(mail);
    }
}
