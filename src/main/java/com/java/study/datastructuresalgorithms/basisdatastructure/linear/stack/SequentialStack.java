package com.java.study.datastructuresalgorithms.basisdatastructure.linear.stack;

import java.util.Arrays;

/**
 * 顺序栈,不可动态扩容
 *
 * @author Mr.Xu
 * @date 2020/3/18 21:34
 */
public class SequentialStack {

    public static void main(String[] args) {

        SequentialStack sequentialStack = new SequentialStack(4, "1", "3");
        System.out.println(sequentialStack.push("5"));
        System.out.println(sequentialStack.push("4"));
        System.out.println(sequentialStack.push("8"));
        System.out.println(sequentialStack.peek());
        System.out.println(sequentialStack.pop());
        System.out.println(sequentialStack.pop());
        System.out.println(sequentialStack.pop());
        System.out.println(sequentialStack.pop());
        System.out.println(sequentialStack.pop());
        System.out.println(sequentialStack.pop());

        System.out.println(sequentialStack);


    }

    /**
     * 栈中的数据
     */
    private String[] dataArray;
    /**
     * 栈中已有的数据个数
     */
    private int count;

    public SequentialStack() {
        this(16);
    }

    public SequentialStack(int n, String... dataArray) {
        if (dataArray != null) {
            this.count = dataArray.length;
            int actualLen = n > dataArray.length ? n : dataArray.length;
            this.dataArray = new String[actualLen];
            System.arraycopy(dataArray, 0, this.dataArray, 0, count);
        } else {
            this.dataArray = new String[n];
            this.count = 0;
        }

    }

    public String push(String item) {
        if (count == dataArray.length) {
            return null;
        } else {
            dataArray[count] = item;
            count++;
            return item;
        }
    }


    public String pop() {
        if (count == 0) {
            return null;
        } else {
            count--;
            String result = dataArray[count];
            dataArray[count] = null;
            return result;
        }
    }


    public String peek() {
        if (count == 0) {
            return null;
        } else {
            return dataArray[count - 1];
        }
    }


    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public String toString() {
        return "dataArray=" + Arrays.toString(dataArray);
    }
}
