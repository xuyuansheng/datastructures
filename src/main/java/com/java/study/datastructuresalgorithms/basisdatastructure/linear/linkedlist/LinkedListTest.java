package com.java.study.datastructuresalgorithms.basisdatastructure.linear.linkedlist;

import org.junit.Test;

import java.util.LinkedList;
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
        lru.addAll(Stream.of(1, 3, 4, 5, 2, 6, 7, 2).collect(Collectors.toList()));
        /*  找到目标值的位置索引 */
        int index = lru.indexOf(target);
        if (index > 0) {
            /*  如果找到了,且不再链表头位置,则删除此元素,再重新在链表头添加此元素 */
            lru.remove(index);
            lru.addFirst(target);
        }
        /*  输出链表 */
        System.out.println(lru);

    }


}
