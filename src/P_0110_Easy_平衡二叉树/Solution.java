package P_0110_Easy_平衡二叉树;


/**
 * 110. Balanced Binary Tree (Easy)
 *
 * Definition for a binary tree node.
 *
 * 思路：
 * 平衡二叉树：任意节点的左子树和右子树高度差不能超过1
 * 空树(即高度为0的树)是平衡二叉树，所以res默认值为true
 *
 * 对于某个节点cur考察以下情况：
 * 1.其左子树是否为平衡二叉树
 * 2.其右子树是否为平衡二叉树
 * 3.其左右子树都是平衡二叉树
 * 找到其左子树高度
 * 找到其右子树高度
 * 将两高度值相减取绝对值看是否大于1
 */

//自底向上
class Solution {

    private boolean res = true;

    public boolean isBalanced(TreeNode root) {
        maxDepth(root);
        return res;
    }

    private int maxDepth(TreeNode cur) {
        //如果当前节点已经为空或已经有子树不是平衡二叉树，就直接返回0，不用再做下面的处理
        if(cur == null || res == false) {
            return 0;
        }

        //考察左右子树的高度差是否不超过1
        int l = maxDepth(cur.left);
        int r = maxDepth(cur.right);
        if(Math.abs(l - r) > 1) {
            res = false;
        }

        //return的是当前节点的树的高度，即左子树和右子树中较高的高度值加上当前节点本身的高度1
        return Math.max(l, r) + 1;
    }
}