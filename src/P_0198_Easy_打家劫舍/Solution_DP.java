package P_0198_Easy_打家劫舍;

public class Solution_DP {
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length + 1];
        dp[0] = 0;
        dp[1] = nums[0];

        for(int i=2; i<dp.length; i++) {
            //这里i是从2开始遍历的，对应的是nums[1]即nums[i-1]
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i-1]);
        }

        return dp[nums.length];
    }
}
