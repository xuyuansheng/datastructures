package com.java.study.datastructuresalgorithms.basisdatastructure.linear.queue;


import com.java.study.datastructuresalgorithms.basisdatastructure.linear.linkedlist.Node;
import com.java.study.datastructuresalgorithms.basisdatastructure.linear.linkedlist.XbSingleLinkedList;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Mr.Xu
 * @date 2020/3/30 13:24
 */
public class LinkedQueue<T> {

    public static void main(String[] args) {

        LinkedQueue<Integer> integerArrayQueue = new LinkedQueue<>(1, 2);
        System.out.println(integerArrayQueue.peek());
        System.out.println(integerArrayQueue);
        System.out.println(integerArrayQueue.pop());
        System.out.println(integerArrayQueue);
        System.out.println(integerArrayQueue.pop());
        System.out.println(integerArrayQueue);
        System.out.println(integerArrayQueue.push(3));
        System.out.println(integerArrayQueue);
        System.out.println(integerArrayQueue.push(4));
        System.out.println(integerArrayQueue);
        System.out.println(integerArrayQueue.pop());
        System.out.println(integerArrayQueue);
        System.out.println(integerArrayQueue.pop());
        System.out.println(integerArrayQueue);
        System.out.println(integerArrayQueue.push(1));
        System.out.println(integerArrayQueue);
    }

    /**
     * 队列中的数据
     */
    private XbSingleLinkedList<T> linkedList;

    private int count;

    public LinkedQueue() {
        this.linkedList = new XbSingleLinkedList(null);
    }

    public LinkedQueue(T... dataArray) {
        if (dataArray != null) {
            this.linkedList = new XbSingleLinkedList(Stream.of(dataArray).collect(Collectors.toList()));
            this.count = dataArray.length;
        } else {
            this.linkedList = new XbSingleLinkedList(null);
            this.count = 0;
        }
    }

    public T push(T item) {
        return linkedList.addLast(item) ? item : null;
    }


    public T pop() {
        return linkedList.removeFirst();
    }


    public T peek() {
        Node<T> node = linkedList.getNode();
        return node == null ? null : node.data;
    }

    @Override
    public String toString() {
        return linkedList.toString();
    }
}
