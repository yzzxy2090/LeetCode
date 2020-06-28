package P_0055_Medium_跳跃游戏;

/**
 * 记忆化回溯
 * 用一个dp数组来记录递归中的状态
 *
 * 自顶向下的dp
 *
 * 时间复杂度O(n^2)
 * 空间复杂度O(n)
 */

public class Solution_TopDownMemoDFS {
    public boolean canJump(int[] nums) {
        if(nums == null || nums.length == 0) {
            return false;
        }

        int len = nums.length;

        /**
         * dp[i]表示从第i位置起跳能否跳到最后一个位置
         * dp[i]有三种状态
         * 1. dp[i] == 1，说明还没访问过该位置
         * 2. dp[i] == 2，说明该位置已被访问并且该位置可以跳到
         * 3. dp[i] == 3，说明该位置已被访问过且该位置跳不到
         */
        int[] dp = new int[len];

        //初始化所有位置都未访问
        for(int i=0; i<len; i++) {
            dp[i] = 1;
        }

        //站在最后一个位置上往最后一个位置跳肯定是可以跳到的，所以dp[len-1]初始化为2
        dp[len-1] = 2;

        return dfs(nums, 0, dp);
    }

    private boolean dfs(int[] nums, int curPos, int[] dp) {
        if(curPos >= nums.length) {
            return true;
        }

        //dp[curPos] != 1说明该位置已被访问过，看该位置的状态是2还是3,2表明可以跳到，否则不能
        if(dp[curPos] != 1) {
            return dp[curPos] == 2 ? true : false;
        }

        int furthestPos = Math.min(nums.length - 1, curPos + nums[curPos]);

        for(int nextPos = furthestPos; nextPos > curPos; nextPos--) {
            if(dfs(nums, nextPos, dp)) {
                dp[nextPos] = 2;
                return true;
            }
        }

        dp[curPos] = 3;
        return false;
    }
}
