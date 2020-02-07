package P_0136_Easy_只出现一次的数字;

/**
 * 136. Single Number (Easy)
 *
 * 方法一：暴力 时间O(n^2)，空间O(1)
 * 方法二：HashMap 时间O(n)，空间O(n)  遍历数组，第一次遇到某数就map.put(num, null)，第二次遇到就map.remove(num)，最后map中只剩下只出现一次的元素，map.keySet()
 * 方法三：异或运算 时间O(n)，空间O(1)
 *
 * 对于异或运算^
 * a^0==a
 * a^a==0
 *
 * 并且异或运算满足交换律和结合律
 * a^b^a == (a^a)^b == 0^b == b
 *
 */

public class Solution {
    public int singleNumber(int[] nums) {
        if(nums.length == 1) {
            return nums[0];
        }

        int res = 0;

        for(int num : nums) {
            res = res ^ num;
        }
        return res;
    }
}
