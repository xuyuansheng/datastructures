package com.java.study.datastructuresalgorithms.basisdatastructure.tree;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
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
@EqualsAndHashCode(callSuper = true)
public class XbLinkedBinaryTree<T> extends AbstractBinaryTree<T> {


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
     * 广度优先遍历
     */
    @Override
    public List<T> bfsTraversal() {
        ArrayList<T> list = new ArrayList<>();
        LinkedBlockingDeque<XbLinkedBinaryTree<T>> queue = new LinkedBlockingDeque<>();
        queue.add(this);
        int lenFromRoot = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            System.out.print("距离根节点的距离 = " + (lenFromRoot++));
            Stream.generate(queue::poll).limit(size).forEach(element -> {
                System.out.print("  element = " + element.data);
                list.add(element.data);
                XbLinkedBinaryTree<T> leftNode = element.getLeftNode();
                if (leftNode != null) {
                    queue.add(leftNode);
                }
                XbLinkedBinaryTree<T> rightNode = element.getRightNode();
                if (rightNode != null) {
                    queue.add(rightNode);
                }
            });
            System.out.println("\n");
        }
        return list;
    }

    /**
     * 默认使用自定义栈深度优先遍历
     */
    private boolean useStack = true;

    /**
     * 深度优先遍历,使用递归的方式 和 使用栈的方式
     *
     * @return 结果
     */
    @Override
    public List<T> dfsTraversal() {
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
     * @return 结果
     */
    private void doDfsTraversalRecursive(XbLinkedBinaryTree<T> parentNode, List<T> result) {
        if (result.contains(parentNode.data)) {
            /*  剪枝,重复的节点不需要重复访问 */
            return;
        }
        int height = 0;
        if (parentNode.getLeftNode() != null) {
            doDfsTraversalRecursive(parentNode.getLeftNode(), result);
            height = Math.max(height, parentNode.getLeftNode().getHeight() + 1);
        }
        if (parentNode.getRightNode() != null) {
            doDfsTraversalRecursive(parentNode.getRightNode(), result);
            height = Math.max(height, parentNode.getRightNode().getHeight() + 1);
        }
        parentNode.height = height;
        result.add(parentNode.data);
    }

    /**
     * 深度优先遍历,使用栈的方式
     *
     * @return 结果
     */
    private void doDfsTraversalStack(XbLinkedBinaryTree<T> parentNode, List<T> result) {

        Stack<XbLinkedBinaryTree<T>> stack = new Stack<>();
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

    @Override
    public void prettyPrintToDoc() {
        /*  把二叉树按层打印出来 */
        List<XbLinkedBinaryTree<T>> dataList = new ArrayList<>();
        dataList.add(this);
        /* 每一行的元素个数(即格子数) */
        int lineCount = (int) Math.pow(2, this.height + 1) - 1;

        try {
            FileWriter fileWriter = new FileWriter(new File("tree.adoc"));
            fileWriter.append("[width=\"50%\"]\n|===\n");

            Optional<XbLinkedBinaryTree<T>> any;
            while ((any = dataList.stream().filter(Objects::nonNull).findAny()).isPresent()) {
                int currentNodeHeight = any.get().height;
                /*  当前行左侧第一个元素的位置 */
                int leftLen = (int) Math.pow(2, currentNodeHeight) - 1;
                /*  当前元素之间的间距 */
                int stepLen = (int) Math.pow(2, currentNodeHeight + 1) - 1;
                List<XbLinkedBinaryTree<T>> nextDataList = new ArrayList<>(dataList.size() * 2);
                /*  初始化当前行的表格 */
                String[] lineString = Stream.generate(() -> "|").limit(lineCount).toArray(String[]::new);
                for (int i = 0; i < dataList.size(); i++) {
                    XbLinkedBinaryTree<T> data = dataList.get(i);
                    /*  当前元素在这一行中的下标 */
                    int location = leftLen + i * (stepLen + 1);
                    if (data != null) {
                        /*  有数据的位置替换为具体数据 */
                        lineString[location] = "|" + data.data;
                        nextDataList.add(data.leftNode);
                        nextDataList.add(data.rightNode);
                    } else {
                        /*  没有数据，但此位置在满二叉树是个应该有数据的位置，所以用 * 填充 */
                        lineString[location] = "|*";
                        nextDataList.add(null);
                        nextDataList.add(null);
                    }
                }
                dataList = nextDataList;
                fileWriter.append(String.join("", lineString)).append("\n");
            }
            fileWriter.append("|===\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static <T> XbLinkedBinaryTree<T> build(T[] t) {
        XbLinkedBinaryTree<T> root = null;
        Queue<XbLinkedBinaryTree<T>> queue = new LinkedList<>();
        if (t.length > 0) {
            root = new XbLinkedBinaryTree<T>().setData(t[0]).setDepth(0).setLevel(1);
            queue.offer(root);
        }
        int index = 1;
        while (!queue.isEmpty()) {
            XbLinkedBinaryTree<T> poll = queue.poll();
            int depth = poll.depth + 1;
            if (index < t.length && t[index] != null) {
                poll.leftNode = new XbLinkedBinaryTree<T>().setData(t[index]).setDepth(depth).setLevel(depth + 1);
                queue.offer(poll.leftNode);
            }
            index += 1;
            if (index < t.length && t[index] != null) {
                poll.rightNode = new XbLinkedBinaryTree<T>().setData(t[index]).setDepth(depth).setLevel(depth + 1);
                queue.offer(poll.rightNode);
            }
            index += 1;
        }
        return root;
    }

    public static void main(String[] args) {
        Integer[] objects = Stream.of(1, 2, null, 3, 4, 5, 6, 7, 8, 9, null).toArray(Integer[]::new);
        XbLinkedBinaryTree<Integer> build = XbLinkedBinaryTree.build(objects).setUseStack(false);
        build.bfsTraversal();
        build.dfsTraversal();
        build.prettyPrintToDoc();
    }


}
