package P_0015_Medium_三数之和;

/**
 * 首先对数组进行排序，排序后固定一个数 nums[i]
 * 再使用左右指针指向 nums[i]后面的两端，数字分别为 nums[L] 和 nums[R]，
 * 计算三个数的和 sum 判断是否满足为 0，满足则添加进结果集
 * 如果 nums[i]大于 0，则三数之和必然无法等于 0，结束循环
 * 如果 nums[i] == nums[i-1]，则说明该数字重复，会导致结果重复，所以应该跳过
 * 当 sum == 0 时，nums[L] == nums[L+1] 则会导致结果重复，应该跳过，L++
 * 当 sum == 0 时，nums[R] == nums[R-1] 则会导致结果重复，应该跳过，R--
 * 时间复杂度：O(n^2)，n为数组长度
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length < 3) {
            return res;
        }

        Arrays.sort(nums);
        int left, right;
        int sum;

        //外层循环固定住一个数，内层循环当作两数之和来做，注意外层循环到nums.length-2，至少要留出三个数字的位置
        for(int i=0; i<nums.length-2; i++) {

            //当当前遍历元素值已经>0时，由于已排序，其后面元素一定也>0，那么不可能出现三数之和为0的情况，直接退出循环
            if(nums[i] > 0) {
                break;
            }

            //当前遍历元素值等于前一个元素值，跳过
            if(i > 0 && nums[i] == nums[i-1]) {
                continue;
            }

            left = i + 1;
            right = nums.length - 1;

            while(left < right) {
               sum = nums[i] + nums[left] + nums[right];
               if(sum < 0) {
                   left++;
               }

               else if(sum > 0) {
                   right--;
               }

               //三数之和==0时，添加结果，并跳过重复数字
               else {
                   List<Integer> tempRes = new ArrayList<Integer>();
                   tempRes.add(nums[i]);
                   tempRes.add(nums[left]);
                   tempRes.add(nums[right]);

                   res.add(tempRes);

                   //nums[left]==nums[left+1]时，nums[left+1]会导致结果重复，应该跳过，L++
                   while(left < right && nums[left] == nums[left + 1]) {
                       left++;
                   }
                   //同样跳过重复
                   while(left < right && nums[right] == nums[right - 1]) {
                       right--;
                   }

                   //left、right正常移动
                   left++;
                   right--;
               }
            }
        }
        return res;
    }
}
