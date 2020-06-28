package P_0032_Hard_最长有效括号;

public class Test {
    public static void main(String[] args) {
        Solution_TwoPointer s = new Solution_TwoPointer();
        String temp = "))))((((";
        int res = s.longestValidParentheses(temp);
        System.out.println(res);
    }
}
