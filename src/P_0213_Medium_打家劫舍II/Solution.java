package P_0213_Medium_打家劫舍II;

public class Solution {
    public int rob(int[] nums) {
        if(nums == null) {
            return 0;
        }

        int len = nums.length;

        if (len == 1) return nums[0];

        //分别考虑不偷第一家和不偷最后一家的情况，取两者较大的作为结果
        return Math.max(robRange(nums, 0, len - 2),
                robRange(nums, 1, len - 1));
    }

    // 仅计算闭区间 [start,end] 的最优结果
    int robRange(int[] nums, int start, int end) {

        /**
         * dp[i]表示当来到第i家，所能偷到的最大收益
         * 有两种情况：
         *      1. 当前这家不偷，则在当前这家的收益就是dp[i-1]
         *      2. 偷当前这家，则在当前这家的收益等于再前一家的收益加上当前这家的价值dp[i-2]+nums[i]
         *
         * pre表示当前遍历的这家的前一家的最大收益即dp[i-1]
         * prePre表示当前遍历的这家的在前一家的最大收益即dp[i-2]
         */
        int prePre = 0, pre = 0;
        int cur = 0;

        for(int i= start; i<=end; i++) {
            cur = Math.max(pre, prePre + nums[i]);
            prePre = pre;
            pre = cur;
        }

        return pre;
    }
}
