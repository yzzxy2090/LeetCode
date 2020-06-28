package P_0437_Easy_路径总和III;

public class Solution {
    public int pathSum(TreeNode root, int sum) {
        if(root == null) {
            return 0;
        }

        //保存从根节点到当前节点路径
        int[] path = new int[1001];

        return dfs(root, sum, 0, path);
    }

    private int dfs(TreeNode root, int sum, int curLevel, int[] path) {
        if(root == null) {
            return 0;
        }

        //当前level的代价为当前节点的值
        path[curLevel] = root.val;

        int temp = 0;
        int count = 0;

        /**
         * 以当前节点root为路径终点的所有路径和
         *
         * 从当前level向上层找路径，一路累加各层的路径代价path[i]
         */
        for(int i=curLevel; i>=0; i--) {
            temp += path[i];
            if(temp == sum) {
                count++;
            }
        }

        //把以下层节点为终点的符合条件的路径累计上来，这样层层返回，最终dfs(root, sum, 0, path)返回以根节点为终点的所有合法路径的数量
        return count
                + dfs(root.left, sum, curLevel+1, path)
                + dfs(root.right, sum, curLevel+1, path);
    }
}
