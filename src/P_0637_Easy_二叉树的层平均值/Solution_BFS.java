package P_0637_Easy_二叉树的层平均值;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 637. Average of Levels in Binary Tree (Easy)
 *
 * BFS广度优先遍历
 */

public class Solution_BFS {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<Double>();
        if(root == null) {
            return res;
        }

        Deque<TreeNode> queue = new LinkedList<TreeNode>();//存放当前层的所有节点
        queue.add(root);

        while(!queue.isEmpty()) {
            double sum = 0;//记录本层的节点值之和
            double count = 0;//记录本层的节点数量

            Deque<TreeNode> temp = new LinkedList<TreeNode>();//temp用于存放queue中节点的下一层所有节点
            while(!queue.isEmpty()) {
                TreeNode n = queue.poll();
                sum += n.val;
                count++;

                if(n.left != null) {
                    temp.add(n.left);
                }
                if(n.right != null) {
                    temp.add(n.right);
                }
            }
            queue = temp;//接着遍历下一层节点
            res.add(sum * 1.0 / count);
        }

        return res;
    }
}
