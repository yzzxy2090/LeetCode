package P_0104_Easy_二叉树的最大深度;

public class Solution_Recursion {
    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int res = dfs(root);

        return res;
    }

    private int dfs(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int left = root.left == null ? 0 : dfs(root.left);
        int right = root.right == null ? 0 : dfs(root.right);

        return 1 + Math.max(left, right);
    }
}
