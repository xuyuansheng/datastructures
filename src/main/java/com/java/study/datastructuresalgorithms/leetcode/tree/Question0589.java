package com.java.study.datastructuresalgorithms.leetcode.tree;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 给定一个 N 叉树，返回其节点值的前序遍历。
 * <p>
 * 例如，给定一个 3叉树 :
 * <p>                  1
 * <p>         3          2           4
 * <p>     5     6
 * <p>
 * 返回其前序遍历: [1,3,5,6,2,4]。
 *
 * @author Mr.Xu
 * @date 2020/3/7 16:47
 */
public class Question0589 {


    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node3 = new Node(3);
        node1.children = Stream.of(node3, new Node(2), new Node(4)).collect(Collectors.toList());
        node3.children = Stream.of(new Node(5), new Node(6)).collect(Collectors.toList());
        List<Integer> preorder = new Question0589().preorder(node1);
        new Question0589().bfs(node1);
        System.out.println(preorder);
    }


    public List<Integer> preorder(Node root) {
        ArrayList<Integer> objects = new ArrayList<>();
        preorder(root, objects);
        return objects;
    }

    public void preorder(Node root, List<Integer> result) {
        if (root != null) {
            result.add(root.val);
            if (root.children != null) {
                for (Node n : root.children) {
                    preorder(n, result);
                }
            }
        }
    }

    public void bfs(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            Integer size = queue.size();
            Stream.generate(queue::poll).limit(size)
                    .filter(Objects::nonNull).forEach(f -> {
                System.out.print("  " + f.val);
                if (f.children != null) {
                    queue.addAll(f.children);
                }
            });
        }
        System.out.println();
    }

}
