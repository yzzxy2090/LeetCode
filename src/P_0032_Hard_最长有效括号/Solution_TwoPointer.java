package P_0032_Hard_最长有效括号;

public class Solution_TwoPointer {
    public int longestValidParentheses(String s) {
        if(s == null || s== "") {
            return 0;
        }

        char[] cArr = s.toCharArray();
        int res = 0;

        int left = 0, right = 0;

        /**
         * 第一遍遍历数组，从左往右
         * 期望先遇到左括号，然后遇到右括号和之前遇到的左括号匹配
         * 当)数量right超过(数量left时，此时的右括号已无法被匹配，将left和right都重置为0
         *
         * left==right时，记录当前匹配的子串长度，为2 * right
         * 并更新res
         */
        for(int i=0; i<cArr.length; i++) {
            if(cArr[i] == '(') {
                left++;
            } else {
                right++;
            }

            if(left == right) {
                res = Math.max(res, 2 * right);
            } else if(left < right) {
                left = 0;
                right = 0;
            }
        }

        left = 0;
        right = 0;
        /**
         * 第二遍遍历数组，从右往左
         * 期望先遇到右括号，然后遇到左括号和之前遇到的右括号匹配
         * 当(数量left超过)数量right时，此时的左括号已无法被匹配，将left和right都重置为0
         *
         * left==right时，记录当前匹配的子串长度，为2 * left
         * 并更新res
         */
        for(int i=cArr.length-1; i>=0; i--) {
            if(cArr[i] == ')') {
                right++;
            } else {
                left++;
            }

            if(left == right) {
                res = Math.max(res, 2 * left);
            } else if(left > right) {
                left = 0;
                right = 0;
            }
        }

        return res;
    }
}
