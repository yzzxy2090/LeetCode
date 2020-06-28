package P_0003_Medium_无重复字符的最长子串;

import java.util.HashMap;
import java.util.Map;

public class Solution_Map {
    public int lengthOfLongestSubstring(String s) {
        if(s == null) {
            return 0;
        }

        char[] cArr = s.toCharArray();
        int len = cArr.length;

        Map<Character, Integer> map = new HashMap<>();

        int start = 0, end = 0;
        int res = 0;

        while (end < len) {

            //如果当前end所指字符与start到end中某个字符重复，则令start指向该重复字符的最大下标位置，即重新开始计算以该字符开头的子串
            if(map.containsKey(cArr[end])) {
                start = Math.max(map.get(cArr[end]), start);
            }

            res = Math.max(res, end - start + 1);
            map.put(cArr[end], end + 1);

            end++;
        }

        return res;
    }
}
