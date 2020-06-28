package P_0104_Easy_二叉树的最大深度;

import javafx.util.Pair;

import java.util.*;

public class Solution_DFS {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<Pair<TreeNode, Integer>> stack = new LinkedList<>();
        stack.push(new Pair<>(root, 1));
        int maxDepth = 0;
        //DFS实现前序遍历，每个节点记录其所在深度
        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> pair = stack.pop();
            TreeNode node = pair.getKey();
            //DFS过程不断比较更新最大深度
            maxDepth = Math.max(maxDepth, pair.getValue());
            //记录当前节点所在深度
            int curDepth = pair.getValue();
            //当前节点的子节点入栈，同时深度+1
            if (node.right != null) {
                stack.push(new Pair<>(node.right, curDepth + 1));
            }
            if (node.left != null) {
                stack.push(new Pair<>(node.left, curDepth + 1));
            }
        }
        return maxDepth;
    }
}
