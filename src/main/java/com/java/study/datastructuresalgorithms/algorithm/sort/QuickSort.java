package com.java.study.datastructuresalgorithms.algorithm.sort;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

/**
 * 快速排序
 * <p>
 * 快排的思想是这样的：如果要排序数组中下标从 p 到 r 之间的一组数据，我们选择 p 到 r 之间的任意一个数据作为 pivot（分区点）。
 * 我们遍历 p 到 r 之间的数据，将小于 pivot 的放到左边，将大于 pivot 的放到右边，将 pivot 放到中间。经过这一步骤之后，数组 p 到 r 之间的数据就被分成了三个部分，前面 p 到 q-1 之间都是小于 pivot 的，中间是 pivot，后面的 q+1 到 r 之间是大于 pivot 的。
 *
 * @author Mr.Xu
 * @date 2020/12/5 9:23
 */
public class QuickSort {

    public static void main(String[] args) {
        Integer[] sort = Stream.of(11, 12, 3, 4, 19, 71, 8).toArray(Integer[]::new);
        new QuickSort().quickSort(sort, 0, sort.length);
        System.out.println(Arrays.toString(sort));
    }

    public void quickSort(Integer[] sort, int p, int q) {
        /*  随机数来选择分区点 */
        int pivotIndex = new Random().nextInt(q - p) + p;
        /*  把分区点数据交换到当前区间末尾，方便编码 */
        swap(sort, pivotIndex, q - 1);
        /*   | 已处理小区间 | (i)已处理大区间 | (j)未处理区间 | (pivot) |
         *  i 是已处理小区间的末尾元素，即已处理小区间的最后一个元素的下一个元素,
         *  j 是未处理区间的第一个元素，即已处理大区间的最后一个元素的下一个元素 */
        int i = p, j = p, value = sort[q - 1];
        for (; j < q; j++) {
            if (sort[j] < value) {
                /*  比分区点元素小的放到已处理小区间，i,j 同时后移 */
                swap(sort, j, i);
                i++;
            }
        }
        /*  把分区点交换到已处理大小区间的中间 */
        swap(sort, i, j - 1);
        /*  当左右区间的元素个数大于1时， 递归的处理左右区间 */
        if (i - p > 1) {
            quickSort(sort, p, i);
        }
        if (q - (i + 1) > 1) {
            quickSort(sort, i + 1, q);
        }
    }

    public void swap(Integer[] sort, int i, int j) {
        int temp = sort[i];
        sort[i] = sort[j];
        sort[j] = temp;
    }

}
