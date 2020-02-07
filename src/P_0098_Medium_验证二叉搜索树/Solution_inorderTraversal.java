package P_0098_Medium_验证二叉搜索树;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 98. Validate Binary Search Tree (Medium)
 *
 * 一个二叉搜索树具有如下特征：
 *    节点的左子树只包含小于当前节点的数。
 *    节点的右子树只包含大于当前节点的数。
 *    所有左子树和右子树自身必须也是二叉搜索树。
 *
 * 因此一个二叉搜索树的中序遍历集合为升序
 * 所以只要在对树进行中序遍历过程中加入一个对当前节点的前一个节点的判断就可以判断其是否为二叉搜索树
 */

public class Solution_inorderTraversal {
    public boolean isValidBST(TreeNode root) {
        boolean res = true;
        if(root == null) {
            return res;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;
        long preVal = Long.MIN_VALUE;//preVal用来存放中序遍历的前一个节点
        while(!stack.isEmpty() || cur != null) {
            if(cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                /**
                 * 访问popNode时判断其节点值是否大于其中序遍历的前一个节点值preVal
                 * 是，则继续遍历
                 * 否则该树不是二叉搜索树，将res设为false，并退出循环
                 */
                TreeNode popNode = stack.pop();
                if(popNode.val > preVal) {
                    preVal = popNode.val;
                } else {
                    res = false;
                    break;
                }

                cur = popNode.right;
            }
        }

        return res;
    }
}
