package com.java.study.datastructuresalgorithms.leetcode.tree;

import java.util.List;

/**
 * @author Mr.Xu
 * @date 2020/9/15 13:44
 */
public class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, List<Node> children) {
        this.val = val;
        this.children = children;
    }
}
