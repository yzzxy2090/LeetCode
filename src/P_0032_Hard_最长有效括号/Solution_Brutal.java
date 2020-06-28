package P_0032_Hard_最长有效括号;

import java.util.Stack;

public class Solution_Brutal {
    public int longestValidParentheses(String s) {
        if(s == null || s == "") {
            return 0;
        }

        int res = 0;
        String temp = "";

        for(int i=0; i<s.length(); i++) {
            for(int j=i+2; j<s.length(); j+=2) {
                temp = s.substring(i, j);
                if(isValid(temp)) {
                    res = Math.max(res, j-i);
                }
            }
        }

        return res;
    }

    /**
     * 每当我们遇到一个 ‘(’ ，我们把它放在栈顶。
     * 对于遇到的每个 ‘)’ ，我们从栈中弹出一个 ‘(’ ，
     * 如果栈顶没有 \text{‘(’}‘(’，或者遍历完整个子字符串后栈中仍然有元素，
     * 那么该子字符串是无效的。这种方法中，我们对每个偶数长度的子字符串都进行判断，
     * 并保存目前为止找到的最长的有效子字符串的长度
     * @param s
     * @return
     */
    private boolean isValid(String s) {
        char[] cArr = s.toCharArray();
        Stack<Character> stack = new Stack<>();

        for(int i=0; i<cArr.length; i++) {
            if(cArr[i] == '(') {
                stack.push(cArr[i]);
            } else if(!stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            } else {
                return false;
            }
        }

        //执行到这里说明s中的右括号都匹配完成，但栈中可能还有残留的左括号，这样的s也是不合法的
        return stack.isEmpty();
    }
}
