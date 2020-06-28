package P_0354_Hard_俄罗斯套娃信封问题;

/**
 * 最长上升子序列变种
 */

import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    public int maxEnvelopes(int[][] envelopes) {

        int n = envelopes.length;

        //对n行2列的二维数组进行排序，首先按第一个数字升序排，第一个数字相同的情况下按第二个数字降序排
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if(a[0] != b[0]) {
                    return a[0] - b[0];
                } else {
                    return b[1] - a[1];
                }
            }
        });

        int[] heights = new int[n];

        //用另一数组保存矩阵第二列数字
        for(int i=0; i<n; i++) {
            heights[i] = envelopes[i][1];
        }

        //转化为对矩阵第2列数字求最长上升子序列长度
        int res = lengthOfLIS(heights);

        return res;
    }

    private int lengthOfLIS(int[] nums) {
        if(nums == null) {
            return 0;
        }

        int len = nums.length;
        if(len < 2) {
            return len;
        }

        int[] tail = new int[len];
        tail[0] = nums[0];

        int end = 0;
        int index = 0;

        for(int i=1; i<len; i++) {
            if(tail[end] < nums[i]) {
                end++;
                tail[end] = nums[i];
            } else {
                index = binSearch(tail, nums[i], 0, end);
                tail[index] = nums[i];
            }
        }

        return end + 1;
    }

    private int binSearch(int[] arr, int target, int start, int end) {
        if(start >= end) {
            return start;
        }

        int mid = 0;
        while(start < end) {
            mid = start + (end - start) / 2;

            if(arr[mid] < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return start;
    }
}
