package com.java.study.datastructuresalgorithms.leetcode.dp;


/**
 * <p> 在计算机界中，我们总是追求用有限的资源获取最大的收益。
 * <p>
 * <p> 现在，假设你分别支配着 m 个 0 和 n 个 1。另外，还有一个仅包含 0 和 1 字符串的数组。
 * <p>
 * <p> 你的任务是使用给定的 m 个 0 和 n 个 1 ，找到能拼出存在于数组中的字符串的最大数量。每个 0 和 1 至多被使用一次。
 * <p>
 * <p> 示例 1:
 * <p> 输入: strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
 * <p> 输出: 4
 * <p> 解释: 总共 4 个字符串可以通过 5 个 0 和 3 个 1 拼出，即 "10","0001","1","0" 。
 * <p>
 * <p>示例 2:
 * <p>输入: strs = ["10", "0", "1"], m = 1, n = 1
 * <p>输出: 2
 * <p>解释: 你可以拼出 "10"，但之后就没有剩余数字了。更好的选择是拼出 "0" 和 "1" 。
 * <p>
 *
 * @author Mr.Xu
 * @date 2020/3/7 16:47
 */
public class Question0474 {


    public static void main(String[] args) {
        String[] ints = {"10", "0", "1"};
        int maxForm = new Question0474().findMaxForm(ints, 1, 1);
        System.out.println("maxForm = " + maxForm);
    }

    public int findMaxForm(String[] strs, int m, int n) {
        /*  因为第K个字符的状态只和K-1 相关，所以第一个长度为 2 ，后面两个是因为要计算每个字符在m,n范围内所有容量背包的值，所以长度分别为m,n   */
        int[][][] dp = new int[2][m + 1][n + 1];
        int preIndex = 0;
        int currentIndex = 1;
        for (String str : strs) {
            int[] count = countzeroesones(str);
            for (int i = 0; i < m + 1; i++) {
                for (int j = 0; j < n + 1; j++) {
                    /*
                     *  i 当前背包 0 的个数            j 当前背包 1 的个数
                     *  count[0] 当前字符串 0 的个数  count[1] 当前字符串 1 的个数
                     * 当前字符串有两种选择  放入背包  或  不放入背包
                     * 1.不放入  ->    dp[currentIndex][i][j]  =  dp[preIndex][i][j]
                     *          此时最大可拼成的字符串数量和前一个字符串相同
                     * 2.放入   ->     dp[currentIndex][i][j] = 1 + dp[preIndex][i-count[0]][j-count[1]]
                     *    (i-count[0] 为当前背包的容量当前字符消耗的 0 的个数 ，同理 j-count[1]为消耗 1 的个数)
                     *
                     *   */
                    int noPick = dp[preIndex][i][j];
                    int pick = 0;
                    if (i >= count[0] && j >= count[1]) {
                        /*  只有当前背包的容量够大时，才能选择当前字符串 */
                        pick = 1 + dp[preIndex][i - count[0]][j - count[1]];
                    }
                    dp[currentIndex][i][j] = Math.max(pick, noPick);
                }
            }
            /*  切换两个数组，让后刚刚计算过的状态表作为下一个要计算的字符的pre状态表 */
            preIndex = currentIndex;
            currentIndex = 1 - currentIndex;
        }
        return dp[preIndex][m][n];
    }

    public int[] countzeroesones(String s) {
        int[] c = new int[2];
        for (int i = 0; i < s.length(); i++) {
            c[s.charAt(i) - '0']++;
        }
        return c;
    }


}
