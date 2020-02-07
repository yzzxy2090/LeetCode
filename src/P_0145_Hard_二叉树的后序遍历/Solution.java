package P_0145_Hard_二叉树的后序遍历;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 145. Binary Tree Postorder Traversal (Hard)
 *
 * 先序遍历访问节点顺序为 中->左->右
 * 也就是先访问根节点，再访问左孩子，最后再访问右孩子
 *
 * 而后序遍历访问节点顺序为 左->右->中
 * 也就是先访问左孩子，再访问右孩子，最后再访问根节点
 *
 * 现在考虑一个访问顺序：中->右->左
 * 参考先序遍历，很容易实现，就是访问根节点后，先将其左孩子压栈，再将其右孩子压栈
 *
 * 而 中->右->左 即为 左->右->中(后序遍历节点访问顺序)的逆序
 * 因此只需将 中->右->左 遍历的节点依次再放入一个辅助栈中
 * 这样便利完成后从该辅助栈中依次弹出的节点就是后序遍历所访问的节点顺序
 */

public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if(root == null) {
            return res;
        }

        TreeNode cur = root;
        Stack<TreeNode> traversalStack = new Stack<TreeNode>();//中->右->左 遍历栈
        Stack<TreeNode> resStack = new Stack<TreeNode>();//辅助栈，用于存储遍历到的节点

        /**
         * 遍历过程和先序遍历很像
         * 只是先序遍历是先将根节点值加入结果链表，然后将右孩子压栈，再将左孩子压栈
         * 这里先将根节点压入一个辅助栈resStack，然后将左孩子压遍历栈，再将右孩子压遍历栈
         *
         */
        traversalStack.push(cur);
        while(!traversalStack.isEmpty()) {
            TreeNode popNode = traversalStack.pop();
            resStack.push(popNode);

            if(popNode.left != null) {
                traversalStack.push(popNode.left);
            }
            if(popNode.right != null) {
                traversalStack.push(popNode.right);
            }
        }

        //最终结果是上述遍历顺序的逆序，也就是辅助栈resStack依次弹出的顺序
        while(!resStack.isEmpty()) {
            TreeNode resNode = resStack.pop();
            res.add(resNode.val);
        }

        return res;
    }
}
