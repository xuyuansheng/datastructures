package com.java.study.datastructuresalgorithms.basisdatastructure.linear.queue;


import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Mr.Xu
 * @date 2020/3/30 13:24
 */
public class ArrayQueue<T> {

    public static void main(String[] args) {

        ArrayQueue<Integer> integerArrayQueue = new ArrayQueue<>(new Integer[]{1, 2});
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


    private T[] dataArray;
    private int head;
    private int tail;
    private int count;

    public ArrayQueue(T[] data) {
        this.dataArray = data;
        this.head = 0;
        this.tail = data.length - 1;
        this.count = data.length;
    }

    public T push(T item) {
        if (count == dataArray.length) {
            return null;
        } else {
            tail = (tail + 1) % dataArray.length;
            dataArray[tail] = item;
            count++;
            return item;
        }
    }


    public T pop() {
        if (count == 0) {
            return null;
        } else {
            T result = dataArray[head];
            dataArray[head] = null;
            head = (head + 1) % dataArray.length;
            count--;
            return result;
        }
    }


    public T peek() {
        if (count == 0) {
            return null;
        } else {
            return dataArray[head];
        }
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer("[");
        String collect = Stream.of(dataArray)
                .map(m -> m == null ? "null" : m.toString())
//                .map(m -> m.toString().concat(m == dataArray[head] ? "(head)" : m == dataArray[tail] ? "(tail)" : ""))
                .collect(Collectors.joining(","));
        result.append(collect);
        result.append("]    ")
                .append(String.format("head %d %s  ", head, dataArray[head]))
                .append(String.format("tail %d %s  ", tail, dataArray[tail]));
        return result.toString();
    }
}
