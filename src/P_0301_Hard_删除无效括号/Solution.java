package P_0301_Hard_删除无效括号;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        //统计需要删除多少个左右括号
        int l = 0, r = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') l++;
            else if (s.charAt(i) == ')') {
                if (l > 0) l--;//此时有左括号可以和右括号匹配
                else r++;//此时右括号落单
            }
        }
        List<String> res = new ArrayList<>();
        dfs(s, 0, l, r, res);
        return res;
    }

    private void dfs(String s, int start, int l, int r, List<String> res) {
        //递归终止条件，没有要删除的左括号和右括号
        //此时要判断字符串是否合法，合法的话就加入结果中
        if (l == 0 && r == 0) {
            if (isValid(s)) res.add(s);
            return;
        }

        for (int i = start; i < s.length(); i++) {
            if (i != start && s.charAt(i) == s.charAt(i - 1)) continue;//连续多个相同的括号只删除第一个
            if (s.charAt(i) == '(' || s.charAt(i) == ')') {
                String cur = s;
                cur = cur.substring(0,i) + cur.substring(i+1);
                if (r > 0 && s.charAt(i) == ')') dfs(cur, i, l, r - 1, res);
                else if (l > 0 && s.charAt(i) == '(') dfs(cur, i, l - 1, r, res);
            }
        }
    }

    private boolean isValid(String s) {
        int count = 0;
        char[] chars = s.toCharArray();
        for (char ch : chars) {
            if (ch == '(') count++;
            if (ch == ')') count--;
            if (count < 0) return false;
        }
        return count == 0;
    }
}