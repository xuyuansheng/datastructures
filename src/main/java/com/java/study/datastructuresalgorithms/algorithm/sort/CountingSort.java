package com.java.study.datastructuresalgorithms.algorithm.sort;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 计数排序
 * <p>
 * 计数排序其实是桶排序的一种特殊情况。当要排序的 n 个数据，所处的范围并不大的时候，比如最大值是 k，我们就可以把数据划分成 k 个桶。
 * 每个桶内的数据值都是相同的，省掉了桶内排序的时间。
 * <p>
 * 计数排序的算法思想就是这么简单，跟桶排序非常类似，只是桶的大小粒度不一样。不过，
 * 为什么这个排序算法叫“计数”排序呢？“计数”的含义来自哪里呢？
 * 想弄明白这个问题，我们就要来看计数排序算法的实现方法
 *
 * @author Mr.Xu
 * @date 2020/12/5 15:21
 */
public class CountingSort {

    public static void main(String[] args) {

        Integer[] sort = Stream.of(1, 2, 33, 44, 56, 78, 22, 31, 99, 76, 57, 44, 37, 89, 64, 52, 1).toArray(Integer[]::new);
        Integer[] sorted = new CountingSort().countingSort(sort);
        System.out.println(Arrays.toString(sorted));

    }

    /**
     * 计数排序 时间复杂度 O(n)
     * 空间复杂度O(k) k为桶的个数，即数据范围的大小
     *
     * @param sort 排序前
     * @return 排序后
     */
    private Integer[] countingSort(Integer[] sort) {

        /* 数据范围是 0-100 所以需要101个桶,下标就是数据的大小，值为这个数据的数量 如：buckets[1]=7 表示 1 这个数据总共有7个  */
        Integer[] buckets = new Integer[101];
        Arrays.fill(buckets, 0);

        /*  计算每个桶的数据个数 */
        for (Integer integer : sort) {
            buckets[integer]++;
        }

        /*  数据优化 依次对buckets求和，使buckets中存放的数据为，小于等于这个值的个数
            如：buckets[0]=7，buckets[1]=8  表示 0 这个数总共有7个， 1 共有 8-7=1 个 */
        for (int i = 1; i < buckets.length; i++) {
            buckets[i] = buckets[i - 1] + buckets[i];
        }

        /*  开始排序 */
        Integer[] result = new Integer[sort.length];
        for (int i = sort.length - 1; i >= 0; i--) {
            Integer data = sort[i];
            /*  buckets[data] 如：buckets[3]=4 表示小于等于 3 这个数字的数据有 4 个
             * 所以 3 这个数排序后的位置下标肯定为 index = 3 (数组从0开始计数)
             * --buckets[3] 是因为下标的值要减去1 同时也因为 3 这个数已经放到目标数组中了，所以数量也要减去1 ，所以--放到前面一箭双雕。 */
            result[--buckets[data]] = data;
        }
        return result;
    }


}



