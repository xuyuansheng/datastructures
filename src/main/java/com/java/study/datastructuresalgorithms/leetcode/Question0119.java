package com.java.study.datastructuresalgorithms.leetcode;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Mr.Xu
 * @date 2020/3/7 16:47
 */
public class Question0119 {


    public static void main(String[] args) {

        List<Integer> generate = new Question0119().getRow(5);
        System.out.println(generate);

    }

    public List<Integer> getRow(int rowIndex) {
        /*  先初始化一个列表 */
        List<Integer> result = Stream.generate(() -> 1).limit(rowIndex).collect(Collectors.toList());
        for (int i = 1; i < rowIndex - 1; i++) {
            for (int j = i; j > 0; j--) {
                /*  从下标为i的数向前计算,值为自己的值加上前一个元素的值 */
                Integer iData = result.get(j);
                Integer preData = result.get(j - 1);
                result.set(j, (preData + iData));
            }
        }
        return result;
    }

}

