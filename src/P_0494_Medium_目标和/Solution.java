package P_0494_Medium_目标和;

/**
 * 暴力深搜
 * 时间复杂度：O(2^N)，其中 N 是数组 nums 的长度。
 * 空间复杂度：O(N)，为递归使用的栈空间大小。
 */

public class Solution {

    int res = 0;

    public int findTargetSumWays(int[] nums, int S) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        dfs(nums, S, 0, 0);

        return res;
    }

    private void dfs(int[] arr, int target, int temp, int cur) {
        if(cur >= arr.length) {
            if(temp == target) {
                res++;
            }

            return;
        }

        //搜索在arr[cur]前加'+'号的情况
        dfs(arr, target, temp + arr[cur], cur + 1);
        //搜索在arr[cur]前加'-'号的情况
        dfs(arr, target, temp - arr[cur], cur + 1);
    }
}
