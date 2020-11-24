package com.java.study.datastructuresalgorithms.leetcode.tree;

import com.java.study.datastructuresalgorithms.leetcode.tree.printer.BinaryTreeInfo;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xuyuansheng
 */
@Data
@Accessors(chain = true)
public class TreeNode implements BinaryTreeInfo<TreeNode> {
    public int val;
    public TreeNode left;
    public TreeNode right;

    TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }


    @Override
    public TreeNode root() {
        return this;
    }

    @Override
    public TreeNode left(TreeNode node) {
        return node.left;
    }

    @Override
    public TreeNode right(TreeNode node) {
        return node.right;
    }

    @Override
    public String string(TreeNode node) {
        return String.valueOf(node.val);
    }
}