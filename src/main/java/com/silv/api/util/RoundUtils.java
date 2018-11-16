package com.silv.api.util;

import java.util.HashSet;
import java.util.Set;

public class RoundUtils {
    public static void main(String[] args) {
        yueSeFuRound(100, 3);
//        lengthOfLongestSubstring("pwwkew");
    }

    /**
     * 约瑟夫环问题
     *
     * @param total 总人数
     * @param a     每隔a个人出去
     */
    public static void yueSeFuRound(int total, int a) {
        int[] persons = new int[total];
        for (int i = 0; i < persons.length; i++) {
            persons[i] = i + 1;
        }

        int personsLeft = persons.length; // 初始化总人数
        int count = 0; // 计数器

        while (personsLeft > 0) { // while条件符合，则走循环体，循环体走完，再判断条件
            for (int j = 0; j < persons.length; j++) {
                if (persons[j] != -1) {
                    count++;
                    if (count == a) {
                        count = 0;
                        personsLeft--;
                        System.out.print(persons[j] + "->");
                        persons[j] = -1;
                    }
                }
            }
        }
    }



        public static int lengthOfLongestSubstring(String s) { // pwwkew
            StringBuilder sb = new StringBuilder();
            int longestLength = 0;
            int i = 0;
            while(i<s.length()){
                String currentLetter = Character.toString(s.charAt(i));
                System.out.println("sb.indexOf:::::"+sb.indexOf(currentLetter));
                if (sb.indexOf(currentLetter) != -1 ){
                    longestLength = sb.length() > longestLength? sb.length() : longestLength;
                    int endIndexToDelete = sb.indexOf(currentLetter) +1;
                    sb.delete(0, endIndexToDelete);
                    System.out.println(sb.toString());
                }
                sb.append(currentLetter);
                i++;
            }
            longestLength = sb.length()>longestLength? sb.length(): longestLength;
//            System.out.println(longestLength);
            return longestLength;
        }


}
