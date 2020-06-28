package P_0032_Hard_最长有效括号;

public class Solution_DP {
    public int longestValidParentheses(String s) {
        if(s == null || s== "") {
            return 0;
        }

        char[] cArr = s.toCharArray();

        //dp[i]表示以cArr[i]结尾的子串的最长有效括号数
        int[] dp = new int[cArr.length];
        dp[0] = 0;
        int res = 0;

        for(int i=1; i<cArr.length; i++) {

            if(cArr[i] == ')') {

                //形如...()
                if(cArr[i-1] == '(') {
                    dp[i] = (i >= 2 ? dp[i-2] : 0) + 2;
                }

                //形如...))，如果 s[i-dp[i-1]-1] = ‘('，则该位置的')'可以匹配上，dp[i]可以增长
                else if(cArr[i-1] == ')' && (i - dp[i-1]-1 >= 0) && cArr[i-dp[i-1]-1] == '(') {
                    dp[i] = (i-dp[i-1]-2 >= 0 ? dp[i-dp[i-1]-2] : 0) + dp[i-1] + 2;
                }

                res = res > dp[i] ? res : dp[i];
            }
        }

        return res;
    }
}
