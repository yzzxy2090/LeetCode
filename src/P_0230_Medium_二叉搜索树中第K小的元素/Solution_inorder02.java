package P_0230_Medium_二叉搜索树中第K小的元素;

import java.util.Stack;

public class Solution_inorder02 {
    public int kthSmallest(TreeNode root, int k) {
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        int count = 0;
        int res = root.val;

        while(!stack.isEmpty() || cur != null) {
            if(cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode popNode = stack.pop();
                count++;//每次有元素出栈的时候count就加1，当前出栈的元素为第count小的元素
                if(count == k) {
                    res = popNode.val;
                    break;
                }
                cur = popNode.right;
            }
        }
        return res;
    }
}
