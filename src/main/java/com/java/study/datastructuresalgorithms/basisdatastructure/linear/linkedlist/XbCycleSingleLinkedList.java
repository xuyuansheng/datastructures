package com.java.study.datastructuresalgorithms.basisdatastructure.linear.linkedlist;

import com.java.study.datastructuresalgorithms.basisdatastructure.linear.XbList;

import java.util.Collection;
import java.util.Iterator;

/**
 * 单链表
 *
 * @author Mr.Xu
 * @date 2020/3/14 16:46
 */
public class XbCycleSingleLinkedList<T> implements XbList<T> {

    private int size = 0;
    private Node<T> head;
    private Node<T> last;


    public static void main(String[] args) {
        XbCycleSingleLinkedList<String> linkedList = new XbCycleSingleLinkedList<>(null);
        linkedList.addLast("1");
        linkedList.addLast("2");
        linkedList.addLast("3");
        linkedList.addLast("4");
        linkedList.addLast("5");
        linkedList.addLast("6");
        linkedList.addFirst("0");
        linkedList.addFirst("-1");
        System.out.println(linkedList.toString());
        System.out.println(linkedList.removeFirst() + " === " + linkedList.toString());
        System.out.println(linkedList.removeLast() + " === " + linkedList.toString());
        System.out.println(linkedList.removeFirst() + " === " + linkedList.toString());
        System.out.println(linkedList.removeLast() + " === " + linkedList.toString());
        System.out.println(linkedList.removeFirst() + " === " + linkedList.toString());
        System.out.println(linkedList.removeLast() + " === " + linkedList.toString());
        System.out.println(linkedList.removeFirst() + " === " + linkedList.toString());
        System.out.println(linkedList.removeLast() + " === " + linkedList.toString());
        System.out.println(linkedList.removeLast() + " === " + linkedList.toString());

    }

    public T removeFirst() {
        return popFirst().data;
    }

    public T removeLast() {
        return popLast().data;
    }

    public boolean addFirst(T e) {
        linkedFirst(e);
        return true;
    }

    public boolean addLast(T e) {
        linkedLast(e);
        return true;
    }

    public Node<T> getNode() {
        return head;
    }

    public XbCycleSingleLinkedList(Collection<T> collection) {
        if (collection != null) {
            Iterator<T> iterator = collection.iterator();
            while (iterator.hasNext()) {
                linkedLast(iterator.next());
            }
            last.next = head;
        }
    }

    private Node<T> popFirst() {
        if (head == null) {
            return new Node<>(null);
        }
        Node<T> temp = head;
        head = temp.next;
        temp.next = null;
        this.size--;
        return temp;
    }

    private Node<T> popLast() {
        if (last == null) {
            return new Node<>(null);
        }
        Node<T> lastPre = null;
        Node<T> tempLast = head;
        while (tempLast != last) {
            /*  进入了while说明链表有超过一个数据 */
            lastPre = tempLast;
            tempLast = tempLast.next;
        }
        last = lastPre;
        if (lastPre != null) {
            /*  说明弹出一个元素后链表中还有元素 */
            last.next = null;
        } else {
            /*  说明弹出一个元素后链表为null了 */
            head = null;
        }
        this.size--;
        return tempLast;
    }


    private void linkedLast(T e) {
        if (head == null) {
            head = new Node<>(e);
            last = head;
        } else {
            last.next = new Node<>(e);
            last = last.next;
        }
        this.size++;
    }

    private void linkedFirst(T e) {
        if (head == null) {
            head = new Node<>(e);
            last = head;
        } else {
            Node<T> newNode = new Node<>(e, head);
            head = newNode;
        }
        this.size++;
    }

    @Override
    public String toString() {
        StringBuffer to = new StringBuffer().append("[");
        Node<T> temp = head;
        while (temp != null) {
            to.append(",").append(temp.data.toString());
            temp = temp.next;
            if (temp == head) {
                break;
            }
        }
        return to.toString().replaceFirst(",", "").concat("]");
    }

    @Override
    public int size() {
        return size;
    }

}
