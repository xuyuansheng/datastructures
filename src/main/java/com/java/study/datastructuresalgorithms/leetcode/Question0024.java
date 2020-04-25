package com.java.study.datastructuresalgorithms.leetcode;

import com.java.study.datastructuresalgorithms.basisdatastructure.SimpleDataStructure;

/**
 * @author Mr.Xu
 * @date 2020/3/7 16:47
 */
public class Question0024 {


    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        listNode.next.next.next.next.next = new ListNode(6);
        ListNode swapPairs = new Question0024().swapPairs(listNode);
        System.out.println(swapPairs);
        /*  把第一个交换后的结果再次交换,重置到原始状态 */
        ListNode swapPairs1 = new Question0024().swapPairs1(swapPairs);
        System.out.println(swapPairs1);
    }

    /**
     * 使用递归的方式实现交换
     */
    public ListNode swapPairs(ListNode head) {
        if (head != null && head.next != null) {
            /* 1, 把头结点保存下来 */
            ListNode swap = head;
            /* 2, 把第二个节点设置为头结点 */
            head = head.next;
            /* 3, 把第一步保存下来的头结点放到第二步新设置的头结点后面 */
            swap.next = head.next;
            head.next = swap;
            /*  4, 递归的交换后面的节点 */
            head.next.next = swapPairs(head.next.next);
        }
        return head;
    }


    /**
     * 使用简单遍历方式交换
     */
    public ListNode swapPairs1(ListNode head) {

        /*  定义一个哨兵节点,哨兵节点的next永远指向链表的头结点(不一定是参数的head,可能是交换后的) */
        ListNode sentinel = new ListNode(-1);
        sentinel.next = head;

        /*  定义一个交换节点的前驱节点,还没交换是默认就是哨兵节点,因为哨兵节点的下个节点就是马上可能要发生交换的头结点 */
        ListNode prevNode = sentinel;

        while (head != null && head.next != null) {
            /* 1, 把需要交换的头节点保存下来 */
            ListNode swap = head;
            /* 2, 把第二个节点设置为头结点 */
            head = head.next;
            /* 3, 把第一步保存下来的要交换的节点放到第二步新设置的头结点后面,且把prevNode.next指向新的head , 以完成交换 */
            swap.next = head.next;
            head.next = swap;
            prevNode.next = head;
            /*  4,更新前驱节点prevNode,为下次交换做准备 */
            prevNode = swap;
            head = prevNode.next;

        }
        return sentinel.next;
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

