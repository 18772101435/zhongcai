//package com.silv.api.controller;
//
//import com.aliyuncs.exceptions.ClientException;
//import com.silv.api.model.Result;
//import com.silv.api.service.SmsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
///**
// * Created by yao on 2017/9/25.
// */
//@RestController
//@RequestMapping("sms")
//public class SmsController {
//    @Autowired
//    private SmsService smsService;
//
//    /**
//     * 发送验证码
//     *
//     * @throws Exception
//     */
//    @PostMapping("/send")
//    public Result sendSmsCode(@RequestParam String [] phones) throws ClientException {
//        return this.smsService.sendSms(phones);
//    }
//
//    /**
//     * 提交验证码
//     *
//     * @throws Exception
//     */
//    @RequestMapping(value="submit",method = RequestMethod.POST)
//    public String submitCode(@RequestParam String code, @RequestParam String phone) {
//        // 设置1分钟过期
////        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////        System.out.println(sdf.parse(sendSmsDate));
////        Timestamp expireTime = new Timestamp(sdf.parse(sendSmsDate).getTime()+1*60*1000);
//        String result = this.smsService.getCodeByPhone(phone, code);   // 根据电话，按照时间降序，取最近的一条短信验证码
//        return result;
//    }
//
//}
