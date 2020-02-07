package P_0144_Medium_二叉树的前序遍历;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 144. Binary Tree Preorder Traversal (Medium)
 * 先序遍历非递归算法
 *
 * 只能使用栈而不能用队列的原因
 * 这里遍历是遍历完左子树还希望回到根节点再遍历右子树
 * 这个向上回溯的过程队列无法模拟
 * 而栈可以压栈之后再依次出栈
 */

public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        //注意边界条件，总是忘！！！
        if(root == null) {
            return res;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);

        while(!stack.isEmpty()) {
            TreeNode popNode = stack.pop();
            res.add(popNode.val);

            /**
             * 先压当前节点的右孩子，再压当前节点的左孩子
             * 这样出栈时，就先出左孩子，再出右孩子(即先访问左孩子在访问右孩子)
             * 符合先序遍历特点
             */
            if(popNode.right != null) {
                stack.push(popNode.right);
            }
            if(popNode.left != null) {
                stack.push(popNode.left);
            }
        }
        return res;
    }
}
