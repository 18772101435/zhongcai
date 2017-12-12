package com.silv.api.service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.silv.api.dao.SmsDao;
import com.silv.api.model.Sms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * Created by yao on 2017/9/25.
 */
@Service
public class SmsService {
    @Value("${sms.message.product}")
    private String product;
    @Value("${sms.message.domain}")
    private String domain;
    @Value("${sms.message.accessKeyId}")
    private String accessKeyId;
    @Value("${sms.message.accessKeySecret}")
    private String accessKeySecret;
    @Value("${sms.message.signName}")
    private String signName;
    @Value("${sms.message.templateCode}")
    private String templateCode;

    @Autowired
    private SmsDao smsDao;

    public String sendSms(String phone) throws ClientException {
        // 可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        // 初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        // 组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        // 必填:待发送手机号
        request.setPhoneNumbers(phone);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);

        String code = this.getRandomCode();
        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的用户,您的验证码为${code}"时,此处的值为
        // 友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam("{\"code\": " + code + "}");
        // 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        // request.setSmsUpExtendCode("90997");

        // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        // hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        String result = "短信发送失败";
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            // 发送短信请求成功,保存短信内容到数据库
            System.out.println("_____________发送SMS_115930654短信收到的响应信息_______________");
            System.out.println("请求的ID:" + sendSmsResponse.getRequestId());
            System.out.println("请求的状态码:" + sendSmsResponse.getCode());
            System.out.println("请求的状态码描述:" + sendSmsResponse.getMessage());
            System.out.println("请求的回执ID:" + sendSmsResponse.getBizId());
            result = this.saveSms(phone, code);
        }
        return result;
    }

    public String saveSms(String phone, String code) {
        Sms sms = new Sms();
        sms.setMessageCode(code);
        sms.setPhone(phone);
        sms.setCreateTime(new Timestamp(System.currentTimeMillis()));
        sms.setExpireTime(new Timestamp(System.currentTimeMillis() + 1 * 60 * 1000)); // 验证码一分钟后过期
        this.smsDao.save(sms);
        return "短信发送成功";
    }

    private static String getRandomCode() {
        return String.valueOf(((Math.random() * 9 + 1) * 100000)).substring(0, 6);
    }

    public String getCodeByPhone(String phone, String code) {
        Sms sms = this.smsDao.getCodeByPhone(phone);
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        if(currentTime.after(sms.getExpireTime())){  // 超时时间已过
            return "该验证码已失效"+"   "+code+"   "+phone;
        }
        if(!sms.getMessageCode().equals(code)){
            return "验证码错误";
        }
        return "验证码正确，验证通过"+"   "+code+"   "+phone;
    }
}
