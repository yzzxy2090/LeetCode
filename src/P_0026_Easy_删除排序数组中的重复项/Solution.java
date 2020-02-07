package P_0026_Easy_删除排序数组中的重复项;

/**
 * 26. Remove Duplicates from Sorted Array (Easy)
 *
 * 双指针法
 *
 * 数组完成排序后，我们可以放置两个指针 i 和 j，其中 i 是慢指针，而 j 是快指针。
 * nums[i] == nums[j]时，我们就增加 j 以跳过重复项。
 *
 * 当我们遇到 nums[j] != nums[i]时，说明此时跳过重复项的运行已经结束，遇到了另一个数字
 * 因此我们必须把它（nums[j]）的值复制到 nums[i + 1]。然后递增 i。
 * 接着我们将再次重复相同的过程，直到 j 到达数组的末尾为止。
 *
 */

public class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }

        int i=0;
        for(int j=1; j<nums.length; j++) {
            if(nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i+1;
    }
}
