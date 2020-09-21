package com.java.study.datastructuresalgorithms.leetcode.tree;


import com.java.study.datastructuresalgorithms.leetcode.tree.printer.BinaryTrees;

/**
 * 538. 把二叉搜索树转换为累加树
 * <p> 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，
 * 使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 * <p>
 * <p> 例如：
 * <p>
 * <p> 输入: 原始二叉搜索树:
 * <p>               5
 * <p>             /   \
 * <p>            2     13
 * <p>
 * <p>  输出: 转换为累加树:
 * <p>               18
 * <p>             /   \
 * <p>           20     13
 *
 * @author Mr.Xu
 * @date 2020/3/7 16:47
 */
public class Question0538 {


    public static void main(String[] args) {
        Integer[] ints = {5, 3, 13, 2, 4, 12, 15};
        TreeNode build = TreeUtils.build(ints);
        BinaryTrees.println(build);
        new Question0538().convertBST(build);
        BinaryTrees.println(build);

    }

    public TreeNode convertBST(TreeNode root) {
        convert(root, 0);
        return root;
    }


    /**
     * 递归反向中序遍历
     * 时间复杂度O(n) n 为树的节点个数
     * 空间复杂度O(m) m 为树的层数
     *
     * @param root    节点
     * @param lastVal 上一个节点遍历后的值
     * @return 当前节点遍历后的值
     */
    public Integer convert(TreeNode root, Integer lastVal) {
        if (root != null) {
            /*  反序中序遍历  右 -> root ->  左  */
            lastVal = convert(root.right, lastVal);
            root.val = root.val + lastVal;
            lastVal = root.val;
            lastVal = convert(root.left, lastVal);
        }
        return lastVal;
    }

}
