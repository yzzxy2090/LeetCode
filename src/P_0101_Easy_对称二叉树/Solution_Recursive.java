package P_0101_Easy_对称二叉树;

/**
 * 101. Symmetric Tree (Easy)
 *
 * 方法一.递归
 *
 * 如果同时满足下面的条件，两个树互为镜像：
 *
 * 它们的两个根结点具有相同的值。
 * 每个树的右子树都与另一个树的左子树镜像对称。
 */

public class Solution_Recursive {
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    private boolean isMirror(TreeNode t1, TreeNode t2) {
        if(t1 == null && t2 == null) {
            return true;
        }
        if(t1 == null || t2 == null) {
            return false;
        }

        return (t1.val == t2.val)
                && isMirror(t1.left, t2.right)
                && isMirror(t1.right, t2.left);
    }
}
