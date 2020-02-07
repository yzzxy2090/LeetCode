package P_0594_Easy_最长和谐子序列;

/**
 * 方法二：哈希
 *
 * 用一个哈希映射（HashMap）来存储每个数出现的次数，这样就能在 O(1) 的时间内得到 x 和 x + 1 出现的次数。
 *
 * 我们首先遍历一遍数组，得到哈希映射。
 * 随后遍历哈希映射，设当前遍历到的键值对为 (x, value)，
 * 那么我们就查询 x + 1 在哈希映射中对应的值，就得到了 x 和 x + 1 出现的次数。
 *
 *
 * 时间复杂度：O(N)，其中 N 是数组的长度。
 *
 * 空间复杂度：O(N)，用来存储哈希映射。
 */

import java.util.HashMap;
import java.util.Map;

public class Solution_HashMap {
    public int findLHS(int[] nums) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        /**
         * 要记录map中key在原数组中出现次数时就这么写
         * map.put(num, map.getOrDefault(num, 0) + 1);
         */
        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int res = 0;

        /**
         * map.keySet()返回一个map中所有key的Set视图，相当于返回一个Set，该Set中包含所有map中的key值
         *
         * 由于和谐序列最多只可能有两种数字(要求序列内最大值最小值差为1)
         * 所以每次更新res时，只需要考虑当前情况下map.get(key)+map.get(key+1)就行了
         * res = Math.max(res, map.get(key) + map.get(key + 1))
         */
        for(int key : map.keySet()) {
            if(map.containsKey(key + 1)) {
                res = Math.max(res, map.get(key) + map.get(key + 1));
            }
        }
        return res;
    }
}
