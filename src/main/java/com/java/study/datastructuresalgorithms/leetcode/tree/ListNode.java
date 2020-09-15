package com.java.study.datastructuresalgorithms.leetcode.tree;

/**
 * @author Mr.Xu
 * @date 2020/9/15 21:22
 */
public class ListNode {
    public int val;
    public ListNode next;

    ListNode(int x) {
        this.val = x;
    }

    @Override
    public String toString() {
        ListNode next = this.next;
        StringBuffer res = new StringBuffer("[" + this.val);
        while (next != null) {
            res.append(", ").append(next.val);
            next = next.next;
        }
        res.append("]");
        return res.toString();
    }
}
