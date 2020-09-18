package com.java.study.datastructuresalgorithms.leetcode.tree;


import com.java.study.datastructuresalgorithms.leetcode.tree.printer.BinaryTrees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * 剑指 Offer 32 - II. 从上到下打印二叉树 II
 * <p> 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
 * <p>
 * <p> 例如:
 * <p>
 * <p> 给定二叉树: [3,9,20,null,null,15,7],
 * <p>
 * <p>
 * <p>     3
 * <p>    / \
 * <p>   9  20
 * <p>     /  \
 * <p>    15   7
 *
 * <p>    返回其层次遍历结果：
 * <p>
 * <p>  [ [3],
 * <p>  [9,20],
 * <p>  [15,7] ]
 * <p>
 * <p>
 *
 * @author Mr.Xu
 * @date 2020/3/7 16:47
 */
public class QuestionOffer32_2 {


    public static void main(String[] args) {
        Integer[] ints = {4, 1, 3, null, null, 2,5,6,7,8,9,null,9,null,10};
        TreeNode build = TreeUtils.build(ints);
        BinaryTrees.println(build);
        List<List<Integer>> resultList = new QuestionOffer32_2().levelOrder(build);
        System.out.println("resultList = " + resultList);

    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        if (!Objects.isNull(root)) {
            queue.add(root);
        }
        List<List<Integer>> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> line = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.removeFirst();
                line.add(poll.val);
                if (!Objects.isNull(poll.left)) {
                    queue.add(poll.left);
                }
                if (!Objects.isNull(poll.right)) {
                    queue.add(poll.right);
                }
            }
            result.add(line);
        }
        return result;
    }

}
