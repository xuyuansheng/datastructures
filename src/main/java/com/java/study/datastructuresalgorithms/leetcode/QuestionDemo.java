package com.java.study.datastructuresalgorithms.leetcode;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Mr.Xu
 * @date 2020/3/7 16:47
 */
public class QuestionDemo {


    public static void main(String[] args) {
        Integer[] nums = new Integer[]{2, 7, 11, 15};
        Integer[] nums2 = new Integer[]{2, 7, 11, 15,16};
        TreeNode build = QuestionDemo.build(nums);
        TreeNode build2 = QuestionDemo.build(nums2);
        boolean sameTree = new QuestionDemo().isSameTree(build, build2);
        System.out.println(sameTree);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == q) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else {
            boolean res = p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
            return res;
        }
    }

    public static <T> TreeNode build(Integer[] t) {
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

    @Data
    @Accessors(chain = true)
    public static class TreeNode {
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
}
