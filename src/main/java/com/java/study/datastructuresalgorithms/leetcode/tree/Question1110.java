package com.java.study.datastructuresalgorithms.leetcode.tree;

import com.java.study.datastructuresalgorithms.leetcode.tree.printer.BinaryTrees;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1110. 删点成林
 * <p> 给出二叉树的根节点 root，树上每个节点都有一个不同的值。
 * <p> 如果节点值在 to_delete 中出现，我们就把该节点从树上删去，最后得到一个森林（一些不相交的树构成的集合）。
 * <p>
 * <p> 输入：root = [1,2,3,4,5,6,7], to_delete = [3,5]
 * <p> 输出：[[1,2,null,4],[6],[7]]
 * <p>
 * <p>
 *
 * @author Mr.Xu
 * @date 2020/3/7 16:47
 */
public class Question1110 {

    public static void main(String[] args) {
        Integer[] nums = new Integer[]{1, 2, 3, 4, 5, 6, 7};
        int[] nums2 = new int[]{3, 5};
        TreeNode build = TreeUtils.build(nums);
        BinaryTrees.println(build);
        List<TreeNode> treeNodes = new Question1110().delNodes(build, nums2);
        treeNodes.forEach(BinaryTrees::println);
    }

    Set<Integer> set = new HashSet<>();
    ArrayList<TreeNode> result = new ArrayList<>();

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        for (Integer i : to_delete) {
            set.add(i);
        }
        delNodes(root, true);
        return result;
    }

    public TreeNode delNodes(TreeNode root, boolean parentIsDel) {
        boolean isDelete;
        if (!(isDelete = set.contains(root.val)) && parentIsDel) {
            result.add(root);
        }
        if (root.left != null) {
            root.left = delNodes(root.left, isDelete);
        }
        if (root.right != null) {
            root.right = delNodes(root.right, isDelete);
        }
        if (isDelete) {
            root = null;
        }
        return root;
    }
}
