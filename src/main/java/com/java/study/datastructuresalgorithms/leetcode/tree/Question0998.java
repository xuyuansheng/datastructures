package com.java.study.datastructuresalgorithms.leetcode.tree;


import com.java.study.datastructuresalgorithms.leetcode.tree.printer.BinaryTrees;

/**
 * 998. 最大二叉树 II
 * <p> 最大树定义：一个树，其中每个节点的值都大于其子树中的任何其他值。
 * <p>
 * <p> 给出最大树的根节点 root。
 * <p>
 * <p> 就像之前的问题那样，给定的树是从表 A（root = Construct(A)）递归地使用下述 Construct(A) 例程构造的：
 * <p>
 * <p>     如果 A 为空，返回 null
 * <p>     否则，令 A[i] 作为 A 的最大元素。创建一个值为 A[i] 的根节点 root
 * <p>     root 的左子树将被构建为 Construct([A[0], A[1], ..., A[i-1]])
 * <p>     root 的右子树将被构建为 Construct([A[i+1], A[i+2], ..., A[A.length - 1]])
 * <p>     返回 root
 * <p>
 * <p> 请注意，我们没有直接给定 A，只有一个根节点 root = Construct(A).
 * <p>
 * <p> 假设 B 是 A 的副本，并附加值 val。保证 B 中的值是不同的。
 * <p>
 * <p> 返回 Construct(B)
 * <p>
 * <p> 输入：root = [4,1,3,null,null,2], val = 5
 * <p> 输出：[5,4,null,1,3,null,null,2]
 * <p> 解释：A = [1,4,2,3], B = [1,4,2,3,5]
 *
 * @author Mr.Xu
 * @date 2020/3/7 16:47
 */
public class Question0998 {


    public static void main(String[] args) {
        Integer[] ints = {4, 1, 3, null, null, 2};
        TreeNode build = TreeUtils.build(ints);
        BinaryTrees.println(build);
        TreeNode treeNode = new Question0998().insertIntoMaxTree(build, 8);
        BinaryTrees.println(treeNode);

    }

    /**
     * 插入节点到最大二叉树(即：大顶堆)中
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if (root == null || root.val < val) {
            /*  当 Val 比当前节点大时，直接把当前节点跟随到新节点后即可 */
            TreeNode newNode = new TreeNode(val);
            newNode.left = root;
            return newNode;
        }
        /*  当 Val 小于当前节点时 要把Val 放到 root.right 下面去  */
        TreeNode newNodeRight = insertIntoMaxTree(root.right, val);
        root.right = newNodeRight;
        return root;
    }

}
