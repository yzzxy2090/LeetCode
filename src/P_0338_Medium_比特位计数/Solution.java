package P_0338_Medium_比特位计数;

public class Solution {
    public int[] countBits(int num) {
        if(num == 0) {
            return new int[]{0};
        }

        //dp[i]表示数字i二进制形式有dp[i]个1
        int[] dp = new int[num + 1];

        /**
         * 只要考虑数字i最后一位是否是1，然后看i>>1有多少个1
         * 如果i最后一位是1，则dp[i]=dp[i>>1]+1
         * 否则dp[i]=dp[i>>1]
         */
        for(int i=1; i<num+1; i++) {
            if((i & 1) == 1) {
                dp[i] = dp[i >> 1] + 1;
            } else {
                dp[i] = dp[i >> 1];
            }
        }

        return dp;
    }
}
