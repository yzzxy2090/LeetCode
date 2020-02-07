package P_0001_Easy_两数之和;

import java.util.HashMap;
import java.util.Map;

/**
 * 方法三：一次哈希表
 *
 * 可以在方法二基础上加以改进
 * 不需要等所有元素放入map后在进行匹配
 * 可以边put元素，边匹配
 *
 * 例如{0, 2, 2, 3, 5}，target==4
 * 遍历到第一个2即nums[1]时，complement==2，map中尚未有key==2的记录，执行put将当前{key:2, value:1}记录放入map
 * 遍历到第二个2即nums[2]时，complement==2，map中有key==2的记录，且map.get(2)==1，当前i==2，value!=i，说明当前遍历到的2和map中保存的2是两个不同的元素，匹配成功
 * 匹配成功时注意返回值的顺序
 * 因为是先判断在put，所以匹配成功时，map中查到的map.get(complement)一定是较小的那个下标值，i为当前遍历的下标值一定是较大的那个
 *
 *
 * 时间复杂度：O(n)，
 * 我们只遍历了包含有 n 个元素的列表一次。在表中进行的每次查找只花费 O(1) 的时间。
 *
 * 空间复杂度：O(n)，
 * 所需的额外空间取决于哈希表中存储的元素数量，该表最多需要存储 n 个元素。
 *
 */

public class Solution_OneHash {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for(int i=0; i<nums.length; i++) {
            int complement = target - nums[i];
            if(map.containsKey(complement) && map.get(complement) != i) {
                return new int[] {map.get(complement), i};
            }

            map.put(nums[i], i);
        }
        return null;
    }
}
