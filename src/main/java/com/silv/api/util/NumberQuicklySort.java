package com.silv.api.util;

public class NumberQuicklySort {
//    public static void main(String[] args) {
//        bubbleSort();
//    }

    /**
     * 冒泡排序
     */
    public static void bubbleSort() {
        int temp = 0;
        int[] numbers = {3, 8, 12, 8, 7, 21, 1, 32, 2, 6};
        System.out.println("排序前数组为：");
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        for (int i = 0; i < numbers.length - 1; i++) { // 外层循环是趟数，每趟可以找出一个最大的数，所以只需要（数组长度-1）趟就可以得出结果
            for (int j = 0; j < numbers.length - 1 - i; j++) {
                if (numbers[j] > numbers[j + 1]) {  //交换两数位置
                    temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }

            }
        }
        System.out.println();
        System.out.println("排序后的数组为：");
        for (int num : numbers) {
            System.out.print(num + " ");
        }
    }

    public static void quicklySort() {
        int[] numbers = {3, 8, 12, 8, 7, 21, 1, 32, 2, 6};

    }
}
