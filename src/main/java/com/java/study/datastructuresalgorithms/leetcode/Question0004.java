package com.java.study.datastructuresalgorithms.leetcode;

/**
 * @author Mr.Xu
 * @date 2020/3/7 16:47
 */
public class Question0004 {


    public static void main(String[] args) {
//        int[] nums1 = {1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
//        int[] nums2 = {2};
        int[] nums1 = {1, 3};
        int[] nums2 = {5};
        double result = new Question0004().findMedianSortedArrays(nums1, nums2);
        System.out.println(result);
    }

    public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) {
            /*  保证 m<=n */
            return findMedianSortedArrays(B, A);
        }
        /*  奇数 */
        boolean odd = ((m + n) & 1) == 1;


        /*  此时A,B数组已经被分为两部分,A(left)=i个元素,B(left)=j个元素,A(right)=m-i个元素,B(right)=n-j个元素
         *  那么要找到中位数,就要i+j=(m-i)+(n-j)+1(总元素个数为奇数) 或者 i+j=(m-i)+(n-j) (总元素个数为偶数)
         * 此时如果左边最大的数比右边最小的数还小,就表示找到了中位数,
         *  1. 当i+j=(m-i)+(n-j)+1 时, median=Max(left) 即:Math.max(A[i-1],B[j-1])
         *  2. 当i+j=(m-i)+(n-j) 时, median=(Max(left)+Min(right))/2.0 即: (Math.max(A[i-1],B[j-1])+Math.min(A[i],B[j]))/2.0
         */
        int aMin = 0, aMax = m;
        do {
            /*  1.给两个数组分区间,A数组左边i个元素,右边m-i个元素,B数组左边j个元素,右边n-j个元素
             *  2.定义i在A数组中的上下区间 aMin = 0(i=0,表示A数组全部分到右侧,左边有没一个元素),aMax=m;
             *  3.取初始状态i为A数组的中间位置,即i=(aMin+aMax)/2; 那么j=(m+n+1)/2-i;
             */
            int i = (aMin + aMax) / 2, j = (m + n + 1) / 2 - i;

            if (i < aMax && B[j - 1] > A[i]) {
                /*  此时A的右边最小数据比B的左边最大数据小,不能满足前面找到中位数的条件,要把A数组的分割点右移,即增多A分割点左边的元素个数i,即增大i
                 *  因为i是在aMIn和aMax范围取值,那么增大i的方式就是把aMin放到i的右边一个元素,再重新计算i和j的大小
                 */
                /*  因为i的取值范围是A的数据范围内,即0-m ,且i = (aMin + aMax) / 2 aMax<=m, 那么aMin也要<=m */
                aMin = (i + 1);
            } else if (i > aMin && A[i - 1] > B[j]) {
                /* 此时,A左边的最大值比B右边的最小值还大,要把A数组的分割点左移,即减少A数组分割点左边元素个数i,即减小i
                 * 因为i是在aMIn和aMax范围取值,那么减小i的方式就是把aMax放到i的左边一个元素,再重新计算i和j的大小
                 */
                /*  因为i的取值范围是A的数据范围内,即0-m ,且i = (aMin + aMax) / 2 aMin>=m, 那么aMax也要>=0 */
                aMax = (i - 1);
            } else {
                /*  此时可能是
                 *     1.到达边界条件 i>=aMax 或 i<=aMin
                 *     2.找到正确结果 Max(left)<Min(right)
                 */

                int maxLeft = 0;
                if (i == 0) {
                    /*  边界条件,表示A的分割线在整个数组左侧,即:A(left)没有元素,那么Max(left)=B[j-1]
                     *  此时因为m<=n 且 n>0 不会出现两个数组都没空,j=(m+n+1)/2 >0  肯定成立,所以B[j-1]不会数组越界
                     */
                    maxLeft = B[j - 1];
                } else if (j == 0) {
                    /*  边界条件,表示B的分割线在整个数组左侧,即:B(left)没有元素,那么Max(left)=A[i-1]
                     *  此时因为m<=n 且 n>0 (不会出现两个数组都没空) ,i=(m+n+1)/2 >0  肯定成立,所以A[i-1]不会数组越界
                     */
                    maxLeft = A[i - 1];
                } else {
                    /*  没有到达边界就找到结果 */
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                }
                if (odd) {
                    /*  总数为奇数时,直接返回maxLeft */
                    return maxLeft;
                }

                int minRight = 0;
                if (i == m) {
                    /*  边界条件,表示A的分割线在整个数组右侧,即:A(right)没有元素,那么Min(right)=B[j]
                     *  此时因为m<=n 且 n>0 不会出现两个数组都没空,j=(m+n+1)/2-m  肯定成立,所以B[j]不会数组越界
                     */
                    minRight = B[j];
                } else if (j == n) {
                    /*  边界条件,表示B的分割线在整个数组右侧,即:B(right)没有元素,那么Min(right)=A[i]
                     *  此时因为m<=n 且 n>0 不会出现两个数组都没空,i=(m+n+1)/2-n  肯定成立,所以A[i]不会数组越界
                     */
                    minRight = A[i];
                } else {
                    /*  没有到达边界就找到结果 */
                    minRight = Math.min(B[j], A[i]);
                }
                return (maxLeft + minRight) / 2.0;
            }
        }
        while (aMin <= aMin);
        return 0D;
    }

}

