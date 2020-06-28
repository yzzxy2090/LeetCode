package P_0055_Medium_跳跃游戏;

/**
 * 回溯法
 */

public class Solution_DFS {
    public boolean canJump(int[] nums) {
        if(nums == null || nums.length == 0) {
            return false;
        }

        return dfs(nums, 0);
    }

    //当前在curPos这个位置上，考察能否从当前curPos位置跳到最后一个位置
    private boolean dfs(int[] nums, int curPos) {
        //如果当前已经来到或者越过最后一个位置，说明可以跳到，则返回true
        if(curPos >= nums.length - 1) {
            return true;
        }

        //从当前位置最远能跳到的位置furthestPos
        int furthestPos = Math.min(nums.length - 1, curPos + nums[curPos]);

        //枚举下一步能跳到的位置nextPos，并从nextPos起跳，若能成功，则说明当前位置可以跳到最后一个位置
        for(int nextPos = curPos + 1; nextPos <= furthestPos; nextPos++) {
            if(dfs(nums, nextPos)) {
                return true;
            }
        }

        /*
        //每次枚举从最大步长开始枚举，更快地跳到后面
        for(int nextPos = furthestPos; nextPos > curPos; nextPos--) {
            if(dfs(nums, nextPos)) {
                return true;
            }
        }
        */

        //上述都没成功，说明当前位置跳不到最后一个位置
        return false;
    }
}
