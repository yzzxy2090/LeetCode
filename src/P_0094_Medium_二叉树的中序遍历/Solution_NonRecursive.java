package P_0094_Medium_二叉树的中序遍历;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 94. Binary Tree Inorder Traversal (Medium)
 */

public class Solution_NonRecursive {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if(root == null) {
            return res;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;//遍历指针

        while(!stack.isEmpty() || cur != null) {
            /**
             * 当前节点cur不为空时(即当前节点cur的父节点有左孩子)
             *      将当前节点压入栈中
             *      并且将当前节点的左孩子(cur.left)赋给cur
             *      即不断向左子树搜索直到左孩子为空
             * 当前节点cur为空且栈不为空时(即当前节点cur的父节点还没有出栈遍历到，并且当前节点cur的父节点没有左孩子)
             *      弹出栈顶元素，将其节点值保存至结果链表(即访问当前节点的父节点)
             *      并且将当前节点的父节点的左孩子(popNode.right)赋给cur
             *      即popNode没有左孩子了，就搜索其右孩子，然后不断搜索该右孩子的左子树直至左孩子为空
             */
            if(cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode popNode = stack.pop();
                res.add(popNode.val);

                cur = popNode.right;
            }
        }
        return res;
    }
}
