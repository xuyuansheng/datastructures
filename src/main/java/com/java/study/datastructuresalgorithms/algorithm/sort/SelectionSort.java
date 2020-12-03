package com.java.study.datastructuresalgorithms.algorithm.sort;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 选择排序
 * <p>
 * 选择排序算法的实现思路有点类似插入排序，也分已排序区间和未排序区间。但是选择排序每次会从未排序区间中找到最小（大）的元素，将其放到已排序区间的末尾。
 *
 * @author Mr.Xu
 * @date 2020/12/3 20:35
 */
public class SelectionSort {


    public static void main(String[] args) {
        Integer[] sort = Stream.of(10, 2, 3, 4, 9, 7, 8).toArray(Integer[]::new);
        new SelectionSort().selectionSort(sort);
        System.out.println(Arrays.toString(sort));
    }

    /**
     * 选择排序
     * 时间复杂度 O(n2)  空间复杂度O(1)
     *
     * @param sort
     * @return
     */
    public void selectionSort(Integer[] sort) {
        for (int i = 0; i < sort.length; i++) {
            int minValueIndex = i;
            for (int j = i + 1; j < sort.length; j++) {
                if (sort[minValueIndex] > sort[j]) {
                    minValueIndex = j;
                }
            }
            if (minValueIndex != i) {
                int temp = sort[i];
                sort[i] = sort[minValueIndex];
                sort[minValueIndex] = temp;
            }
        }

    }

}
