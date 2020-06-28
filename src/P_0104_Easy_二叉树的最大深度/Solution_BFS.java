package P_0104_Easy_二叉树的最大深度;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_BFS {
    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        TreeNode cur = root;
        int res = 0;
        int size = 0;

        while(!q.isEmpty()) {

            size = q.size();

            //整层节点处理完成深度才增长1
            while(size-- > 0) {
                cur = q.poll();

                if(cur.left != null) {
                    q.offer(cur.left);
                }

                if(cur.right != null) {
                    q.offer(cur.right);
                }
            }

            res++;
        }

        return res;
    }
}
