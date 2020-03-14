package com.java.study.datastructuresalgorithms.basisdatastructure.linear.linkedlist;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 数据结构-链表
 *
 * @author Mr.Xu
 * @date 2020/3/7 16:34
 */
public class LinkedListTest {

    /**  数组需要一块连续的内存空间来存储,而链表恰恰相反，它并不需要一块连续的内存空间，它通过“指针”将一组零散的内存块串联起来使用
     *
     * 在进行数组的插入、删除操作时，为了保持内存数据的连续性，需要做大量的数据搬移，所以时间复杂度是 O(n)。而在链表中插入或者删除一个数据，我们并不需要为了保持内存的连续性而搬移结点，
     * 因为链表的存储空间本身就不是连续的。所以，在链表中插入和删除一个数据时只需要更新指针即可,是非常快速的。但是对应的链表随机访问的速度就比较慢
     * 因为随机访问需要从表头开始遍历链表,直到找到目标数据,所以链表随机访问时间复杂度为O(n)
     * 而链表有很多种,如:
     *  单链表
     *  循环链表
     *  双向链表
     *  双向循环链表
     *
     * 问题: 如何用链表来实现 LRU 缓存淘汰策略呢？
     * 思路: 我们维护一个有序单链表，越靠近链表尾部的结点是越早之前访问的。当有一个新的数据被访问时，我们从链表头开始顺序遍历链表。
     * -----1. 如果此数据之前已经被缓存在链表中了，我们遍历得到这个数据对应的结点，并将其从原来的位置删除，然后再插入到链表的头部。
     * -----2. 如果此数据没有在缓存链表中，又可以分为两种情况：
     * ------2.1. 如果此时缓存未满，则将此结点直接插入到链表的头部；
     * ------2.2. 如果此时缓存已满，则链表尾结点删除，将新的数据结点插入链表的头部。
     * 因为无论如何都要遍历链表,所以时间复杂度为O(n)
     *
     *
     * */


    /**
     * 链表实现LRU缓存淘汰
     */
    @Test
    public void lruCacheElimination() {
        int target = 2;
        LinkedList<Integer> lru = new LinkedList<>();
        /*  初始化一些数据 */
        lru.addAll(Stream.of(1, 3, 4, 5, 2, 6, 7).collect(Collectors.toList()));
        /*  在表头添加最新访问元素 */
        lru.addFirst(target);
        /*  从第二个位置开始一次遍历链表,找target,找到就删除 */
        for (int i = 1; i < lru.size(); i++) {
            if (lru.get(i) == target) {
                lru.remove(i);
                break;
            }
        }
        /*  输出链表 */
        System.out.println(lru);

    }

    /**
     * 检查链表存储的字符串是否是回文串?
     * 如何判断一个字符串是否是回文字符串的问题，我想你应该听过，我们今天的题目就是基于这个问题的改造版本。
     * 如果字符串是通过单链表来存储的，那该如何来判断是一个回文串呢？
     * <p>
     * 思路 : 如果字符串是数组存储的,直接用爽指针分别从前到后和从后到前遍历比较就能判断是否为回文串,时间复杂度为O(n)
     * 当用链表存储时,我们可以用快慢指针,找到链表的中心点,且同时反转链表的前半部分,然后在依次比较两个链表判断是否为回文串
     * 时间复杂度为O(n),空间复杂度为O(1)
     */
    @Test
    public void checkPalindrome() {

        List<String> collect = Stream.of("1", "2", "3", "4", "4", "3", "2", "1").collect(Collectors.toList());
        XbSingleLinkedList<String> linkedList = new XbSingleLinkedList<>(null);

        XbSingleLinkedList<String> reverse = new XbSingleLinkedList<>(null);
        Node<String> head = linkedList.getNode();
        Node<String> slow = head;
        Node<String> fast = head;
        while (fast != null && fast.next != null) {
            reverse.addFirst(slow.data);
            slow = slow.next(1);
            fast = fast.next(2);
            linkedList.removeFirst();
            System.out.println(slow + " | " + fast);
        }
        if (reverse.size() == 0) {
            System.out.println("链表中只有一个元素或0个元素,所以是回文串");
        }
        if (linkedList.size() > reverse.size()) {
            linkedList.removeFirst();
        }
        if (reverse.toString().equals(linkedList.toString())) {
            System.out.println("是回文串");
        } else {
            System.out.println("不是回文串");
        }
        System.out.println(reverse + "" + linkedList);
    }


}
