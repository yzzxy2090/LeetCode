package P_0010_Hard_正则表达式匹配;

/**
 * 10. Regular Expression Matching (Hard)
 */

public class Solution {
    public boolean isMatch(String s, String p) {
        if(s == null && p == null) {
            return true;
        }

        if(s == null || p == null) {
            return false;
        }

        int m = s.length(), n = p.length();

        //dp[i][j]表示，s的前i个和p的前j个字符是否匹配
        boolean[][] dp = new boolean[m + 1][n + 1];

        //初始化
        dp[0][0] = true;
        /**
         * 从p的第1个字符(即p.charAt(1-1))开始判断
         * 若当前字符为'*'(如p的第2个字符为'*'，p前两个字符为"a*")
         * 那么此时s的前0个字符与p的前2个字符时匹配的(a*可以是0个a)
         *
         * 又如p前4个字符为"a*b*"，这种情况s前0个字符和p前4个字符也是匹配的
         *
         * 由于这种情况下p的x*组合一定是两个两个出现的，
         * 因此dp[0][i] = dp[0][i-2]
         */
        for(int i=1; i<=n; i++) {
            if(p.charAt(i-1) == '*') {
                dp[0][i] = dp[0][i-2];
            }
        }

        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                //若p的第j个字符为'.'或p的第j个字符==s的第i个字符，即p[j]和s[i]匹配，那么dp[i][j]的状态就由dp[i-1][j-1]的状态决定
                if(p.charAt(j-1) == '.' || p.charAt(j-1) == s.charAt(i-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else if(p.charAt(j-1) == '*') {
                    /**
                     * 这种情况p[j]的*被视为0个
                     * 如s:aab，p:aac*b，i=2，j=4时，s.charAt(i-1)=='a'，p.charAt(j-1)=='*'且p.charAt(j-2)=='c'
                     * p.charAt(j-1)!='.'且p.charAt(j-2)!=s.charAt(i-1)
                     * 此时将p的'c*'看作0个c
                     * dp[i][j]的状态就由p的c*之前的那个a(p.charAt(j-3))对应的dp[i][j-2]决定
                     */
                    if(p.charAt(j-2) != '.' && p.charAt(j-2) != s.charAt(i-1)) {
                        dp[i][j] = dp[i][j-2];
                    }
                    /**
                     * dp[i-1][j]:
                     * 这种情况将*视为多个
                     * 如s:acaab，p:aca*b，i=4，j=4时，s.charAt(i-1)=='a'，p.charAt(j-1)=='*'且s.charAt(i-2)=='a'
                     * p.charAt(j-2)==s.charAt(i-1)
                     * 此时可将"a*"视为多个a
                     * dp[i][j]的状态就由s的前一个字符a(s.charAt(i-2))对应的dp[i-1][j]决定
                     *
                     * dp[i][j-1]:
                     * 这种情况将*视为1个
                     * 如s:acab，p:aca*b，i=3，j=4时，s.charAt(i-1)=='a'，p.charAt(j-1)=='*'且s.charAt(i-2)=='c'
                     * p.charAt(j-2)==s.charAt(i-1)
                     * 此时可将"a*"视为1个a，相当于无视该位的"*"
                     * dp[i][j]的状态就由p的前一个字符a(s.charAt(j-2))对应的dp[i][j-1]决定
                     *
                     * dp[i][j-2]:
                     * 这种情况将*视为0个
                     * 如s:acb，p:aca*b，i=2，j=4时，s.charAt(i-1)=='c'，p.charAt(j-1)=='*'且p.charAt(i-2)=='a'
                     * p.charAt(j-2)!=s.charAt(i-1)
                     * 此时可将"a*"视为0个a
                     * dp[i][j]的状态就由p的前两个字符c(s.charAt(j-3))对应的dp[i][j-2]决定
                     */
                    else {
                        dp[i][j] = dp[i-1][j] || dp[i][j-1] || dp[i][j-2];
                    }
                }
            }
        }
        return dp[m][n];
    }
}
