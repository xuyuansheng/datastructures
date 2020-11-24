package com.java.study.datastructuresalgorithms.leetcode.tree;


import com.java.study.datastructuresalgorithms.leetcode.tree.printer.BinaryTrees;

import java.util.LinkedList;

/**
 * <p>给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * <p>百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * <p>例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
 * <p>
 * <p>   示例 1:
 * <p>   输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * <p>   输出: 6
 * <p>   解释: 节点 2 和节点 8 的最近公共祖先是 6。
 *
 * <p>   示例 2:
 * <p>   输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * <p>   输出: 2
 * <p>   解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
 *
 * @author Mr.Xu
 * @date 2020/3/7 16:47
 */
public class Question0235 {


    public static void main(String[] args) {
        Integer[] ints = {1,2};
        TreeNode build = TreeUtils.build(ints);
        BinaryTrees.println(build);
        TreeNode treeNode = new Question0235().lowestCommonAncestor(build, new TreeNode(1), new TreeNode(1));
        BinaryTrees.println(treeNode);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            /*  叶子节点的子节点 */
            return null;
        } else if (root.val == p.val || root.val == q.val) {
            /*  目标节点 */
            return root;
        } else if (root.left == null && root.right == null) {
            /*  叶子节点 */
            return null;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            /*  此节点为最近公共祖先 */
            return root;
        } else if (left != null) {
            return left;
        }
        return right;
    }

}
