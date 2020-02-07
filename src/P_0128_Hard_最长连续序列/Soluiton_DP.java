package P_0128_Hard_最长连续序列;

/**
 * 128. Longest Consecutive Sequence (Hard)
 *
 * 方法一：动态规划
 * Step1.对数组排序
 * Step2.初始化dp数组，dp[i]初始化为1，即每个元素自成一个序列，最长长度为1
 * Step3.从第二个元素开始遍历数组
 *          若当前元素和前一个元素相等nums[i]==nums[i-1]，则最大长度没有增长，但要将前一个元素的状态转移给当前元素，即dp[i]=dp[i-1]
 *          若当前元素和前一个元素不相等nums[i]!=nums[i-1]时
 *              (1)考察当前元素是否正好等于前一个数字+1即nums[i]==nums[i-1]+1，这种情况最长长度增长1即dp[i]=dp[i-1]+1
 *              (2)否则说明前一个序列结束，重新开始计算下一个序列长度，初始就是当前数字，长度为1，也就是dp[i]的初始状态为1
 *              这种情况下每次要更新最长序列长度res即res = res > dp[i] ? res : dp[i]
 */

import java.util.Arrays;

public class Soluiton_DP {
    public int longestConsecutive(int[] nums) {
        if(nums.length < 2) {
            return nums.length;
        }

        Arrays.sort(nums);

        int res = 1;
        int[] dp = new int[nums.length];
        for(int i=0; i< dp.length; i++) {
            dp[i] = 1;
        }

        for(int i=1; i<nums.length; i++) {
            if(nums[i] == nums[i - 1]) {
                dp[i] = dp[i-1];
            } else {
                if(nums[i] == nums[i - 1] + 1) {
                    dp[i] = dp[i-1] + 1;
                }
                res = res > dp[i] ? res : dp[i];
            }
        }
        return res;
    }
}
