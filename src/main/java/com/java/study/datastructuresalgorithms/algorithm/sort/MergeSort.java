package com.java.study.datastructuresalgorithms.algorithm.sort;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 归并排序
 * <p>
 * 归并排序的核心思想还是蛮简单的。如果要排序一个数组，我们先把数组从中间分成前后两部分，然后对前后两部分分别排序，再将排好序的两部分合并在一起，这样整个数组就都有序了
 *
 * @author Mr.Xu
 * @date 2020/12/3 20:35
 */
public class MergeSort {


    public static void main(String[] args) {
        Integer[] sort = Stream.of(11, 12, 3, 4, 19, 71, 8).toArray(Integer[]::new);
        new MergeSort().mergeSort(sort, 0, sort.length);
        System.out.println(Arrays.toString(sort));
    }

    /**
     * 归并排序
     * 时间复杂度 O(nlogn)  空间复杂度O(n)
     *
     * @param sort 数组
     * @param p 开始下标
     * @param q 结束下标
     */
    public void mergeSort(Integer[] sort, int p, int q) {
        if (p + 1 >= q) {
            /*  此处还可以更改条件（如：q-p<10，即数据规模小于10），在数据规模小于一定值时使用其他适合小规模数据量的排序算法（冒泡，插入，选择等） */
            return;
        }
        int mid = (p + q) / 2;
        mergeSort(sort, p, mid);
        mergeSort(sort, mid, q);
        merge(sort, p, mid, q);
    }

    public void merge(Integer[] sort, int p, int mid, int q) {
        Integer[] tempArray = new Integer[q - mid];
        /*  先缓存一部分数据，腾出空间来存放合并的数据 */
        for (int i = mid, c = 0; i < q; i++) {
            tempArray[c++] = sort[i];
        }
        /*  通过双指针方式来合并两个数组 */
        int left = mid - 1, right = tempArray.length - 1, data = q - 1;
        while (left >= p && right >= 0) {
            if (sort[left] > tempArray[right]) {
                sort[data--] = sort[left--];
            } else {
                sort[data--] = tempArray[right--];
            }
        }
        /*  把右数组没有合并完的数据继续放入目标数组的正确位置，左数据原本就在目标数组中，所以不需要处理 */
        while (right >= 0) {
            sort[data--] = tempArray[right--];
        }
    }
}
