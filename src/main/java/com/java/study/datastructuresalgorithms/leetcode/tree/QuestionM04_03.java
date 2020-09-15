package com.java.study.datastructuresalgorithms.leetcode.tree;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 面试题 04.03. 特定深度节点链表
 * 给定一棵二叉树，设计一个算法，创建含有某一深度上所有节点的链表（比如，若一棵树的深度为 D，则会创建出 D 个链表）。返回一个包含所有深度的链表的数组。
 *
 * <p>  示例：
 * <p>  输入：[1,2,3,4,5,null,7,8]
 * <p>
 * <p>        1
 * <p>       /  \
 * <p>      2    3
 * <p>     / \    \
 * <p>    4   5    7
 * <p>   /
 * <p>  8
 * <p>
 * <p>
 * 输出：[[1],[2,3],[4,5,7],[8]]
 *
 * @author Mr.Xu
 * @date 2020/3/7 16:47
 */
public class QuestionM04_03 {


    public static void main(String[] args) {
        Integer[] nums = new Integer[]{2, 7, 11, 15};
        TreeNode build = TreeUtils.build(nums);
        ListNode[] listNodes = new QuestionM04_03().listOfDepth(build);
        System.out.println(Arrays.toString(listNodes));
    }

    /**
     * 层序遍历
     *
     * @param tree
     * @return
     */
    public ListNode[] listOfDepth(TreeNode tree) {
        Stream.Builder<ListNode> builder = Stream.builder();
        Queue<TreeNode> queue = new LinkedList<>();
        if (tree != null) {
            queue.add(tree);
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            ListNode head = new ListNode(Integer.MIN_VALUE);
            ListNode next = head;
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
                next.next = new ListNode(poll.val);
                next = next.next;
            }
            builder.add(head.next);
        }
        return builder.build().toArray(ListNode[]::new);
    }

}
