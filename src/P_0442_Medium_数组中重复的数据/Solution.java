package P_0442_Medium_数组中重复的数据;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 442. Find All Duplicates in an Array (Medium)
 * 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。
 * 找到所有出现两次的元素。
 *
 * 思路：
 * 注意1 ≤ a[i] ≤ n （n为数组长度），这就可以参考桶排序的思想，将元素值和下标值对应起来
 * nums[i]应该所处的下标值为nums[i]-1，即nums[nums[i]-1]=nums[i]
 * 遍历一遍数组，将各元素放好，最后那些元素值与下标值不匹配(nums[i]-1!=i)的元素就是重复的元素
 */

public class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();
        int len = nums.length;
        if(len < 1) {
            return res;
        }

        for(int i=0; i<len; i++) {
            /**
             * 这里注意是while，因为交换一次后，只是将原来的nums[i]交换到了正确位置，
             * 交换过来的新的nums[i]不一定就来到了正确位置
             * 所以用while直至前位置的元素是处于正确位置
             */
            while (nums[nums[i]-1] != nums[i]) {
                swap(nums, i, nums[i]-1);
            }
        }

        for(int i=0; i<nums.length; i++) {
            if(nums[i]-1 != i) {
                res.add(nums[i]);
            }
        }
        return res;
    }

    /**
     * 这里为了不占用额外空间，采用位运算来交换数组中两个元素
     * swap(a, b)：
     * a = a ^ b
     * b = a ^ b
     * a = a ^ b
     *
     * 其中，如果a, b值相等，二者亦或值为0，即a^a==0
     * 从而改变了元素值，这是所不希望发生的
     * 因此，采用亦或运算来交换两元素值时要先加一个判断，若二者值相等就直接返回
     */
    private void swap(int[] nums, int i, int j) {
        if(i == j) {
            return;
        }

        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
}
