package P_0046_Medium_全排列;

import java.util.ArrayList;
import java.util.List;

public class Solution_DFS01 {
    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();

        //通过boolean数组实现去重
        boolean[] visited = new boolean[nums.length];
        dfs(nums, 0, visited, new ArrayList<Integer>(), res);
        return res;

    }

    private void dfs(int[] nums, int cur, boolean[] visited, ArrayList<Integer> path, List<List<Integer>> res) {

        //dfs终止条件
        if (cur >= nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            //已访问的节点就跳过
            if (visited[i]) continue;

            //当前节点未访问则访问当前节点
            visited[i] = true;
            path.add(nums[i]);

            dfs(nums, cur + 1, visited, path, res);

            //递归后还要把当前节点置为未访问，并将这一层加入访问路径path的节点删掉，从而能够回溯
            visited[i] = false;
            path.remove(path.size() - 1);
        }
    }
}
