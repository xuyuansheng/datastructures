package com.java.study.datastructuresalgorithms.algorithm.sort;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 冒泡排序
 * <p>
 * 冒泡排序只会操作相邻的两个数据。每次冒泡操作都会对相邻的两个元素进行比较，看是否满足大小关系要求。如果不满足就让它俩互换。
 * 一次冒泡会让至少一个元素移动到它应该在的位置，重复 n 次，就完成了 n 个数据的排序工作。
 *
 * @author Mr.Xu
 * @date 2020/12/3 20:35
 */
public class BubbleSort {


    public static void main(String[] args) {
        Integer[] sort = Stream.of(1, 2, 3, 4, 9, 7, 8).toArray(Integer[]::new);
        new BubbleSort().bubbleSort(sort);
        System.out.println(Arrays.toString(sort));
    }

    /**
     * 冒泡排序
     * 时间复杂度 O(n2)  空间复杂度O(1)
     *
     * @param sort
     * @return
     */
    public void bubbleSort(Integer[] sort) {
        for (int i = sort.length; i > 0; i--) {
            /*  一轮冒泡就可以让一个数据到指定的位置 */
            boolean switchFlag = false;
            for (int j = 0; j < i - 1; j++) {
                if (sort[j] > sort[j + 1]) {
                    Integer temp = sort[j];
                    sort[j] = sort[j + 1];
                    sort[j + 1] = temp;
                    switchFlag = true;
                }
            }
            if (!switchFlag) {
                /* 如果一次冒泡后，没有发生过数据交换，说明数据已经完全有序了
                 * 如：1, 2, 3, 4, 9, 7, 8 第一次冒泡后 （1, 2, 3, 4, 7, 8, 9） 9 已经在最后的位置了，整体已经有序了，下一次冒泡就不会有数据交换  */
                break;
            }
        }
    }

}
