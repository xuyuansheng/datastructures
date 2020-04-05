package com.java.study.datastructuresalgorithms.basisdatastructure.tree;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * 实现自己的二叉树
 * 链式二叉树,使用顺序存储结构
 *
 * @author Mr.Xu
 * @date 2020/4/4 12:50
 */

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class XbSequenceBinaryTree<T> {

    /*  我们把根节点存储在下标 i = 1 的位置，
    那左子节点存储在下标 2 * i = 2 的位置，
    右子节点存储在 2 * i + 1 = 3 的位置。
    以此类推，B 节点的左子节点存储在 2 * i = 2 * 2 = 4 的位置，
    右子节点存储在 2 * i + 1 = 2 * 2 + 1 = 5 的位置。 */

    private T[] data;

    private int height;

    /**
     * 使用一组数据构建一个完全二叉树
     *
     * @param t 原始数据
     * @return
     */
    public static <T> XbSequenceBinaryTree<T> build(T[] t) {
        /*  求以2为底,t.length的对数,再向上取整即为高度 */
        double log2 = Math.log(t.length + 1) / Math.log(2);
        int height = (int) Math.ceil(log2);
        Object[] dataT = new Object[t.length + 1];
        System.arraycopy(t, 0, dataT, 1, t.length);
        return new XbSequenceBinaryTree<>((T[]) dataT, height);
    }


    @Override
    public String toString() {
        /*  打印一个完全二叉树 */
        for (int i = 1; i <= height; i++) {
            int index = (int) Math.pow(2, i - 1);
            int stepLen = (int) Math.pow(2, height - i + 1);
            int preLen = (stepLen - 2);
            String collectPreLen = Stream.generate(() -> ".").limit(preLen).collect(Collectors.joining());
            for (int j = index; j < Math.min(data.length, (2 * index)); j++) {
                String collect = Stream.generate(() -> ".").limit(stepLen).collect(Collectors.joining());
                System.out.print(collectPreLen + data[j] + collect);
            }
            System.out.println("\n");
        }
        return "";
    }


    /**
     * 广度优先遍历
     */
    public List<T> bfsTraversal() {
        ArrayList<T> list = new ArrayList<>();
        LinkedBlockingDeque<Integer> queue = new LinkedBlockingDeque<>();
        if (data.length > 1) {
            queue.add(1);
        }
        int lenFromRoot = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            System.out.print("距离根节点的距离 = " + (lenFromRoot++) + "  element = [");
            Stream.generate(() -> queue.poll()).limit(size).forEach(index -> {
                System.out.print(" " + data[index] + " ");
                int leftNodeIndex = index * 2;
                if (data.length > (leftNodeIndex) && data[leftNodeIndex] != null) {
                    queue.add(leftNodeIndex);
                }
                int rightNodeIndex = index * 2 + 1;
                if (data.length > (rightNodeIndex) && data[rightNodeIndex] != null) {
                    queue.add(rightNodeIndex);
                }
            });
            System.out.println("]\n");
        }
        return list;
    }

    public static void main(String[] args) {
        Integer[] objects = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16).toArray(Integer[]::new);
        XbSequenceBinaryTree<Integer> build = XbSequenceBinaryTree.build(objects);
        build.bfsTraversal();
        System.out.println(build);
    }

}
