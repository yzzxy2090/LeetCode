package P_0581_Easy_最短无序连续子数组;

import java.util.Arrays;

public class Solution_Sort {
    public int findUnsortedSubarray(int[] nums) {
        if(nums == null || nums.length < 2) {
            return 0;
        }

        int[] arr = nums.clone();
        Arrays.sort(arr);

        int start = nums.length - 1, end = 0;

        for(int i=0; i<nums.length; i++) {
            if(arr[i] != nums[i]) {
                start = Math.min(i, start);
                end = Math.max(i, end);
            }
        }

        return (end - start) < 0 ? 0 : (end - start + 1);
    }
}
