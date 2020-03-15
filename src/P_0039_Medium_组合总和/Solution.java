package P_0039_Medium_组合总和;

/**
 * https://www.bilibili.com/video/av51692387?p=30
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if(candidates == null || candidates.length < 1) {
            return res;
        }

        int len = candidates.length;

        //先对数组排序
        Arrays.sort(candidates);

        //深度优先遍历回溯，new ArrayList<Integer>()是当前层的路径
        dfs(candidates, len, target, 0, res, new ArrayList<Integer>());

        return res;
    }

    /**
     *
     * @param arr
     * @param len
     * @param target
     * @param cur
     * @param res
     * @param curRes
     */
    private void dfs(int[] arr, int len, int target, int cur, List<List<Integer>> res, List<Integer> curRes) {
        if(target == 0) {
            //添加至结果集时要将curRes作为参数新建一个链表，因为curRes可能还要回溯
            res.add(new ArrayList<Integer>(curRes));
        }

        if(target < 0) {
            return;
        }

        for(int i=cur; i<len; i++) {

            //当前遍历值已经比target大，那么不可能组合成target，退出循环，直接回溯上一层，即剪枝
            if(arr[i] > target) {
                break;
            }

            curRes.add(arr[i]);

            dfs(arr, len, target-arr[i], i, res, curRes);

            //递归下一层之后还要将当前层加入的元素删掉，再令arr[i+1]作为当前层元素进行递归，即回溯
            curRes.remove(curRes.size()-1);
        }
    }
}
