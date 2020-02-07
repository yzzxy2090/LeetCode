package P_0001_Easy_两数之和;

import java.util.HashMap;
import java.util.Map;

/**
 * 方法二：两次哈希表
 *
 * 将数组中元素全部放入一个HashMap中，key为元素值，value为元素数组下标
 * 注意，若数组中有两个相等的值，放入map中时由于map的key不能出现重复
 * 因此后放入map的值会覆盖前一个
 * 例如{0, 2, 2, 3, 5}
 * 元素全部放入map后，{key:2, value:2}，数组下标为1的2被覆盖
 *
 * 然后遍历数组，设一complement值等于target减当前遍历值nums[i]
 * 在map中找是否存在一个key为complement的键值对，并且该key对应的value不能是当前遍历的i
 * 如{0, 2, 2, 3, 5}，target==4
 * 遍历到第一个2即nums[1]时，complement==2
 * 在map中查到key==2时，value==2，当前遍历的i==1，value!=i
 * 说明是数组中两个不同的元素，那么就匹配成功
 *
 *
 * 时间复杂度：O(n)，
 * 我们把包含有 nn 个元素的列表遍历两次。由于哈希表将查找时间缩短到 O(1) ，所以时间复杂度为 O(n)。
 *
 * 空间复杂度：O(n)，
 * 所需的额外空间取决于哈希表中存储的元素数量，该表中存储了 n 个元素。
 *
 */

public class Solution_TwoHash {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i=0; i<nums.length; i++) {
            map.put(nums[i], i);
        }

        for(int i=0; i<nums.length; i++) {
            int complement = target - nums[i];
            if(map.containsKey(complement) && map.get(complement) != i) {
                return new int[] {i, map.get(complement)};
            }
        }
        return null;
    }
}
