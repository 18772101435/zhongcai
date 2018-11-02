package com.silv.api.util;

import java.util.Arrays;
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

    /**
     * 求最大公倍数 -- 多个数的求法，先求出两个数的最大公倍数，用结果和第三个数再求
     *
     * @return
     */
    public static int getGreatestCommonDivisor(int a, int b) {
        int max, min;
        max = a > b ? a : b;
        min = a < b ? a : b;
        if (max % min != 0) {
            return getGreatestCommonDivisor(min, max % min);
        } else {
            return min;
        }
    }

    /**
     * 求最小公倍数 -- 最小公倍数*最大公约数 = 两数乘积   （a，b）×[a，b]=a×b --->[a,b] = a*b/(a,b)
     *
     * @return
     */
    public static int getLeastCommonMultiple(int a, int b) {
        int gcd = getGreatestCommonDivisor(a, b);
        int lcm = a * b / gcd;
        return lcm;
    }

    public static void main(String[] args) {
        getGreatestCommonDivisor(12, 12);
//        System.out.println(getLeastCommonMultiple(122, getLeastCommonMultiple(369,987)));
//        ICD(2, 10000, 10001, 3);
//        GCD(1, 16);
    }

    public static int ICD(int a, int b, int c, int d) {//求最小公倍数
        Long start = System.currentTimeMillis();
        int k = getMax(a, b, c, d);
        for (int i = k; ; i++) {
            if (i % a == 0 && i % b == 0 && i % c == 0 && i % d == 0) {
                Long end = System.currentTimeMillis();
                System.out.println(end - start);
                System.out.println("i:::" + i);
                return i;
            }
        }
    }

    public static int getMax(int a, int b, int c, int d) {//求最大值
        int[] aa = {a, b, c, d};
        Arrays.sort(aa);
        return aa[3];
    }

    public static int GCD(int a, int b) {//求最大公倍数
        a = a < b ? a : b;
        for (int i = a; ; i--) {
            if (a % i == 0 && b % i == 0) {
                System.out.println("i:::" + i);
                return i;
            }
        }
    }
}


