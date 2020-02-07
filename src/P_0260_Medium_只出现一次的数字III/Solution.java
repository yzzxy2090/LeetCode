package P_0260_Medium_只出现一次的数字III;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int[] singleNumber(int[] nums) {
        int[] res = new int[2];
        if(nums == null || nums.length < 2) {
            return res;
        }

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int index = 0;
        for(Integer key : map.keySet()) {
            if(map.get(key) == 1)
                res[index++] = key;
        }
        return res;
    }
}
