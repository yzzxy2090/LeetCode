package P_0112_Easy_路径总和;

public class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) {
            return false;
        }

        return dfs(root, sum, 0);
    }

    private boolean dfs(TreeNode root, int sum, int cur) {
        //运行到这里说明之前都没能进入true，遍历完成了当前路径，没有符合条件的，返回false
        if(root == null) {
            return false;
        }

        int temp = cur + root.val;

        //当前遍历到的root为叶节点，查看此时的路径是否等于sum
        if(root.left == null && root.right == null && temp == sum) {
            return true;
        }

        //否则继续向左右子树递归
        return dfs(root.left, sum, temp) || dfs(root.right, sum, temp);

    }
}
