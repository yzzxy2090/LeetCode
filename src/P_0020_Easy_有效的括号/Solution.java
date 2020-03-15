package P_0020_Easy_有效的括号;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 20. Valid Parentheses (Easy)
 */

public class Solution {
    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<Character, Character>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        Stack<Character> stack = new Stack<Character>();

        char c;
        for(int i=0; i<s.length(); i++) {
            c = s.charAt(i);

            /**
             * 遇到'('、'{'、'['左括号时就压栈
             * 遇到')'、'}'、']'右括号时判断：
             *      当前栈为空，无法匹配，返回false
             *      栈顶元素是否能和当前类型的右括号匹配
             *      能匹配上就继续往下遍历，否则就直接返回false
             *
             */
            if(map.containsKey(c)) {
                //栈为空时将popEle赋为'#'，即无法匹配成功
                char popEle = stack.empty() ? '#' : stack.pop();
                if(map.get(c) != popEle) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}
