package com.java.study.datastructuresalgorithms.algorithm.sort;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * 桶排序
 * 首先，我们来看桶排序。桶排序，顾名思义，会用到“桶”，核心思想是将要排序的数据分到几个有序的桶里，每个桶里的数据再单独进行排序。
 * 桶内排完序之后，再把每个桶里的数据按照顺序依次取出，组成的序列就是有序的了
 *
 * @author Mr.Xu
 * @date 2020/12/5 13:57
 */
public class BucketSort {


    public static void main(String[] args) {
        Integer[] sort = Stream.of(1, 2, 33, 44, 56, 78, 22, 31, 99, 76, 57, 44, 37, 89, 64, 52, 1).toArray(Integer[]::new);
        Integer[] sorted = new BucketSort().bucketSort(sort);
        System.out.println(Arrays.toString(sort));
        new BucketSort().bucketSort(sort, 5, b -> b / 20, Comparator.naturalOrder());
        System.out.println(Arrays.toString(sorted));
        System.out.println(Arrays.toString(sort));
    }

    /**
     * 桶排序 时间复杂度O(n*log(n/m)) m为桶的个数，当 m 无限接近 n 时，复杂度就达到O(n)
     * 空间复杂度O(n)
     *
     * @param sort 待排序
     * @return 排序后
     */
    public Integer[] bucketSort(Integer[] sort) {

        Map<String, List<Integer>> buckets = new HashMap<>(5);
        buckets.put("0-19", new ArrayList<>());
        buckets.put("20-39", new ArrayList<>());
        buckets.put("40-59", new ArrayList<>());
        buckets.put("60-79", new ArrayList<>());
        buckets.put("80-99", new ArrayList<>());

        for (Integer i : sort) {
            if (i < 20) {
                buckets.get("0-19").add(i);
            } else if (i < 40) {
                buckets.get("20-39").add(i);
            } else if (i < 60) {
                buckets.get("40-59").add(i);
            } else if (i < 80) {
                buckets.get("60-79").add(i);
            } else if (i < 100) {
                buckets.get("80-99").add(i);
            }
        }

        return buckets.entrySet().stream()
                /*  桶内数据使用普通方式排序 */
                .peek(p -> p.getValue().sort(Comparator.comparingInt(s -> s)))
                /*  桶本身排序 */
                .sorted(Map.Entry.comparingByKey())
                /*  合并桶的数据 */
                .flatMap(f -> f.getValue().stream())
                /*  转换为数组 */
                .toArray(Integer[]::new);

    }

    /**
     * 通用桶数排序算法实现
     *
     * @param sort           待排序数据
     * @param bucketCount    桶个数
     * @param getBucketIndex 根据要排序的数据，计算出该数据在桶中的位置索引（如：按照手机号某一位排序时，那么dataRange = 10 ,getBucketIndex就可以是 b->b.charAt(i)-'0' ）
     * @param comparator     桶内排序比较器
     * @param <T>            要排序的数据类型
     */
    public <T> void bucketSort(T[] sort, Integer bucketCount, Function<T, Integer> getBucketIndex, Comparator<T> comparator) {
        List<T>[] buckets = Stream.generate(() -> new ArrayList(sort.length / bucketCount + 5)).limit(bucketCount).toArray(ArrayList[]::new);
        for (T t : sort) {
            buckets[getBucketIndex.apply(t)].add(t);
        }
        int aIndex = 0;
        for (List<T> bucket : buckets) {
            /*  桶内排序 */
            bucket.sort(comparator);
            /*  排序好的数据放回sort */
            for (int j = 0; j < bucket.size(); j++) {
                sort[aIndex++] = bucket.get(j);
            }
        }
    }


}
