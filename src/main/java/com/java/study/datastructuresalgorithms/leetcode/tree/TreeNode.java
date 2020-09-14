package com.java.study.datastructuresalgorithms.leetcode.tree;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xuyuansheng
 */
@Data
@Accessors(chain = true)
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}