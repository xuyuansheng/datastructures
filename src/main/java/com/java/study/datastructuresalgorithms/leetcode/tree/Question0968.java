package com.java.study.datastructuresalgorithms.leetcode.tree;

import com.java.study.datastructuresalgorithms.leetcode.tree.printer.BinaryTrees;

/**
 * 968. 监控二叉树
 * <p> 给定一个二叉树，我们在树的节点上安装摄像头。
 * <p> 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
 * <p> 计算监控树的所有节点所需的最小摄像头数量。
 * <p>
 * <p> 输入：[0,0,null,0,0]
 * <p> 输出：1
 * <p> 解释：如图所示，一台摄像头足以监控所有节点。
 * <p>
 * <p>输入：[0,0,null,0,null,0,null,null,0]
 * <p>输出：2
 * <p>解释：需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。
 * <p>
 *
 * @author Mr.Xu
 * @date 2020/3/7 16:47
 */
public class Question0968 {

    public static void main(String[] args) {
        Integer[] nums = new Integer[]{0, 0, null, null, 0, 0, null, null, 0, 0, null, 0};
        TreeNode build = TreeUtils.build(nums);
        BinaryTrees.println(build);
        int i = new Question0968().minCameraCover(build);
        System.out.println(i);
        BinaryTrees.println(build);
    }

    public int minCameraCover(TreeNode root) {
        Integer[] integers = new Integer[]{0};
        cover(root, new TreeNode(0), integers);
        if (root.val == 0) {
            /*  最后根节点可能会没有被覆盖 */
            return integers[0] + 1;
        }
        return integers[0];
    }

    /**
     * 时间复杂度：O(n) n 为节点数量， 因为dfs需要遍历一遍所有节点，所以复杂度为O(n)
     * 空间复杂度：O(h) h 为树的高度
     *
     * @param root
     * @param parent
     * @param result
     * @return
     */
    public int cover(TreeNode root, TreeNode parent, Integer[] result) {

        if (root.left != null) {
            int i = cover(root.left, root, result);
            /*  i == 0 && root.val != 1
                表示左子节点没有被监控（覆盖）且 当前节点不是监控摄像头，所以要在当前节点放一个摄像头，不然子节点无法被监控到 */
            if (i == 0 && root.val != 1) {
                /*  放了摄像头的节点Val= 1 */
                root.val = 1;
                /*  只是被摄像头覆盖到的节点Val=2  */
                parent.val = 2;
                root.left.val = 2;
                result[0] += 1;
            }
        }

        if (root.right != null) {
            int i = cover(root.right, root, result);
            if (i == 0 && root.val != 1) {
                root.val = 1;
                parent.val = 2;
                root.right.val = 2;
                result[0] += 1;
            }
        }
        return root.val;
    }
}
