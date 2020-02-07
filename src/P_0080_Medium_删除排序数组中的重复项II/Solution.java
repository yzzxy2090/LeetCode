package P_0080_Medium_删除排序数组中的重复项II;

/**
 * 80. Remove Duplicates from Sorted Array II (Medium)
 *
 * 分析：
 * 快指针：遍历整个数组；
 * 慢指针：记录可以覆盖数据的位置；
 * 题目中规定每个元素最多出现两次，因此，应检查快指针指向的元素和慢指针指针所指向单元的前一个元素是否相等。
 * 相等则不更新慢指针，只更新快指针；
 * 不相等时，先将慢指针后移一位，再将快指针指向的元素覆写入慢指针指向的单元，最后更新快指针（详见图解）。
 *
 * 边界：
 * 当数组的长度小于等于 2 时，不需要操作，直接返回原数组即可。
 *
 * 初始化：
 * 快指针用于遍历数组，但算法不可能操作序号小于 2 的元素，因此快指针初始值为 2；
 * 初始状态下，慢指针应紧随快指针之后，因此初始值为 1；
 * 结束条件：
 *
 * 结束条件：
 * 快指针达到数组结尾。
 */

public class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length < 3) {
            return nums.length;
        }

        /**
         * 考察快指针j所指元素是否和慢指针所指元素的前一个元素相等，即nums[j]!=nums[i-1]
         * 相等，则快指针往后移一位
         * 否则，先将i后移一位，然后将nums[j]值赋给nums[i]
         */
        int i = 1;
        for(int j = 2; j<nums.length; j++) {
            if(nums[j] != nums[i-1]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i+1;
    }
}
