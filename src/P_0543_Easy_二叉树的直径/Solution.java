package P_0543_Easy_二叉树的直径;

/**
 * 543. Diameter of Binary Tree (Easy)
 *
 * 二叉树的直径是从二叉树的一个叶子到一个节点，然后再回到另一个叶子所走过路径中最长的那一个
 * 从节点看，该路径的长度为左子树的最大深度和右子树的最大深度之和。
 * 该最长路径不一定会经过根节点，该路径可能只存在于左右子树内部
 */

class Solution {

    private int res = 1;

    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);

        //maxDepth计算的是深度，即点的数量，要求的直径为边数，边数=点数-1
        return res - 1;
    }

    private int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int l = maxDepth(root.left);
        int r = maxDepth(root.right);

        /**
         * 对于每个节点root来说，该节点root对应的直径所经过的点的数量为maxDepth(root.left)+maxDepth(root.right)+1
         *
         * 求二叉树的直径就转化为求书中每个节点的直径的最大值
         */
        res = Math.max(res, l + r + 1);

        return Math.max(l, r) + 1;
    }
}
