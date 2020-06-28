package P_0581_Easy_最短无序连续子数组;

public class Solution_Brutal {
    public int findUnsortedSubarray(int[] nums) {
        if(nums == null || nums.length < 2) {
            return 0;
        }

        int start = nums.length - 1;
        int end = 0;

        for(int i=0; i<nums.length; i++) {
            for(int j=i+1; j<nums.length; j++) {
                if(nums[i] > nums[j]) {
                    start = Math.min(i, start);
                    end = Math.max(j, end);
                }
            }
        }

        return (end - start) < 0 ? 0 : (end - start + 1);
    }
}
