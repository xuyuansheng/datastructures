package com.java.study.datastructuresalgorithms.basisdatastructure.linear.linkedlist;

/**
 * @author Mr.Xu
 * @date 2020/3/14 18:34
 */
public class Node<T> {

    public Node<T> previous;
    public T data;
    public Node<T> next;


    public Node<T> next(int i) {
        Node<T> temp = this;
        for (int j = 0; j < i; j++) {
            temp = temp.next;
        }
        return temp;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                '}';
    }

    public Node(T data) {
        this(null, data, null);
    }

    public Node(Node<T> prefixIndex, T data) {
        this(prefixIndex, data, null);
    }

    public Node(T data, Node<T> suffixIndex) {
        this(null, data, suffixIndex);
    }


    public Node(Node<T> prefixIndex, T data, Node<T> suffixIndex) {
        this.previous = prefixIndex;
        this.data = data;
        this.next = suffixIndex;
    }
}
