package P_0230_Medium_二叉搜索树中第K小的元素;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 230. Kth Smallest Element in a BST (Medium)
 *
 * 方法一.中序遍历
 */

public class Solution_inorder01 {
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = inorder(root);

        //list的下标是从0开始的
        return list.get(k - 1);
    }

    private List<Integer> inorder(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if(root == null) return res;

        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<TreeNode>();

        while(!stack.isEmpty() || cur != null) {
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
