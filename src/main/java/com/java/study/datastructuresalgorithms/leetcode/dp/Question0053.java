package com.java.study.datastructuresalgorithms.leetcode.dp;

import java.util.Arrays;
import java.util.Objects;

/**
 * 53. 最大子序和
 * <p>
 * <p> 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * <p> 示例:
 * <p> 输入: [-2,1,-3,4,-1,2,1,-5,4]
 * <p> 输出: 6
 * <p> 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 * @author Mr.Xu
 * @date 2020/3/7 16:47
 */
public class Question0053 {


    public static void main(String[] args) {
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4, 7};
        int i = new Question0053().maxSubArray(nums);
        System.out.println(i);
    }


    public int maxSubArray(int[] nums) {
        return maxSubArrayDivide(nums, 0, nums.length - 1)[2];
    }

    /**
     * 分治
     *
     * @param nums
     * @param left
     * @param right
     * @return
     */
    public int[] maxSubArrayDivide(int[] nums, int left, int right) {
        if (left == right) {
            return new int[]{nums[left], nums[left], nums[left], nums[left]};
        } else if (left < right) {
            int mid = (left + right) / 2;
            int[] intsL = maxSubArrayDivide(nums, left, mid);
            int[] intsR = maxSubArrayDivide(nums, mid + 1, right);
            /*左端点为起点的最大子段和, 右端点为终点的最大子段和, 最大子段和,     所有端点的和 */
            int lSumL = intsL[0], rSumL = intsL[1], mSumL = intsL[2], iSumL = intsL[3];
            int lSumR = intsR[0], rSumR = intsR[1], mSumR = intsR[2], iSumR = intsR[3];
            /* 当前区间左端点为起点的最大子段和 */
            int lSum = Math.max(lSumL, iSumL + lSumR);
            /*  当前区间右端点为终点的最大子段和 */
            int rSum = Math.max(rSumR, rSumL + iSumR);
            /*  当前区间最大子段和 */
            int mSum = Math.max(Math.max(mSumL, mSumR), rSumL + lSumR);
            /*  当前区间所有端点的和 */
            int iSum = iSumL + iSumR;
            return new int[]{lSum, rSum, mSum, iSum};
        } else {
            return new int[]{0, 0, Integer.MIN_VALUE, 0};
        }
    }

    /**
     * 动态规划
     * <p> 时间复杂度：O(n)
     * <p> 空间复杂度：O(n)
     * <p> 分析： 暴力法中，求以 i 为起点的所有子数组的和，找出里面的最大值 这个操作是耗时的主要原因，所以要优化这个步骤
     * <p>    求以 i 为起点的子数组的和时，我们可以反序操作，先求以 n-1 为正点的子数组和，然后再 n-2, n-3, ...
     * <p>    这样就可以分析出递推公式，因为以 i-1 为起点，所以 i-1 必在子数组中 ，这样就有两种情况
     * <p>    1. nums[i-1]就是子数组 2. nums[i-1]，(i为起点的子数组), ... 组成的子数组， 然后取其中的最大值即可。
     * <p>    那么 f(i-1) = Math.max( nums[i-1]+f(i) , num[i-1] )  f(i)是以下标 i 为起点的所有子数组和中最大的值
     * <p>    终止条件： f(n-1) = nums[n-1]
     * <p>  以上优化都是以暴力法为基础的思考思路，这个其实和官方解答逻辑是一样的，
     * <p>   上面是求以 i 为正点的数组，需要反序求 ，官方解答思路是求以 i 为结尾的数组，所以正序求即可
     * <p>
     *
     * @param nums
     * @return
     */
    public int dynamicMaxSubArray(int[] nums) {
        if (Objects.isNull(nums) || nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        int ans = nums[nums.length - 1];
        int result = ans;
        for (int i = nums.length - 2; i >= 0; i--) {
            /*  求以 i 为起点的子数组的和 ,以 i=n-2为起点，
            因为 i=n-1为终止条件，即 ans[nums.length - 1] = nums[nums.length - 1]; 直接赋值即可 */
            ans = Math.max(nums[i], nums[i] + ans);
            result = Math.max(result, ans);
        }
        System.out.println(ans);
        return result;
    }


    /**
     * 暴力法
     * 时间复杂度：O(n*n)
     * 空间复杂度：O(n)
     *
     * @param nums
     * @return
     */
    public int violenceMaxSubArray(int[] nums) {
        if (Objects.isNull(nums) || nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = nums[i];
            int sum = nums[i];
            /*  计算以 i 为起点的所有子数组的和，找出里面最大的 */
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                ans[i] = Math.max(ans[i], sum);
            }
        }
        int max = ans[0];
        for (int i = 1; i < ans.length; i++) {
            max = Math.max(ans[i], max);
        }
        System.out.println(Arrays.toString(ans));
        return max;
    }

}
