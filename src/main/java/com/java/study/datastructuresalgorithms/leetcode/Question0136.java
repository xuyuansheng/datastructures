package com.java.study.datastructuresalgorithms.leetcode;

/**
 * @author Mr.Xu
 * @date 2020/3/7 16:47
 */
public class Question0136 {


    public static void main(String[] args) {
        int[] nums = new int[]{2, 2, 7, 7, 15, 11, 15};
        int singleNumber = new Question0136().singleNumber(nums);
        System.out.println(singleNumber);
    }

    public int singleNumber(int[] nums) {
        /*  异或运算 当自己和自己异或时结果为0 任何数字和0异或结果为它本身 且异或符合交换律 所以把所有数字从头到尾异或即可得到只出现一次的数字 */
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }

}
