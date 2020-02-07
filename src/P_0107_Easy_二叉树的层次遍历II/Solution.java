package P_0107_Easy_二叉树的层次遍历II;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 107. Binary Tree Level Order Traversal II (Easy)
 *
 * 时间复杂度：O(N)O(N)，因为每个节点恰好会被运算一次。
 * 空间复杂度：O(N)O(N)，保存输出结果的数组包含 N 个节点的值。
 */

public class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root == null) {
            return res;
        }

        Deque<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        while(!queue.isEmpty()) {

            int count = queue.size();//count为当前队列中的节点个数，也就是当前层有几个节点
            List<Integer> levelVal = new LinkedList<Integer>();//levelVal用于记录当前层各节点值

            /**
             * 将当前层次的节点依次出队(当前层共count个节点)
             * 并且，如果其有左右孩子，则依次将其左孩子、右孩子进入队列尾部，即将下一层的节点入队
             */
            while(count-- > 0) {
                TreeNode cur = queue.poll();

                if(cur.left != null) {
                    queue.add(cur.left);
                }
                if(cur.right != null) {
                    queue.add(cur.right);
                }

                levelVal.add(cur.val);
            }
            //逐层将相应的节点值链表逆序地加入到结果链表
            res.add(0, levelVal);
        }
        return res;
    }
}
