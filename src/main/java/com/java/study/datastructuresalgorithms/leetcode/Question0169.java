package com.java.study.datastructuresalgorithms.leetcode;

/**
 * @author Mr.Xu
 * @date 2020/3/7 16:47
 */
public class Question0169 {


    public static void main(String[] args) {
        int[] nums = new int[]{2, 2, 7, 7, 15, 11, 11, 11, 11, 11, 11, 15};
        int majorityNumber = new Question0169().majorityElement(nums);
        System.out.println(majorityNumber);
    }

    public int majorityElement(int[] nums) {
        int count = 0;
        int candidate = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                candidate = nums[i];
                count++;
            } else if (candidate == nums[i]) {
                count++;
            } else {
                count--;
            }
        }
        return candidate;
    }

}
