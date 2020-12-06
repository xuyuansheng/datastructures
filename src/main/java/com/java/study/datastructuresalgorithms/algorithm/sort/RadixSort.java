package com.java.study.datastructuresalgorithms.algorithm.sort;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 基数排序
 * <p>
 * 适用场景 ： 基数排序对要排序的数据是有要求的，需要可以分割出独立的“位”来比较，而且位之间有递进的关系，如果 a 数据的高位比 b 数据大，那剩下的低位就不用比较了。
 * 除此之外，每一位的数据范围不能太大，要可以用线性排序算法来排序，否则，基数排序的时间复杂度就无法做到 O(n) 了。
 *
 * @author Mr.Xu
 * @date 2020/12/6 8:41
 */
public class RadixSort {


    public static void main(String[] args) {
        String[] sort = Stream.of("12345345678", "12345342678",
                "15345345678", "16345345678",
                "22345345678", "15355345678",
                "15345634567").toArray(String[]::new);
        new RadixSort().radixSort(sort);
        System.out.println(Arrays.toString(sort));
    }


    public void radixSort(String[] sort) {

        /*  如： 手机号排序，可以从低位开始，依次向高位推进，过程中使用稳定的排序算法就可以保证低位的已排序相对位置不会改变 */
        CountingSort countingSorter = new CountingSort();
        for (int k = 10; k >= 0; k--) {
            int finalK = k;
            /*  内部使用计数排序 */
            countingSorter.countingSort(sort, 10,
                    b -> b.charAt(finalK) - '0');
        }

    }


}
