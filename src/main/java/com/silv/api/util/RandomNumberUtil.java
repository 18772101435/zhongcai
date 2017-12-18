package com.silv.api.util;

import java.util.Random;

/**
 * Created by yao on 2017/12/18.
 */
public class RandomNumberUtil {

    // 随机生成指定长度的数字字母组合的验证码
    public static String generateNumCode(int length) {
        String val = "";
        String charStr = "char";
        String numStr = "num";
        Random random = new Random();

        // 参数length，表示生成几位随机数
        for (int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? charStr : numStr;
            // 输出字母还是数字
            if (charStr.equalsIgnoreCase(charOrNum)) {
                // 输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (random.nextInt(26) + temp);
            } else if (numStr.equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }
}
