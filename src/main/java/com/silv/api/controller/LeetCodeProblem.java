package com.silv.api.controller;

public class LeetCodeProblem {
    public static void main(String[] args) {
        getNumberIndex();
    }

    /**
     * 数组中两个数的和等于某个值，得出两个数的下标
     * <p>
     * Example:
     * Given nums = [2, 7, 11, 15], target = 9,
     * Because nums[0] + nums[1] = 2 + 7 = 9,
     * return [0, 1].
     */
    public static int[] getNumberIndex() {
        int[] aa = {2, 11, 15, 7};
        int target = 9;
        for (int i = 0; i < aa.length - 1; i++) {
            for (int j = i + 1; j < aa.length; j++) {
                if (aa[i] + aa[j] == target) {
                    System.out.println("[" + i + "," + j + "]");
                    return new int[]{i, j}; // 学会这是int数组的写法    [1,2]
                }
            }
        }
        throw new IllegalArgumentException("no such number");
    }
}
