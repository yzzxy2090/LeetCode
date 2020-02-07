package P_0337_Medium_打家劫舍III;

/**
 * 337. House Robber III (Medium)
 * 这是一个树型动态规划问题
 *
 *
 * 首先来定义这个问题的状态
 *
 * 首先要明确相邻的节点不能偷，也就是选择偷根节点，就不能偷儿子了，但是可以偷孙子
 * 二叉树只有左右两个孩子，一个根节点最多2个儿子,4个孙子
 * 根据以上条件，我们可以得出单个节点的钱该怎么算
 * 4个孙子偷的钱 + 根节点的钱 VS 2个儿子偷的钱 哪个组合钱多，就当做当前节点能偷的最大钱数
 * 这就是动态规划里面的最优子结构
 *
 *
 * 方法一. 暴力递归
 * 逐个计算所有节点的最大收益
 */

public class Solution_BrutalRecursive {
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }

        //rootProfit保存根节点层次+孙子层次节点的收益和
        int rootProfit = root.val;
        if (root.left != null) {
            rootProfit += (rob(root.left.left) + rob(root.left.right));
        }
        if (root.right != null) {
            rootProfit += (rob(root.right.right) + rob(root.right.left));
        }

        //childProfit保存儿子层次节点的收益
        int childProfit = rob(root.left) + rob(root.right);

        return rootProfit > childProfit ? rootProfit : childProfit;
    }
}
