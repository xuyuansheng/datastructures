package com.java.study.datastructuresalgorithms.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Mr.Xu
 * @date 2020/3/7 16:47
 */
public class Question0100 {


    public static void main(String[] args) {
        Integer[] nums = new Integer[]{2, 7, 11, 15};
        Integer[] nums2 = new Integer[]{2, 7, 11, 16};
        TreeNode build = TreeUtils.build(nums);
        TreeNode build2 = TreeUtils.build(nums2);
        boolean sameTree = new Question0100().isSameTree(build, build2);
        System.out.println(sameTree);
    }


    /**
     * 广度优先
     * 时间复杂度O(min(m,n)),因为要遍历所有节点，所以时间复杂度为节点数比较少的那个数量
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Queue<TreeNode> queueP = new LinkedList<>();
        Queue<TreeNode> queueQ = new LinkedList<>();
        queueP.offer(p);
        queueQ.offer(q);
        while (!queueQ.isEmpty() && !queueP.isEmpty()) {
            TreeNode pollP = queueP.poll();
            TreeNode pollQ = queueQ.poll();
            if (pollP == null && pollQ == null) {
                continue;
            } else if (pollP == null || pollQ == null || pollP.val != pollQ.val) {
                return false;
            } else {
                queueP.offer(pollP.left);
                queueP.offer(pollP.right);
                queueQ.offer(pollQ.left);
                queueQ.offer(pollQ.right);
            }
        }
        return true;
    }


    /**
     * 深度优先,
     * 时间复杂度O(min(m,n)),因为要遍历所有节点，所以时间复杂度为节点数比较少的那个数量
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTreeDfs(TreeNode p, TreeNode q) {
        if (p == q) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else {
            boolean res = p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
            return res;
        }
    }

}
