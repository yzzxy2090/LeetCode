package P_0099_Hard_恢复二叉搜索树;

/**
 * 利用Morris中序遍历
 * 额外空间复杂度为O(1)
 */

public class Solution_Morris {
    public void recoverTree(TreeNode root) {
        TreeNode cur = root;
        TreeNode first = null;
        TreeNode second = null;
        TreeNode pre = null;

        while(cur != null) {
            if(cur.left == null) {
                if(pre != null && cur.val < pre.val) {
                    if(first == null) {
                        first = pre;
                        second = cur;
                    } else {
                        second = cur;
                    }
                }
                pre = cur;
                cur = cur.right;
            }
            else {
                TreeNode last = cur.left;
                while(last.right != null && last.right != cur) {
                    last = last.right;
                }

                if(last.right == null) {
                    last.right = cur;
                    cur = cur.left;
                }

                if(last.right == cur) {
                    last.right = null;
                    if(pre != null && cur.val < pre.val) {
                        if(first == null) {
                            first = pre;
                            second = cur;
                        } else {
                            second = cur;
                        }
                    }
                    pre = cur;
                    cur = cur.right;
                }
            }
        }

        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
}
