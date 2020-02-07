package P_0669_Easy_修剪二叉搜索树;

/**
 * 当node.val > R，那么修剪后的二叉树必定出现在节点的左边。
 * 当node.val < L，那么修剪后的二叉树出现在节点的右边。
 * 否则，我们将会向二叉树的两边修剪。
 */

public class Solution {
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if(root == null) {
            return null;
        }

        //当node.val > R，那么修剪后的二叉树必定出现在节点的左边
        if(root.val > R) {
            return trimBST(root.left, L, R);
        }
        //当node.val < L，那么修剪后的二叉树出现在节点的右边
        if(root.val < L) {
            return trimBST(root.right, L, R);
        }

        //否则，我们将会向二叉树的两边修剪，将往左修剪后的部分作为结果的左子树，将往右修剪后的部分作为结果的右子树
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);

        return root;
    }
}
