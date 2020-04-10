package com.java.study.datastructuresalgorithms.basisdatastructure.tree;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
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
    /**
     * 深度 : 根节点到这个节点所经历的边的个数
     * 从上先下递增,根节点深度为0
     */
    private int depth;
    /**
     * 层 : 节点的深度 + 1
     */
    private int level;
    /**
     * 高度 : 节点到叶子节点的最长路径(边数)
     * 从下向上递增,最下面高度为0
     */
    private int height;
    /**
     * 若使用数组存储二叉树时,此元素在数组中位置的下标
     */
    private int index;

    /**
     * 使用一组数据随机的构建一个二叉树
     *
     * @param t 原始数据
     * @return
     */
    public static <T> XbLinkedBinaryTree<T> build(T[] t) {
        XbLinkedBinaryTree result = null;
        if (t.length > 0) {
            result = XbLinkedBinaryTree.build(0, t.length - 1, t, 0, 1);
        }
        return result;
    }

    private static <T> XbLinkedBinaryTree<T> build(int left, int right, T[] t, int depth, int index) {
        XbLinkedBinaryTree result = null;
        int length = right - left;
        if (length >= 0) {
            /*  选择此节点的数据索引位置 */
            int pickNumber = RandomUtils.nextInt(left, right + 1);
            XbLinkedBinaryTree<T> leftTree = XbLinkedBinaryTree.build(left, pickNumber - 1, t, (depth + 1), index * 2);
            XbLinkedBinaryTree<T> rightTree = XbLinkedBinaryTree.build(pickNumber + 1, right, t, (depth + 1), (index * 2 + 1));
            int leftTreeHeight = leftTree == null ? 0 : leftTree.getHeight() + 1;
            int rightTreeHeight = rightTree == null ? 0 : rightTree.getHeight() + 1;
            result = new XbLinkedBinaryTree(leftTree, t[pickNumber], rightTree, depth, (++depth), Math.max(leftTreeHeight, rightTreeHeight), index);
        }
        return result;
    }


    public String prettyToString(int unitCharsLength) {
        /*  单个字符的组成方式 */
        String unitChars = Stream.generate(() -> " ").limit(unitCharsLength).collect(Collectors.joining());
        String lineString = Stream.generate(() -> unitChars).limit((int) Math.pow(2, this.height + 2)).collect(Collectors.joining());
        /*  把二叉树按层打印出来 */
        List<XbLinkedBinaryTree<T>> dataList = new ArrayList();
        dataList.add(this);
        Stream.Builder<String> builder = Stream.builder();
        Optional<XbLinkedBinaryTree<T>> any;
        while ((any = dataList.stream().findAny()).isPresent()) {
            int currentNodeHeight = this.getHeight()-any.get().getDepth();
            int leftLen = (int) Math.pow(2, currentNodeHeight);
            int stepLen = leftLen * 2;
            StringBuffer lineStringBuffer = new StringBuffer(lineString);
            int upLevelCount = (int) Math.pow(2, any.get().getDepth()) - 1;
            dataList = dataList.stream().flatMap(s -> {
                String stringData = s.getData().toString();
                /*  计算此元素在这一行中是第几个元素,按照满二叉树计算 即: null ,null , 2 ,3 ,则, 3 是第四个元素 */
                int lineIndex = s.getIndex() - upLevelCount;
                /*  计算这个元素的首个字符在这一行内是第几个位置 */
                int start = ((lineIndex - 1) * stepLen + leftLen) * unitChars.length();
                lineStringBuffer.replace(start, start + stringData.length(), stringData);
                Stream<XbLinkedBinaryTree<T>> nodeStream;
                nodeStream = Stream.of(s.getLeftNode(), s.getRightNode()).filter(n -> n != null);
                return nodeStream;
            }).collect(Collectors.toList());
            builder.add(lineStringBuffer.toString()).add("\n");
        }
        return builder.build().collect(Collectors.joining());
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
            doDfsTraversalStack(this, result);
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


    /**
     * 深度优先遍历,使用栈的方式
     *
     * @return
     */
    private void doDfsTraversalStack(XbLinkedBinaryTree<T> parentNode, List<T> result) {

        Stack<XbLinkedBinaryTree> stack = new Stack<>();
        if (parentNode != null) {
            stack.push(parentNode);
        }
        while (!stack.isEmpty()) {
            XbLinkedBinaryTree<T> pop = stack.pop();
            result.add(pop.data);
            /*  此处的左右节点要反序存到stack中,这样出栈的时候才是正序的 */
            if (pop.getRightNode() != null) {
                stack.push(pop.getRightNode());
            }
            if (pop.getLeftNode() != null) {
                stack.push(pop.getLeftNode());
            }
        }
    }

    public static void main(String[] args) {
        Integer[] objects = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).toArray(Integer[]::new);
        XbLinkedBinaryTree<Integer> build = XbLinkedBinaryTree.build(objects);
        System.out.println(build.prettyToString(2));
        System.out.println(build.bfsTraversal());
        build.dfsTraversal(true);

    }

}
