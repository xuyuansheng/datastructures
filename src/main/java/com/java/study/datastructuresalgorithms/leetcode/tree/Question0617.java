package com.java.study.datastructuresalgorithms.leetcode.tree;

import com.java.study.datastructuresalgorithms.leetcode.tree.printer.BinaryTrees;

/**
 * 617. 合并二叉树
 * <p> 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 * <p> 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，
 * <p> 否则不为 NULL 的节点将直接作为新二叉树的节点。
 * <p>
 * <p>
 *
 * @author Mr.Xu
 * @date 2020/3/7 16:47
 */
public class Question0617 {

    public static void main(String[] args) {
        Integer[] nums = new Integer[]{1, 3, 2, 5};
        Integer[] nums2 = new Integer[]{2, 1, 3, null, 4, null, 7};
        TreeNode build = TreeUtils.build(nums);
        TreeNode build2 = TreeUtils.build(nums2);
        BinaryTrees.println(build);
        BinaryTrees.println(build2);
        TreeNode treeNode = new Question0617().mergeTrees(build, build2);
        BinaryTrees.println(treeNode);
    }

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        } else if (t2 == null) {
            return t1;
        } else {
            return mergeTrees(t1, t2, new TreeNode(t1.val + t2.val));
        }
    }


    public TreeNode mergeTrees(TreeNode t1, TreeNode t2, TreeNode result) {

        if (t1.left == null || t2.left == null) {
            result.left = t2.left == null ? t1.left : t2.left;
        } else {
            result.left = mergeTrees(t1.left, t2.left, new TreeNode(t1.left.val + t2.left.val));
        }

        if (t1.right == null || t2.right == null) {
            result.right = t2.right == null ? t1.right : t2.right;
        } else {
            result.right = mergeTrees(t1.right, t2.right, new TreeNode(t1.right.val + t2.right.val));
        }
        return result;
    }


}
