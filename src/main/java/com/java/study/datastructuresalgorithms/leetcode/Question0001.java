package com.java.study.datastructuresalgorithms.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mr.Xu
 * @date 2020/3/7 16:47
 */
public class Question0001 {


    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int[] ints = new Question0001().twoSum1(nums, 18);
        System.out.println(Arrays.toString(ints));
    }

    /**
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum1(int[] nums, int target) {

        int length = nums.length;
        Map<Integer, Integer> map = new HashMap<>(length);
        for (int i = 0; i < nums.length; i++) {
            int second = target - nums[i];
            if (map.containsKey(second)) {
                return new int[]{map.get(second), i};
            }
            int current = (nums[i]);
            map.put(current, i);
        }
        return new int[0];
    }

}
