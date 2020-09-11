package com.java.study.datastructuresalgorithms.basisdatastructure.tree;

import java.io.IOException;
import java.util.List;

/**
 * 数据结构-树
 *
 * @author Mr.Xu
 * @date 2020/3/7 16:46
 */
public interface Tree<T> {

    /**
     * 广度优先遍历
     *
     * @return 结果
     */
    List<T> bfsTraversal();


    /**
     * 深度优先遍历
     *
     * @return 结果
     */
    List<T> dfsTraversal();

    /**
     * 打印树
     */
    void prettyPrintToDoc();

}
