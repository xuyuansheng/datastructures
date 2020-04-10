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
        int height = (int) Math.ceil(log2) - 1;
        Object[] dataT = new Object[t.length + 1];
        System.arraycopy(t, 0, dataT, 1, t.length);
        return new XbSequenceBinaryTree<>((T[]) dataT, height);
    }

    public String prettyToString() {
        /*  计算每一行最大需要的字符数 */
        String lineString = Stream.generate(() -> "     ").limit((int) Math.pow(2, this.height + 2)).collect(Collectors.joining());
        Stream.Builder<String> builder = Stream.builder();
        /*  广度优先遍历二叉树,即一行一行遍历 */
        for (int i = this.height; i >= 0; i--) {
            /*  计算左边距离 */
            int leftLen = (int) Math.pow(2, i);
            /*  计算元素之间的距离 */
            int stepLen = leftLen * 2;
            StringBuffer lineStringBuffer = new StringBuffer(lineString);
            /*  每一行的元素个数 */
            int lineCount = (int) Math.pow(2, this.height - i);
            /*  每一行元素开始 和 结束的下边 */
            int lineStartIndex = lineCount;
            int lineEndIndex = Math.min(lineStartIndex + lineCount - 1, this.data.length - 1);
            /*  当前元素是这一行中的第几个元素 */
            int lineIndex = 0;
            for (int j = lineStartIndex; j <= lineEndIndex; j++) {
                lineIndex++;
                T s = this.data[j];
                if (s == null) {
                    continue;
                } else {
                    String stringData = s.toString();
                    /*  计算此元素在这一行中是第几个元素,按照满二叉树计算 即: null ,null , 2 ,3 ,则, 3 是第四个元素 */
                    /*  计算这个元素的首个字符在这一行内是第几个位置 */
                    int start = ((lineIndex - 1) * stepLen + leftLen) * 5;
                    lineStringBuffer.replace(start, start + stringData.length(), stringData);
                }
            }
            builder.add(lineStringBuffer.toString()).add("\n");
        }
        return builder.build().collect(Collectors.joining());
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
        Integer[] objects = Stream.iterate(1,i->i+1).limit(50).toArray(Integer[]::new);
        XbSequenceBinaryTree<Integer> build = XbSequenceBinaryTree.build(objects);
        System.out.println(build.prettyToString());
        build.bfsTraversal();
        System.out.println(build);
    }

}
