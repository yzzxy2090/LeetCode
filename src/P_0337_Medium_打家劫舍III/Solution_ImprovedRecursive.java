package P_0337_Medium_打家劫舍III;

import java.util.HashMap;

/**
 * 方法二. 重复子问题记忆化
 * 用HashMap保存递归中计算过的值，以避免重复计算
 */

public class Solution_ImprovedRecursive {
    public int rob(TreeNode root) {
        HashMap<TreeNode, Integer> dp = new HashMap<TreeNode, Integer>();
        return process(root, dp);
    }

    private int process(TreeNode root, HashMap<TreeNode, Integer> dp) {
        if (root == null) {
            return 0;
        }

        //处理到某个节点时，先考察HashMap中是否已经有该节点对应的最大收益值，即是否已计算过，以此来避免重复计算
        if (dp.containsKey(root)) {
            return dp.get(root);
        }

        //rootProfit保存根节点层次+孙子层次节点的收益和
        int rootProfit = root.val;
        if (root.left != null) {
            rootProfit += (process(root.left.left, dp) + process(root.left.right, dp));
        }
        if(root.right != null) {
            rootProfit += (process(root.right.left, dp) + process(root.right.right, dp));
        }

        //childProfit保存儿子层次节点的收益
        int childProfit = process(root.left, dp) + process(root.right, dp);

        //当前节点的最大收益为max(rootProfit, childProfit)，并将其存入到HashMap中
        int res = rootProfit > childProfit ? rootProfit : childProfit;

        dp.put(root, res);

        return res;
    }
}
