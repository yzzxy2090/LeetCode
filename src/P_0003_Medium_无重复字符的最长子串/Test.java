package P_0003_Medium_无重复字符的最长子串;

public class Test {
    public static void main(String[] args) {
        Solution_Set so = new Solution_Set();
        String s = "abcabcbb";
        int res = so.lengthOfLongestSubstring(s);
        System.out.println(res);
    }
}
