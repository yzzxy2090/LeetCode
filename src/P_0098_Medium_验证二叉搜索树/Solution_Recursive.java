package P_0098_Medium_验证二叉搜索树;

/**
 * 从根节点往下遍历，考察遍历过程中树的节点值的上界下界
 *
 * 开始时，只能看到根节点，上下界都为空
 *
 * 若只遍历到根节点的左孩子时，此时能看到根节点以及当前遍历到的根节点的左孩子
 * 根据二叉查搜索树的性质，此时视野内的上界应该是根节点，下界依然为空
 *
 * 若只遍历到根节点的右孩子时，此时能看到根节点以及当前遍历到的根节点的右孩子
 * 根据二叉查搜索树的性质，此时视野内的下界应该是根节点，上界依然为空
 */

public class Solution_Recursive {
    public boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }

    /**
     * 判断遍历到当前节点cur时，是否符合搜索二叉树性质
     *
     * @param cur 当前遍历到的节点
     * @param lower 遍历到当前节点时节点值的上界
     * @param upper 遍历到当前节点时节点值的下界
     * @return
     */
    private boolean helper(TreeNode cur, Integer lower, Integer upper) {
        if(cur == null) {
            return true;
        }

        int val = cur.val;

        /**
         * 若当前遍历到cur时，cur的节点值cur.val<=下界值，则不符合二叉搜索树的性质
         * 若当前遍历到cur时，cur的节点值cur.val>=上界值，则不符合二叉搜索树的性质
         */
        if(lower != null && val <= lower) return false;
        if(upper != null && val >= upper) return false;

        /**
         * 一旦有子树不符合二叉搜索树的性质，那么肯定不是二叉搜索树，直接返回false
         * 往左子树遍历时，当前节点值cur.val就是其左子树的上界
         * 往右子树遍历时，当前节点值cur.val就是其右子树的下界
         */
        if(!helper(cur.left, lower, val)) return false;
        if(!helper(cur.right, val, upper)) return false;

        return true;
    }
}
