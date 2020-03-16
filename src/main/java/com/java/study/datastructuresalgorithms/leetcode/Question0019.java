package com.java.study.datastructuresalgorithms.leetcode;

/**
 * @author Mr.Xu
 * @date 2020/3/7 16:47
 */
public class Question0019 {


    public static void main(String[] args) {
        ListNode head = new ListNode(5);
        ListNode temp = head;
        for (int i = head.val - 1; i > 0; i--) {
            temp.next = new ListNode(i);
            temp = temp.next;
        }
//        head = head.next;
//        System.out.println(head);
        ListNode listNode = new Question0019().removeNthFromEnd(head, 5);
        System.out.println(listNode);
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        /*  要删除的倒数第n个元素 */
        ListNode next_n = head;
        /*  遍历走到链表末尾的元素 */
        ListNode next_last = head;
        /*  先让next_last走n-1步,使其和next_n拉开差距 */
        for (int i = 0; i < n - 1; i++) {
            next_last = next_last.next;
        }
        /*  倒数第n+1个元素,即要删除元素的前一个元素 */
        ListNode next_n1 = null;
        while (next_last != null && next_last.next != null) {
            /*  把next_last向后移动一位 */
            next_last = next_last.next;
            /*  把next_n1 指向 next_n */
            next_n1 = next_n;
            /*  把next_n向后移动一位 */
            next_n = next_n.next;
        }
        if (head == next_n) {
            /* 要删除的就是第一个元素 */
            return next_n.next;
        } else {
            next_n1.next = next_n.next;
        }
        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return val + "  " + (next == null ? "" : next.toString());
        }
    }

}
