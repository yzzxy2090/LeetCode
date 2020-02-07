package P_0617_Easy_合并二叉树;

/**
 * 617. Merge Two Binary Trees (Easy)
 *
 * 方法一：递归
 * 我们可以对这两棵树同时进行前序遍历，并将对应的节点进行合并。
 * 在遍历时，如果两棵树的当前节点均不为空，我们就将它们的值进行相加，并对它们的左孩子和右孩子进行递归合并；
 * 如果其中有一棵树为空，那么我们返回另一颗树作为结果；
 * 如果两棵树均为空，此时返回任意一棵树均可（因为都是空）。
 */

public class Solution_Recursive {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1 == null) {
            return t2;
        }
        if(t2 == null) {
            return t1;
        }

        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);

        return t1;
    }
}
