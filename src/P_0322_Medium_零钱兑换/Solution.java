package P_0322_Medium_零钱兑换;

public class Solution {
    public int coinChange(int[] coins, int amount) {
        if(coins == null || coins.length == 0) {
            return -1;
        }

        int n = coins.length;

        //dp[i]表示用这些硬币拼出i块钱所需要的最少硬币数
        int[] dp = new int[amount+1];

        for(int i=1; i<amount+1; i++) {

            //dp[i] = Integer.MAX_VALUE表示初始时默认所有硬币都拼不出当前金额
            dp[i] = Integer.MAX_VALUE;


            for(int j=0; j<n; j++) {

                /**
                 * 遍历可选择的硬币面额，若当前硬币面额coins[j]超过当前金额i或用当前面额硬币coins[j]拼不出当前金额i，即dp[i-coins[j]] == Integer.MAX_VALUE，跳过
                 * 否则若选用当前面额硬币coins[j]拼出i的总硬币数更少，就更新dp[i]
                 */
                if(i-coins[j] >= 0 && dp[i-coins[j]] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coins[j]]);
                }
            }
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}
