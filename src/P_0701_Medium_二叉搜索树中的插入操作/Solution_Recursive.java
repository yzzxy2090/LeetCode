package P_0701_Medium_二叉搜索树中的插入操作;

/**
 * 若 root == null，则返回一个新建节点TreeNode(val)
 * 若 val > root.val，插入到右子树
 * 若 val < root.val，插入到左子树
 * 返回 root
 *
 * 时间复杂度：O(H)，其中H指的是树的高度。平均情况下O(logN)，最坏的情况下O(N)。
 * 空间复杂度：平均情况下O(H)。最坏的情况下是O(N)，是在递归过程中堆栈使用的空间。
 */

public class Solution_Recursive {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null) {
            return new TreeNode(val);
        }

        if(val > root.val) {
            root.right = insertIntoBST(root.right, val);
        }
        if(val < root.val) {
            root.left = insertIntoBST(root.left, val);
        }

        return root;
    }
}
