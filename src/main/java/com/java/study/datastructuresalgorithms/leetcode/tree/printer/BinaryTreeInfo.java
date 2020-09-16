package com.java.study.datastructuresalgorithms.leetcode.tree.printer;

/**
 * @author xuyuansheng
 */
public interface BinaryTreeInfo<E> {
    /**
     * who is the root node
     *
     * @return E
     */
    E root();

    /**
     * how to get the left child of the node
     *
     * @param node node
     * @return E
     */
    E left(E node);

    /**
     * how to get the right child of the node
     *
     * @param node node
     * @return E
     */
    E right(E node);

    /**
     * how to print the node
     *
     * @param node node
     * @return 要怎么展示当前节点
     */
    String string(E node);
}
