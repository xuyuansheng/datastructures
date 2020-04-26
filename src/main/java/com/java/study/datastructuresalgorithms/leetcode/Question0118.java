package com.java.study.datastructuresalgorithms.leetcode;

import com.java.study.datastructuresalgorithms.basisdatastructure.SimpleDataStructure;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Mr.Xu
 * @date 2020/3/7 16:47
 */
public class Question0118 {


    public static void main(String[] args) {

        List<List<Integer>> generate = new Question0118().generate(5);
        System.out.println(generate);

    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>(numRows);
        for (int i = 1; i <= numRows; i++) {
            Integer[] lineArray = new Integer[i];
            /*  第一个元素为1 */
            lineArray[0] = 1;
            for (int j = 1; j < i - 1; j++) {
                List<Integer> prevLine = result.get(i - 2);
                lineArray[j] = prevLine.get(j) + prevLine.get(j - 1);
            }
            /*  最后一个元素为1 */
            lineArray[i - 1] = 1;
            result.add(Stream.of(lineArray).collect(Collectors.toList()));
        }
        return result;
    }

}

