package com.java.study.datastructuresalgorithms.leetcode;

/**
 * @author Mr.Xu
 * @date 2020/3/7 16:47
 */
public class Question0014 {


    public static void main(String[] args) {
        String[] nums = new String[]{"abc", "abbcc"};
        String prefix = new Question0014().longestCommonPrefix(nums);
        System.out.println(prefix);
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String result = "";
        for (int i = 0; ; i++) {
            char pre = 0;
            for (String str : strs) {
                if (str.length() <= i) {
                    return result;
                }
                char a = str.charAt(i);
                if (pre == 0 || pre == a) {
                    pre = a;
                }else {
                    return result;
                }
            }
            result = result.concat(String.valueOf(pre));
        }
    }

}
