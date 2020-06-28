package P_0055_Medium_跳跃游戏;

public class Solution_Greedy {
    public boolean canJump(int[] nums) {
        if(nums == null || nums.length == 0) {
            return false;
        }

        int len = nums.length;

        //lastPos记录上一个能跳到的位置，从最右边一个位置开始
        int lastPos = len - 1;

        //从右往左遍历，查看当前位置是否可以跳到lastPos，能跳到则说明从当前位置可以跳到最右边一个位置
        for(int i=len-1; i>=0; i--) {
            if(i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }

        return lastPos == 0;
    }
}
