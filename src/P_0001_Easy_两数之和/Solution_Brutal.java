package P_0001_Easy_两数之和;

/**
 * 1. Two Sum (Easy)
 *
 * 方法一：暴力
 *
 * 时间复杂度：O(n^2)
 * 对于每个元素，我们试图通过遍历数组的其余部分来寻找它所对应的目标元素，这将耗费 O(n) 的时间。
 * 因此时间复杂度为 O(n^2)
 *
 * 空间复杂度：O(1)
 *
 */

public class Solution_Brutal {
    public int[] twoSum(int[] nums, int target) {
        for(int i=0; i<nums.length; i++) {
            for(int j=i+1; j<nums.length; j++) {
                if(nums[i] + nums[j] == target) {
                    return new int[] {i, j};
                }
            }
        }
        return null;
    }
}
