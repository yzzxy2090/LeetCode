package P_0055_Medium_跳跃游戏;

/**
 * 自底向上动态规划
 */

public class Solution_DownTopDP {
    public boolean canJump(int[] nums) {
        if(nums == null || nums.length == 0) {
            return false;
        }

        int len = nums.length;
        //int[] dp = new int[len];

        //dp[i]表示能否从第i个位置跳到最后一个位置，初始时都是false
        boolean[] dp = new boolean[len];

        //最后一个位置本身初始化为true
        dp[len - 1] = true;

        /**
         * 从最右边位置的左边一个位置开始向左考察每个位置能否跳到最后一个位置上
         * 内层循环考察从当前位置curPos可跳到的下一个位置nextPos起跳，能否跳到最后一个位置
         */
        for(int curPos = len-2; curPos >= 0; curPos--) {
            int furthestPos = Math.min(len - 1, nums[curPos] + curPos);
            for(int nextPos = curPos + 1; nextPos <= furthestPos; nextPos++) {

                if(dp[nextPos]) {
                    dp[curPos] = true;
                    break;
                }
            }
        }

        //结果返回第一个位置能否跳到最后一个位置
        return dp[0];
    }
}
