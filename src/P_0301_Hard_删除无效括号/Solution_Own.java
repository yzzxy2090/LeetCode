package P_0301_Hard_删除无效括号;

import java.util.ArrayList;
import java.util.List;

public class Solution_Own {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        if(s == null || s == "") {
            return res;
        }

        int l = 0, r = 0;
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) == '(') {
                l++;
            }

            else if(s.charAt(i) == ')') {
                if(l > 0) {
                    l--;
                }

                //此时已有的左括号数量不足以匹配当前右括号，不匹配的右括号数r++
                else {
                    r++;
                }
            }
        }

        dfs(s, 0, l, r, res);

        return res;
    }

    private void dfs(String s, int cur, int l, int r, List<String> res) {

        if(l == 0 && r == 0) {
            if(isValid(s)) {
                res.add(s);
            }

            return;
        }

        for(int i=cur; i<s.length(); i++) {

            //重复出现相同括号，只删第一次出现的，剪枝
            if(i != cur && s.charAt(i) == s.charAt(i-1))
                continue;

            if(s.charAt(i) == ')' || s.charAt(i) == '(') {
                String temp = s;

                //删掉当前位s.charAt(i)的括号
                temp = s.substring(0, i) + s.substring(i+1);

                //先删多余的右括号，再删多余的左括号
                if(r > 0 && s.charAt(i) == ')') {
                    dfs(temp, i, l, r - 1, res);
                }else if(l > 0 && s.charAt(i) == '(') {
                    dfs(temp, i, l - 1, r, res);
                }
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
