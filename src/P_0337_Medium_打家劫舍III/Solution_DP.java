package P_0337_Medium_打家劫舍III;

/**
 * 方法三. 动态规划
 *
 * 上面两种解法用到了孙子节点，计算爷爷节点能偷的钱还要同时去计算孙子节点投的钱，虽然有了记忆化，但是还是有性能损耗。
 *
 * 我们换一种办法来定义此问题
 * 每个节点可选择偷或者不偷两种状态，根据题目意思，相连节点不能一起偷
 *
 * 当前节点选择偷时，那么两个孩子节点就不能选择偷了
 * 当前节点选择不偷时，当前节点最大收益为两个孩子的最大收益之和(两个孩子节点保存了偷或不偷的情况的最大收益，要取这两者中较大的)
 * 我们使用一个大小为2的数组来表示 int[] res = new int[2]
 * res[0]代表不偷当前节点的情况下的最大收益
 * res[1]代表偷当前节点的情况下的最大收益
 * 任何一个节点能偷到的最大钱的状态可以定义为
 *
 * 当前节点选择不偷(即res[0]): 当前节点最大收益res[0] = 左孩子节点保存的最大收益 + 右孩子节点保存的最大收益
 * 当前节点选择偷(即res[1]): 当前节点最大收益res[1] = 左孩子选择不偷时节点保存的最大收益 + 右孩子选择不偷时节点保存的最大收益 + 当前节点的钱数
 *
 */

public class Solution_DP {
    public int rob(TreeNode root) {
        int[] res = process(root);
        return Math.max(res[0], res[1]);
    }

    private int[] process(TreeNode root) {
        if(root == null) {
            return new int[] {0, 0};
        }

        /**
         * 只关心两种情况
         * 1.当前节点不偷，则返回其左右孩子偷或不偷的最大收益和
         * 2.当前节点偷，则返回其左右孩子不偷的情况下最大收益和+自己本身的收益
         */
        int[] res = new int[2];
        int[] left = process(root.left);
        int[] right = process(root.right);

        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = left[0] + right[0] + root.val;

        return res;
    }
}
