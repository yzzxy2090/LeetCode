package P_0198_Easy_打家劫舍;

public class Solution_TwoVariables {
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        int prePre = 0;
        int pre = 0;

        int cur = 0;

        for(int i=0; i<nums.length; i++) {
            cur = Math.max(pre, prePre + nums[i]);
            prePre = pre;
            pre = cur;
        }

        //有可能nums只有一个元素，不会进入for循环，为了通用起见这里应该返回pre
        return pre;
    }
}
