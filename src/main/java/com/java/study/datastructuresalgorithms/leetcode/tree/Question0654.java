package com.java.study.datastructuresalgorithms.leetcode.tree;


import com.java.study.datastructuresalgorithms.leetcode.tree.printer.BinaryTrees;

import java.util.LinkedList;

/**
 * 给定一个不含重复元素的整数数组。一个以此数组构建的最大二叉树定义如下：
 * <p>
 * 二叉树的根是数组中的最大元素。
 * 左子树是通过数组中最大值左边部分构造出的最大二叉树。
 * 右子树是通过数组中最大值右边部分构造出的最大二叉树。
 * 通过给定的数组构建最大二叉树，并且输出这个树的根节点。
 * <p>
 * 示例 ：
 * <p>
 * 输入：[3,2,1,6,0,5]
 * 输出：返回下面这棵树的根节点：
 *
 * <p>      6
 * <p>    /   \
 * <p>   3     5
 * <p>    \    /
 * <p>     2  0
 * <p>      \
 * <p>       1
 *  
 * <p>
 * 提示：
 * <p>
 * 给定的数组的大小在 [1, 1000] 之间。
 *
 * @author Mr.Xu
 * @date 2020/3/7 16:47
 */
public class Question0654 {


    public static void main(String[] args) {
        int[] ints = {3, 2, 1, 6, 0, 1, 2, 5};
        TreeNode treeNode = new Question0654().constructMaximumBinaryTree(ints);
        BinaryTrees.print(treeNode);
    }

    /**
     * 使用栈的方式实现递归
     *
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {

        if (nums != null && nums.length > 0) {
            /*  哨兵节点 */
            TreeNode sentinel = new TreeNode();
            /*  递归栈 */
            LinkedList<Object[]> stack = new LinkedList<>();
            /*  默认把Root节点放到哨兵节点的Left节点上 */
            stack.add(new Object[]{0, nums.length - 1, true, sentinel});
            while (!stack.isEmpty()) {
                Object[] peek = stack.removeLast();
                int left = (int) peek[0];
                int right = (int) peek[1];
                boolean isLeft = (boolean) peek[2];
                TreeNode parent = (TreeNode) peek[3];

                if (right - left >= 0) {
                    int maxIndex = left;
                    for (int i = left; i < right + 1; i++) {
                        if (nums[i] > nums[maxIndex]) {
                            maxIndex = i;
                        }
                    }
                    TreeNode treeNode = new TreeNode(nums[maxIndex]);
                    if (isLeft) {
                        parent.left = treeNode;
                    } else {
                        parent.right = treeNode;
                    }
                    stack.add(new Object[]{left, maxIndex - 1, true, treeNode});
                    stack.add(new Object[]{maxIndex + 1, right, false, treeNode});
                }
            }
            return sentinel.left;
        }
        return null;
    }

    /**
     * 递归的方式实现
     *
     * @param nums
     * @param left
     * @param right
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums, int left, int right) {

        if (right - left >= 0) {
            int maxIndex = left;
            for (int i = left; i < right + 1; i++) {
                if (nums[i] > nums[maxIndex]) {
                    maxIndex = i;
                }
            }
            TreeNode treeNode = new TreeNode(nums[maxIndex]);
            treeNode.left = constructMaximumBinaryTree(nums, left, maxIndex - 1);
            treeNode.right = constructMaximumBinaryTree(nums, maxIndex + 1, right);
            return treeNode;
        } else {
            return null;
        }
    }

}
