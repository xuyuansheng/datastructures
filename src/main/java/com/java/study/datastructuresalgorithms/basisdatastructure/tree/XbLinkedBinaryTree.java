package com.java.study.datastructuresalgorithms.basisdatastructure.tree;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.RandomUtils;

import java.util.stream.Stream;

/**
 * 实现自己的二叉树
 * 链式二叉树,使用链式结构存储数据
 *
 * @author Mr.Xu
 * @date 2020/4/1 21:50
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class XbLinkedBinaryTree<T> {


    private XbLinkedBinaryTree<T> leftNode;
    private T data;
    private XbLinkedBinaryTree<T> rightNode;

    private int height;

    /**
     * 使用一组数据随机的构建一个二叉树
     *
     * @param t 原始数据
     * @return
     */
    public static <T> XbLinkedBinaryTree<T> build(T[] t) {
        XbLinkedBinaryTree result = null;
        if (t.length > 0) {
            result = XbLinkedBinaryTree.build(0, t.length - 1, t);
        }
        return result;
    }

    private static <T> XbLinkedBinaryTree<T> build(int left, int right, T[] t) {
        XbLinkedBinaryTree result = null;
        int length = right - left + 1;
        if (length > 0) {
            int leftNumber = RandomUtils.nextInt(0, length);
            XbLinkedBinaryTree<T> leftTree = XbLinkedBinaryTree.build(left + 1, left + leftNumber, t);
            XbLinkedBinaryTree<T> rightTree = XbLinkedBinaryTree.build(left + leftNumber + 1, right, t);
            int leftTreeHeight = leftTree == null ? 0 : leftTree.getHeight();
            int rightTreeHeight = rightTree == null ? 0 : rightTree.getHeight();
            result = new XbLinkedBinaryTree(leftTree, t[left], rightTree, Math.max(leftTreeHeight, rightTreeHeight) + 1);
        }
        return result;
    }


    public static void main(String[] args) {
        Integer[] objects = Stream.of(1, 2, 3).toArray(Integer[]::new);
        XbLinkedBinaryTree<Integer> build = XbLinkedBinaryTree.build(objects);
        System.out.println(build);
    }

}
