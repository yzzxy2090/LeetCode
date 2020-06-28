package P_0300_Medium_最长上升子序列;

/**
 * dp[i]的值代表 nums 数组中以第 i 个数字结尾的最长子序列长度。
 * 1. 前i-1个数都大于当前第i个数，则dp[i]=1，即以当前数字结尾的最长上升子序列为本身，长度为1
 * 2. 前i-1个数中有小于当前数字的，则dp[i]=max(dp[i], dp[j]+1)，j范围是0 <= j < i
 */

public class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int[] dp = new int[n];

        int res = 0;

        for(int i=0; i<n; i++) {
            dp[i] = 1;
            for(int j=0; j<i; j++) {
                if(nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
