package com.java.study.datastructuresalgorithms.basisdatastructure.linear.stack;

import com.java.study.datastructuresalgorithms.basisdatastructure.linear.linkedlist.Node;
import com.java.study.datastructuresalgorithms.basisdatastructure.linear.linkedlist.XbSingleLinkedList;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 链式栈
 *
 * @author Mr.Xu
 * @date 2020/3/18 22:18
 */
public class LinkedStack {


    public static void main(String[] args) {
        LinkedStack linkedStack = new LinkedStack("1");
        System.out.println(linkedStack.isEmpty());
        System.out.println(linkedStack.push("5"));
        System.out.println(linkedStack.push("4"));
        System.out.println(linkedStack.push("8"));
        System.out.println(linkedStack.isEmpty());
        System.out.println(linkedStack.peek());
        System.out.println(linkedStack);
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());

        System.out.println(linkedStack);
    }


    /**
     * 栈中的数据
     */
    private XbSingleLinkedList<String> linkedList;
    /**
     * 栈中已有的数据个数
     */
    private int count;

    public LinkedStack() {
        this.linkedList = new XbSingleLinkedList(null);
    }

    public LinkedStack(String... dataArray) {
        if (dataArray != null) {
            this.linkedList = new XbSingleLinkedList(Stream.of(dataArray).collect(Collectors.toList()));
            this.count = dataArray.length;
        } else {
            this.linkedList = new XbSingleLinkedList(null);
            this.count = 0;
        }

    }

    public String push(String item) {
        linkedList.addFirst(item);
        count++;
        return item;
    }


    public String pop() {
        String removeFirst = linkedList.removeFirst();
        if (removeFirst != null) {
            count--;
            return removeFirst;
        } else {
            return null;
        }
    }


    public String peek() {
        Node<String> node = linkedList.getNode();
        if (node != null) {
            return node.data;
        } else {
            return null;
        }
    }


    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public String toString() {
        return linkedList.toString();
    }


}
