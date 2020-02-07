package P_0701_Medium_二叉搜索树中的插入操作;

/**
 * 时间复杂度：O(H)，其中H指的是树的高度。平均情况下O(logN)，最坏的情况下O(N)。
 * 空间复杂度：O(1)
 */

public class Solution_NonRecursive {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode cur = root;
        while(cur != null) {
            if(val > cur.val) {
                if(cur.right == null) {
                    cur.right = new TreeNode(val);
                    return root;
                } else {
                    cur = cur.right;
                }
            } else {
                if(cur.left == null) {
                    cur.left = new TreeNode(val);
                    return root;
                } else {
                    cur = cur.left;
                }
            }
        }
        //root==null时
        return new TreeNode(val);
    }
}
