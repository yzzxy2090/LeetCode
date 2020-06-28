package P_0072_Hard_编辑距离;

/**
 * 对“dp[i-1][j-1] 表示替换操作，dp[i-1][j] 表示删除操作，dp[i][j-1] 表示插入操作。”的补充理解：
 *
 * 以 word1 为 "horse"，word2 为 "ros"，且 dp[5][3] 为例，即要将 word1的前 5 个字符转换为 word2的前 3 个字符，也就是将 horse 转换为 ros，因此有：
 *
 * (1) dp[i-1][j-1]，即先将 word1 的前 4 个字符 hors 转换为 word2 的前 2 个字符 ro，然后将第五个字符 word1[4]（因为下标基数以 0 开始） 由 e 替换为 s（即替换为 word2 的第三个字符，word2[2]）
 *
 * (2) dp[i][j-1]，即先将 word1 的前 5 个字符 horse 转换为 word2 的前 2 个字符 ro，然后在末尾补充一个 s，即插入操作
 *
 * (3) dp[i-1][j]，即先将 word1 的前 4 个字符 hors 转换为 word2 的前 3 个字符 ros，然后删除 word1 的第 5 个字符
 */

public class Solution {
    public int minDistance(String word1, String word2) {
        if(word1 == null && word2 == null || word1.length() == 0 && word2.length() == 0) {
            return 0;
        }

        if(word1 == null || word1.length() == 0) {
            return word2.length();
        }

        if(word2 == null || word2.length() == 0) {
            return word1.length();
        }

        char[] c1 = word1.toCharArray();
        char[] c2 = word2.toCharArray();

        int m = c1.length;
        int n = c2.length;

        /**
         * dp[i][j]表示word1前i个字符组成的子字符串和word2前j个字符组成的子字符串相比，之间相差的最小编辑距离为dp[i][j]
         * 即dp[i][j] 代表 word1 到 i 位置转换成 word2 到 j 位置需要最少步数
         */
        int[][] dp = new int[m+1][n+1];

        //初始化，word1前i个字符组成的子字符串和word2前0个字符组成的子字符串相比，之间相差的最小编辑距离从0递增至word1的长度
        for(int i=1; i<m+1; i++) {
            dp[i][0] = dp[i-1][0] + 1;
        }

        //word1为空变成word2最少步数，就是插入操作
        for(int j=1; j<n+1; j++) {
            dp[0][j] = dp[0][j-1] + 1;
        }

        /**
         * 当 word1[i] == word2[j]时，dp[i][j] = dp[i-1][j-1]；
         *
         * 当 word1[i] != word2[j]时，dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1
         * 其中，dp[i-1][j-1]+1表示替换word1第i个字符的操作
         * dp[i-1][j]+1表示删除word1第i个字符的操作
         * dp[i][j-1]+1表示word1变成word2要进行插入操作（相当于对于word2来说要删除第j个字符）
         */
        for(int i=1; i<m+1; i++) {
            for(int j=1; j<n+1; j++) {
                if(c1[i-1] == c2[j-1]) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
                }
            }
        }

        return dp[m][n];
    }
}
