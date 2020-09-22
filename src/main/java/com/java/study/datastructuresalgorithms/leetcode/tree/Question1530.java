package com.java.study.datastructuresalgorithms.leetcode.tree;

import com.java.study.datastructuresalgorithms.leetcode.tree.printer.BinaryTrees;

/**
 * 1530. 好叶子节点对的数量
 * <p>  给你二叉树的根节点 root 和一个整数 distance 。
 * <p>
 * <p>
 * <p>  如果二叉树中两个 叶 节点之间的 最短路径长度 小于或者等于 distance ，那它们就可以构成一组 好叶子节点对 。
 * <p>
 * <p>  返回树中 好叶子节点对的数量 。
 *
 * @author Mr.Xu
 * @date 2020/3/7 16:47
 */
public class Question1530 {


    public static void main(String[] args) {
        Integer[] nums = new Integer[]{1, 2, null, 3, 4, 5, 6};
        TreeNode build = TreeUtils.build(nums);
        BinaryTrees.println(build);
        int pairs = new Question1530().countPairs(build, 2);
        System.out.println(pairs);
    }

    public int countPairs(TreeNode root, int distance) {
        return distance < 2 ? 0 : dfs(root, distance)[0];
    }


    /**
     * <p> 时间复杂度：O(n * ( distance * distance )) n是节点的个数， distance * distance 是每个节点中都要计算满足条件的节点对数量
     * <p> 空间复杂度：O(m * distance ) m是递归的深度 ， distance 是每个节点的数据
     *
     * @param root     当前节点
     * @param distance 条件距离
     * @return
     */
    public int[] dfs(TreeNode root, int distance) {
        /*  ints ints[0]的值是此节点满足条件的叶子节点对的数量
        从下标 1 开始， 下标为此节点到叶子节点的距离 +1 ，值为此距离的叶子节点的数量
         如： ints[1] = 3 表示距离此节点的距离为 0（1-1）的叶子节点个数为 3  */
        int[] ints = new int[distance];
        if (root.left == null && root.right == null) {
            /*  叶子节点 */
            ints[1] = 1;
            return ints;
        }
        int[] left;
        if (root.left != null) {
            left = dfs(root.left, distance);
        } else {
            left = new int[distance];
        }

        int[] right;
        if (root.right != null) {
            right = dfs(root.right, distance);
        } else {
            right = new int[distance];
        }

        for (int i = 2; i < ints.length; i++) {
            /*  计算当前节点距离数据  */
            ints[i] = ints[i] + left[i - 1] + right[i - 1];
        }

        /*  计算当前节点满足条件的节点对数量
         *   即：i+j+2-2 < distance ，其中
         *  +2 是左右子树到此节点的距离分别+1 ，
         *  -2 是初始化数据时每个距离的值都加了 1 。  */
        int currentCount = 0;
        for (int i = 1; i < distance; i++) {
            for (int j = 1; i + j <= distance; j++) {
                currentCount += (left[i] * right[j]);
            }
        }
        ints[0] = currentCount + left[0] + right[0];
        return ints;
    }
}
