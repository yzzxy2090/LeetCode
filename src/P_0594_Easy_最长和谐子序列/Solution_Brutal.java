package P_0594_Easy_最长和谐子序列;

import java.util.HashMap;
import java.util.Map;

/**
 * 594. Longest Harmonious Subsequence (Easy)
 *
 * 方法一：暴力
 * 遍历数组，每次遍历都设一个count记录当前情况下符合和谐序列的最长长度，设一flag用以标识是否符合和谐序列
 * 如1, 1, 1, 1,的情况下，count会一直增长，但不符合和谐序列，1, 1, 2, 1才符合要求
 * 只有每次内层循环遍历完，flag被置为true才会更新结果res
 */

public class Solution_Brutal {
    public int findLHS(int[] nums) {
        int res = 0;

        for(int i=0; i<nums.length; i++) {
            int count = 0;
            boolean flag = false;
            for(int j=0; j<nums.length; j++) {
                //内层循环遍历值与外层循环遍历值相等，只增长count，flag不变，由于内层循环也遍历了所有数，因此count包含了外层循环的当前遍历值
                if(nums[i]==nums[j]) {
                    count++;
                } else if(nums[i] == nums[j] + 1) {
                    count++;
                    flag = true;
                }
            }
            if(flag) {
                res = res > count ? res : count;
            }
        }

        return res;
    }
}
