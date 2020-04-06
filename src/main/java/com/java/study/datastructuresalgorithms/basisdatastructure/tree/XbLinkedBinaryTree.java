package com.java.study.datastructuresalgorithms.basisdatastructure.tree;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Collectors;
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
        int length = right - left;
        if (length >= 0) {
            /*  选择此节点的数据索引位置 */
            int pickNumber = RandomUtils.nextInt(left, right + 1);
            XbLinkedBinaryTree<T> leftTree = XbLinkedBinaryTree.build(left, pickNumber - 1, t);
            XbLinkedBinaryTree<T> rightTree = XbLinkedBinaryTree.build(pickNumber + 1, right, t);
            int leftTreeHeight = leftTree == null ? 0 : leftTree.getHeight();
            int rightTreeHeight = rightTree == null ? 0 : rightTree.getHeight();
            result = new XbLinkedBinaryTree(leftTree, t[pickNumber], rightTree, Math.max(leftTreeHeight, rightTreeHeight) + 1);
        }
        return result;
    }

    @Override
    public String toString() {
        /*  toString 把二叉树按层打印出来 */
        List<XbLinkedBinaryTree> data = new ArrayList();
        data.add(this);
        while (data.size() > 0) {
            System.out.println(data.stream().map(k -> k.getData().toString()).collect(Collectors.joining()));
            List<XbLinkedBinaryTree> collect = data.stream().map(k -> {
                List<XbLinkedBinaryTree> temp = new ArrayList();
                if (k.getLeftNode() != null) {
                    temp.add(k.getLeftNode());
                }
                if (k.getRightNode() != null) {
                    temp.add(k.getRightNode());
                }
                return temp;
            }).flatMap(k -> k.stream()).collect(Collectors.toList());
            data.clear();
            data.addAll(collect);
        }
        return "";
    }

    /**
     * 广度优先遍历
     */
    public List<T> bfsTraversal() {
        ArrayList<T> list = new ArrayList<>();
        LinkedBlockingDeque<XbLinkedBinaryTree<T>> queue = new LinkedBlockingDeque<>();
        queue.add(this);
        int lenFromRoot = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            System.out.print("距离根节点的距离 = " + (lenFromRoot++));
            Stream.generate(() -> queue.poll()).limit(size).forEach(element -> {
                System.out.print("  element = " + element.data);
                XbLinkedBinaryTree leftNode = element.getLeftNode();
                if (leftNode != null) {
                    queue.add(leftNode);
                }
                XbLinkedBinaryTree rightNode = element.getRightNode();
                if (rightNode != null) {
                    queue.add(rightNode);
                }
            });
            System.out.println("\n");
        }
        return list;
    }

    /**
     * 深度优先遍历,使用递归的方式 和 使用栈的方式
     *
     * @return
     */
    public List<T> dfsTraversal(boolean useStack) {
        ArrayList<T> result = new ArrayList<>();
        if (useStack) {

        } else {
            doDfsTraversalRecursive(this, result);
        }
        System.out.println("深度优先遍历 " + result);
        return result;
    }

    /**
     * 深度优先遍历,使用递归的方式
     *
     * @return
     */
    private void doDfsTraversalRecursive(XbLinkedBinaryTree<T> parentNode, List<T> result) {
        if (result.contains(parentNode.data)) {
            /*  剪枝,重复的节点不需要重复访问 */
            return;
        }
        result.add(parentNode.data);
        if (parentNode.getLeftNode() != null) {
            doDfsTraversalRecursive(parentNode.getLeftNode(), result);
        }
        if (parentNode.getRightNode() != null) {
            doDfsTraversalRecursive(parentNode.getRightNode(), result);
        }
    }


    public static void main(String[] args) {
        Integer[] objects = Stream.of(1, 2, 3, 4, 5, 6, 7).toArray(Integer[]::new);
        XbLinkedBinaryTree<Integer> build = XbLinkedBinaryTree.build(objects);
        build.toString();
        build.bfsTraversal();
        build.dfsTraversal(false);

    }

}
