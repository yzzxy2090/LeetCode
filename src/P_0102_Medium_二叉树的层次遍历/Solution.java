package P_0102_Medium_二叉树的层次遍历;

/**
 * 102. Binary Tree Level Order Traversal (Medium)
 *
 * BFS(Breadth First Search)
 * 广度优先搜索
 * 从根节点开始逐层遍历
 * 即每次遍历完一层节点再遍历下一层节点
 *
 * 思路：
 * Q;//队列
 * root入Q;
 * while(Q.length > 0){
 *     //本层的节点数
 *     cnt  = Q.length;
 *     for(i < cnt){
 *         t = Q.head出Q;
 *         记录t;
 *         if(t.left) t.left入Q;
 *         if(t.right) t.right入Q;
 *     }
 * }
 *
 * 时间复杂度：O(N)O(N)，因为每个节点恰好会被运算一次。
 * 空间复杂度：O(N)O(N)，保存输出结果的数组包含 N 个节点的值。
 */

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
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

                levelVal.add(cur.val);

                if(cur.left != null) {
                    queue.add(cur.left);
                }
                if(cur.right != null) {
                    queue.add(cur.right);
                }
            }
            //逐层将相应的节点值链表加入到结果链表
            res.add(levelVal);
        }
        return res;
    }
}
