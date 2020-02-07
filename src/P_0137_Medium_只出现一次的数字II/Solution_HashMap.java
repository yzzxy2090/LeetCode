package P_0137_Medium_只出现一次的数字II;

import java.util.HashMap;
import java.util.Map;

public class Solution_HashMap {
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        //key:nums[i]，value:nums[i]出现的次数
        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for(Integer key : map.keySet()) {
            if(map.get(key) == 1) {
                return key;
            }
        }
        return Integer.MIN_VALUE;
    }
}
