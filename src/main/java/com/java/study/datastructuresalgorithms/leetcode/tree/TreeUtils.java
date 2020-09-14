package com.java.study.datastructuresalgorithms.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Mr.Xu
 * @date 2020/9/14 20:17
 */
public class TreeUtils {


    public static TreeNode build(Integer[] t) {
        TreeNode root = null;
        Queue<TreeNode> queue = new LinkedList<>();
        if (t.length > 0) {
            root = new TreeNode(t[0]);
            queue.offer(root);
        }
        int index = 1;
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (index < t.length && t[index] != null) {
                poll.left = new TreeNode(t[index]);
                queue.offer(poll.left);
            }
            index += 1;
            if (index < t.length && t[index] != null) {
                poll.right = new TreeNode(t[index]);
                queue.offer(poll.right);
            }
            index += 1;
        }
        return root;
    }

}
