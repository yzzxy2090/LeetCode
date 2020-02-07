package P_0226_Easy_翻转二叉树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 2. 迭代法
 *
 * 这个方法的思路就是，我们需要交换树中所有节点的左孩子和右孩子
 * 因此可以创一个队列来存储所有左孩子和右孩子还没有被交换过的节点。
 * 开始的时候，只有根节点在这个队列里面。
 * 只要这个队列不空，就一直从队列中出队节点，然后互换这个节点的左右孩子节点
 * 接着再把孩子节点入队到队列，对于其中的空节点不需要加入队列
 * 最终队列一定会空，这时候所有节点的孩子节点都被互换过了，直接返回最初的根节点就可以了。
 */

public class Solution_NonRecursive {
    public TreeNode invertTree(TreeNode root) {
        if(root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        while(!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            TreeNode temp = cur.left;
            cur.left = cur.right;
            cur.right = temp;

            if(cur.left != null) {
                queue.add(cur.left);
            }
            if(cur.right != null) {
                queue.add(cur.right);
            }
        }

        return root;
    }
}
