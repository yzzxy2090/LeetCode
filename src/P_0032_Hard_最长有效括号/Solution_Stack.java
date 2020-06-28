package P_0032_Hard_最长有效括号;

/**
 * 我们首先将 -1 放入栈顶。
 *
 * 对于遇到的每个 ‘(’ ，我们将它的下标放入栈中。
 *
 * 对于遇到的每个 ‘)’ ，我们弹出栈顶的元素并将当前元素的下标i与弹出元素后的栈顶元素stack.peek()下标作差，得出当前有效括号字符串的长度。
 * 通过这种方法，我们继续计算有效子字符串的长度，并最终返回最长有效子字符串的长度。
 */

import java.util.Deque;
import java.util.LinkedList;

public class Solution_Stack {
    public int longestValidParentheses(String s) {
        if(s == null || s== "") {
            return 0;
        }

        int res = 0;

        Deque<Integer> stack = new LinkedList<>();
        stack.push(-1);
        int temp = 0;

        char[] cArr = s.toCharArray();

        for(int i=0; i<cArr.length; i++) {
            if(cArr[i] == '(') {
                stack.push(i);
            } else {

                stack.pop();

                //pop之后栈为空，就把当前i给push进栈
                if(stack.isEmpty()) {
                    stack.push(i);
                }
                //否则说明匹配上了，匹配的子串长度为i - stack.peek()，同时尝试更新res
                else {
                    temp = i - stack.peek();
                    res = res > temp ? res : temp;
                }
            }
        }

        return res;
    }
}
