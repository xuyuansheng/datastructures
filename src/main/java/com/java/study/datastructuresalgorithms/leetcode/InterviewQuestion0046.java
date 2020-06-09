package com.java.study.datastructuresalgorithms.leetcode;

/**
 * @author Mr.Xu
 * @date 2020/3/7 16:47
 */
public class InterviewQuestion0046 {


    public static void main(String[] args) {
        int parameter = 19;
        int result = new InterviewQuestion0046().translateNum(parameter);
        System.out.println(String.format("数字:%d , 结果:%d ", parameter, result));
    }

    public int translateNum(int num) {
        char[] chars = String.valueOf(num).toCharArray();
        int count = 1, p2 = 1, p1 = 1;
        for (int i = 1; i < chars.length; i++) {
            int newI = chars[i] + chars[i - 1] * 10;
            /* 数字要在10到25之间 */
            if (538 <= newI && newI < 554) {
                count += p2;
            }
            p2 = p1;
            p1 = count;
        }
        return count;
    }

}
