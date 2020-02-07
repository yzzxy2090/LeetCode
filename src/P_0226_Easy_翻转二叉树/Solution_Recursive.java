package P_0226_Easy_翻转二叉树;

/**
 * 226. Invert Binary Tree (Easy)
 *
 * 1. 递归法
 * 反转一颗空树结果还是一颗空树
 * 对于一颗根为r，左子树为left，右子树为right的树来说
 * 它的反转树是一颗根为r，左子树为right的反转树，右子树为left的反转树的树
 */

public class Solution_Recursive {
    public TreeNode invertTree(TreeNode root) {
        if(root == null) {
            return null;
        }

        TreeNode right = invertTree(root.right);
        TreeNode left = invertTree(root.left);

        root.right = left;
        root.left = right;

        return root;
    }
}
