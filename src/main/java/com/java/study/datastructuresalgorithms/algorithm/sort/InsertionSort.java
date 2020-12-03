package com.java.study.datastructuresalgorithms.algorithm.sort;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 插入排序
 * <p>
 * 首先，我们将数组中的数据分为两个区间，已排序区间和未排序区间。初始已排序区间只有一个元素，就是数组的第一个元素。
 * 插入算法的核心思想是取未排序区间中的元素，在已排序区间中找到合适的插入位置将其插入，并保证已排序区间数据一直有序。重复这个过程，直到未排序区间中元素为空，算法结束。
 *
 * @author Mr.Xu
 * @date 2020/12/3 20:35
 */
public class InsertionSort {


    public static void main(String[] args) {

        Integer[] sort = Stream.of(6, 5, 4, 3, 2, 1, 8, 0).toArray(Integer[]::new);
        new InsertionSort().insertionSort(sort);
        System.out.println(Arrays.toString(sort));
    }

    /**
     * 插入排序
     * 时间复杂度 O(n2)  空间复杂度O(1)
     *
     * @param sort
     * @return
     */
    public void insertionSort(Integer[] sort) {
        if (sort.length <= 1) {
            return;
        }
        for (int i = 1; i < sort.length; ++i) {
            /*  拿出当前数据，把位置空出来做数据移动 */
            int value = sort[i];
            /*  从当前数据的前一个位置开始遍历 */
            int j = i - 1;
            /*  查找插入的位置,同时搬运数据 */
            for (; j >= 0; j--) {
                if (sort[j] > value) {
                    /*   如果j位置的数据比value大，则value要放大j或者更前的位置，所以把j位置的数据向后移动 */
                    sort[j + 1] = sort[j];
                } else {
                    /*   value大于等于a[j] ,所以找到value的位置, 结束遍历 */
                    break;
                }
            }
            /*  插入数据，把当前数据插入到查找到的位置  */
            sort[j + 1] = value;
        }
    }
}
