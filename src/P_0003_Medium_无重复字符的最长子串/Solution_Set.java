package P_0003_Medium_无重复字符的最长子串;

import java.util.HashSet;
import java.util.Set;

public class Solution_Set {
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }

        char[] cArr = s.toCharArray();
        int len = cArr.length;

        int start = 0, end = 0;
        int res = 1;

        Set<Character> set = new HashSet<>();

        while (end < len && start < len) {

            //未发生重复时
            if(set.add(cArr[end])) {
                res = Math.max(res, end - start + 1);
                end++;
            }

            /**
             * 发生重复时
             * 当前end指向的字符已存在于Set窗口中时，应该将窗口中该字符及其左边所有字符都扔出去
             * 直到start右移至窗口中不存在的字符的位置上
             */
            else {
                set.remove(cArr[start]);
                start++;
            }
        }

        return res;
    }
}
