package P_0046_Medium_全排列;

import java.util.ArrayList;
import java.util.List;

public class Solution_DFS02 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return res;
        }

        dfs(nums, 0, res);

        return res;
    }

    private void dfs(int[] nums, int cur, List<List<Integer>> res) {
        if(cur >= nums.length) {
            List<Integer> path = new ArrayList<>();
            for(int num : nums) {
                path.add(num);
            }
            if(res.indexOf(path) < 0) {
                res.add(path);
            }
            return;
        }

        for(int i=cur; i<nums.length; i++) {

            swap(nums, cur, i);

            dfs(nums, cur + 1, res);

            swap(nums, cur, i);
        }
    }

    private void swap(int[] arr, int i, int j) {
        if(arr[i] == arr[j]) {
            return;
        }

        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
