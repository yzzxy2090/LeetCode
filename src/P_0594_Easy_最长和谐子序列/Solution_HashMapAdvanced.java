package P_0594_Easy_最长和谐子序列;

/**
 * 方法三：哈希映射 + 单次扫描
 *
 * 在方法二中，我们需要对数组进行一次扫描，再对哈希映射进行一次扫描。事实上，我们也可以设计出只进行一次扫描的算法。
 *
 * 我们扫描一次数组，当扫描到元素 x 时，我们首先将 x 加入哈希映射，
 * 随后获取哈希映射中 x - 1, x, x + 1 三者出现的次数 u, v, w，
 * 那么 u + v 即为 x - 1 和 x 组成的和谐子序列的长度，v + w 即为 x 和 x + 1 组成的和谐子序列的长度。
 * 假设数组中最长的和谐子序列的最后一个元素在数组中的位置为 i，那么在扫描到 nums[i] 时，
 * u + v 和 v + w 中一定有一个就是答案。因此这种方法可以找到最长的和谐子序列的长度。
 *
 * 例1.{1,3,2,2,5,2,3,7}，扫描到最后一个3即nums[6]时，map中存在x-1即2，u==5，map中没有x+1即4，此时res被更新为5
 * 例2.{1,3,2,2,5,3,2,7}，扫描到最后一个2即nums[6]时，map中存在x-1即1，u==4，map中存在x+1即3，v==5，此时res取u和v较大值被更新为5
 *
 *
 * 时间复杂度：O(N)，其中 N 是数组的长度。
 *
 * 空间复杂度：O(N)，用来存储哈希映射。
 */

import java.util.HashMap;
import java.util.Map;

public class Solution_HashMapAdvanced {
    public int findLHS(int[] nums) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        int res = 0;

        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);

            if(map.containsKey(num - 1)) {
                res = Math.max(res, map.get(num) + map.get(num - 1));
            }
            if(map.containsKey(num + 1)) {
                res = Math.max(res, map.get(num) + map.get(num + 1));
            }
        }

        return res;
    }
}
