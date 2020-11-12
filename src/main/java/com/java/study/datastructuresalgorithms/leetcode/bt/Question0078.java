package com.java.study.datastructuresalgorithms.leetcode.bt;

import java.util.*;

/**
 * 78. 子集
 * <p>
 * <p>给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * <p>
 * <p>说明：解集不能包含重复的子集。
 * <p>
 * <p>  示例:
 * <p>
 * <p>  输入: nums = [1,2,3]
 * <p>  输出:
 * <p>  [     [3],
 * <p>       [1],
 * <p>       [2],
 * <p>       [1,2,3],
 * <p>       [1,3],
 * <p>       [2,3],
 * <p>       [1,2],
 * <p>       []       ]
 * <p>
 *
 * @author Mr.Xu
 * @date 2020/3/7 16:47
 */
public class Question0078 {


    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4};
        List<List<Integer>> subsets = new Question0078().subsets(nums);
        System.out.println(subsets);

    }

    /**
     * 使用递推的方式
     *
     * @param nums 集合
     * @return 结果
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>(0));
        for (int num : nums) {
            int oldSize = result.size();
            for (int j = 0; j < oldSize; j++) {
                List<Integer> srcList = result.get(j);
                ArrayList<Integer> destList = new ArrayList<>(srcList.size() + 1);
                /*  addAll相当于复制这个List，
                因为列表的元素是Integer类型，所以可以直接addAll,否则需要自己实现复制列表的代码 */
                destList.addAll(srcList);
                /*  复制后的List添加上当前元素，相当于当前元素Pick上的情况 */
                destList.add(num);
                /*  把destList添加到结果中，
                此处有个隐性逻辑是，非Pick的情况不需要处理，因为result中的子集srcList本身就相当于非Pick的结果 */
                result.add(destList);
            }
        }
        return result;
    }

}
